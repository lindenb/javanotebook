m4_divert(-1)
m4_define(`jnb_md_code',m4_changequote([, 
])[m4_changequote([,])```$1 m4_changequote(`,')]m4_changequote(`,'))
m4_define(`jnb_docurl',`http://docs.oracle.com/javase/$1')
m4_define(`jnb_md_bold',`**$1**')
m4_define(`jnb_md_anchor',`[m4_ifelse(`$#',`1',`$1',`$2')]($1)')
m4_define(`jnb_main_begin',`public static void main(String args[])
	{
	')
m4_define(`jnb_main_end',`
	}')
m4_define(`jnb_simple_java',`
Code:
jnb_md_code(java)
(...)
$1
(...)
jnb_md_code
m4_syscmd(`mkdir -p tmp/demo && cat << __END__ > tmp/demo/Example.java
package demo;
import demo.ncbi.tseq.*;

public class Example {
$1
}
__END__
')
m4_syscmd(`javac -d tmp -sourcepath ../common:tmp tmp/demo/Example.java')
Output:
jnb_md_code
m4_esyscmd(`java -cp tmp demo.Example')
jnb_md_code
')
m4_divert
# JDK8 Notebook

## __Default__ methods
see jnb_md_anchor(`jnb_docurl(`tutorial/java/IandI/defaultmethods.html')').

In the example below: **Human** is **NOT** abstract but we dont need to implement the method **isVirus** because there is a default implementation in **Organism**.

jnb_simple_java(

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

jnb_main_begin
	Human h=new Human();
	System.out.println("A "+h.getName()+": Is it a virus ? "+h.isVirus());
jnb_main_end

)

## java.util.function.Predicate

Functional interfaces provide target types for lambda expressions and method references.

See https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html.

jnb_simple_java(`

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

')

m4_divert(-1)
## Lambda expressions.
see jnb_md_anchor(`jnb_docurl(`tutorial/java/javaOO/lambdaexpressions.html')').
A **functional interface** is any interface that contains only one abstract method.
Because a functional interface contains only one abstract method, you can omit the name of that method when you implement it.
m4_divert

# See also

* http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
* https://leanpub.com/whatsnewinjava8/read
* https://github.com/katoquro/jdk8-lambda-samples


