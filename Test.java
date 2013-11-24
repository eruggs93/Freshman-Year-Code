
public class Test {
	public static void main(String[] args)
	{
		A[] hi = {new A(4, "hi"), new A(1,"bye"), new A(6,"hello")};
		MergeSort i = new MergeSort(hi);
		i.sort();
		i.show();
//		A yo = new A(4, "hello");
		B ya = new B(4, "what");
//		System.out.println(yo.getClass().getName());
		System.out.println(hi[1].equals(ya));
	}
}
