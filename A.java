
public class A implements Comparable
{
	public int a;
	public String data;

	public A(int ain, String datain)
	{
		a = ain;
		data = datain;
	}

	public int compareTo(Object object) 
	{
		// TODO Auto-generated method stub
		if(this.a < ((A)object).a)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

	public boolean equalsDiff(Object other)
	{
		return this.a == ((B)other).b;
	}
	public int getA()
	{
		return a;
	}

	public boolean equals(Object other)
	{
		if(other.getClass().getName() == "B")
		{
			return this.a == ((B)other).b;
		}
		else
			return this.a == ((A)other).a;
	}

	public String toString()
	{
		return "data: " + data + " " + a;
	}

}
