
public class Queue<T> extends list<T> {
	
	public Queue<T> enque(T v){
		super.insert(v,count);
		return this;
	}
	
	public T deque(){
		return remove(0);
	}
	
	public T front(){
		return empty() ? null : front.data;
	}
}
