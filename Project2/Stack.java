
public class Stack<T> extends list<T> {
	public Stack<T> push(T v){
		 super.insert(v,0);
		 return this;
	}
	
	/*
	private Stack<T> add(T v, int pos){
		return null;
	}
	*/
	
	public T pop(){
		return remove(0);
	}
	
	public T top(){
		return empty() ? null : front.data;
	}
}
