/*************************************************************************
 *  Compilation:  javac QueensToSAT.java
 *  Execution:    java QueensToSAT N [-d]
 *  
 *  An translate from N Queens to and from SAT.
   -- * -- * -- * -- * -- * -- * -- * -- * -- * -- * -- * -- * -- * -- 

 INSTRUCTIONS:

  - Typing
      java QueensToSat N 
    will generate the boolean satisfiability encoding of the 
    N Queens problem out to the terminal.

  - To redirect the output into a file called NQ.wff, type
      java QueensToSat N > NQ.wff

  - Now run your SAT solver on the file by typing
      java yourProgram < NQ.wff

    or

      java yourProgram NQ.wff

    if your program takes the name of the input file as a 
    commandline argument.

  - If you want to output the result of your program into a file, 
    say NQ.sol, type
      java yourProgram < NQ.wff > NQ.sol

  - To see the result nicely displayed, typed
      java QueensToSat N -d < NQ.sol

 *************************************************************************/

import java.util.Scanner;

/*************************************************************************
 *  A stack of clauses -- to help to print the clauses in
 *  reverse order.
 *************************************************************************/


class LinkedStackOfClauses {
    private int N;          // size of the stack
    private Node first;     // top of stack

    // helper Node class
    private class Node {
        private int[] clause;
        private Node next;

	public String toString() {
	    String s = "";
	    for (int i = 0; i < clause.length; i++) {
		s += clause[i] + " ";
	    }
	    return s;
	}

    }

    // is the stack empty?
    public boolean isEmpty() { return first == null; }

    // number of elements on the stack
    public int size() { return N; }


    // add an element to the stack
    public void push(int[] item) {
        Node oldfirst = first;
        first = new Node();
        first.clause = item;
        first.next = oldfirst;
        N++;
    }

    // delete and return the most recently added element
    public int[] pop() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        int[] item = first.clause;      // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }



    // test client
    public static void main(String[] args) {

        LinkedStackOfClauses s = new LinkedStackOfClauses();

	int[] item1 = {1,2,3};
	int[] item2 = {3,4};
	int[] item3 = {5,6,7,8};

	s.push(item1);
	s.push(item2);
	s.push(item3);

	while (!s.isEmpty()) {
	    int[] elem = s.pop();
	    for (int i = 0; i < elem.length; i++) 
		System.out.print(elem[i] + " ");
	    System.out.println();
	}

    } 
}

public class QueensToSAT {
    static int N;  // NxN chess board

    private static int toVar(int x, int y) {
	// encode (row,column) to a SAT proposition
	return x * N + y + 1;
    }

    private static int toX(int v) {
	// decode the row associated with the proposition v
	return (v - 1) / N;
    }

    private static int toY(int v) {
	// decode the column associated with the proposition v
	return (v - 1) % N;
    }

    public static void main(String args[]) {
	if (args.length < 1) {
	    System.out.println("Usage: QueenToSat N [-d]");
	    System.exit(0);
	}

        N = Integer.parseInt(args[0]);
	
	boolean decode = false;

	if (args.length == 2) 
	    decode = (args[1].equals("-d") ? true : false);

	if (! decode) 
	    encode();
	else
	    decode();

    }

    public static void encode() {

	LinkedStackOfClauses clauseSet = new LinkedStackOfClauses();

	int[] aClause;
	int clauseCount = 0;

	int CI = 0;
	// one queen in each row
	for (int i = 0; i < N; i++) {
	    aClause = new int[N+1];
	    CI = 0;
	    // i represents the queen
	    for (int j = 0; j < N; j++) {
		// j represents the column position
		// System.out.print(toVar(i,j) + " ");
		aClause[CI++] = toVar(i,j);
	    }
	    //System.out.println("0");
	    aClause[CI++] = 0;
	    clauseCount++;
	    clauseSet.push(aClause);

	    for (int j = 0; j < N; j++) {
		// j represents the column position
		// System.out.print(toVar(i,j) + " ");
		for (int k = j + 1; k < N; k++) {
		    aClause = new int[3];
		    aClause[0] = -toVar(i,j);
		    aClause[1] = -toVar(i,k);
		    aClause[2] = 0;
		}
		clauseCount++;
		clauseSet.push(aClause);
	    }
	}


	// no attacking queens
	for (int i = 0; i < N; i++) 
	    // i represents the first queen
	    for (int j = 0; j < N; j++) 
		// j represents the column position of i
		for (int m = i + 1; m < N; m++) 
		    // m represents the second queen
		    for (int k = 0; k < N; k++)
			// k represents the column position of m
			if (j == k) {  // no queens on the same column
			    // System.out.println(-toVar(i,j) + " " +  -toVar(m,k) + " 0");
			    aClause = new int[3];
			    aClause[0] = -toVar(i,j);
			    aClause[1] = -toVar(m,k);
			    aClause[2] = 0;
			    clauseSet.push(aClause);
			    clauseCount++;
			} 
			else if (Math.abs(k - j) == Math.abs(i - m)) {
			    // no queens on the same diagonal
			    // System.out.println(-toVar(i,j) + " " +  -toVar(m,k) + " 0");
			    aClause = new int[3];
			    aClause[0] = -toVar(i,j);
			    aClause[1] = -toVar(m,k);
			    aClause[2] = 0;
			    clauseSet.push(aClause);
			    clauseCount++;
			}
	
	System.out.println("p cnf " + N*N + " " + clauseCount);

	while (!clauseSet.isEmpty()) {
	    int[] elem = clauseSet.pop();
	    for (int i = 0; i < elem.length; i++) 
		System.out.print(elem[i] + " ");
	    System.out.println();
	}
    }

    public static void decode() {
	// assume that the variables are inputted in increasing order
	Scanner input = new Scanner(System.in);

	int row = 0;

	if (input.next().equals("SAT")) {
	    // satisfiable, so read the solution
	    for (int i = 0; i < N*N; i++) {
		//	    while (input.hasNext()) {
		int var = input.nextInt();
		if (var > 0) {  // a positive literal
		    int varX = toX(var);  

		    // verify that the variable corresponds to the right row
		    assert row++ == varX;

		    int varY = toY(var);
		    for (int j = 0; j < N; j++) {
			if (j == varY) System.out.print("Q ");
			else System.out.print("* ");
		    }
		    System.out.println();
		}
	    }
	}
    }
}