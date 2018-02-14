package au.com.vocus.fetch;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import au.com.vocus.fetch.schema.Event;
import au.com.vocus.fetch.schema.FetchTvRecord;
import au.com.vocus.fetch.schema.WatchedMedia;



public class FileWriter {
	
	private String PATH_PREFIX = "/data/app";
	private PrintWriter pw = null;
	private String delimiter = "";
	private boolean withQuote;
	private String filepath;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void createFolder(String folder) {
		this.filepath = PATH_PREFIX + File.separatorChar + folder;
		Path path = Paths.get(filepath);
		try {
			if(!Files.exists(path))
				Files.createDirectories(path);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void createFile(String filename, String delimiter, boolean withQuote) {
		this.delimiter = delimiter == null ? this.delimiter : delimiter;
		this.withQuote = withQuote;
		filepath = this.filepath == null ? PATH_PREFIX : filepath;
		
		try {
			pw = new PrintWriter(new File(filepath + File.separatorChar + filename));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Long writeRecord(FetchTvRecord record, Long lastEventDate) {
		Long newEventDate = lastEventDate;
		for(Event event : record.getEvents()) {
			if(!(event.getData() instanceof WatchedMedia))
				continue;
			
			WatchedMedia data = (WatchedMedia) event.getData();
			if(isNewRecord(data, lastEventDate)) {
				writeField(record.getIspCustomerRef(), false);
				writeField(record.getTerminalUUID(), false);
				writeField(record.getHouseholdUUID(), false);
				writeField(sdf.format(convertDate(data.getStartDate())), false);
				writeField(sdf.format(convertDate(data.getEventTime())), false);
				writeField(sdf.format(convertDate(data.getEndDate())), false);
				writeField(data.getPreviousSurface(), false);
				writeField(data.getCurrentSurface(), false);
				writeField(data.getNavigationSource(), false);
				writeField(data.getChannelId(), false);
				writeField(data.getProgramId(), false);
				writeField(data.getSeriesId(), false);
				writeField(data.getEpisodeId(), false);
				writeField(data.getRecordingId(), false);
				writeField(data.getMediaDuration(), false);
				writeField(data.getDurationWatched(), false);
				writeField(data.getPercentageWatched(), false);
				writeField(data.getBaseType(), false);
				writeField(data.getMediaType(), false);
				writeField(data.getItem_identifier(), false);
				writeField(data.getDataReceived(), false);
				writeField(data.getAverageBitRate(), false);
				writeField(data.getHlsMaxStream(), false);
				writeField(data.getHlsMinStream(), false);
				writeField(data.getTitle(), false);
				writeField(data.getApplicationName(), false);
				writeField(data.getSeriesName(), true);
			}
			newEventDate = (newEventDate == null || data.getEventTime() > newEventDate) ? data.getEventTime() : newEventDate;
		}
		return newEventDate;
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
		
		/*
		if(data.getEventTime() > lastEventDate)
			return true;
		*/
		
		if(data.getEndDate() > lastEventDate)
			return true;
		
		return false;
	}
	
	private void writeField(String value, boolean lastField) {
		
		if(value != null)
			pw.printf("\"%s\"", value);
		
		if(lastField)
			pw.println();
		else
			pw.print(delimiter);
	}
	
	private Date convertDate(Long date) {
		if (date == null)
			return null;
		
		//return new Date(date*1000L);
		return new Date(date);
	}	
	
	public void closeFile() {
		pw.flush();
		pw.close();
	}
}
