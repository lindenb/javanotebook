
# JDK8 Notebook

## __Default__ methods
see [http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html](http://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html).

In the example below: **Human** is **NOT** abstract but we dont need to implement the method **isVirus** because there is a default implementation in **Organism**.


Code:
```java 
(...)
interface Organism
	{
	public String getName();
	default public boolean isVirus() { return false;}
	}

static /* NOT ABSTRACT */ class Human implements Organism
	{
	@Override
	public String getName()
		{
		return "Human";
		}
	/* isVirus is NOT defined */
	}

public static void main(String args[])
	{
	
	Human h=new Human();
	System.out.println("A "+h.getName()+": Is it a virus ? "+h.isVirus());

	}


(...)
``` 


Output:
``` 
A Human: Is it a virus ? false

``` 


## java.util.function.Predicate

Functional interfaces provide target types for lambda expressions and method references.

See https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html.


Code:
```java 
(...)


private static java.util.List<TSeq> filterSequences(
	java.util.List<TSeq> sequences,
	java.util.function.Predicate<TSeq> pred
	)
	{
	java.util.List<TSeq> L= new java.util.ArrayList<>();
	for(TSeq tseq: sequences)
		{
		if(pred.test(tseq)) L.add(tseq);
		}
	return L;
	}

public static void main(String args[]) throws Exception
	{
	java.util.List<TSeq> sequences = TSeqSet.load("sequences.xml").getTSeq();
	java.util.function.Predicate<TSeq> predicate = 
		new java.util.function.Predicate<TSeq>()
			{
			@Override
			public boolean test(TSeq seq)
				{
				return seq.length()>50 && seq.length()<200;
				}
			};
	System.out.println("#Sequences with 50<length<200");
	for(TSeq seq: filterSequences(sequences,predicate))
		{
		System.out.println(seq.getFastaHeader());
		}
	
	/* now invert predicate */
	predicate= predicate.negate();
	System.out.println("\n#Sequences with not(50<length<200)");
	for(TSeq seq: filterSequences(sequences,predicate))
		{
		System.out.println(seq.getFastaHeader());
		}
	
	/* append a LOGICAL-OR expression */
	predicate= predicate.or(
		new java.util.function.Predicate<TSeq>()
			{
			@Override
			public boolean test(TSeq seq)
				{
				return seq.getTSeqDefline().contains("retrovirus");
				}
			});
	System.out.println("\n#Sequences with not(50<length<200) or defline contains retrovirus");
	for(TSeq seq: filterSequences(sequences,predicate))
		{
		System.out.println(seq.getFastaHeader());
		}
	}


(...)
``` 


Output:
``` 
#Sequences with 50<length<200
gi:300810614|len:192|Mammuthus primigenius voucher SP1349 clone 273 locus B120 genomic sequence
gi:300810505|len:145|Mammuthus primigenius voucher SP1349 clone 164 locus A326 genomic sequence
gi:300810611|len:166|Mammuthus primigenius voucher SP1349 clone 270 locus B114 genomic sequence
gi:300810545|len:137|Mammuthus primigenius voucher SP1349 clone 204 locus A412 genomic sequence
gi:300810527|len:129|Mammuthus primigenius voucher SP1349 clone 186 locus A380 genomic sequence
gi:14090863|len:109|Mammuthus primigenius clone Ala3.5 endogenous retrovirus ERV-L pol gene, partial sequence
gi:6465998|len:82|Mammuthus primigenius interphotoreceptor retinoid binding protein (IRBP) gene, partial cds
gi:300810541|len:132|Mammuthus primigenius voucher SP1349 clone 200 locus A405 genomic sequence
gi:300810364|len:107|Mammuthus primigenius voucher SP1349 clone 23 locus A019 genomic sequence
gi:300810675|len:178|Mammuthus primigenius voucher SP1349 clone 334 locus B226 genomic sequence
gi:300810535|len:152|Mammuthus primigenius voucher SP1349 clone 194 locus A390 genomic sequence
gi:14090923|len:106|Mammuthus primigenius clone Wra3.5 endogenous retrovirus ERV-L pol gene, partial sequence
gi:300810589|len:195|Mammuthus primigenius voucher SP1349 clone 248 locus B081 genomic sequence
gi:300810419|len:117|Mammuthus primigenius voucher SP1349 clone 78 locus A178 genomic sequence
gi:14090875|len:109|Mammuthus primigenius clone Sib1.7 endogenous retrovirus ERV-L pol gene, partial sequence

#Sequences with not(50<length<200)
gi:195972538|len:513|cytochrome c oxidase subunit I [Homo sapiens neanderthalensis]
gi:160332318|len:570|RecName: Full=Collagen alpha-1(I) chain; AltName: Full=Alpha-1 type I collagen
gi:158958248|len:30|melanocortin 1 receptor [Homo sapiens neanderthalensis]
gi:146286082|len:18|RecName: Full=Collagen alpha-2(I) chain; AltName: Full=Alpha-2 type I collagen
gi:146286083|len:14|RecName: Full=Collagen alpha-1(II) chain; AltName: Full=Alpha-1 type II collagen
gi:198241471|len:743|Mammuthus primigenius isolate 2001/412 cytochrome b (cytb) gene, partial cds; tRNA-Thr (trnT) and tRNA-Pro (trnP) genes, complete sequence; and D-loop, partial sequence; mitochondrial
gi:924720|len:228|Mammuthus primigenius cytochrome b (cytb) gene, Fairbanks allele, mitochondrial gene encoding mitochondrial protein, partial cds
gi:198241429|len:743|Mammuthus primigenius isolate NMC42135 cytochrome b (cytb) gene, partial cds; tRNA-Thr (trnT) and tRNA-Pro (trnP) genes, complete sequence; and D-loop, partial sequence; mitochondrial
gi:27451265|len:841|Mammuthus primigenius cytochrome b gene, partial cds; mitochondrial gene for mitochondrial product
gi:512435738|len:741|Mammuthus primigenius isolate L262 cytochrome b (cytb) gene, partial cds; tRNA-Thr and tRNA-Pro genes, complete sequence; and D-loop, partial sequence; mitochondrial

#Sequences with not(50<length<200) or defline contains retrovirus
gi:195972538|len:513|cytochrome c oxidase subunit I [Homo sapiens neanderthalensis]
gi:160332318|len:570|RecName: Full=Collagen alpha-1(I) chain; AltName: Full=Alpha-1 type I collagen
gi:158958248|len:30|melanocortin 1 receptor [Homo sapiens neanderthalensis]
gi:146286082|len:18|RecName: Full=Collagen alpha-2(I) chain; AltName: Full=Alpha-2 type I collagen
gi:146286083|len:14|RecName: Full=Collagen alpha-1(II) chain; AltName: Full=Alpha-1 type II collagen
gi:198241471|len:743|Mammuthus primigenius isolate 2001/412 cytochrome b (cytb) gene, partial cds; tRNA-Thr (trnT) and tRNA-Pro (trnP) genes, complete sequence; and D-loop, partial sequence; mitochondrial
gi:924720|len:228|Mammuthus primigenius cytochrome b (cytb) gene, Fairbanks allele, mitochondrial gene encoding mitochondrial protein, partial cds
gi:198241429|len:743|Mammuthus primigenius isolate NMC42135 cytochrome b (cytb) gene, partial cds; tRNA-Thr (trnT) and tRNA-Pro (trnP) genes, complete sequence; and D-loop, partial sequence; mitochondrial
gi:27451265|len:841|Mammuthus primigenius cytochrome b gene, partial cds; mitochondrial gene for mitochondrial product
gi:512435738|len:741|Mammuthus primigenius isolate L262 cytochrome b (cytb) gene, partial cds; tRNA-Thr and tRNA-Pro genes, complete sequence; and D-loop, partial sequence; mitochondrial
gi:14090863|len:109|Mammuthus primigenius clone Ala3.5 endogenous retrovirus ERV-L pol gene, partial sequence
gi:14090923|len:106|Mammuthus primigenius clone Wra3.5 endogenous retrovirus ERV-L pol gene, partial sequence
gi:14090875|len:109|Mammuthus primigenius clone Sib1.7 endogenous retrovirus ERV-L pol gene, partial sequence

``` 




# See also

* http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
* https://leanpub.com/whatsnewinjava8/read
* https://github.com/katoquro/jdk8-lambda-samples


