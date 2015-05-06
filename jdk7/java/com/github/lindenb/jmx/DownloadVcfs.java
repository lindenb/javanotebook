package com.github.lindenb.jmx;

import java.lang.management.*; 
import javax.management.*; 
import java.io.*; 
import java.util.*; 
import java.net.*; 
import java.util.zip.*;

public class DownloadVcfs
	//extends NotificationBroadcasterSupport
	implements Runnable,DownloadVcfsMBean
	{
	private List<String> vcfs = new Vector<String>();
	private int currentIndex=0;
	private String currentVcf=null;
	private long currentDuration=0L;
	private long totalDuration=0L;
	private boolean abort=false;
	
	/** current VCF downloaded */
	public String getCurrentUrl()	
		{
		return currentVcf;
		}
	/** current elapsed time */
	public double getCurrentDuration()
		{
		return currentDuration/1000.0;
		}
	/** current elapsed time */
	public double getTotalDuration()
		{
		return totalDuration/1000.0;
		}
	/** current index in VCF list */
	public int getCurrentIndex()
		{
		return currentIndex;
		}
	/** current index in VCF list */
	public int getNumberOfVcfs()
		{
		return vcfs.size();
		}
	
	public synchronized void setAbort(boolean b)
		{
		abort=true;
		}
	
	public boolean isAbort()
		{
		return this.abort;
		}
	
	@Override
	public void run()
		{
		BufferedReader in=null;
		try
			{
			long totalStart = System.currentTimeMillis();
			this.totalDuration=0L;
			while(currentIndex < vcfs.size() && !this.isAbort())
				{
				long start = System.currentTimeMillis();
				this.currentDuration=0L;
				this.currentVcf = vcfs.get(currentIndex);
				URL url=new URL(this.currentVcf);
				String line;
				in = new BufferedReader(new InputStreamReader(new GZIPInputStream(url.openStream())));
				while((line=in.readLine())!=null && !this.isAbort())
					{
					this.currentDuration = System.currentTimeMillis()-start;
					this.totalDuration = System.currentTimeMillis()-totalStart;
					}
				in.close();
				in=null;
				++currentIndex;
				}
			this.currentVcf="";
			}
		catch(Exception err)
			{
			err.printStackTrace();
			}
		finally
			{
			try {if(in!=null) in.close();}	catch(Exception err2) {}
			}
		}
	
	
	public static void main(String args[]) throws Exception
		{
		String line;
		DownloadVcfs app= new DownloadVcfs();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while((line=in.readLine())!=null)
			{
			if(!line.endsWith(".vcf.gz")) continue;
			app.vcfs.add(line);
			}
		in.close();
		
		/* get a MBean server that has been created and initialized by the platform, */
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
		/* defines an object name for the MBean instance that it will create
		   Every JMX MBean must have an object name.
		*/
		ObjectName name = new ObjectName("com.github.lindenb.jmx:type=VCF"); 
		
		
		mbs.registerMBean(app, name); 
		new Thread(app).start();
		}
	}
