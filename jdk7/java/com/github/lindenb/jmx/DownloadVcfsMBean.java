package com.github.lindenb.jmx;

public interface DownloadVcfsMBean
	{
	/** current VCF downloaded */
	public String getCurrentUrl();
	/** current elapsed time */
	public double getCurrentDuration();
	/** current elapsed time */
	public double getTotalDuration();
	/** current index in VCF list */
	public int getCurrentIndex();
	/** current index in VCF list */
	public int getNumberOfVcfs();
	/** abort download */
	public void setAbort(boolean b);
	public boolean isAbort();
	}
