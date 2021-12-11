public class LinkedList<E>{
	private Node<E> head;

	public LinkedList(){
		head = null;
	}

	public void add(E data){
		// 0 case
		if (head == null) {
			head = new Node<E>(data);
		}
		else if (head.getNext() == null) {
			Node<E> toAdd = new Node<E>(data);
			head.setNext(toAdd);
		}else{
			Node<E> curr = head;
			while(curr.getNext()!=null){
				curr = curr.getNext();
			}
			Node<E> toAdd = new Node<E>(data);
			curr.setNext(toAdd);

		}
	}
	//precondition: only valid indexes
	public E get(int index){
		Node<E> curr = head;
		int currCount = 0;
		while(currCount< index){
			currCount++;
			curr= curr.getNext();
		}
		return curr.getData();
	}

	//precondition: only valid indexes
	public E remove(int index){
		if(index == 0){
			E toReturn = head.getData();
			head = head.getNext();
			return toReturn;
		}else{
			int currCount = 0;
			Node<E> curr = head;
			while(currCount< index){
				currCount++;
				curr= curr.getNext();
			}
			E toReturn = curr.getNext().getData();
			Node<E> newNext = curr.getNext().getNext();
			curr.setNext(newNext); 
			return toReturn;
		}
	}

	public void removeFirst(E thing){
		if(head.getData().equals(thing)){
			head = head.getNext();
		}else{
			Node<E> curr = head;
			while(!curr.getNext().getData().equals(thing))
				curr =curr.getNext();
			Node<E> newNext = curr.getNext().getNext();
			curr.setNext(newNext);
				
		
		}

	}

	public void addToIndex(int index, E thing){
		Node<E> toAdd = new Node<E>(thing);

		if (index>0) {
			Node<E> curr = head;
			int currCount = 0;
			while(currCount< index-1){
				currCount++;
				curr = curr.getNext();

			}
			toAdd.setNext(curr.getNext());
			curr.setNext(toAdd);
		}else{
			toAdd.setNext(head);
			head = toAdd;
		}
	}


	public String toString(){
		if(head==null){
			return "";
		}
		else if (head.getNext()==null) {
			return head.getData().toString();
		}
		else{
			Node<E> curr = head;
			String toReturn = "";
			while (curr!=null) {
				toReturn+= curr.getData() + ", ";
				curr = curr.getNext();
			}
			return toReturn;
		}
	}
	public int size(){
		Node<E> curr = head;
		int toReturn = 0;
		while(true){
			toReturn++;
			if (curr.getNext()==null) {
				return toReturn;
			}
			curr = curr.getNext();
		}
	}
}