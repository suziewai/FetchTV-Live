package au.com.vocus.fetch.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import au.com.vocus.fetch.schema.Event;
import au.com.vocus.fetch.schema.FetchTvRecord;
import au.com.vocus.fetch.schema.WatchedMedia;

public class DataAccessManager {

	Connection conn;
	PreparedStatement pStmt;
	
	public DataAccessManager(String connStr, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.amazon.redshift.jdbc42.Driver");

		Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        
        conn = DriverManager.getConnection(connStr, props);
        pStmt = conn.prepareStatement(buildSql());
	}
	
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isNewRecord(WatchedMedia data, Long lastEventDate) {
		
		// We don't want ANY "RECORDINGS"
		try {
			if(data.getCurrentSurface().startsWith("RECORDINGS:recordings"))
				return false;
		} catch(Exception e){
			//currentSurface can be null sometimes.
		}
				
		if(lastEventDate == null)
			return true;
		
		if(data.getEventTime() > lastEventDate)
			return true;
		
		return false;
	}
	
	public Long insertRecord(FetchTvRecord record, Long lastEventDate) throws SQLException {
		Long newEventDate = lastEventDate;
		for(Event event : record.getEvents()) {
			if(!(event.getData() instanceof WatchedMedia))
				continue;
			
			WatchedMedia data = (WatchedMedia) event.getData();
			if(isNewRecord(data, lastEventDate)) {
				insertParams(record, data);
				pStmt.execute();
			}
			newEventDate = (newEventDate == null || data.getEventTime() > newEventDate) ? data.getEventTime() : newEventDate;
		}
		return newEventDate;
	}
	
	private String buildSql() {
		return "INSERT INTO fetchtv.watched_media_event ("
				+ " isp_customer_ref,"
				+ " terminal_uuid,"
				+ " household_uuid,"
				+ " start_date,"
				+ " event_time,"
				+ " end_date,"
				+ " previous_surface,"
				+ " current_surface,"
				+ " navigation_source,"
				+ " channel_id,"
				+ " program_id,"
				+ " series_id,"
				+ " episode_id,"
				+ " recording_id,"
				+ " media_duration,"
				+ " duration_watched,"
				+ " percentage_watched,"
				+ " base_type,"
				+ " media_type,"
				+ " item_identifier,"
				+ " data_received,"
				+ " average_bit_rate,"
				+ " hls_max_stream,"
				+ " hls_min_stream,"
				+ " title,"
				+ " application_name,"
				+ " series_name"
				+ ")"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	}
	
	private void insertParams(FetchTvRecord record, WatchedMedia data) throws SQLException {
		pStmt.setString(1, record.getIspCustomerRef());
		pStmt.setString(2, record.getTerminalUUID());
		pStmt.setString(3, record.getHouseholdUUID());
		pStmt.setDate(4, convertDate(data.getStartDate()));
		pStmt.setDate(5, convertDate(data.getEventTime()));
		pStmt.setDate(6, convertDate(data.getEndDate()));
		pStmt.setString(7, data.getPreviousSurface());
		pStmt.setString(8, data.getCurrentSurface());
		pStmt.setString(9, data.getNavigationSource());
		pStmt.setString(10, data.getChannelId());
		pStmt.setString(11, data.getProgramId());
		pStmt.setString(12, data.getSeriesId());
		pStmt.setString(13, data.getEpisodeId());
		pStmt.setString(14, data.getRecordingId());
		pStmt.setObject(15, data.getMediaDuration(), Types.BIGINT);
		pStmt.setObject(16, data.getDurationWatched(), Types.BIGINT);
		pStmt.setObject(17, data.getPercentageWatched(), Types.NUMERIC);
		pStmt.setString(18, data.getBaseType());
		pStmt.setString(19, data.getMediaType());
		pStmt.setObject(20, data.getItem_identifier(), Types.BIGINT);
		pStmt.setObject(21, data.getDataReceived(), Types.BIGINT);
		pStmt.setObject(22, data.getAverageBitRate(), Types.INTEGER);
		pStmt.setObject(23, data.getHlsMaxStream(), Types.NUMERIC);
		pStmt.setObject(24, data.getHlsMinStream(), Types.NUMERIC);
		pStmt.setString(25, data.getTitle());
		pStmt.setString(26, data.getApplicationName());
		pStmt.setString(27, data.getSeriesName());
	}

	private Date convertDate(Long date) {
		if (date == null)
			return null;
		
		//Instant timestamp = Instant.ofEpochMilli(date);
		return new Date(date);
	}
}
