// CSE 143, Winter 2011, Marty Stepp
// A LinkedIntList object stores a list of integers using a series of linked
// node objects.  We will implement the same methods as ArrayIntList.
// (I have included several methods from the Section 8 handout to make the list
// a more complete class, though we did not write such methods in lecture.
// The key methods written in lecture were add, addSorted, get, and remove.)

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class LinkedIntList {
    private ListNode front;   // refers to first node in list (null if empty)

    // Constructs a new empty list.
    public LinkedIntList() {
        front = null;  // null front means empty
    }

    // Adds the given value to the end of this list.
    public void add(int value) {
        // add(size(), value);

        if (front == null) {
            // empty list
            front = new ListNode(value);
        } else {
            // non-empty list; walk to last node
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }

            // add new node at end of list
            current.next = new ListNode(value);
        }
    }

    // Inserts the given value into this list at the specified index.
    // Precondition: 0 <= index <= size
    // Throws a NullPointerException if index > size.
    public void add(int index, int value) {
        if (index == 0) {
            // insert at the front
            front = new ListNode(value, front);
        } else {
            // insert in middle/end; walk to node before the one to insert
            ListNode current = goTo(index - 1);
            ListNode newNode = new ListNode(value, current.next);
            current.next = newNode;

            // shorter version of the above code
            // current.next = new ListNode(value, current.next);
        }
    }

    // Adds the given element value to the list at the appropriate index
    // so that the list will remain in sorted order.
    // Precondition: The existing elements of the list are already sorted.
    // Postcondition: The new element value will be added, and the elements
    //                of the list will still be sorted.
    // This method may add the value in an improper location if the list's
    // existing elements are not sorted when this method is called.
    public void addSorted(int value) {
        if (front == null || front.data > value) {
            front = new ListNode(value, front);
        } else {
            ListNode current = front;
            while (current.next != null && current.next.data < value) {
                current = current.next;
            }
            current.next = new ListNode(value, current.next);
        }
    }

    // Returns the element at the specified index from the list.
    // Precondition: 0 <= index < size
    // Throws a NullPointerException if index >= size.
    public int get(int index) {
        ListNode current = goTo(index);
        return current.data;
    }

    // Returns the index of the first occurrence of the given value in the list,
    // or -1 if the value is not found in the list.
    public int indexOf(int value) {
        int index = 0;
        ListNode current = front;
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // Returns whether this list does not contain any elements.
    public boolean isEmpty() {
        return front == null;
    }

    // Removes the value at the specified index from this list.
    // Precondition: 0 <= index < size
    // Throws a NullPointerException if index > size.
    public void remove(int index) {
        if (index == 0) {
            // removing from the front
            front = front.next;
        } else {
            ListNode current = goTo(index - 1);
            current.next = current.next.next;   // deletes the node
        }
    }

    //CH16EX01//

    public int set(int index, int value) {
        ListNode current = goTo(index); //goTo-metoden kan findes nederst i klassen//
        current.data = value;
        return value;
    }
    //CH16EX02//

    public int max() {

        if (front == null){
            throw new NoSuchElementException();
    }

        ListNode current = front;
        int max = current.data;

        while (current.next != null) {

            if (current.data > max) {
                max = current.data;
            }

            current = current.next;
        }

        return max;
    }

    //CH16EX03//
    public boolean isSorted(){

        if (front == null) {
            return true;
        }

        ListNode current = front;

        while (current.next!= null){ //Fjern ".next" i denne linje, og afkommentér de 3 næste linjer for alternativ løsning

            /*if (current.next == null) {
                break;
            }*/

            if(current.data > current.next.data) {
                return false;
            }

            current = current.next;
        }

        return true;
    }

    //CH16EX04//
    public int lastIndexOf(int e){

        int noValue = -1;
        int i = 0;
        ListNode current = front;

        while (current!=null){

            if (current.data == e) {
                noValue = i;
            }
                ++i;


            if (current.next == null) {
                break;
            }

            current = current.next;
        }


        if (noValue == 0) {
            return 0;
        }

        if (noValue == -1) {
            return -1;
        }

        return noValue;
    }

    //CH16EX05//

    public int countDuplicates(){

        ListNode current = front;
        int count = 0;

        while (current!=null) {

            if (current.next == null){
                break;
        }

            if (current.data == current.next.data) {
                count++;
            }

            current = current.next;
        }

        return count;
    }

    //CH16EX06//
    public boolean hasTwoConsecutive(){

        ListNode current = front;

        while (current!=null){

            if (current.next == null) {
                break;
            }

            if (current.next.data - current.data == 1) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    //CH16EX07//

    public int deleteBack(){

        if(size()== 0) {
            throw new NoSuchElementException();
        }

        if(front==null) {
            throw new NoSuchElementException();
        }
        else {
            if (front.next == null) {
                int i = front.data;
                front = null;
                return i;
            }

            ListNode current = front.next;
            ListNode previous= front;

            while(current.next!=null){
                previous = current;
                current = current.next;
            }

            int i = current.data;
            previous.next = null;
            return i;
        }
    }

    //CH16EX08//
    public void switchPairs(){

       //////////////LAV MIG//////////////

    }

    //CH16EX09//

    public void stutter(){

        ListNode current = front;

        while (current!=null){
            current.next = new ListNode(current.data, current.next);
            current = current.next.next;
        }

    }

    //CH16EX10//
    //Printer intet//

    public void stretch(int n){
        ListNode p1 = front;
        ListNode p2 = front;
        if (n<=0){
            front = null;
            return;
        }
        if (n ==1	)
            return;
        while(p1!=null){
            for (int i = 0; i < n; ++i){
                p2.next = new ListNode(p1.data,p1.next);
                p2 = p2.next;
            }
            p1 = p1.next;
        }
        //if (current.next == null)
        //	current.next = new ListNode(current.data);
    }


    //CH16EX11//
public void compress(){

    //////////////LAV MIG//////////////

    }

    //CH16EX12//

    public void split(){

        ListNode current = front;
        int index = 0;

        if (size() <= 1	) {
            return;
        }

        while(current!=null){

            if ( current.data < 0){
                Integer e = current.data;
                remove(index);
                add(0,e);
            }

            index++;
            current=current.next;

        }
    }

    //CH16EX13//

    public void transferFrom(LinkedIntList list){

        ListNode other_current = list.front;

        while(other_current!=null){
            this.add(other_current.data);
            other_current = other_current.next;
        }

        list.front = null;
    }

    //CH16EX14//

    public void removeAll(int n){

        ListNode current = front;
        int index = 0;

        while (current!=null){
            if (current.data == n){
                remove(index);
                index--;
            }

            index++;
            current = current.next;
        }
    }

    //CH16EX15//

    public void notEquals(){

        //////////////LAV MIG//////////////

    }

    //CH16EX16//
    // KIG PÅ DENNE HER//
    public LinkedIntList removeEvens(){

        ListNode current = front;
        LinkedIntList newList = new LinkedIntList();
        int index = 0;
        while (current!=null){
            int evenNum = current.data;
            newList.add(evenNum);
            remove(index);
            ++index;
            if (current.next == null)
                break;

            current = current.next.next;
        }
        return newList;
    }

    //CH16EX17//

    public void removeRange(int a, int b){

        if (a <0 || b < 0) {
            throw new IllegalArgumentException();
        }

        int count = b - a + 1;

        for (int i = 0; i < count; i++) {
            remove(a);
        }
    }

    //CH16EX18//

    public void doubleList(){

        int size = size();

        for (int i = 0; i < size; i++){
            add(size() , get(i));
        }
    }

    //CH16EX19//

    public void rotate (){

        //////////////LAV MIG//////////////

    }

    //CH16EX20//

    public void shift (){

        //////////////LAV MIG//////////////

    }

    //CH16EX21//

    public void reverse(){

        ListNode current = front;
        Stack<Integer> stack = new Stack<Integer>();

        while (current!=null){
            stack.push(current.data);
            current = current.next;
        }

        front = null;
        while (!stack.isEmpty()) {
            add(stack.pop());
        }

    }


    // Returns the number of elements in this list.
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    // Returns a text representation of this list, such as "[42, -3, 17]"
    // or "[]" for an empty list.
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front.data;
            ListNode current = front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }
    // Returns a reference to the node object representing the index'th element
    // in the list.  Used as a helper by many of the public methods.
    private ListNode goTo(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}






