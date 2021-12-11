import java.util.*;
public class DoublyLinkedList<E extends Comparable<E>>{
	private Node head;
	private Node tail;
	private int size;
	public DoublyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	// No loops used 
	public void add2end(E thing){
		Node toAdd = new Node(thing);
		if(size == 0 || head == null){
			head = toAdd;
			tail = toAdd;
			head.previous = null;
			tail.next = null;
		}else{
			tail.next = toAdd;
			toAdd.previous = tail;
			tail = toAdd;
			tail.next = null;
		}
		size++;
	}
	// No loops used 
	public void add2front(E thing){
		Node toAdd = new Node(thing);
		if(size == 0){
			head = toAdd;
			tail = toAdd;
			head.previous = null;
			tail.next = null;
		}
		else{
			toAdd.next = head;
			head.previous = toAdd;
			head = toAdd;
		}
		size++;
	}
	// should be O(n/2) 
	public boolean add2index(int index, E thing){
		if (size<index||index<0) {
			return false;
		}
		Node toAdd = new Node(thing);
		if (index == size) {
			add2end(thing);
		}
		else if (index == 0) {
			add2front(thing);
		}else{
			Node curr = head;
			int currCount = 0;
			if (index<size/2) {
				while (currCount<index-1) {
					currCount++;
					curr = curr.next;
				}
			}
			else{
				currCount = size-1;
				curr = tail;
				while (currCount<index-1) {
					currCount--;
					curr = curr.previous;
				}	
			}
				toAdd.next = curr.next;
				toAdd.previous = curr;
				curr.next.previous = toAdd;
				curr.next = toAdd;
				size++;
		}
		return true;
	}
	// O(n/2)
	public E remove(int index){
		if (index>=size||index<0) {
			return null; 
		}
		E toReturn = null;
		size--;		
		if(index == 0){
			toReturn = head.data;
			head = head.next;
			if(head!=null){
				head.previous = null;
			}
			return toReturn;
		}else if(index==size){
			toReturn = tail.data;
			tail = tail.previous;
			tail.next = null;
			return toReturn;
		}
		else if (size/2>index) {
			Node curr = head;
			int currCount = 0;
			while(currCount!=index-1){
				currCount++;
				curr = curr.next;
			}
			toReturn = curr.next.data;
			curr.next = curr.next.next;
			curr.next.previous = curr;
			return toReturn;
		}else{
			Node curr = tail;
			int currCount = size;
			while(currCount!=index-1){
				currCount--;
				curr = curr.previous;
			}
			toReturn = curr.next.data;			
			curr.next = curr.next.next;
			curr.next.previous = curr;
			return toReturn;
		}		
	}
//bouncy bouncy bouncy bounce
//puts the back in the front
	public void bounce(){ //
		Node end = tail;
		if(head!=tail){
			tail = tail.previous;
			end.next = head;
			head.previous = end;
			head = end;
			tail.next = null;
			head.previous = null;
		}else{
			System.out.println("Cant bounce that");
		}
	}
//is O(n/2) no matter what
	public E findMiddle(){
		Node curr = head;
		int currCount = 0;
		while(currCount<(size/2)){
			curr = curr.next;
			currCount++;
		}
		if (size%2 == 0) {
			if(curr.previous.data.compareTo(curr.data)< 0){
				return curr.previous.data;
			}
		}
		return curr.data;
	}
//O(n/2)
	public int indexOf(E thing){
		Node curr = head;
		Node curr2 = tail;
		int curr2Count = size-1;
		int currCount = 0;
		while(currCount<=curr2Count){
			if (curr.data.equals(thing)) {
				return currCount;
			}else if (curr2.data.equals(thing)) {
				return curr2Count;
			}
			curr = curr.next;
			curr2 = curr2.previous;
			currCount++;
			curr2Count--;
		}
		System.out.println("Error Not Found");
		return -1;	
	}
//should be O(n/2)
	public void addInOrder(E thing){
		Node curr = head;
		boolean done = false;
		Node curr2 = tail;
		Node toAdd = new Node(thing);
		if (toAdd.data.compareTo(head.data)<0) {
			add2front(thing);
		}
		else if(toAdd.data.compareTo(tail.data)>0){
			add2end(thing);
		}else{
			while(done == false){
				if(toAdd.data.compareTo(curr.next.data)<0){
					toAdd.next = curr.next;
					toAdd.previous = curr;
					curr.next.previous = toAdd;
					curr.next = toAdd;
					done = true;
				}else if((toAdd.data.compareTo(curr2.data)>0)){
					toAdd.next = curr2.next;
					toAdd.previous = curr2;
					curr2.next.previous = toAdd;
					curr2.next = toAdd;
					done = true;
				}
				curr = curr.next;
				curr2 = curr2.previous;
		}
		size++;
	}
}
//can be O(n)
public ArrayList<Integer> removeAll(E thing){
	ArrayList<Integer> toReturn= new ArrayList<Integer>();
	Node curr = tail;
	int indexCount = size-1;
	while(curr!=null){
		if (curr.data==(thing)) {
			remove(indexCount);
			toReturn.add(0,indexCount);
		}
		curr = curr.previous;
		indexCount--;
	}
	return toReturn;
}
 //can be O(n)
	public String toString(){
		String toReturn= "";
		Node curr = head;
		while(curr!=null){
			toReturn+=curr.toString();
			curr = curr.next;
		}
		return toReturn;
	}
//can be O(n)
	public String reverseToString(){
		String toReturn= "";
		Node curr = tail;
		while(curr!=null){
			toReturn+=curr.toString();
			curr = curr.previous;
		}
		return toReturn;
	}
	public int size(){
		return size;
	}
	class Node{
		E data;
		Node next;
		Node previous;

		public Node(E data){
			this.data = data;
		}
		public String toString(){
			return data+" ";
		}
	}
}
