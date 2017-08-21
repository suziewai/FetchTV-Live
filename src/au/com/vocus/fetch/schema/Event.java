package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class Event {

	private Data data;
	private String event;
	
	public Event(JSONObject json) {

		event = (String)json.get("event");
		data = "systemInfo".equals(event) ? new SystemInfo((JSONObject)json.get("data")) : new CurrentlyWatching((JSONObject)json.get("data"));
	}

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
}
