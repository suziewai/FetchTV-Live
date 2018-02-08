package au.com.vocus.fetch.schema;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import au.com.vocus.elastictool.schema.ElasticRecord;

public class FetchTvRecord extends ElasticRecord{

	private String ispCustomerRef;
	private String terminalUUID;
	private List<Event> events;
	private String householdUUID;
	
	
	public void setMembers(JSONObject json) {
		super.setMembers(json);
		ispCustomerRef = (String)_source.get("ispCustomerRef");
		//terminalUUID = (String)_source.get("teriminalUUID");
		terminalUUID = (String)_source.get("terminalID");
		householdUUID = (String)_source.get("householdUUID");
		
		events = new ArrayList<Event>();
		for(Object element : (JSONArray) _source.get("events")) {
			events.add(new Event((JSONObject)element));
		}		
	}

	/**
	 * @return the ispCustomerRef
	 */
	public String getIspCustomerRef() {
		return ispCustomerRef;
	}

	/**
	 * @return the terminalUUID
	 */
	public String getTerminalUUID() {
		return terminalUUID;
	}

	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * @return the householdUUID
	 */
	public String getHouseholdUUID() {
		return householdUUID;
	}

}
