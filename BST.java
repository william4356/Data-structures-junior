public class BST{
	private Node root;

	public BST(){
		root = null;
	}

	public boolean addWithLoop(int thing){
		if(root == null){
			root = new Node(thing);
			return true;
		}else{
			Node curr = root;
			boolean added = false;
			while(!added){
				if(thing < curr.data){
					if(curr.left == null){
						curr.left = new Node(thing);
						added = true;
					}
					else {
						curr = curr.left;
					}
				}else if (thing> curr.data) {
					if(curr.right == null){
						curr.right = new Node(thing);
						added = true;
					}else{
						curr=curr.right;
					}
				}else{
					return false;
				}
			}
			return true;
		}

	}
	public boolean add(int thing){
		if(root == null){
			root = new Node(thing);
			return true;
		}else{
			return add(root,thing);
		}
	}
	private boolean add(Node curr,int thing){
		if(thing<curr.data&&curr.left==null){
			curr.left = new Node(thing);
			return true;
		}else if (thing>curr.data&&curr.right==null) {
			curr.right = new Node(thing);
			return true;
		}else if (thing<curr.data) {
			return add(curr.left,thing);
		}else if (thing>curr.data) {
			return add(curr.right,thing);
		}	
		else {
			return false;
		}

	}

	public boolean contains(int num){
		return contains(num, root);
	}
	private boolean contains(int num, Node curr){
		if(curr == null){
			return false;
		}
		if(curr.data == num){
			return true;
		}else if(num>curr.data){
			return contains(num, curr.right);
		}else{
			return contains(num,curr.left);
		}
	}
	public boolean containsWithLoop(int num){
		Node curr = root;
		while(curr!=null){
			if(curr == null){
			return false;
		}
			if (num==curr.data) {
				return true;
			}
			else if (num>curr.data) {
				curr = curr.right;
			}
			else if (num<curr.data) {
				curr = curr.left;
			}
		}
		return false;
	}
	public Node findParent(int thing){
		return findParent(thing,root);
	}
	public int findParentInt(int thing){
		return findParent(thing,root).data;
	}
	public Node findParent(int thing, Node curr){
		if (root.data == thing) {
			return null;
		}
		else if ((curr.right!=null&&curr.right.data == thing) || (curr.left!=null&&curr.left.data == thing)) {
			return curr;
		}
		else if (curr.data>thing) {
			return(findParent(thing,curr.left));
		} else if (curr.data<thing){
			return(findParent(thing,curr.right));
		}
		else{ 
			return null;
		}
	}
	public int findMin(){
		Node curr = root;
		if(curr == null){
			return Integer.MAX_VALUE;
		}
		while(curr.left!=null){
			curr = curr.left;
		}
		return curr.data;
	}
	public int findMax(){
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		return findMax(root);
	}
	private int findMax(Node curr){
		while(curr.right!=null){
			return findMax(curr.right);
		}
		return curr.data;
	}
	public String preOrder(){
		return preOrder(root);
	}
	private String preOrder(Node curr){
		if(curr == null){
			return"";
		}else{
			int n = curr.data;
			String left = preOrder(curr.left);
			String right = preOrder(curr.right);
			return(n+", "+ left+ right);
		}

	}
	public String postOrder(){
		return postOrder(root);
	}
	private String postOrder(Node curr){
		if(curr == null){
			return"";
		}else if(curr.right == null&&curr.left == null){
			return curr.data+", ";
		}else{
			int n = curr.data;
			String left = postOrder(curr.left);
			String right = postOrder(curr.right);
			return(left+ right+ n + ", ");
		}

	}

	public String inOrder(){
		return inOrder(root);
	}
	private String inOrder(Node curr){
		if(curr==null){
			return "";
		}else{
			int n = curr.data;
			String left = inOrder(curr.left);
			String right = inOrder(curr.right);
			return(left+ n+", "+right);
		}

	}
	public void remove(int thing){
		if(!contains(thing))return;
		if (root.data == thing && root.isLeaf()) {
			root = null;
			return;
		}else if(root.data == thing && root.oneChild()){
			if (root.right!=null) {
				root = root.right;
			}else{
				root = root.left;
			}
			return;
		}
		Node me  = find(thing,root);
		if(me.isLeaf()){
			Node parent = findParent(thing);
			if(thing > parent.data){
				parent.right = null;
			}else{
				parent.left = null;
			}

		}else if(me.oneChild()){
			Node parent = findParent(thing);
			if(parent.left == me){
				if(me.left!=null){
					parent.left = me.left;
				}else{
					parent.left = me.right;
				}

			}else{
				if(me.left !=null){
					parent.right = me.left;
				}else{
					parent.right = me.right;
				}
			}
		}else{
			int pred = findMax(me.left);
			remove(pred);
			me.data = pred;
		}

	}


	private Node find(int thing, Node curr){
		if (curr.data == thing) {
		 	return curr;
		 } else{
		 	if(thing>curr.data){
		 		curr = curr.right;
		 	}else{
		 		curr=curr.left;
		 	}
		 	return find(thing, curr);
		 }
	}

	class Node{
		int data;
		Node left, right;

		public Node(int data){
			this.data = data;
			left = null;
			right = null;
		}

		public boolean isLeaf(){
			return left == null && right == null;
		}
		public boolean oneChild(){
			return (right == null)!=(left==null);
		}
	}

}