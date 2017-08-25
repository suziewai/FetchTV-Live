package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class VodPurchaseSuccess implements Data {

	private Long eventTime;
	private Long vodTitleId;
	private String history;
	private Long vodTitleChildId;
	
	public VodPurchaseSuccess(JSONObject json) {
		eventTime = (Long)json.get("eventTime");
		vodTitleId = (Long)json.get("vodTitleId");
		history = (String)json.get("history");
		vodTitleChildId = (Long)json.get("vodTitleChildId");
	}

	@Override
	public Long getEventTime() {
		return eventTime;
	}

	/**
	 * @return the vodTitleId
	 */
	public Long getVodTitleId() {
		return vodTitleId;
	}

	/**
	 * @return the history
	 */
	public String getHistory() {
		return history;
	}

	/**
	 * @return the vodTitleChildId
	 */
	public Long getVodTitleChildId() {
		return vodTitleChildId;
	}

}
