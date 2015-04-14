SHELL=/bin/bash
.PHONY:all clean

all: jdk8.md
	cat $<

jdk8.md : jdk8.m4
	m4 -P $< > $@

sequences.xml :
	wget -O - "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?&db=nuccore&id=195972538,160332318,    158958248,146286082,146286083,300810614,198241471,924720,198241429,27451265,512435738,300810505,300810611,300810545,300810527,14090863,6465998,300810541,300810364,300810675,300810535,14090923,300810589,300810419,14090875&rettype=fasta&retmode=xml"  |\
	xmllint --format  - | grep -v DOCTYPE > $@	


clean:
	rm -f sequences.xml
	

