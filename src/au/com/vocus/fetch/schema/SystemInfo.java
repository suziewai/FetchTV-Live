package au.com.vocus.fetch.schema;

import org.json.simple.JSONObject;

public class SystemInfo implements Data{
	
	public Long eventCount;
    public String buildID;
    public String hardwareType;
    public String flushReason;
    public Long hddFreeSize;
    public String firmwareVersion;
    public Long eventTime;
    public Boolean privacy;
    public String subscribedPlans;
    public Long hddSize;
    public Long activeSeriesTags;
    public Long nextRebootDate;
    public String localIP;
    public String entitledTo;
    public String mannersViolations;
    public Long futureRecordings;
    public Long pvrHDDUsed;
    public String activeNetworkInterface;
    public String epgMode;
    public Long pvrHDDAllocated;
    public String buildFromBranch;
    public Long onCommandUsed;
    public String applicationCacheStatus;
    public Long onDiskRecordings;
    public String suiVersion;
    public String isp;
    public Long activeReminders;
    public Boolean idle;
    public Long onCommandAllocated;
    public Double suiUpTime;
    public String currentSurface;
	
	public SystemInfo(JSONObject json) {
		eventCount = (Long)json.get("eventCount");
		buildID = (String)json.get("buildID");
		hardwareType = (String)json.get("hardwareType");
		flushReason = (String)json.get("flushReason");
		hddFreeSize = (Long)json.get("hddFreeSize");
		firmwareVersion = (String)json.get("firmwareVersion");
		eventTime = (Long)json.get("eventTime");
		privacy = (Boolean)json.get("privacy");
		subscribedPlans = (String)json.get("subscribedPlans");
		hddSize = (Long)json.get("hddSize");
		activeSeriesTags = (Long)json.get("activeSeriesTags");
		nextRebootDate = (Long)json.get("nextRebootDate");
		localIP = (String)json.get("localIP");
		entitledTo = (String)json.get("entitledTo");
		mannersViolations = (String)json.get("mannersViolations");
		futureRecordings = (Long)json.get("futureRecordings");
		pvrHDDUsed = (Long)json.get("pvrHDDUsed");
		activeNetworkInterface = (String)json.get("activeNetworkInterface");
		epgMode = (String)json.get("epgMode");
		pvrHDDAllocated = (Long)json.get("pvrHDDAllocated");
		buildFromBranch = (String)json.get("buildFromBranch");
		onCommandUsed = (Long)json.get("onCommandUsed");
		applicationCacheStatus = (String)json.get("applicationCacheStatus");
		onDiskRecordings = (Long)json.get("onDiskRecordings");
		suiVersion = (String)json.get("suiVersion");
		isp = (String)json.get("isp");
		activeReminders = (Long)json.get("activeReminders");
		idle = (Boolean)json.get("idle");
		onCommandAllocated = (Long)json.get("onCommandAllocated");
		suiUpTime = (Double)json.get("suiUpTime");
		currentSurface = (String)json.get("currentSurface");
	}

	/**
	 * @return the eventCount
	 */
	public Long getEventCount() {
		return eventCount;
	}

	/**
	 * @return the buildID
	 */
	public String getBuildID() {
		return buildID;
	}

	/**
	 * @return the hardwareType
	 */
	public String getHardwareType() {
		return hardwareType;
	}

	/**
	 * @return the flushReason
	 */
	public String getFlushReason() {
		return flushReason;
	}

	/**
	 * @return the hddFreeSize
	 */
	public Long getHddFreeSize() {
		return hddFreeSize;
	}

	/**
	 * @return the firmwareVersion
	 */
	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	/**
	 * @return the eventTime
	 */
	public Long getEventTime() {
		return eventTime;
	}

	/**
	 * @return the privacy
	 */
	public Boolean getPrivacy() {
		return privacy;
	}

	/**
	 * @return the subscribedPlans
	 */
	public String getSubscribedPlans() {
		return subscribedPlans;
	}

	/**
	 * @return the hddSize
	 */
	public Long getHddSize() {
		return hddSize;
	}

	/**
	 * @return the activeSeriesTags
	 */
	public Long getActiveSeriesTags() {
		return activeSeriesTags;
	}

	/**
	 * @return the nextRebootDate
	 */
	public Long getNextRebootDate() {
		return nextRebootDate;
	}

	/**
	 * @return the localIP
	 */
	public String getLocalIP() {
		return localIP;
	}

	/**
	 * @return the entitledTo
	 */
	public String getEntitledTo() {
		return entitledTo;
	}

	/**
	 * @return the mannersViolations
	 */
	public String getMannersViolations() {
		return mannersViolations;
	}

	/**
	 * @return the futureRecordings
	 */
	public Long getFutureRecordings() {
		return futureRecordings;
	}

	/**
	 * @return the pvrHDDUsed
	 */
	public Long getPvrHDDUsed() {
		return pvrHDDUsed;
	}

	/**
	 * @return the activeNetworkInterface
	 */
	public String getActiveNetworkInterface() {
		return activeNetworkInterface;
	}

	/**
	 * @return the epgMode
	 */
	public String getEpgMode() {
		return epgMode;
	}

	/**
	 * @return the pvrHDDAllocated
	 */
	public Long getPvrHDDAllocated() {
		return pvrHDDAllocated;
	}

	/**
	 * @return the buildFromBranch
	 */
	public String getBuildFromBranch() {
		return buildFromBranch;
	}

	/**
	 * @return the onCommandUsed
	 */
	public Long getOnCommandUsed() {
		return onCommandUsed;
	}

	/**
	 * @return the applicationCacheStatus
	 */
	public String getApplicationCacheStatus() {
		return applicationCacheStatus;
	}

	/**
	 * @return the onDiskRecordings
	 */
	public Long getOnDiskRecordings() {
		return onDiskRecordings;
	}

	/**
	 * @return the suiVersion
	 */
	public String getSuiVersion() {
		return suiVersion;
	}

	/**
	 * @return the isp
	 */
	public String getIsp() {
		return isp;
	}

	/**
	 * @return the activeReminders
	 */
	public Long getActiveReminders() {
		return activeReminders;
	}

	/**
	 * @return the idle
	 */
	public Boolean getIdle() {
		return idle;
	}

	/**
	 * @return the onCommandAllocated
	 */
	public Long getOnCommandAllocated() {
		return onCommandAllocated;
	}

	/**
	 * @return the suiUpTime
	 */
	public Double getSuiUpTime() {
		return suiUpTime;
	}

	/**
	 * @return the currentSurface
	 */
	public String getCurrentSurface() {
		return currentSurface;
	}

	
}
