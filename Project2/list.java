import java.util.Iterator;

public class list<C>{
	int count = 0;
	protected node<C> front = null,rear = null;
	
	public int size(){
		return count;
	}
	
	public boolean empty(){
		return count == 0;
	}
	
	protected list<C> insert(C v){
		return insert(v,0);
	}
	
	protected list<C> insert(C v,int pos){
		pos = pos < 0 ? 0: pos > count ? count : pos;
		node<C> nn = new node<C>(v);
		//if list is empty
		if(count == 0){
			front =  rear = nn;
			count++;
			return this;
		}		
		//the node is in the first position
		if(pos == 0){
			nn.next = front;
			front.previous = nn;
			front = nn;
			count++;
			return this;
		}
		//the node is in the last position
		if(pos == count){
			rear.next = nn;
            nn.previous = rear;
			rear = nn;
			count++;
			return this;
		}
		//create the node is in first position a new node and move it in the front of cur
		node<C> cur = front;
		//there must be another node after the cur node
		for(int i = 1; i < pos ; i++)
			cur = cur.next;
		//copy the cur into the position
		nn.next = cur.next;
		nn.previous = cur;
		cur.next.previous = nn;
		cur.next = nn;
		count++;
		return this;
	}
	
	protected C remove(int pos){
		C ptr = front.data;
		if(count < 1 || pos < 0 || pos >= count) 
			return null;
		
		if(count == 1){
			count--;
			front = rear = null;
			return ptr;
		}
		
		if( pos == 0){
			front = front.next;
			front.previous.next= null;
			front.previous = null;
			count--;
			return ptr;
		}
		
		if( pos == count - 1){
			ptr = rear.data;
			rear = rear.previous;
			rear.next.previous = null;
			rear.next = null;
			count--;
			return ptr;
		}
		node<C> cur = front;
		for(int i = 1 ; i < pos ; i++){
			cur = cur.next;
		}
	    ptr = cur.data;
	    cur.previous.next = cur.next;
	    cur.next.previous = cur.previous;
	    count--;
	    return ptr;
	}
	
	public Iterator<C> iterator(){
		return iterator(true);
	}
	
	public Iterator<C> iterator(boolean dir){
		return new MylistIterator<C>(dir,dir ? front:rear);
	}
}
