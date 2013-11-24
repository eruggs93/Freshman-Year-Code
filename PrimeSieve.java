
/*************************************************************************
 * Eric Ruggieri
 * assign 2
 * Date Submitted 10/5/11
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 * Compilation:  javac PrimeSieve.java
 *  Execution:    java -Xmx1100m PrimeSieve N
 *  
 *  Purpose:      Computes the prime numbers of N
 *  Approach:     the Sieve of Eratosthenes
 *  
 *  The 110MB and 1100MB is the amount of memory you want to allocate
 *  to the program. If your computer has less, make this number smaller,
 *  but it may prevent you from solving the problem for very large
 *  values of N.
 *
 *
 *                  N     Primes <= N
 *  ---------------------------------
 *                 10               4   
 *                100              25  
 *              1,000             168  
 *             10,000           1,229  
 *            100,000           9,592  
 *          1,000,000          78,498  
 *         10,000,000         664,579  
 *        100,000,000       5,761,455  
 *      1,000,000,000      50,847,534  
 *
 *************************************************************************/


public class PrimeSieve {

	public static void main(String[] args) { 

		int N = 0;
		if (args.length < 1) {
			System.out.println("Usage: java PrimeSieve N");
			System.exit(0);
		}
		N = Integer.parseInt(args[0]);

		// initially assume all integers are prime
		boolean[] isPrime = new boolean[N + 1];

		for (int i = 2; i <= N; i++) {
			isPrime[i] = true;
		}

		int tripCount = 0;

		// mark non-primes <= N using Sieve of Eratosthenes
		for (int i = 2; i * i <= N; i++) {
			// if i is prime, then mark multiples of i as nonprime
			if (isPrime[i]) {
				int j = 0;
				if(i == 2)
					j = i + i;
				else if(i == 3)
					j = i + i + i;
				else if(i == 5)
					j = i + i + i + i + i;
				else
					j = i * 7;
				while (j <= N) {
					tripCount++;
					isPrime[j] = false;
					if(i == 2)
						j = j + i;
					else 
						j = j + i + i;
				}
			}
		}

		System.out.println("Number of times in the inner loop: " + tripCount);

		// count and display primes
		int primes = 0;
		for (int i = 2; i <= N; i++) {
			if (isPrime[i]) {
				primes++;
				System.out.print(i + " ");
			}
		}
		System.out.println(" ");
		System.out.println("The number of primes <= " + N + " is " + primes);
	}
}
