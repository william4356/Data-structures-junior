import java.util.ArrayList;

/**New cool hip type of hashtables
 * @author Will Cummings
 * @version 1.0
 */
public class MDHT{
	SwitchArray table;

	/**creates a new Multi-dimentional Hash Table
	 * using a Switch array
	 */
	public MDHT(){
		table = new SwitchArray(null, 0);
	}

	/**packs the 2 params into a pair and 
	 * calls the private insert method which uses recursion
	 * @param key this takes in the key for the Pair you want to insert
	 * @param value this takes in the value for that Pair
	 * 
	 */
	public void insert(String key, Integer value){
		Pair data = new Pair(key,value);
		insert(data,table);		
	}

	/**using recursion this method inserts a pair into
	 * the MDHT
	 * @param data a pair that contains the data to be inserted
	 * @param currTable is the current SwitchArray in the recursive cycle
	 */
	private void insert(Pair data, SwitchArray currTable){
		if (currTable.isExpanded == false && currTable.iValue==null) { //checks the very first iValue
			currTable.iValue = data;
			return;
		}
		else if (!currTable.isExpanded) {// if there is a new colision
			SwitchArray[] x = new SwitchArray[currTable.primeArray[currTable.depth]];
			currTable.sA = x;
			Integer index= Math.abs(data.key.hashCode()) % currTable.sA.length;
			currTable.sA[index] = new SwitchArray(data, currTable.depth+1);
			currTable.isExpanded = true;
			insert(currTable.iValue,currTable);
			currTable.iValue = null;
		}
		else{ // if expanded
			Integer index= Math.abs(data.key.hashCode()) % currTable.sA.length;
			if (currTable.sA[index] == null){
				currTable.sA[index] = new SwitchArray(data, currTable.depth+1); //this updates ivalue in the constructor
			} else {
				currTable = currTable.sA[index];
				insert(data,currTable);//go one level deeper

			}
		}
	}

	/**calls the recursive private contains method
	 * @param key is the key to be checked if it exist
	 * @return a boolean that tells if it is contained in the MDHT
	 */
	public boolean contains(String key){
		return contains(key,table);
	}

	/**checks recursively if a key is in the MDHT
	 * @param key is the key that is checked
	 * @param curr is the current SwitchArray in the recursive check
	 * @return a boolean that tells if the current key is in the MDHT
	 */
	private boolean contains(String key, SwitchArray curr){
		if (!curr.isExpanded) { //checks the first iValue
			if (curr.iValue.key == key) {
			return true;
		}
			
		}
		Integer index= Math.abs(key.hashCode()) % curr.length;
		if(curr.sA[index]!=null&&curr.sA[index].isExpanded){//checks if it is one level deeper
			return contains(key,curr.sA[index]);// goes one level deeper
		}
		else if(curr.sA[index]!=null&&curr.sA[index].iValue!=null&&curr.sA[index].iValue.key==key){//checks if the key is there
			return true;
		}
		 return false;// returned if it is not in the spot it should be
	}

	/**calls the recursive private getValue method 
	 * if the key exist
	 * @param key is the key that is used to find the pair
	 * @return a Pair that tells the depth and value or an
	 * empty pair if the pair does not exist
	 */
	public Pair getValue(String key){
		if(!contains(key)){ //checks existance
			System.out.println("Does not exist");
			return new Pair(null,null);
		}
		return getValue(table,key);
	}
	/**gets the value and depth of a key recursively
	 * @param curr is the current SwitchArray
	 * @param key is the key that is used to find the pair
	 * @return a Pair that tells the depth and value
	 */
	private Pair getValue(SwitchArray curr,String key){
		if (!curr.isExpanded) {
			if (curr.iValue.key == key) {
				return curr.iValue;
			}	
		}
		Integer index= Math.abs(key.hashCode()) % curr.length;
		if(curr.sA[index].isExpanded){
			return getValue(curr.sA[index],key);
		}
		else if (curr.sA[index].iValue.key == key) {
			return curr.sA[index].iValue;
		}
		
		return new Pair(null,null);
	}

	/**calls the private delete method
	 * if it exist otherwise it tells the user that
	 * it does not exist
	 * @param key is the key that is passed through the private delete
	 * @return The pair returned by the recursive method
	*/
	public Pair delete(String key){
		if(!contains(key)){//checks if it exists
			System.out.println("Does Not Exist");
			return new Pair(null,null);
		}
		return delete(table,key);
	}

