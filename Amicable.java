/*************************************************************************
 * Eric Ruggieri
 * assign 1
 * Date submitted ________
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation: javac Amicable.java 
 *  Execution: java Amicable N
 * 
 *  Purpose: Prints all amicable pairs whose sum is less than or equal
 *  to N
 *************************************************************************/
import java.util.*;

import javax.swing.JOptionPane;
public class Amicable {
	public static void main(String[] args) {
		int N = 0, i = 0, j = 0;
		if (args.length < 1) {
			System.out.println("Usage: java Amicable N");
			//            System.exit(0);
			String input = JOptionPane.showInputDialog("Enter the number you wish to go up to here: ");
			N = Integer.parseInt(input);
			System.out.println(N);
			//			String junk = read.nextLine();
		}
		else
		{
			N = Integer.parseInt(args[0]);
		}


		for (int sum = 1; sum < 100000; sum ++)
		{//this loop tests every number to see if its sum of divisors 
			//is amicable to itself
			j = sum;
			i = 0;
			while(sumOfDivisors(i) != j || sumOfDivisors(j) != i)
			{
				if(i == sum)
					break;
//				System.out.println("(" + i + "," + j + ")");
				i++;
				j--;
			}
			if(i == 220 && j == 284)
				System.out.println("hey");
			if(sumOfDivisors(i) == j && sumOfDivisors(j) == i)
			{
				System.out.println("in if statement");
				System.out.println("(" + i + "," + j + ")");
				if(i != j)
					System.out.println("Amicable(" + i + "," + j + ")");
			}
		}
	}

	public static int sumOfDivisors(int k) {
		// pre: k >= 0
		// post: returns the sum of all divisors of k
		int sum = 0;
		for (int i = 1; i < k; i++)
			if (k % i == 0)  // divisor, add to sum
				sum += i;
		return sum;
	}

}

