/*************************************************************************
 * Eric Ruggieri
 * assign 3
 * Date Submitted 10/5/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation:  javac DPLL.java
 *  Execution:    java DPLL < .wff
 *  Dependencies: StdIn.java
 *
 *************************************************************************/

public class DPLL 
{
	static int[] clauses;
	static int callcount = 0;
	public static void main(String[] args)
	{
		String skip = StdIn.readString();
		skip = StdIn.readString();
		int varNum = StdIn.readInt();
		int clauseNum = StdIn.readInt();
		clauses = StdIn.readInts();
		boolean[] vars = new boolean[varNum];
		for(int i = 0; i < vars.length; i++)
			vars[i] = false;
		boolean check = dpll(vars,0);
		if(check)
		{
			System.out.println();
			System.out.println("SAT");
			printSol(vars);
		}
		else
			System.out.println("UNSAT");
	}

	public static boolean dpll(boolean[] a, int n)
	{
		callcount++;
		if(n>= a.length)
			return check(a, n);
		if(check(a, n))
		{
			return true;
		}
		else
		{
			a[n] = true;
			int hold = n%8;
			boolean result = dpll(a, n+1);
			if(result)
			{
				return true;
			}
			else
			{
				a[n] = false;
				return dpll(a, n+1);
			}
		}
	}

	private static boolean check(boolean[] a, int count) {
		// TODO Auto-generated method stub
		boolean continual = true;
		boolean check = false;
		for(int i = 0; i < clauses.length; i++)
		{
			if(clauses[i] != 0)
			{
				if(clauses[i] < 0)
					check = check || !a[Math.abs(clauses[i])-1];
				else
					check = check || a[clauses[i]-1];
			}
			else
			{
				if(check == false)
					return false;
				else
					continual = continual && check;
				check = false;
				continue;
			}
		}
		return true;
	}

	public static void printSol(boolean[] vars)
	{
		int hold = 0;
		for(int i = 0; i < vars.length; i++)
		{
			if(i == 64)
				break;
			hold = i+1;
			if(vars[i])
				System.out.print(hold + " ");
			else
				System.out.print("-" + hold + " ");
		}
		System.out.println();
		System.out.println("number of recursive calls: " + callcount);
	}
}
