package com.github.lindenb.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject.Kind;

/* REFERENCES: 

http://www.informit.com/articles/article.aspx?p=2027052&seqNum=2 
http://atamur.blogspot.fr/2009/10/using-built-in-javacompiler-with-custom.html 
https://github.com/trung/InMemoryJavaCompiler

 * */

public class DynamicJavaCompilation
	{
	private static final Logger LOG= Logger.getLogger("dynamicjavac");
	
	public static interface Filter
		{
		public boolean accept(int v);
		}
	
	private class DynamicClassLoader extends ClassLoader
		{
	    private Map<String, InMemoryJavaClass> customCompiledCode = new HashMap<>();
	    
	    public DynamicClassLoader(ClassLoader parent) {
	        super(parent);
	    	}
	    	    
	    public void setCode(InMemoryJavaClass cc) {
	        customCompiledCode.put(cc.getName(), cc);
	    	}

	    @Override
	    protected Class<?> findClass(String name) throws ClassNotFoundException {
	    	LOG.info("##findClass called "+name);
	    	InMemoryJavaClass cc = customCompiledCode.get(name);
	    	if(cc==null) cc=customCompiledCode.get("/"+name);
	        if (cc == null) {
	        	LOG.info("no "+name +" in "+customCompiledCode.keySet());
	            return super.findClass(name);
	        }
	        byte[] byteCode = cc.getByteCode();
	        return defineClass(name, byteCode, 0, byteCode.length);
	    	}
		}
	
	private static class InMemoryJavaSource
		extends javax.tools.SimpleJavaFileObject
		{
		private CharSequence code;
		protected InMemoryJavaSource(String path,CharSequence code)
			{
			super(URI.create("string:///" + path.replace('.', '/') +".java"),Kind.SOURCE);
			this.code=code;
			}
		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors)
				throws IOException {
			return code;
			}
		}
	
	private static class InMemoryJavaClass
	extends javax.tools.SimpleJavaFileObject
		{
		private ByteArrayOutputStream baos=new ByteArrayOutputStream();
		protected InMemoryJavaClass(String path)
			{
			super(URI.create("bytes:///" + path),Kind.CLASS);
			}
		@Override
		public OutputStream openOutputStream() throws IOException {
			return baos;
			}
		public byte[] getByteCode() {
	        return baos.toByteArray();
			}
		}
	
	
	private class MyFwdJavFileManager extends ForwardingJavaFileManager<JavaFileManager>
		{
		private DynamicClassLoader myclassloader;
		private InMemoryJavaClass inMemoryJavaClass;
		MyFwdJavFileManager(JavaFileManager delegate,DynamicClassLoader myclassloader,InMemoryJavaClass inMemoryJavaClass)
			{
			super(delegate);
			this.myclassloader=myclassloader;
			this.inMemoryJavaClass=inMemoryJavaClass;
			this.myclassloader.setCode(inMemoryJavaClass);
			}
		@Override
		public ClassLoader getClassLoader(Location location) {
			return myclassloader;
			}	
		
		@Override
		public JavaFileObject getJavaFileForOutput(Location location,
				String className, Kind kind, FileObject sibling)
				throws IOException {
			LOG.warning("#getJavaFileForOutput "+className);
			return inMemoryJavaClass;
			}
		}
	private void run() throws Exception
		{
		LOG.setLevel(Level.ALL);
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if( compiler == null )				
			{
			LOG.warning("cannot get java compiler");
			return;
			}
		DiagnosticListener<JavaFileObject> diagnosticListener=new DiagnosticListener<JavaFileObject>()
				{
				@Override
				public void report(
						Diagnostic<? extends JavaFileObject> diagnostic) {
					LOG.warning(diagnostic.toString());
					}
				};
				
				
		JavaFileManager javaFileManager = compiler.getStandardFileManager(
				diagnosticListener,
				null, /* locale */
				null /* charset */
				);
	
		List<? extends JavaFileObject> javaFileObjects = Collections.singletonList(
				new InMemoryJavaSource(
				"test.MyFilter",
				"package test;" +
				"public class MyFilter implements com.github.lindenb.compiler.DynamicJavaCompilation.Filter {" +
				" @Override public boolean accept(int v) { return v%2==0 && v>10 && v<25;} " +
				"}"));
		
		InMemoryJavaClass inMemoryJavaClass = new InMemoryJavaClass("test.MyFilter");
		DynamicClassLoader cl=new DynamicClassLoader(ClassLoader.getSystemClassLoader());
		javaFileManager = new MyFwdJavFileManager(javaFileManager,cl,inMemoryJavaClass);

				
		List<String> javacOptions = null;
		List<String> classList = null;
        JavaCompiler.CompilationTask task = compiler.getTask(
        		null,/* output */
        		javaFileManager,
        		diagnosticListener,
        		javacOptions,
        		classList,
        		javaFileObjects
        		);
        if(!task.call())
        	{
        	LOG.warning("task.call failed");
        	return ;
        	}
        Random random=new Random();
        Filter filter = (Filter)cl.loadClass("test.MyFilter").newInstance();
        for(int i=0;i< 100000;++i)
        	{
        	int v= random.nextInt(100);
        	if(filter.accept(v)) System.out.println(v);
        	}
        	
        

		LOG.warning("done");
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
		{
		try {
			new DynamicJavaCompilation().run();
			} 
		catch (Exception e)
			{
			e.printStackTrace();
			}
		}

}
