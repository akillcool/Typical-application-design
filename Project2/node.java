public class node<T> {	
	T data;
	node<T> next,previous;
     
	//init node
	public node(T v){
		data = v;
		next = previous = null;
	}	
}