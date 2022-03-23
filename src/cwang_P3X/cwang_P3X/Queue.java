package cwang_P3X.cwang_P3X;

import java.util.EmptyStackException;   // Needed for EmptyStackException
/**
 * This class implements a queue based on linked list to display the patterns.
 *
 * @author Jason Wang
 * @version 34.0
 */
public class Queue <T> {

    /**
     * This Node class stores a list element and two references to the next and
     * prev node.
     */
    private class Node {

        public T cardNumber;

        public Node next, prev;

        /**
         * Constructor.
         *
         * @param cardNumber The int element to be stored in the node.
         * @param prev The reference to the predecessor node.
         * @param next The reference to the successor node.
         */
        public Node(T cardNumber, Node prev, Node next) {
            this.cardNumber = cardNumber;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;      // Head of the list
    private Node tail;      // Last element on the list

    /**
     * Constructor.
     */
    public Queue (){

        head = null;
        tail = null;
    }
		
    /**
     * Enqueue all the elements from another queue onto this queue.
     * @param other the queue with the elements to enqueue.
     */

	public void append(Queue <T> other) {

        for (Node p = other.head; p != null; p = p.next)
            enqueue(p.cardNumber);
	}


    /**
     * Make a deep copy of the queue. This means that you will create a new
     * RendorQueue, and copy the contents of the current queue to the new queue.
     * This means you have to remove from the current queue and add to the new
     * queue. This likely will mean starting from the head and getting each
     * value from the node and calling enqueue on the new queue that you will
     * return.
     * @return the new queue you created.
     */

	public Queue <T> copy() {
        Queue <T> theCopy = new Queue<> ();

        Node tmp = head;        // Assign head to tmp

        // Use tmp to walk down the linked list
        while (tmp != null) {
            theCopy.enqueue(tmp.cardNumber);
            tmp = tmp.next;
        }

        // Return theCopy
		return theCopy;
	}


    /**
     * The equal method check if two Queue objects are same.
     * @param object Another Queue object
     * @return Whether they are same
     */
    public boolean equal (Queue <T> object) {
	    Node tmp = head;
	    Node tmpObject = object.head;

        while (tmp != null && tmpObject != null) {

            if (!tmp.cardNumber.equals(tmpObject.cardNumber))
                return false;

            tmp = tmp.next;
            tmpObject = tmpObject.next;
        }

        return tmp == null && tmpObject == null;
    }


    /**
     * The peek method returns data at the head of the queue.
     *
     * @return Item at head of queue.
     */
    public T peek() {

	    if (empty())
	        throw new EmptyStackException();
	    else
	        return head.cardNumber;

    }

    /**
     * Remove an element from the front of the queue (oldest element in the 
     * queue).
     * @return oldest element's payload
     */
	public T dequeue() {

        if (head == null) {
            throw new IllegalArgumentException("cannot dequeue from "
                    + "empty queue");
        }
        
        T cardNumber = head.cardNumber;

        head = head.next;

        if (head == null)
            tail = null;

        // Return render;
        return cardNumber;
	}
	
    /**
     * Add an element to the end of the queue.
     * @param cardNumber new element's payload
     */	
	public void enqueue(T cardNumber) {
		// PLEASE CODE THE REST

        // Enqueue render to a node
        if (tail != null){
            tail.next = new Node(cardNumber, tail, null);
            tail = tail.next;
        }
        else {
            tail = new Node(cardNumber, null, null);
            head = tail;
        }
	}

    /**
     * String representation of the queue contents.
     * Uses traditional notation for the render commands.
     * @return the string representation
     */
    public String toString() {

        StringBuilder s = new StringBuilder();

        for (Node p = head; p != null; p = p.next){

            s.append("| ").append(p.cardNumber).append(" ");
        }

        s.append("|");

        return s.toString();
    }
	
	/**
     * Reports if the queue has any elements.
     * @return true if the queue has zero elements, false otherwise
     */	
	public boolean empty() {
		// TODO Auto-generated method stub
        return head == null;
	}
}
