package cwang_P3X.cwang_P3X;

import java.util.EmptyStackException;   // Needed for EmptyStackException object

/**
 * This program demonstrates how to use a stack class based on linked lists.
 *
 * @author Jason Wang
 * @version 66.0
 */
public class Stack <T> {

    /**
     * The Node class is used to implement the linked list.
     */
    private class Node{
        T value;            // The variable that holds the list element
        Node next;          // Link to next node in the list

        /**
         * Constructor.
         * @param val The variable that holds the list element
         * @param n Link to next node in the list
         */
        Node (T val, Node n){
            value = val;
            next = n;
        }
    }

    private Node top;    // Reference to the top node in the list

    /**
     * Constructor.
     */
    public Stack (){

        top = null;
    }

    /**
     * The empty method checks whether the list is empty.
     *
     * @return ture for empty, otherwise false.
     */
    public boolean empty(){
        return top == null;
    }

    /**
     * The push method adds a new item to the stack.
     *
     * @param val The item to be pushed onto the stack.
     */
    public void push(T val){
        top = new Node(val, top);
    }

    /**
     * The pop method removes the item at the top of the stack.
     *
     * @return The item at the top of the stack.
     * @exception EmptyStackException When the stack is empty.
     */
    public T pop(){

        if(empty())
            throw new EmptyStackException();
        else{
            T retNum = top.value;
            top = top.next;
            return retNum;
        }
    }

    /**
     * The peek method returns the top item of the stack.
     *
     * @return The item at the top of the stack.
     * @exception EmptyStackException When the stack is empty.
     */
    public T peek(){

        if(empty())
            throw new EmptyStackException();
        else
            return top.value;
    }

    /**
     * The copy method copies an old stack to a new stack.
     *
     * @return the copied stack
     */
    public Stack <T> copy () {

        // Create a new Stack abject
        Stack <T> tmpCopyStack = new Stack<>();
        Node tmp1 = top;

        // Push element to the Stack tmpStack
        while (tmp1 != null) {

            tmpCopyStack.push(tmp1.value);

            tmp1 = tmp1.next;
        }

        // Create new Stack abject
        Stack <T> copyStack = new Stack<>();
        Node tmp2 = tmpCopyStack.top;

        // Push element to the Stack copyStack
        while (tmp2 != null) {

            copyStack.push(tmp2.value);

            tmp2 = tmp2.next;
        }

        return copyStack;
    }

    /**
     * The equal method check if two objects are same.
     *
     * @param object The other object.
     * @return Whether two objects are same.
     */
    public boolean equal (Stack <T> object) {

        Node tmp = top;
        Node tmp2 = object.top;

        while (tmp != null && tmp2 != null) {

            if (tmp.value != tmp2.value)
                return false;

            tmp = tmp.next;
            tmp2 = tmp2.next;
        }

        return tmp == null && tmp2 == null;
    }

    /**
     * String representation of the stack contents.
     *
     * @return the string representation
     */
    public String toString () {

        StringBuilder str = new StringBuilder();

        Node tmp = top;

        while (tmp != null){

            str.append(tmp.value).append("\n");

            tmp = tmp.next;
        }
        return str.toString();
    }

    /**
     * The size method return the size of the Stack.
     *
     * @return The size of the Stack.
     */
    public int size() {
        int count = 0;
        Node p = top;

        while (p != null){
            count++;
            p = p.next;
        }

        return count;
    }
}