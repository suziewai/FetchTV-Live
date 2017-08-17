package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class Data {
	
	private Long eventTime;
	private String baseType;
	private Boolean isPLTV;
	private String channelId;
	private String mediaType;
	private String playbackStatus;
	private String currentMediaTitle;
	private Long playbackDateTime;
	private String mediaTitle;
	private Long epgProgramId;
	
	public Data(JSONObject json) {
		eventTime = (Long)json.get("eventTime");
		baseType = (String)json.get("baseType");
		isPLTV = (Boolean)json.get("isPLTV");
		channelId = (String)json.get("channelId");
		mediaType = (String)json.get("mediaType");
		playbackStatus = (String)json.get("playbackStatus");
		currentMediaTitle = (String)json.get("currentMediaTitle");
		playbackDateTime = (Long)json.get("playbackDateTime");
		mediaTitle = (String)json.get("mediaTitle");
		epgProgramId = (Long)json.get("epgProgramId");
	}

	/**
	 * @return the eventTime
	 */
	public long getEventTime() {
		return eventTime;
	}

	/**
	 * @return the baseType
	 */
	public String getBaseType() {
		return baseType;
	}

	/**
	 * @return the isPLTV
	 */
	public boolean isPLTV() {
		return isPLTV;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * @return the playbackStatus
	 */
	public String getPlaybackStatus() {
		return playbackStatus;
	}

	/**
	 * @return the currentMediaTitle
	 */
	public String getCurrentMediaTitle() {
		return currentMediaTitle;
	}

	/**
	 * @return the playbackDateTime
	 */
	public long getPlaybackDateTime() {
		return playbackDateTime;
	}

	/**
	 * @return the mediaTitle
	 */
	public String getMediaTitle() {
		return mediaTitle;
	}

	/**
	 * @return the epgProgramId
	 */
	public Long getEpgProgramId() {
		return epgProgramId;
	}
}
