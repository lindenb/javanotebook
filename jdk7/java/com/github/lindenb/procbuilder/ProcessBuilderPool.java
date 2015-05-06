package  com.github.lindenb.procbuilder;

import java.util.concurrent.*;
import java.io.*; 
import java.util.*; 

public class ProcessBuilderPool
	{
	private static final int POOL_SIZE=6;
	private List<Program> programs = new Vector<Program>();
	
	private class StreamBoozer extends Thread {
        private InputStream in;
        private PrintStream pw;
 		private String prefix;
        StreamBoozer(InputStream in, PrintStream pw,String prefix) {
            this.in = in;
            this.pw = pw;
            this.prefix=prefix;
        	}
        @Override
        public void run()
        	{
        	boolean begin=true;
        	try {
        		int c;
        		while((c=in.read())!=-1)
        			{
        			if(begin) pw.print(prefix);
        			pw.write((char)c);
        			begin=(c=='\n');
        			}
        	 	}
        	catch(Exception err)
        		{
        		err.printStackTrace();
        		}
        	finally
        		{
        		try{in.close();} catch(Exception e2) {}
        		}
        	}
		}
	private class Program
		 implements Callable<Integer>
		{
		int ret=-1;
		int command;
		@Override
		public Integer call()
			{
			try
				{
				System.err.println("running "+command);
				Thread.sleep(10*100);
				Process p = new ProcessBuilder().command("ls"+(command%10==0?"xxx":""),"/tmp").start();
				StreamBoozer seInfo = new StreamBoozer(p.getInputStream(), System.out,"[LOGO "+command+"]");
        		StreamBoozer seError = new StreamBoozer(p.getErrorStream(), System.err,"[LOGE "+command+"]");
        		seInfo.start();
        		seError.start();
				ret= p.waitFor();
				seInfo.join();
      			seError.join();
				System.err.println("DOne");
				return ret;
				}
			catch(Exception err)
				{
				System.err.println(err.getMessage());
				return -1;
				}
			
			}
		}
	/* http://stackoverflow.com/questions/1493040/java-os-process-queue */
	private void run() throws Exception	
		{
		/*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while((line=in.readLine())!=null)
			{
			Program program = new Program();
			program.command=line;
			programs.add(program);
			}
		in.close();*/
		for(int i=0;i< 1000;++i)
			{
			Program program = new Program();
			program.command=i;
			programs.add(program);
			}
		
		ExecutorService ex = Executors.newFixedThreadPool(POOL_SIZE);
		List<Future<Integer>> futures=new Vector<Future<Integer> >();
		for(Program p: programs)
			{
			System.err.println("submit "+p.command);
			Future<Integer> future = ex.submit(p);
			futures.add(future);
			}
		System.err.println("Done submission");
		while(!futures.isEmpty())
			{
			System.err.println("loop");
			int i=0;
			while(i<futures.size())
				{
				if(futures.get(i).isDone())
					{
					System.err.println("done ret="+futures.get(i).get());
					futures.remove(i);
					}
				else
					{
					++i;
					}
				}
			Thread.sleep(10*1000);
			}
		 ex.shutdown();
		 
		}
	
	public static void main(String args[]) throws Exception	
		{
		new ProcessBuilderPool().run();
		}
	}
