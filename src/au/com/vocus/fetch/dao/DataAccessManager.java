package au.com.vocus.fetch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Properties;

import au.com.vocus.fetch.schema.Event;
import au.com.vocus.fetch.schema.FetchTvRecord;
import au.com.vocus.fetch.schema.WatchedMedia;

public class DataAccessManager {

	Connection conn;
	
	public DataAccessManager(String connStr, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.amazon.redshift.jdbc42.Driver");

		Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        
        conn = DriverManager.getConnection(connStr, props);
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
				String sql = buildSql(record, data);
				Statement statement = conn.createStatement();
				statement.execute(sql);
			}
			newEventDate = (newEventDate == null || data.getEventTime() > newEventDate) ? data.getEventTime() : newEventDate;
		}
		return newEventDate;
	}
	
	private String buildSql(FetchTvRecord record, WatchedMedia data) {
		
		String sql = "INSERT INTO fetchtv.watched_media_event ("
					+ " isp_customer_ref,"
					+ "  terminal_uuid,"
					+ "  household_uuid,"
					+ "  start_date,"
					+ "  event_time,"
					+ "  end_date,"
					+ "  previous_surface,"
					+ "  current_surface,"
					+ "  navigation_source,"
					+ "  channel_id,"
					+ "  program_id,"
					+ "  series_id,"
					+ "  episode_id,"
					+ "  recording_id,"
					+ "  media_duration,"
					+ "  duration_watched,"
					+ "  percentage_watched,"
					+ "  base_type,"
					+ "  media_type,"
					+ "  item_identifier,"
					+ "  data_received,"
					+ "  average_bit_rate,"
					+ "  hls_max_stream,"
					+ "  hls_min_stream,"
					+ "  title,"
					+ "  application_name,"
					+ "  series_name"
					+ ")"
					+ "VALUES"
					+ "("
					+ "  '" + record.getIspCustomerRef() + "',"
					+ "  '" + record.getTerminalUUID() + "',"
					+ "  '" + record.getHouseholdUUID() + "',"
					+ "  '" + convertDate(data.getStartDate()) + "',"
					+ "  '" + convertDate(data.getEventTime()) + "',"
					+ "  '" + convertDate(data.getEndDate()) + "',"
					+ "  '" + data.getPreviousSurface() + "',"
					+ "  '" + data.getCurrentSurface() + "',"
					+ "  '" + data.getNavigationSource() + "',"
					+ "  '" + data.getChannelId() + "',"
					+ "  '" + data.getProgramId() + "',"
					+ "  '" + data.getSeriesId() + "',"
					+ "  '" + data.getEpisodeId() + "',"
					+ "  '" + data.getRecordingId() + "',"
					+ "  " + data.getMediaDuration() + ","
					+ "  " + data.getDurationWatched() + ","
					+ "  " + data.getPercentageWatched() + ","
					+ "  '" + data.getBaseType() + "',"
					+ "  '" + data.getMediaType() + "',"
					+ "  " + data.getItem_identifier() + ","
					+ "  " + data.getDataReceived() + ","
					+ "  " + data.getAverageBitRate() + ","
					+ "  " + data.getHlsMaxStream() + ","
					+ "  " + data.getHlsMinStream() + ","
					+ "  '" + data.getTitle() + "',"
					+ "  '" + data.getApplicationName() + "',"
					+ "  '" + data.getSeriesName() + "'"
				    + ");";

		return sql;
	}

	private String convertDate(Long date) {
		if (date == null)
			return null;
		
		Instant timestamp = Instant.ofEpochMilli(date);
		return timestamp.toString();
	}
}