	/**deletes and returns a pair with a given key
	 * @param curr is the current Switch Array
	 * @param key is the key that is used to find the pair 
	 * that is going to be deleted
	 * @return the pair that was deleted
	*/
	private Pair delete(SwitchArray curr, String key){
		if (!curr.isExpanded) {//the very first iValue
			if (curr.iValue.key == key) {
				Pair temp = curr.iValue;
				curr.iValue = null;
				return temp;
			}	
		}
		Integer index= Math.abs(key.hashCode()) % curr.length;
		if(curr.sA[index].isExpanded){//checks if the key is one level deeper
			return delete(curr.sA[index],key);
		}
		else if (curr.sA[index].iValue.key == key) {//checks if the key is on the curent Switch Array
			Pair temp = curr.sA[index].iValue;
			curr.sA[index] = null;
			return temp;
		}
		
		return new Pair(null,null);
	}

	/** gets all of the data from the recursive printLayer
	 * then reformats it and returns it
	 * @return all data formated
	*/
  	public String printLayers(){
  		ArrayList<ArrayList<String>> aL = new ArrayList<ArrayList<String>>();
  		String toReturn= "";
  		for (int i = 0; i <10; i++){
  			aL.add(new ArrayList<String>()); // formats aL to correct size
  		}
  		printLayers(table, aL);//adds all data to aL

  		for (int i = 0;i<10 ;i++) {//reformats all data
  			toReturn+=("Layer: "+ i+"\n");
  			for(int j = 0;j<aL.get(i).size();j++){
  				toReturn+=(aL.get(i).get(j)+" ");
  			}
  			toReturn+=("\n______________________\n");

  		}
  		return toReturn;
  	} 

  	/**recursivly gets all of the data from a given SwitchArray and adds it to 
  	 * an ArrayList of Arraylists
  	 * @param curr is the current SwitchArray
  	 * @param aL is the arrayList all data is being added to
  	*/
  	private void printLayers(SwitchArray curr, ArrayList<ArrayList<String>> aL){
  		int currLayer = 0;
  		String s = "";
  		
  		for (int i = 0;i<curr.length;i++ ) {
  			if (curr.sA[i] == null){
  				//do nothing
  			}
  			else if (!curr.sA[i].isExpanded) {
  				currLayer = curr.sA[i].iValue.myDepth;
  				s = curr.sA[i].iValue.toString();
  				aL.get(currLayer).add(s);
  			}
  			else{
  				printLayers(curr.sA[i], aL);
  			}
  		}
  		
  	}
	/** makes the MDHT printable 
	 * @return all data formated
	*/
	public String toString(){
		return printLayers();
	}

	/**the key component that makes MDHT work
	 * gets around the generic array error
	*/
	class SwitchArray{
		int depth;
		int length;
		SwitchArray[] sA;
		Pair iValue;
		int[] primeArray = new int[]{5,7,11,13,17,19,23,29,31,37};
		boolean isExpanded = false;
		/**creates a new Switch Array
		 * @param iValue sets the iValue of the Switch Array
		 * @param depth is the curent depth of the Switch Array in the MDHT
		*/
		public SwitchArray(Pair iValue, int depth){
			this.iValue = iValue;
			this.depth = depth;
			length = primeArray[depth];
			//this.sA = new SwitchArray[length];
			//primeArray = [5,7,11,13,17,19,23,29,31,37];
			if (iValue != null)
				iValue.myDepth = depth;
			
		}
		
	}

	/**this is the way data is stored at an indivisual level
	*/
	class Pair{
		String key;
		Integer value;
		int myDepth;
		/**creates a new Pair
		 * @param key is the key of the Pair
		 * @param value is the value of the pair
		*/
		public Pair(String key, Integer value){
			this.key=key;
			this.value=value;
			myDepth = -1;
		}
		/**converts the data to readable data when it needs
		 *  to be printed out
		 * @return the key, value, and depth printed out
		 * in a read-able way
		*/
		public String toString(){
			return "( " + key + ", " + value + " | depth: " + myDepth + ")";
		}
	
	}
}
