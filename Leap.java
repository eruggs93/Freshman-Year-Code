/*************************************************************************
 * Eric Ruggieri
 * assign 1
 * Date submitted ______
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING CODE 
 * WRITTEN BY OTHER STUDENTS. -ERIC RUGGIERI-
 *
 *  Compilation:  javac Leap.java
 *  Execution:    java Leap <year>
 *  
 *  Purpose:      
 *  Prints true if N corresponds to a leap year, and false otherwise.
 *  Assumes N >= 1582, corresponding to a year in the Gregorian calendar.
 *  Rule: a given year is a leap year if it is divisible by 4 and not by 
 *    100 unless it is divisible by 100 
 
*************************************************************************/

public class Leap { 
    public static void main(String[] args) { 
        int year = Integer.parseInt(args[0]);

	// divisible by 4 and not by 100 -> true
	if ((year % 4 == 0) && (year % 100 != 0))
	    System.out.println(true);

	// divisible by 4 but also by 100 -> true
	else if ((year % 4 == 0) && (year % 100 == 0)) 
	    System.out.println(true);

	// divisible by 4, by 100, and by 400 -> true
	else if (((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)))
	    System.out.println(true);

	// none of the above -> false
	else 
	    System.out.println(false);

    }
}
