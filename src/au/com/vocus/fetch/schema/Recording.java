package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class Recording implements Data {

	private Long startDate;
	private Long eventTime;
	private Long endDate;
	private String baseType;
	private String recordingId;
	private String channelId;
	private String programId;
	private String programName;
	private Boolean recordingModified;
	private Long epgProgramId;
	private Boolean isSeriesLinked;
	private String history;
	
	public Recording(JSONObject json) {
		startDate = (Long)json.get("startDate");
		eventTime = (Long)json.get("eventTime");
		endDate = (Long)json.get("endDate");
		baseType = (String)json.get("baseType");
		recordingId = json.get("recordingId").toString();
		channelId = json.get("channelId").toString();
		programId = json.get("programId").toString();
		programName = json.get("programName").toString();
		epgProgramId = (Long)json.get("epgProgramId");
		history = (String)json.get("history");
		
		if(json.get("recordingModified") != null)
			recordingModified = Boolean.parseBoolean(json.get("recordingModified").toString());
		if(json.get("isSeriesLinked") != null)
			isSeriesLinked = Boolean.parseBoolean(json.get("isSeriesLinked").toString());

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
	 * @return the baseType
	 */
	public String getBaseType() {
		return baseType;
	}


	/**
	 * @return the recordingId
	 */
	public String getRecordingId() {
		return recordingId;
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
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}


	/**
	 * @return the recordingModified
	 */
	public Boolean getRecordingModified() {
		return recordingModified;
	}


	/**
	 * @return the epgProgramId
	 */
	public Long getEpgProgramId() {
		return epgProgramId;
	}


	/**
	 * @return the isSeriesLinked
	 */
	public Boolean getIsSeriesLinked() {
		return isSeriesLinked;
	}


	/**
	 * @return the history
	 */
	public String getHistory() {
		return history;
	}

}
