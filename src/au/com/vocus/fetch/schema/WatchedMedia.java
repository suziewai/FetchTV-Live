package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class WatchedMedia implements Data {

	private Long startDate;
	private Long eventTime;
	private Long endDate;
	
	private String previousSurface;
	private String currentSurface;
	private String navigationSource;
	
	private String channelId;
	private String programId;
	private String seriesId;
	private String episodeId;
	private String recordingId;
	
	private String mediaDuration;
	private String durationWatched;
	private String percentageWatched;
	
	private String baseType;
	private String mediaType;
	
	private String item_identifier;
	private String dataReceived;
	private String averageBitRate;
	private String hlsMaxStream;
	private String hlsMinStream;
	
	private String title;
	private String applicationName;
	private String seriesName;
	private String viewActions;
	private String programsWatched;
		
	
	public WatchedMedia(JSONObject json) {
		startDate = (Long)json.get("startDate");
		eventTime = (Long)json.get("eventTime");
		endDate = (Long)json.get("endDate");
		
		previousSurface = extractData(json, "previousSurface");
		currentSurface = extractData(json, "currentSurface");
		navigationSource = extractData(json, "navigationSource");
		
		channelId = extractData(json, "channelId");
		programId = extractData(json, "programId");
		seriesId = extractData(json, "seriesId");
		episodeId = extractData(json, "episodeId");
		recordingId = extractData(json, "recordingId");
		
		mediaDuration = extractData(json, "mediaDuration");
		durationWatched = extractData(json, "durationWatched");
		percentageWatched = extractData(json, "percentageWatched");
		
		baseType = extractData(json, "baseType");
		mediaType = extractData(json, "mediaType");
		
		item_identifier = extractData(json, "item_identifier");
		dataReceived = extractData(json, "dataReceived");
		averageBitRate = extractData(json, "averageBitRate");
		hlsMaxStream = extractData(json, "hlsMaxStream");
		hlsMinStream = extractData(json, "hlsMinStream");
		
		title = extractData(json, "title");
		applicationName = extractData(json, "applicationName");
		seriesName = extractData(json, "seriesName");
		viewActions = extractData(json, "viewActions");
		programsWatched = extractData(json, "programsWatched");
		
	}
	
	private String extractData(JSONObject json, String name) {
		return json.get(name) != null ? json.get(name).toString() : null;
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
	 * @return the endDate
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * @return the previousSurface
	 */
	public String getPreviousSurface() {
		return previousSurface;
	}

	/**
	 * @return the currentSurface
	 */
	public String getCurrentSurface() {
		return currentSurface;
	}

	/**
	 * @return the navigationSource
	 */
	public String getNavigationSource() {
		return navigationSource;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @return the seriesId
	 */
	public String getSeriesId() {
		return seriesId;
	}

	/**
	 * @return the episodeId
	 */
	public String getEpisodeId() {
		return episodeId;
	}

	/**
	 * @return the recordingId
	 */
	public String getRecordingId() {
		return recordingId;
	}

	/**
	 * @return the mediaDuration
	 */
	public String getMediaDuration() {
		return mediaDuration;
	}

	/**
	 * @return the durationWatched
	 */
	public String getDurationWatched() {
		return durationWatched;
	}

	/**
	 * @return the percentageWatched
	 */
	public String getPercentageWatched() {
		return percentageWatched;
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
	 * @return the item_identifier
	 */
	public String getItem_identifier() {
		return item_identifier;
	}

	/**
	 * @return the dataReceived
	 */
	public String getDataReceived() {
		return dataReceived;
	}

	/**
	 * @return the averageBitRate
	 */
	public String getAverageBitRate() {
		return averageBitRate;
	}

	/**
	 * @return the hlsMaxStream
	 */
	public String getHlsMaxStream() {
		return hlsMaxStream;
	}

	/**
	 * @return the hlsMinStream
	 */
	public String getHlsMinStream() {
		return hlsMinStream;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @return the seriesName
	 */
	public String getSeriesName() {
		return seriesName;
	}

	/**
	 * @return the viewActions
	 */
	public String getViewActions() {
		return viewActions;
	}

	/**
	 * @return the programsWatched
	 */
	public String getProgramsWatched() {
		return programsWatched;
	}

}
