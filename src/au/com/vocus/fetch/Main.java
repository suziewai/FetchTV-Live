package au.com.vocus.fetch;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import au.com.vocus.elastictool.parser.ElasticParser;
import au.com.vocus.elastictool.schema.ElasticResponse;
import au.com.vocus.elastictool.schema.search.Bool;
import au.com.vocus.elastictool.schema.search.Match;
import au.com.vocus.elastictool.schema.search.Must;
import au.com.vocus.elastictool.schema.search.MustNot;
import au.com.vocus.elastictool.schema.search.Query;
import au.com.vocus.elastictool.schema.search.Range;
import au.com.vocus.fetch.dao.DataAccessManager;
import au.com.vocus.fetch.schema.Event;
import au.com.vocus.fetch.schema.FetchTvRecord;
import au.com.vocus.fetch.schema.WatchedMedia;


public class Main {

	static FetchProperty prop = new FetchProperty();
	static Long lastEventDate = prop.getLastEventDate();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpHost hh = new HttpHost(prop.getServerURL(), 80, "http");
		RestClient restClient = RestClient.builder(hh, hh).build();
		
		try {
			Response response = restClient.performRequest("GET", prop.getResource(), Collections.singletonMap("source", ElasticParser.buildSearchQuery(getQuery())));
			String responseTxt = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			restClient.close();
			
			print(parseResponse(responseTxt));
			persist(parseResponse(responseTxt));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		event.setMatch(" watchedMedia");
		must.addCriteria(event);
		
		MustNot mustNot = new MustNot();
		Match currentSurface = new Match();
		currentSurface.setField("events.data.currentSurface");
		currentSurface.setMatch("RECORDINGS:recordings");
		mustNot.addCriteria(currentSurface);
		
		
		Bool bool = new Bool();
		bool.addCriteria(must);
		bool.addCriteria(mustNot);
		
		Query q = new Query();
		q.addCriteria(bool);
		return q;
	}
	
	private static ElasticResponse<FetchTvRecord> parseResponse(String jsonStr) {
		ElasticParser parser = new ElasticParser();
		ElasticResponse<FetchTvRecord> eObj = parser.parse(jsonStr, FetchTvRecord.class);
		return eObj;
	}
	
	private static void persist(ElasticResponse<FetchTvRecord> records) {

		try {
			DataAccessManager manager = new DataAccessManager(prop.getConnectionString(), prop.getUsername(), prop.getPassword());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
}
