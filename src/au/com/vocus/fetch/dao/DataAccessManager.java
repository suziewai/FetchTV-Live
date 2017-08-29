package au.com.vocus.fetch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import au.com.vocus.fetch.schema.FetchTvRecord;

public class DataAccessManager {

	Connection conn;
	
	public DataAccessManager(String connStr, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.amazon.redshift.jdbc42.Driver");

		Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        
        conn = DriverManager.getConnection(connStr, props);
	}
	
	public void insertRecord(FetchTvRecord record) {
		String sql = "INSERT INTO fetchtv.watched_media_event ("
					+ " ispcustomerref,"
					+ "  terminaluuid,"
					+ "  householduuid,"
					+ "  startdate,"
					+ "  eventtime,"
					+ "  enddate,"
					+ "  previoussurface,"
					+ "  currentsurface,"
					+ "  navigationsource,"
					+ "  channelid,"
					+ "  programid,"
					+ "  epgprogramid,"
					+ "  seriesid,"
					+ "  episodeid,"
					+ "  recordingid,"
					+ "  mediaduration,"
					+ "  durationwatched,"
					+ "  percentagewatched,"
					+ "  basetype,"
					+ "  mediatype,"
					+ "  item_identifier,"
					+ "  datareceived,"
					+ "  averagebitrate,"
					+ "  hlsmaxstream,"
					+ "  hlsminstream,"
					+ "  title,"
					+ "  applicationname,"
					+ "  seriesname"
					+ ")"
					+ "VALUES"
					+ "("
					+ "  '" + ispcustomerref_value + "',"
					+ "  '" + terminaluuid_value + "',"
					+ "  '" + householduuid_value + "',"
					+ "  '" + startdate_value + "',"
					+ "  '" + eventtime_value + "',"
					+ "  '" + enddate_value + "',"
					+ "  '" + previoussurface_value + "',"
					+ "  '" + currentsurface_value + "',"
					+ "  '" + navigationsource_value + "',"
					+ "  '" + channelid_value + "',"
					+ "  '" + programid_value + "',"
					+ "  '" + epgprogramid_value + "',"
					+ "  '" + seriesid_value + "',"
					+ "  '" + episodeid_value + "',"
					+ "  '" + recordingid_value + "',"
					+ "  '" + mediaduration_value + "',"
					+ "  '" + durationwatched_value + "',"
					+ "  '" + percentagewatched_value + "',"
					+ "  '" + basetype_value + "',"
					+ "  '" + mediatype_value + "',"
					+ "  '" + item_identifier_value + "',"
					+ "  '" + datareceived_value + "',"
					+ "  '" + averagebitrate_value + "',"
					+ "  '" + hlsmaxstream_value + "',"
					+ "  '" + hlsminstream_value + "',"
					+ "  '" + title_value + "',"
					+ "  '" + applicationname_value + "',"
					+ "  '" + seriesname_value + "',"
				    + ");";


	}
}
