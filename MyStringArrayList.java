public class MyStringArrayList{

	private int defaultLength = 10;
	private String[] arrList;

	public MyStringArrayList(){
		arrList = new String[defaultLength];
	}

	public MyStringArrayList(int length){
		arrList = new String[length];
	}

	public void add(String item){
		if(size() == arrList.length-1)
			resize();
		arrList[size()] = item;
	}

	public void add(int index,String item){
		String temp = "";
		if (index>size()) {
			System.out.println("Nice try silly");
		}
		if(size() == arrList.length-1)
			resize();
		for(int i = size()+1; i>index; i--){
			arrList[i] = arrList[i-1];
		}
		arrList[index] = item;
	}

	public int size(){
		int i = 0;
		while(arrList[i]!=null&&i<arrList.length){
			i++;
		}
		//System.out.println(i)
		return i;
	}

	public void resize(){
		String[] temp = arrList;
		arrList = new String[size()*2];
		int i = 0;
		for (String x: temp) {
			arrList[i] = x;
			i++;
		}
	}
	public String toString(){
		String arry = "";
		for(String x:arrList)
			if(x!=null)
				arry+=x;
		return arry;
	}
	public String get(int index){
		if(size()>index)
			return(arrList[index]);
		return("Nice try silly");
	}
	public String remove(int index){
		if(size()>index){
			String temp = arrList[index];
			for(int i = index; i<size();i++)
				arrList[i] = arrList[i+1];
			return(temp);
		}
		return("Nice try silly");
	}
	public void removeAll(String value){
		boolean removed = false;
		for (int i = size();i>=0 ;i-- ) {
			if (value.equals(arrList[i])){
				remove(i);
				removed= true;
			}
		}
		if (removed==false) 
			System.out.println("Nice try silly");
			
		
	}
	public String set(int index, String item){
		if(index < size()){
			String temp = arrList[index];
			arrList[index] = item;
			return temp;
		}
		return"nice try silly";
	}
}
