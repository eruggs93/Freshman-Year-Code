/*************************************************************************
 * Eric Ruggieri
 * assign 1
 * Date submitted ______
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 *
 *  Compilation:  javac Ramanujan.java
 *  Execution:    java Ramanujan N
 *  
 *  Prints out any number between 1 and N that can be expressed as the
 *  sum of two cubes in two (or more) different ways.
 *
 *  % java Ramanujan 1728
 *
 *  % java Ramanujan 1729
 *  1729 = 1^3 + 12^3 = 9^3 + 10^3
 *
 *  % java Ramanujan 10000
 *  1729 = 1^3 + 12^3 = 9^3 + 10^3
 *  4104 = 2^3 + 16^3 = 9^3 + 15^3
 *  1729 = 9^3 + 10^3 = 1^3 + 12^3
 *  4104 = 9^3 + 15^3 = 2^3 + 16^3
 *
 *  Bugs
 *  ----
 *  If a number can be expressed as a sum of cubes in more than two
 *  different ways, the program prints some duplicates.
 *
 *************************************************************************/

public class Ramanujan {

    static final boolean optimize = false;

    public static void main(String[] args) { 

	// read in one commandline argument
	int counter = 0;
	int N = Integer.parseInt(args[0]);

	// for each a, b, c, d, check whether a^3 + b^3 = c^3 + d^3
	for (int a = 1; a <= N; a++) {
	    int a3 = a * a * a;
	    if (a3 > N) break;
	    
	    // start at a to avoid print out duplicate
	    for (int b = a; b <= N; b++){
		int b3 = b * b * b;
		if (a3 + b3 > N) break;
		
                // start at 1 to 
                for (int c = 1; c <= N; c++) {
		if(c==a)
		continue;
		 int c3 = c * c * c;
                 if (c3 > N) break;
                    
                    // start at c to avoid printing out duplicates
                    for (int d = c; d <= N; d++) {
                        if(d==b)
			continue;
			counter++;
			int d3 = d * d * d;
                        if (c3 + d3 > N) break;

			// check that the two pairs are distinct and their
			// sums of cubes are the same
                        if (a != c && c3 + d3 == a3 + b3) {
                            System.out.print((a3+b3) + " = ");
                            System.out.print(a + "^3 + " + b + "^3 = "); 
                            System.out.print(c + "^3 + " + d + "^3"); 
                            System.out.println();
                        }
                    }
                }
	    }
	}
	System.out.println("counter " + counter);
    }
}

