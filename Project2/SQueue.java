
public class SQueue<T extends Comparable<T>> extends list<T>{
	
	public SQueue<T> enque(T v){
		if(count < 1 || v.compareTo(front.data) <= 0){
			super.insert(v,0);
		    return this;
		}
		if(v.compareTo(rear.data) >= 0){
			super.insert(v,count);
		    return this;
		}
		node<T> cur = front.next;
		while(v.compareTo(cur.data) > 0)
			cur = cur.next;
		node<T> nn = new node<T>(v);
		nn.next = cur;
		nn.previous = cur.previous;
		cur.previous.next = nn;
		cur.previous = nn;
		count++;
		return this;
	}
	
	public T deque(){
		return remove(0);
	}
	
	public T deque(T v){
		if(count < 1 || v.compareTo(front.data) < 0 || v.compareTo(rear.data) > 0)
			return null;
		if(v.compareTo(front.data) == 0){
		    return remove(0);
		}
		if(v.compareTo(rear.data) == 0){		
			return remove(count - 1);
		}
		node<T> cur = front.next;
		while(v.compareTo(cur.data) > 0)
			cur = cur.next;
		if(v.compareTo(cur.data) == 0){
			cur.next.previous = cur.previous;
			cur.previous.next = cur.next;
			cur.next = cur.previous = null;
			count--;
			return cur.data;
		}
		return null;
	}
	
	public T front(){
		return empty() ? null : front.data;
	}
}
