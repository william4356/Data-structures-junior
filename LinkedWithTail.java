public class LinkedWithTail<E>{
	private Node head;
	private Node tail;
	private int size;

	public LinkedWithTail(){
		head = null;
		tail = null;
		size = 0;
	}
	public void add(E thing){
		Node toAdd = new Node(thing);
		if(size == 0 || head == null){
			head = toAdd;
			tail = toAdd;
		}else if (size == 1 || head == tail) {
			head.next = toAdd;
			tail = toAdd;
		}else{
			tail.next = toAdd;
			tail = toAdd;
		}
		size++;
	}

	public void addToFront(E thing){
		Node toAdd = new Node(thing);
		if(size == 0 || head == null){
			head = toAdd;
			tail = toAdd;
		}
		else{
			toAdd.next = head;
			head = toAdd;
		}
		size++;
	}
	public String toString(){
		String toReturn= "";
		Node curr = head;
		while(curr!=null){
			toReturn+=curr.toString();
			curr = curr.next;
		}
		return toReturn;
	}

	public E remove(int index){
		if(index == 0){
			head = head.next
			if(head == null){
				tail = null;
			}
		} else{
			Node curr = head;
			int count = 0;
			while(count<index-1){
				count++;
				curr = curr.next;
			}
			curr.next = curr.next.next;;
			if (curr.next == null) {
				tail = curr
			}
			
			curr.setNext(curr.next)
		}

		size--;
	}

	class Node{
		E data;
		Node next;

		public Node(E data){
			this.data = data;
			next = null;
		}

		public String toString(){
			return data+", ";
		}
	}
}