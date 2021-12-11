public class ExternalChaining{
	Pair[] arr;
	public ExternalChaining(){
		arr= new Pair[5];
	}
	public void insert(String key, Integer value){
		Pair toAdd= new Pair(key, value);
		Integer index= Math.abs(key.hashCode()) % arr.length;
		int count=0;
		if(arr[index]==null){
			arr[index]=toAdd;
			return;
			}
		Pair curr= arr[index];
		while (count<2){
			if(curr.next==null){
				curr.next=toAdd;
				return;
			}
			curr=curr.next;
			count++;
		}
		resize();
		insert(key,value);

	}
	public String toString(){
		String toReturn="";
		for(int i=0; i<arr.length; i++){
			if(arr[i]==null){
				toReturn += "\n";
			}
			else{
				Pair curr= arr[i];
				while(curr!=null){
					toReturn+= curr.toString()+ ", ";
					curr=curr.next;
				}
				toReturn += "\n";
			}

		}
		return toReturn;
	}
	public void resize(){
		Pair[] temp= new Pair[arr.length*2];
		Pair[] temp2=arr;
		arr= temp;
		for(int i=0; i< temp2.length; i++){
			if(temp2[i]!=null){
			insert(temp2[i].key, temp2[i].value);
				if(temp2[i].next!=null){
					insert(temp2[i].next.key, temp2[i].next.value);			
					if(temp2[i].next.next!=null){
						insert(temp2[i].next.next.key, temp2[i].next.next.value);
					}
				}
			}
		}

	}
	public boolean contains(String key, Integer value){
		Boolean toReturn=false;
		Integer index= Math.abs(key.hashCode()) % arr.length;
		if(arr[index]==null){
			return false;
		}else{
			Pair curr= arr[index];
			while(curr!=null){
				if(curr.key.equals(key) && curr.value==value){
					return true;
				}
				curr=curr.next;
			}
			return false;
		}

	}
	public boolean containsKey(String key){
		Boolean toReturn=false;
		Integer index= Math.abs(key.hashCode()) % arr.length;
		if(arr[index]==null){
			return false;
		}else{
			Pair curr= arr[index];
			while(curr!=null){
				if(curr.key.equals(key)){
					return true;
				}
				curr=curr.next;
			}
			return false;
		}

	}
	public boolean containsValue(Integer value){
		for(int i=0; i<arr.length; i++){
			if(arr[i]!=null){
				Pair curr= arr[i];
				while(curr!=null){
					if(curr.value==value){
						return true;
					}
					curr=curr.next;
				}

			}
		}
		return false;
	}
	public int find(String key, Integer value){
		if(!contains(key,value)){
			return -1;
		}
		else{
			return (Math.abs(key.hashCode()) % arr.length);
		}
	}
	public void delete(String key, Integer value){
		Boolean toReturn=false;
		Integer index= Math.abs(key.hashCode()) % arr.length;
		if(arr[index]==null){
			return;
		}else{
			Pair curr= arr[index];
			if(curr.key.equals(key) && curr.value==value && curr.next==null){
				arr[index]=null;
				return;
			}
			if(curr.key.equals(key) && curr.value==value){
				arr[index]=curr.next;
			}
			while(curr.next!=null){
				if(curr.next.key.equals(key) && curr.next.value==value){
					curr.next=curr.next.next;
					return;
				}
				curr=curr.next;
			}
			return;
		}
	}








	class Pair{
		Pair next;
		String key;
		Integer value;
		public Pair(String key, Integer value){
			next=null;
			this.key=key;
			this.value=value;
		}
		public String toString(){
			return "( " + key + ", " + value + ")";
		}
	}
}