.PHONY:all jdk7 jdk7-jmx-01


jdk7-compiler-api-01:
	mkdir -p tmp
	javac -d tmp -sourcepath jdk7/java jdk7/java/com/github/lindenb/compiler/*.java
	java -cp tmp com.github.lindenb.compiler.DynamicJavaCompilation
	
	
jdk7-procbuilder-01:
	mkdir -p tmp
	javac -d tmp -sourcepath jdk7/java jdk7/java/com/github/lindenb/procbuilder/ProcessBuilderPool.java
	java -cp tmp com.github.lindenb.procbuilder.ProcessBuilderPool

jdk7-jmx-01:
	mkdir -p tmp
	javac -d tmp -sourcepath jdk7/java jdk7/java/com/github/lindenb/jmx/DownloadVcfs.java
	echo "###### TURN jconsole on !!"
	curl -kL "ftp://ftp.1000genomes.ebi.ac.uk/vol1/ftp/current.tree" |\
		cut -f 1 | grep 'vcf.gz$$' | head -n 100 | sed 's%^%ftp://ftp.1000genomes.ebi.ac.uk/vol1/%' |\
		java -cp tmp com.github.lindenb.jmx.DownloadVcfs
