package au.com.vocus.fetch;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import au.com.vocus.elastictool.parser.ElasticParser;
import au.com.vocus.elastictool.schema.ElasticResponse;
import au.com.vocus.elastictool.schema.search.Bool;
import au.com.vocus.elastictool.schema.search.Match;
import au.com.vocus.elastictool.schema.search.Must;
import au.com.vocus.elastictool.schema.search.Query;
import au.com.vocus.elastictool.schema.search.Range;
import au.com.vocus.fetch.dao.DataAccessManager;
import au.com.vocus.fetch.schema.Event;
import au.com.vocus.fetch.schema.FetchTvRecord;
import au.com.vocus.fetch.schema.WatchedMedia;


public class Main {

	static FetchProperty prop = new FetchProperty();
	static Long lastEventDate = prop.getLastEventDate();
	static Long newEventDate = lastEventDate;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpHost hh = new HttpHost(prop.getServerURL(), 80, "http");
		RestClient restClient = RestClient.builder(hh, hh).build();
		
		try {
			
			Response response = restClient.performRequest("GET", prop.getResource(), getSearchParams());
			String responseTxt = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			
			
			ElasticResponse<FetchTvRecord> resp = parseResponse(responseTxt);
			//print(resp);
			persistToFile(resp);

			
			//scroll(restClient, resp);
			restClient.close();
			
			prop.setLastEventDate(newEventDate);
			prop.save();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Map<String, String> getSearchParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("source", ElasticParser.buildSearchQuery(getQuery()));
		params.put("size", prop.getPagesize());
		//params.put("scroll", "5m");
		return params;
	}
		
	private static Query getQuery() {
		
		Must must = new Must();
		if(lastEventDate != null) {
			Range eventTime = new Range();
			eventTime.setField("events.data.eventTime");
			eventTime.addRange(Range.PARAM.gt, lastEventDate.toString());
			must.addCriteria(eventTime);
		}
		
		Match event = new Match();
		event.setField("events.event");
		event.setMatch("watchedMedia");
		must.addCriteria(event);
		
		/*
		MustNot mustNot = new MustNot();
		Match currentSurface = new Match();
		currentSurface.setField("events.data.currentSurface");
		currentSurface.setMatch("RECORDINGS:recordings");
		mustNot.addCriteria(currentSurface);
		*/
		
		Bool bool = new Bool();
		bool.addCriteria(must);
		//bool.addCriteria(mustNot);
		
		Query q = new Query();
		q.addCriteria(bool);
		//System.out.println(ElasticParser.buildSearchQuery(q));
		return q;
	}
	
	private static ElasticResponse<FetchTvRecord> parseResponse(String jsonStr) {
		ElasticParser parser = new ElasticParser();
		ElasticResponse<FetchTvRecord> eObj = parser.parse(jsonStr, FetchTvRecord.class);
		return eObj;
	}
	
	private static void persistToFile(ElasticResponse<FetchTvRecord> records) {
		System.out.println(new java.util.Date() + " - Creating file...");
		//try {
			FileWriter fw = new FileWriter();
			String filename = sdf.format(new Date());
			fw.createFolder("fetchtv");
			fw.createFile(filename, "|", true);
			
			for(FetchTvRecord record : records.getHits().getRecords()) {
				Long tempEventDate = fw.writeRecord(record, lastEventDate);
				newEventDate = (newEventDate == null || tempEventDate > newEventDate) ? tempEventDate : newEventDate;
			}

			fw.closeFile();
		//}
		
		System.out.println(new java.util.Date() + " - Writing to file commpleted!");
	}
		
	private static void persistToDB(ElasticResponse<FetchTvRecord> records) {
		System.out.println(new java.util.Date() + " - Establishing DB connection...");
		try {
			DataAccessManager manager = new DataAccessManager(prop.getConnectionString(), prop.getUsername(), prop.getPassword());
			for(FetchTvRecord record : records.getHits().getRecords()) {
				Long tempEventDate = manager.insertRecord(record, lastEventDate);
				newEventDate = (newEventDate == null || tempEventDate > newEventDate) ? tempEventDate : newEventDate;
			}
			manager.closeConn();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new java.util.Date() + " - Insert records commpleted!");
	}

	
	private static void print(ElasticResponse<FetchTvRecord> response) {
		for(FetchTvRecord record : response.getHits().getRecords()) {
			System.out.println("id = " + record.get_id());
			System.out.println("\tispCustomerRef : " + record.getIspCustomerRef());
			System.out.println("\tteriminalUUID : " + record.getTerminalUUID());
			System.out.println("\thouseholdUUID : " + record.getHouseholdUUID());
			
			for(Event event: record.getEvents()){
				System.out.println("\t\tevent : " + event.getEvent());
				System.out.println("\t\tdata.eventTime : " + event.getData().getEventTime());
				if(event.getData() instanceof WatchedMedia) {
					WatchedMedia watched = (WatchedMedia)event.getData();
					System.out.println("\t\tdata.currentSurface : " + watched.getCurrentSurface());
					System.out.println("\t\tdata.application : " + watched.getApplicationName());
					System.out.println("\t\tdata.series : " + watched.getSeriesId());
				}
			}
		}
	}
	
	
	
	private static void scroll(RestClient restClient, ElasticResponse<FetchTvRecord> resp) throws ParseException, IOException {
		
		System.out.println("Total = " + resp.getHits().getTotal());
		while(resp.getHits().getRecords().size() > 0) {
			System.out.println(resp.get_scroll_id());
			
			Response response = restClient.performRequest("GET", prop.getResource() + "/scroll", getScrollParams(resp.get_scroll_id()));
			//response = restClient.performRequest("GET", prop.getResource() + "/scroll", Collections.<String, String>emptyMap(), getScrollParams(resp.get_scroll_id()));
			String responseTxt = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			resp = parseResponse(responseTxt);
			print(resp);
			//persist(resp);
		}
		
	}
	
	private static Map<String, String> getScrollParams(String scrollId) {
	//private static HttpEntity getScrollParams(String scrollId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("scroll_id", scrollId);
		params.put("scroll", "5m");
		return params;
		/*
		JSONObject obj = new JSONObject();
		obj.put("scroll", "5m");
		obj.put("scroll_id", scrollId);
		params.put("body", obj.toJSONString());
		return params;
		
		HttpEntity entity = new org.apache.http.entity.StringEntity(obj.toJSONString(), ContentType.APPLICATION_JSON);
		return entity;
		*/
	}
	
}
