public class AVL{
	Node root;

	public AVL(){
		root = null;
	}

	public AVL(int initialData){
		root = new Node(initialData);
	}

	public void add(int data){
		if (root == null){
			root = new Node(data);
		}
		else{
			add(root, data);
		}
	}

	private void add(Node curr, int data){
		if (data < curr.data && curr.left == null) {
			curr.left = new Node(data);
		}else if (data > curr.data && curr.right == null) {
			curr.right = new Node(data);
		}else if (data< curr.data) {
			add(curr.left, data);
		}else{
			add(curr.right, data);
		}
		balance(curr);
		System.out.println("curr: "+curr.data+ ", bf: "+ curr.bf());
	}

	public String preOrder(){
		return preOrder(root);
	}

	private String preOrder(Node curr){
		if (curr == null){
			return"";
		}
		int n = curr.data;
		String left = preOrder(curr.left);
		String right = preOrder(curr.right);
		return n + ", "+ left +right;
	}	
	
	public void balance(Node top){
		int bf = top.bf();
		Node parent = findParent(top);
		if (bf > 1){
			int leftbf = top.left.bf();
			if(leftbf > 0){
				top = right(top);
			}
			else{
				top.left = left(top.left);
				top = right(top);
			}
		}else if (bf< -1) {
			int rightbf = top.right.bf();
			if (rightbf > 0) {
					top.right = right(top.right);
				}
			top = left(top);	
		}
		if(parent == null){
			root = top;
		}else if (top.data < parent.data) {
			parent.left = top;
		}else{
			parent.right = top;
		}
	}


	private Node findParent(Node me, Node curr){
		if(me.data == curr.data){
			return null;
		}
		if(me.data< curr.data){
			if(curr.left.data == me.data){
				return curr;
			}
			else{
				return findParent(me, curr.left);
			}
		}else{
			if(curr.right.data == me.data){
				return curr;
			}
			else{
				return findParent(me, curr.right);
			}
		}
	}


	private Node right(Node top){
		Node mid = top.left;
		top.left = mid.right;
		mid.right = top;
		return mid;
	}

	private Node left(Node top){
		Node mid = top.right;
		top.right = mid.left;
		mid.left = top;
		return mid;
	}


	class Node{
		int data;
		Node left;
		Node right;

		public Node(int data){
			this.data = data;
			left = null;
			right  = null;
		}

		public int height(){
			int heightLeft = (left == null)? 0: left.height();
			int heightRight = (right == null)? 0: right.height();

			int myHeight = Math.max(heightLeft, heightRight)+1;
			return myHeight;
		}

		public int bf(){
			int heightLeft = (left == null)? 0: left.height();
			int heightRight = (right == null)? 0: right.height();

			return heightLeft - heightRight;
		}
	}
}