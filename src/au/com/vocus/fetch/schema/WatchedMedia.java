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
	private String epgProgramId;
	private String seriesId;
	private String episodeId;
	private String recordingId;
	
	private Long mediaDuration;
	private Long durationWatched;
	private Double precentageWatched;
	
	private String baseType;
	private String mediaType;
	
	private Long item_indentifier;
	private Long dataReceived;
	private Long averageBitRate;
	private Long hlsMaxStream;
	private Long hlsMinStream;
	
	private String title;
	private String applicationName;
	private String seriesName;
	private String viewActions;
	private String programsWatched;
		
	
	public WatchedMedia(JSONObject json) {
		startDate = (Long)json.get("startDate");
		eventTime = (Long)json.get("eventTime");
		endDate = (Long)json.get("endDate");
		
		previousSurface = (String)json.get("previousSurface");
		currentSurface = (String)json.get("currentSurface");
		navigationSource = (String)json.get("navigationSource");
		
		channelId = json.get("channelId") != null ? json.get("channelId").toString() : null;
		programId = json.get("programId") != null ? json.get("programId").toString() : null;
		epgProgramId = json.get("epgProgramId") != null ? json.get("epgProgramId").toString() : null;
		seriesId = json.get("seriesId") != null ? json.get("seriesId").toString() : null;
		episodeId = json.get("episodeId") != null ? json.get("episodeId").toString() : null;
		recordingId = json.get("recordingId") != null ? json.get("recordingId").toString() : null;
		
		mediaDuration = (Long)json.get("mediaDuration");
		durationWatched = (Long)json.get("durationWatched");
		precentageWatched= (Double)json.get("precentageWatched");
		
		baseType = (String)json.get("baseType");
		mediaType = (String)json.get("mediaType");
		
		item_indentifier = (Long)json.get("item_indentifier");
		dataReceived = (Long)json.get("dataRecived");
		averageBitRate = (Long)json.get("averageBitRate");
		hlsMaxStream = (Long)json.get("hlsMaxStream");
		hlsMinStream = (Long)json.get("hlsMinStream");
		
		title = (String)json.get("title");
		applicationName = (String)json.get("applicationName");
		seriesName = (String)json.get("seriesName");
		viewActions = (String)json.get("viewActions");
		programsWatched = (String)json.get("programsWatched");
		
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
	 * @return the epgProgramId
	 */
	public String getEpgProgramId() {
		return epgProgramId;
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
	public Long getMediaDuration() {
		return mediaDuration;
	}

	/**
	 * @return the durationWatched
	 */
	public Long getDurationWatched() {
		return durationWatched;
	}

	/**
	 * @return the precentageWatched
	 */
	public Double getPrecentageWatched() {
		return precentageWatched;
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
	 * @return the item_indentifier
	 */
	public Long getItem_indentifier() {
		return item_indentifier;
	}

	/**
	 * @return the dataReceived
	 */
	public Long getDataReceived() {
		return dataReceived;
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
	 * @return the hlsMinStream
	 */
	public Long getHlsMinStream() {
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
