package projectCode20280;

import org.w3c.dom.Node;

public class LinkedQueue<E> implements Queue<E> {

	private int size=0;

	private SinglyLinkedList<E> ll = new SinglyLinkedList<E>();

	SinglyLinkedList.Node<E> front;
	SinglyLinkedList.Node<E> rear;

	@Override
	public int size() {
		return ll.size();
	}

	@Override
	public boolean isEmpty() {
		return ll.size()==0;
	}

	@Override
	public void enqueue(E e) {
		ll.addLast(e);
	}

	@Override
	public E first() {
		return ll.first();
	}

	@Override
	public E dequeue() {
		return ll.removeFirst();
	}

	@Override
	public String toString() {
		return ll.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
