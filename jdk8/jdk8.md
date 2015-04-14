
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







# See also

* http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html
* https://leanpub.com/whatsnewinjava8/read
* https://github.com/katoquro/jdk8-lambda-samples


