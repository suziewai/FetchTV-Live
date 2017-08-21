package au.com.vocus.fetch;

import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import au.com.vocus.elastictool.parser.ElasticParser;
import au.com.vocus.elastictool.schema.ElasticRecord;
import au.com.vocus.elastictool.schema.ElasticResponse;
import au.com.vocus.fetch.schema.Event;
import au.com.vocus.fetch.schema.FetchTvRecord;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpHost hh = new HttpHost("search-fetchtvstore-ueyvbweznib7ipzh7fzzcvdfby.ap-southeast-2.es.amazonaws.com", 80, "http");
		RestClient restClient = RestClient.builder(hh, hh).build();
		
		try {
			Response response = restClient.performRequest("GET", "/fetchtv/_search", Collections.singletonMap("pretty", "true"));
			String responseTxt = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
			restClient.close();
			
			//print(parseResponse(responseTxt));
			testResponse(responseTxt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ElasticResponse<FetchTvRecord> parseResponse(String jsonStr) {
		ElasticParser parser = new ElasticParser();
		ElasticResponse<FetchTvRecord> eObj = parser.parse(jsonStr, FetchTvRecord.class);
		return eObj;
	}
	
	public static void print(ElasticResponse<FetchTvRecord> response) {
		for(FetchTvRecord record : response.getHits().getRecords()) {
			System.out.println("id = " + record.get_id());
			System.out.println("\tispCustomerRef : " + record.getIspCustomerRef());
			System.out.println("\tteriminalUUID : " + record.getTerminalUUID());
			System.out.println("\thouseholdUUID : " + record.getHouseholdUUID());
			
			for(Event event: record.getEvents()){
				System.out.println("\t\tevent : " + event.getEvent());
				System.out.println("\t\tdata : " + event.getData().getChannelId());
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static ElasticResponse testResponse(String jsonStr) {
		ElasticParser parser = new ElasticParser();
		ElasticResponse eObj = parser.parse(jsonStr);
		
		for(Object element : eObj.getHits().getRecords()) {
			ElasticRecord record = (ElasticRecord) element;
			Hashtable<String, String> table = parser.toDotNotation(record.get_source(), "abc");
			System.out.println("id = " + record.get_id());
			System.out.println("\t_source : " + "------------------------------------");
			
			for(String key : table.keySet()){
				System.out.println("\t" + key + " : " + table.get(key));
			}
			
		}
		return eObj;
	}
}
