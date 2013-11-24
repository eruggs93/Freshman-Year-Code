/*************************************************************************
 *  Compilation:  javac LinkedList.java
 *  Execution:    java LinkedList
 *
 *  A generic linkedlist class to be used by HashTable.  
 *************************************************************************/


public class LinkedList<Key extends Comparable<Key>,Item> {
    private Node first;     // top of stack

    // helper linked list class
    private class Node {
        private Key key;
        private Item item;
        private Node next;
    }

    public LinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Item lookup(Key key) {
	// returns the item associated with the key, if it exists
	// returns null otherwise
	Node ptr, prev;

	ptr = first;
	while (ptr != null) {
	    if (ptr.key.compareTo(key) == 0) 
		return ptr.item;
	    ptr = ptr.next;
	}
	return null;  // indicates not found
    }

    public void insert(Key key, Item item) {
	// inserts the key, item pair; if the key already exists
	// replace its value with item
	this.remove(key);
        Node oldfirst = first;
        first = new Node();
	first.key = key;
        first.item = item;
        first.next = oldfirst;
    }

    public Item remove(Key key) {
	// removes the key and its associated item if it exists
	// returns the item; returns null if the key is not found
	Node ptr = first;
	Node prev = null;
	while (ptr != null) {
	    if (ptr.key.compareTo(key) == 0) { // found it 
		if (prev == null) {
		    first = ptr.next;
		}
		else {
		    prev.next = ptr.next;
		}
		return ptr.item;
	    }
	    prev = ptr;
	    ptr = ptr.next;
	}
        return null;
    }

    /**
     * Return string representation.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
	Node ptr = first;
	// reference chasing
	while (ptr != null) {
            s.append("(" + ptr.key + ":" + ptr.item + ") ");
	    ptr = ptr.next;
	}
	s.append("\n");
        return s.toString();
    }

    /**
     * A test client.
     */
    public static void main(String[] args) {
        LinkedList<String,Integer> l = new LinkedList<String,Integer>();
        l.insert("Hello",1);
        l.insert("World",2);
        l.insert("how",3);
        l.insert("are",4);
        l.insert("you",5);

	System.out.println(l.lookup("are"));

	System.out.println(l);

	l.remove("how");

	System.out.println(l);

	l.remove("do");

	System.out.println(l);

    }
}