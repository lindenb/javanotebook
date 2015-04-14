m4_define(`__DOCURL__',`http://docs.oracle.com/javase')
m4_define(`__FASTA_JAVA__',`class Sequence
	{
	private String name;
	private String sequence;
	public Sequence(String name,String sequence)
		{
		this.name=name;
		this.sequence=sequence;
		}
	public String getName()
		{
		return this.name;
		}
	public char charAt(int index)
		{
		return this.sequence.charAt(index);
		}
	public int length()
		{
		return this.sequence.length;
		}
	}')
# JDK8 Notebook

## Lambda expressions.

see __DOCURL__/tutorial/java/javaOO/lambdaexpressions.html

__FASTA_JAVA__
