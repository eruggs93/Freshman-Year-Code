
public class B implements Comparable
{
	public int b;
	public String data;

	public B(int bin, String datain)
	{
		b = bin;
		data = datain;
	}
	public int compareTo(Object object) 
	{
		// TODO Auto-generated method stub
		if(this.b < ((B)object).b)
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
		return this.b == ((A)other).a;
	}

	public int getB()
	{
		return b;
	}

	public boolean equals(Object other)
	{
		if(other.getClass().getName() == "A")
			return this.b == ((A)other).a;
		else
			return this.b == ((B)other).b;
	}

	public String toString()
	{
		return "data: " + data + " " + b;
	}
}
