/*************************************************************************
 *  Compilation:  javac QuickSortLong.java
 *  Execution:    java QuickSortLong N
 *  
 *  Purpose:      to demonstrates the recursive quicksort algorithm
 *************************************************************************/

class QuickSortLong {
    public Comparable[] theArray;          // the array to be sorted

    public QuickSortLong(Comparable[] given) {         
	theArray = given;
    }

    public void sort() {
	sort(0, theArray.length - 1);  // sort the entire array
    }

    public void sort(int left, int right) {
	// pre: left and right between 0 and nElems-1
	// post: the portion of array between left and right sorted

	if(right-left <= 0)              // if size <= 1,
	    return;                      //    already sorted
	else {                           // size is 2 or larger
	    Comparable pivot = theArray[right];// choose the rightmost item
	                                 // as the pivot 
	    int partition = partitionIt(left, right, pivot);
	    sort(left, partition-1);   // sort left side
	    sort(partition+1, right);  // sort right side
	}
    }  

    public int partitionIt(int left, int right, Comparable pivot) {
	// pre: left <= pivot <= right
	// post: left <= partition <= right, and
	//       all elements between left and partition <= pivot
	//       all elements between partition and right >= pivot
	int leftPtr = left - 1;  
	int rightPtr = right;    
	while(true) {
	    while( theArray[++leftPtr].compareTo( pivot ) < 0) ; // keep looking
	    // after the loop, a bigger or equal element found

	    while(rightPtr > 0 && theArray[--rightPtr].compareTo(pivot) > 0) ; // keep looking
	    // after the loop, a smaller or equal element found

	    if(leftPtr >= rightPtr)       // if pointers cross,
		break;                    //    partition done
	    else                          // not crossed, so
		swap(leftPtr, rightPtr);  //    swap elements
	}  
	swap(leftPtr, right);           // restore pivot
	return leftPtr;                 // return pivot location
    }  // end partitionIt()

    public void swap(int dex1, int dex2) { // swap two array elements
	Comparable temp = theArray[dex1];        // A into temp
	theArray[dex1] = theArray[dex2];   // B into A
	theArray[dex2] = temp;             // temp into B
    }

   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private boolean isSorted() {
        for (int i = 1; i < theArray.length; i++)
            if (theArray[i].compareTo(theArray[i-1]) < 0) return false;
        return true;
    }

   /***********************************************************************
    *  Show results
    ***********************************************************************/
    public void show() {
        for (int i = 0; i < theArray.length; i++)
            System.out.println(theArray[i]);
    }


    // for unit testing the sorting algorithm 

    public static void main(String[] args) {

	if (args.length < 1) {
	    System.out.println("Usage: java MergeSort N");
	    System.exit(0);
	}

        int N = Integer.parseInt(args[0]);

        // generate N random real numbers between 0 and 1
        Comparable[] a = new Comparable[N];  

        for (int i = 0; i < N; i++)
            a[i] = (Comparable) (Math.random() * N * 2);

	// create an instance of the QuickSortLong data type
	QuickSortLong myQuick = new QuickSortLong(a);

        // sort them
        myQuick.sort();
        System.out.println(myQuick.isSorted());

        if (N < 100) myQuick.show();
    }
} 
