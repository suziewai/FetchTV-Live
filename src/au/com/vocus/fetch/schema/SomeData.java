package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class SomeData implements Data {

	private Long eventTime;
	private JSONObject jsonData;
	
	public SomeData(JSONObject json) {
		eventTime = (Long)json.get("eventTime");
		jsonData = json;
	}
	
	@Override
	public Long getEventTime() {
		return eventTime;
	}
	
	public String getjsonData() {
		return jsonData.toString();
	}

}
