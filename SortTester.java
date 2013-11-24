/* Eric Ruggieri
 * assign 5
 * Date Submitted 11/2/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compiliation: javac -cp :..//stlib.jar SortTester.java
 * Usage: java -cp :..//stdlib.jar SortTester < MobyDick.txt
 * Dependencies: stdlib.jar A.java B.java MergeSort.java
 */
public class SortTester 
{
	public static void main(String[] args)
	{
		String content = StdIn.readAll();
		content = content.toLowerCase();
		content = content.replaceAll("[\",!.:;?()']", "");
		String[] text = content.split("\\s+");
		int rand = 0;
		A[] as = new A[100000];
		B[] bs = new B[100000];
		for(int i = 0; i < 100000; i++)
		{
			rand = (int) (Math.random() *100000);
			as[i] = new A(rand, text[rand]);
		}
		for(int j = 0; j < 100000; j++)
		{
			rand = (int) (Math.random() *100000);
			bs[j] = new B(rand, text[rand]);
		}
//		System.out.println("heyyy");
//		nestedJoin(as, bs);
//		mergeJoin(as, bs);
//		quickJoin(as, bs);
		diffJoin(as, bs);
	}

//	

	private static void diffJoin(A[] as, B[] bs) 
	{// TODO Auto-generated method stub
		System.out.println("Binary Search");
		MergeSort mergeA = new MergeSort(as);
		mergeA.sort();
		for(int k = 0; k < bs.length; k++)
		{
			int found = binarySearch(mergeA, bs[k].b, 0, mergeA.theArray.length);
			if(found < 0)
				continue;
			System.out.println("same: " + mergeA.theArray[found] + " " + bs[k]);
			int place = found-1;
			while(place > 0 && mergeA.theArray[place].equals(mergeA.theArray[found]))
			{
				System.out.println("same: " + mergeA.theArray[place] + " " + bs[k]);
				place--;
			}
			place = found+1;
			while(place < as.length && mergeA.theArray[place].equals(mergeA.theArray[found]))
			{
				System.out.println("same: " + mergeA.theArray[place] + " " + bs[k]);
				place++;
			}
		}
	}

	private static int binarySearch(MergeSort mergeA, int b,int bot, int top) 
	{// TODO Auto-generated method stub
//		System.out.println("bot: " + bot);
		if(top-bot <= 1)
		{
			if(top != mergeA.theArray.length && b == ((A)mergeA.theArray[top]).a)
				return top;
			else if(b == ((A)mergeA.theArray[bot]).a)
				return bot;
			else return -1;
		}
		int half = (top+bot)/2;
		if(b == ((A)mergeA.theArray[half]).a)
		{
			return half;
		}
		else if(b > ((A)mergeA.theArray[half]).a)
		{
			if(half+1 < top)
				return binarySearch(mergeA, b, half+1, top);
			else
				return binarySearch(mergeA, b, half, top);
		}
		else
		{
			return binarySearch(mergeA, b, bot, half);
		}
	}

	private static void mergeJoin(A[] as, B[] bs) 
	{// TODO Auto-generated method stub
		System.out.println("Sort Merge Join");
		MergeSort mergeA = new MergeSort(as);
		MergeSort mergeB = new MergeSort(bs);
		System.out.println("sizes " + mergeA.theArray.length + " " + mergeB.theArray.length);
		mergeA.sort();
		mergeB.sort();
		int acount = 0, bcount = 0;
		while(acount < as.length && bcount < bs.length)
		{
			if(((A)mergeA.theArray[acount]).equals((B)mergeB.theArray[bcount]))
			{
				System.out.println("same: " + mergeA.theArray[acount] + " " + mergeB.theArray[bcount]);
				int startb = bcount;
				bcount++;
				if(acount >= as.length || bcount >= bs.length)
					break;
				while(bcount < bs.length && ((B)mergeB.theArray[bcount]).equals((B)mergeB.theArray[startb]))
				{
					if(((A)mergeA.theArray[acount]).equals((B)mergeB.theArray[bcount]))
					{
						System.out.println("sameB: " + mergeA.theArray[acount] + " " + mergeB.theArray[bcount]);
						bcount++;
					}
				}
				int end = --bcount;
				int starta = acount;
				acount++;
				if(acount >= as.length || bcount >= bs.length)
					break;
				while(acount < as.length && mergeA.theArray[acount].equals(mergeA.theArray[starta]))
				{
					for(int i = startb; i <= end; i++)
					{
						if(((A)mergeA.theArray[acount]).equals((B)mergeB.theArray[bcount]))
						{
							System.out.println("sameA: " + mergeA.theArray[acount] + " " + mergeB.theArray[i]);
						}
					}
					acount++;
				}
				bcount++;

			}
			else if(((A)mergeA.theArray[acount]).a < ((B)mergeB.theArray[bcount]).b)
				acount++;
			else if(((A)mergeA.theArray[acount]).a > ((B)mergeB.theArray[bcount]).b)
				bcount++;
		}
	}

	private static void nestedJoin(A[] as, B[] bs) 
	{// TODO Auto-generated method stub
		System.out.println("Nested Loop Join");
		for(int i = 0; i < as.length; i ++)
			for(int j = 0; j < bs.length; j++)
			{
				if(as[i].a == bs[j].b)
					System.out.println("" + as[i].data + " " + bs[j].data);
			}
	}
	private static void quickJoin(A[] as, B[] bs) 
	{
		// TODO Auto-generated method stub
		QuickSortLong mergeA = new QuickSortLong(as);
		QuickSortLong mergeB = new QuickSortLong(bs);
		mergeA.sort();
		mergeB.sort();
		int acount = 0, bcount = 0;
		while(acount < as.length && bcount < bs.length)
		{
			if(((A)mergeA.theArray[acount]).equals((B)mergeB.theArray[bcount]))
			{
				System.out.println("same: " + mergeA.theArray[acount] + " " + mergeB.theArray[bcount]);
				int startb = bcount;
				bcount++;
				if(acount >= as.length || bcount >= bs.length)
					break;
				while(bcount < bs.length && ((B)mergeB.theArray[bcount]).equals((B)mergeB.theArray[startb]))
				{
					if(((A)mergeA.theArray[acount]).equals((B)mergeB.theArray[bcount]))
					{
						System.out.println("sameB: " + mergeA.theArray[acount] + " " + mergeB.theArray[bcount]);
						bcount++;
					}
				}
				int end = --bcount;
				int starta = acount;
				acount++;
				if(acount >= as.length || bcount >= bs.length)
					break;
				while(acount < as.length && mergeA.theArray[acount].equals(mergeA.theArray[starta]))
				{
					for(int i = startb; i <= end; i++)
					{
						if(((A)mergeA.theArray[acount]).equals((B)mergeB.theArray[bcount]))
						{
							System.out.println("sameA: " + mergeA.theArray[acount] + " " + mergeB.theArray[i]);
						}
					}
					acount++;
				}
				bcount++;

			}
			else if(((A)mergeA.theArray[acount]).a < ((B)mergeB.theArray[bcount]).b)
				acount++;
			else if(((A)mergeA.theArray[acount]).a > ((B)mergeB.theArray[bcount]).b)
				bcount++;
		}
	}
}
