public class BinarySearchTreeHW implements BSTMethods{
  private Integer[] tree; //public for tester
	public BinarySearchTreeHW(){
    tree = new Integer[8];
  }

  /**
      * Checks whether the tree is empty
      * @return true if the tree is empty, false otherwise
      */
	public boolean isEmpty(){
		if(tree[1]==null){
      return true;
    }
    return false;
	}

	/**
      * Makes the array twice as large and moves all the data
      * into the bigger array.
      */
	public void expand(){
		Integer[] tree2 =new Integer[tree.length*2];
    for(int i = 0; i<tree.length;i++){
      tree2[i] = tree[i];
    }
    tree = tree2;
	}

      /**
      * Inserts a new number into the tree
      * @param a number to insert
      */
    public void insert(int data){
      if (tree[1]==null) {
        tree[0] = 1;
        tree[1] = data;
      }else{
        tree[0]++;
        int k = 1;
      while(tree[k]!=null){
        if(k*2>=tree.length){
          expand();
        }   
        if(tree[k]>data){
          k*=2;
        }else{
         k*=2;
         k+=1;
        }
    }
    tree[k] = data;
    }
  }
      /**
      * Compiles a pre-order traversal as a string
      * @return a pre-order traversal as a string
      */
    public String preOrder(){
    	return preOrder(1);    	
    }
    private String preOrder(int k){
      if (k>=tree.length||tree[k]==null) {
        return "";
      }  
      String left = preOrder(k*2);
      String right = preOrder(k*2+1);
      return(tree[k]+", "+left +right);
    }
      /**
      * Compiles a post-order traversal as a string
      * @return a post-order traversal as a string
      */
    public String postOrder(){
    	return postOrder(1);    	
    }
    private String postOrder(int k){
      if (k>=tree.length||tree[k]==null) {
        return "";
      }  
      String left = postOrder(k*2);
      String right = postOrder(k*2+1);
      return(left +right+tree[k]+", ");
    }
  


      /**
      * Compiles a in-order traversal as a string
      * @return a in-order traversal as a string
      */
    public String inOrder(){
    	return inOrder(1);
    }
    private String inOrder(int k){
      if (k>=tree.length||tree[k]==null) {
        return "";
      }  
      String left = inOrder(k*2);
      String right = inOrder(k*2+1);
      return(left+ tree[k]+", "+ right);
    }


	/**
      * Checks whether a number is in the tree
      * @param the number to check
      * @return true if the number is in the tree, false otherwise
      */
	public boolean contains(int data){
		return contains(data,1);
	}
  private boolean contains(int data, int k){
    if ( k>=tree.length||tree[k] == null) {
      return false;
    }
    if (tree[k]== data) {
      return true;
    }
    else if (tree[k]>data) {
      return contains(data, k*2);
    }else{
      return contains(data, k*2+1);
    }
    
  }
	/**
      * Finds the index of a particular number in the tree.
      * If it does not exist, return -1
      * @param the number to look for
      * @return the index where it is located, or -1
      */
	public int find(int data){
		return find(data,1);
	}
  private int find(int data, int k){
    if ( k>=tree.length||tree[k] == null) {
      return -1;
    }
    if (tree[k]== data) {
      return k;
    }
    else if (tree[k]>data) {
      return find(data, k*2);
    }else{
      return find(data, k*2+1);
    }
  }
	/**
      * Finds the minimum of the tree
      * @return the minimum value in the tree
      */
	public int min(){
    if(tree[0]==null||tree[0]==0){
      return Integer.MAX_VALUE;
    }
    else{
		return min(1);
  }

	}
  private int min(int k){
    if (2*k>=tree.length||tree[2*k]==null) {
        return tree[k];
      }
      return min(2*k);
  }

	/**
      * Finds the maximum of the tree
      * @return the maximum value in the tree
      */
	public int max(){
    if(tree[0]==null||tree[0]==0){
      return Integer.MIN_VALUE;
    }
    else{
      return max(1);
    }
	}
  private int max(int k){
    if ((2*k+1)>=tree.length||tree[2*k+1]==null) {
        return tree[k];
      }
      return max(2*k+1);
  }


	/**
      * Deletes a number from the tree. DO NOT iterate
      * over the array to find the data. Make sure to 
      * maintain the structure of the tree.
      * @param the number to delete
      */
	public void delete(int data){
    if (contains(data)) {
      delete(data, 1);
      tree[0]--;
    }
	  
    
	}
  private void delete(int data, int k){
    if (contains(data)) {
      
      if(tree.length<=k*2+1||(tree[k*2] == null&& tree[k*2+1] == null)){
        tree[k] = null;
      }
      else if (tree[k]==data&&!(tree[k*2] == null|| tree[k*2+1] == null)) {
        int toDelete = max(k);
        delete(max(k),find(max(k)));
        tree[k] = toDelete;
      }
      else if (tree[k] == data && tree[k*2+1] == null&&!(tree[k*2] == null)) {
        int toDelete = max(k*2);
        delete(max(k*2),k);
        tree[k] = toDelete;
      }
      else if (tree[k] == data && tree[k*2] == null&&!(tree[k*2+1] == null)) {
        int toDelete = min(k*2+1);
        delete(min(k*2+1),k);
        tree[k] = toDelete;
      }else if (tree[k]>data) {
        delete(data, k*2);
      }else{
        delete(data, k*2+1);
      }
    }
	}
  public String toString(){
    String toReturn = "";
    for(int x = 0; x<tree.length;x++) toReturn+=tree[x] + ", ";
    return toReturn;
  }
}
