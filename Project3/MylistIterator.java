import java.util.Iterator;
	
	class MylistIterator<E> implements Iterator<E>{
		protected node<E> currentNode;
		protected boolean forward;
		public MylistIterator(boolean dir,node<E> ptr){
			forward = dir;
			currentNode = ptr;
		}
		@Override
		public boolean hasNext(){
			return currentNode != null;
		}
		@Override
		public E next(){
			E v = currentNode.data;
			currentNode = forward ? currentNode.next : currentNode.previous;
			return v;
		}
		@Override
		public void remove(){			
		}
	}
