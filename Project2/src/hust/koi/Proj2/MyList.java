package hust.koi.Proj2;

import java.util.Iterator;

import org.omg.CORBA.PRIVATE_MEMBER;

public class MyList<T> implements Iterable<T> {

	Node front, rear;
	int size;

	public MyList(MyList<T>.Node front, MyList<T>.Node rear, int size) {
		super();
		this.front = front;
		this.rear = rear;
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(T obj, int position) {
		
		position = fixedPosition(position);
		Node currentNode = front;
		Node insertNode = new Node(obj);
		int count = 0;

		while (count != position) {
			currentNode = currentNode.next;
			count++;
		}

		currentNode.previous.next = insertNode;
		insertNode.next = currentNode;
		size++;
	}

	public T remove(int position) {
		int count = 0;
		Node removeNode = front;

		while (count != position) {
			removeNode = removeNode.next;
			count++;
		}

		removeNode.next.previous = removeNode.previous;
		removeNode.previous.next = removeNode.next;
		size--;

		return removeNode.obj;
	}

	// check that the position given was right
	private int fixedPosition(int position) {
		return position < 0 ? 0 : (position > size ? size : position);
	}

	protected class Node {

		T obj;
		Node next, previous;

		public Node(T obj) {
			super();
			this.obj = obj;
		}
	}

	protected class ListIterator implements Iterator<T> {

		protected Node currentNode;
		protected boolean forward;

		// Add flag for Iterator to traverse direction
		public ListIterator(boolean forward) {
			this.forward = forward;
			currentNode = forward ? front : rear;
		}

		@Override
		public boolean hasNext() {
			return currentNode.next == null ? false : true;
		}

		@Override
		public T next() {
			return currentNode.next.obj;
		}

		@Override
		public void remove() {
			if (currentNode.previous != null) {
				currentNode.previous.next = currentNode.next;
				currentNode.next.previous = currentNode.previous;
			} else {
				currentNode.next.previous = null;
			}
		}

	}

	@Override
	public Iterator<T> iterator() {
		// traverse from front to back by default
		return iterator(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator<T> iterator(boolean direction) {
		return new MyList.ListIterator(direction);
	}

}
