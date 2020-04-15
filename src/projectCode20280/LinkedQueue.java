package projectCode20280;

import org.w3c.dom.Node;

public class LinkedQueue<E> implements Queue<E> {

	private int size=0;

	private SinglyLinkedList<E> ll = new SinglyLinkedList<E>();

	SinglyLinkedList.Node<E> front = null;
	SinglyLinkedList.Node<E> rear = null;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return ll.size()==0;
	}

	@Override
	public void enqueue(E e) {
		ll.add(size, e);
		if(rear==null) {
			front.setElement(e);
		}
		else {
			//rear-2 = e;
		}

		size++;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
