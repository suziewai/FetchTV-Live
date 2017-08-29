package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class Event {

	private Data data;
	private String event;
	
	public Event(JSONObject json) {

		event = (String)json.get("event");
		JSONObject jsonData = (JSONObject)json.get("data");
		switch(event) {
		/*
			case "systemInfo":
				data = new SystemInfo(jsonData);
				break;
			case "currentlyWatching":
				data = new CurrentlyWatching(jsonData);
			case "scheduleRecording":
			case "startRecording":
			case "stopRecording":
				data = new Recording(jsonData);
				break;
			case "vodPurchaseSuccess":
				data = new VodPurchaseSuccess(jsonData);
				*/
			case "watchedMedia":
				data = new WatchedMedia(jsonData);
				break;
			
			default:
				data = new SomeData(jsonData);
				
		}
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
