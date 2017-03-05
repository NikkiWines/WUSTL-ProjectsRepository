package edu.wustl.cse231s.rice.classic.options;

import java.io.File;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class SystemPropertiesOption {
	public static class Builder {
		public Builder isDumpStatisticsDesired( boolean isDumpStatisticsDesired ) {
			this.isDumpStatisticsDesired = isDumpStatisticsDesired;
			return this;
		}
		
		public Builder eventLogFile(File eventLogFile) {
			this.eventLogFile = eventLogFile;
			return this;
		}
		
		public SystemPropertiesOption build() {
			return new SystemPropertiesOption( this );
		}
		
		private boolean isDumpStatisticsDesired;
		private File eventLogFile;
	}
	
	private SystemPropertiesOption( Builder builder ) {
		this.isDumpStatisticsDesired = builder.isDumpStatisticsDesired;
		this.eventLogFile = builder.eventLogFile;
	}
	
	public boolean isDumpStatisticsDesired() {
		return this.isDumpStatisticsDesired;
	}
	
	public File getEventLogFile() {
		return this.eventLogFile;
	}
	
	private final boolean isDumpStatisticsDesired;
	private final File eventLogFile;
}
