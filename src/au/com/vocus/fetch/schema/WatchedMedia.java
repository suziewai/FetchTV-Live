package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class WatchedMedia implements Data {

	private Long startDate;
	private Long eventTime;
	private String previousSurface;
	private Long endDate;
	private String navigationSource;
	private Long durationWatched;
	private Long recordingId;
	private String baseType;
	private String mediaType;
	private Long averageBitRate;
	private Long hlsMaxStream;
	private String programId;
	private String title;
	private Boolean recordingModified;
	private Long mediaDuration;
	private Long hlsMinStream;
	private String currentSurface;
	private Long epgProgramId;
	private String viewActions;
	private String channelId;
	private Long dateRecived;
	private Long item_indentifier;
	private Double precentageWatched;
	
	public WatchedMedia(JSONObject json) {
		startDate = (Long)json.get("startDate");
		eventTime = (Long)json.get("eventTime");
		previousSurface = (String)json.get("previousSurface");
		endDate = (Long)json.get("endDate");
		navigationSource = (String)json.get("navigationSource");
		durationWatched = (Long)json.get("durationWatched");
		recordingId = (Long)json.get("recordingId");
		baseType = (String)json.get("baseType");
		mediaType = (String)json.get("mediaType");
		averageBitRate = (Long)json.get("averageBitRate");
		hlsMaxStream = (Long)json.get("hlsMaxStream");
		programId = json.get("programId") != null ? json.get("programId").toString() : null;
		title = (String)json.get("title");
		recordingModified = (Boolean)json.get("recordingModified");
		mediaDuration = (Long)json.get("mediaDuration");
		hlsMinStream = (Long)json.get("hlsMinStream");
		currentSurface = (String)json.get("currentSurface");
		epgProgramId = (Long)json.get("epgProgramId");
		viewActions = (String)json.get("viewActions");
		if(json.get("channelId") != null)
			channelId = json.get("channelId").toString();
		dateRecived = (Long)json.get("dateRecived");
		item_indentifier = (Long)json.get("item_indentifier");
		precentageWatched= (Double)json.get("precentageWatched");
	}
	
	@Override
	public Long getEventTime() {
		return eventTime;
	}

	/**
	 * @return the startDate
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * @return the previousSurface
	 */
	public String getPreviousSurface() {
		return previousSurface;
	}

	/**
	 * @return the endDate
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * @return the navigationSource
	 */
	public String getNavigationSource() {
		return navigationSource;
	}

	/**
	 * @return the surationWatched
	 */
	public Long getDurationWatched() {
		return durationWatched;
	}

	/**
	 * @return the recordingId
	 */
	public Long getRecordingId() {
		return recordingId;
	}

	/**
	 * @return the baseType
	 */
	public String getBaseType() {
		return baseType;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * @return the averageBitRate
	 */
	public Long getAverageBitRate() {
		return averageBitRate;
	}

	/**
	 * @return the hlsMaxStream
	 */
	public Long getHlsMaxStream() {
		return hlsMaxStream;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the recordingModified
	 */
	public Boolean getRecordingModified() {
		return recordingModified;
	}

	/**
	 * @return the mediaDuration
	 */
	public Long getMediaDuration() {
		return mediaDuration;
	}

	/**
	 * @return the hlsMinStream
	 */
	public Long getHlsMinStream() {
		return hlsMinStream;
	}

	/**
	 * @return the currentSurface
	 */
	public String getCurrentSurface() {
		return currentSurface;
	}

	/**
	 * @return the epgProgramId
	 */
	public Long getEpgProgramId() {
		return epgProgramId;
	}

	/**
	 * @return the viewActions
	 */
	public String getViewActions() {
		return viewActions;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @return the dateRecived
	 */
	public Long getDateRecived() {
		return dateRecived;
	}

	/**
	 * @return the item_indentifier
	 */
	public Long getItem_indentifier() {
		return item_indentifier;
	}

	/**
	 * @return the precentageWatched
	 */
	public Double getPrecentageWatched() {
		return precentageWatched;
	}

}
