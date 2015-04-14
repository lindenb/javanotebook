SHELL=/bin/bash
.PHONY:all clean

all: jdk8.md x
	cat $^

x:
	mkdir -p tmp
	javac -d tmp -sourcepath ../common/ ../common/demo/ncbi/tseq/ObjectFactory.java

jdk8.md : jdk8.m4
	m4 -P $< > $@

clean:
	

