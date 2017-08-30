package au.com.vocus.fetch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FetchProperty {
	public static class PropertyKey {
		public static String SERVER_URL = "server.url";
		public static String API_RESOURCE = "server.resource";
		public static String LAST_EVENT_DATE = "event.lastevent.date";
		
		public static String CONNECTION_STRING = "database.access.connectionString";
		public static String USERNAME = "database.access.username";
		public static String PASSWORD = "database.access.password";
		public static String TABLE_NAME = "database.data.table";
		
	}

	private static final String filename = "fetch.properties";
	private Properties PROPS = new Properties();
	private Long lastFileDate;
	
	public FetchProperty() {
		try {
            final InputStream inputStream = new FileInputStream(filename);
            PROPS.load(inputStream);
            inputStream.close();
            
        } catch (IOException e) {
            System.out.println("Error loading properties file.");
            e.printStackTrace();
        }
	}
	
	public String getServerURL() {
		return PROPS.getProperty(PropertyKey.SERVER_URL);
	}

	public String getResource() {
		return PROPS.getProperty(PropertyKey.API_RESOURCE);
	}
	
	public String getConnectionString() {
		return PROPS.getProperty(PropertyKey.CONNECTION_STRING);
	}
		
	public String getUsername() {
		return PROPS.getProperty(PropertyKey.USERNAME);
	}
	
	public String getPassword() {
		return PROPS.getProperty(PropertyKey.PASSWORD);
	}
	
	public String getTableName() {
		return PROPS.getProperty(PropertyKey.TABLE_NAME);
	}
	
	public Long getLastEventDate() {
		if(PROPS.getProperty(PropertyKey.LAST_EVENT_DATE) == null || PROPS.getProperty(PropertyKey.LAST_EVENT_DATE).trim().isEmpty())
			return null;
		
		if (lastFileDate == null) {
			
			try {
				lastFileDate = Long.parseLong(PROPS.getProperty(PropertyKey.LAST_EVENT_DATE));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		return lastFileDate;
	}
	
	public void setLastEventDate(Long datetime) {
		PROPS.put(PropertyKey.LAST_EVENT_DATE, datetime.toString());
	}
	
	public void save() {
		try {
			PROPS.store(new FileOutputStream(filename), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
