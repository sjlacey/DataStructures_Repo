package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private Node<E> tail = null;
	private int size = 0;

	public CircularlyLinkedList() { }

	private static class Node<E>
	{
		private E element;

		private Node<E> next;

		public Node(E e, Node<E> n)
		{
			element = e;
			next = n;
		}
		//Accessors
		public Node<E> getNext()
		{
			return next;
		}
		public E getElement()
		{
			return element;
		}
		public void setElement(E e)
		{
			this.element = e;
		}

		//Mutators
		public void setNext(Node<E> n)
		{
			next = n;
		}
	}

	public E tail() {
		if(tail==null)
			return null;
		return tail.getElement();
	}
	public E head() { return tail.getNext().getElement(); }

	@Override
	public int size() { return size; }

	@Override
	public boolean isEmpty() { return size == 0; }


	@Override
	public E get(int i)
	{
		Node<E> cur = tail.getNext();
		Node<E> prev = tail;

		for(int j=0; j<i; j++)
		{
			prev = cur;
			cur = cur.next;
		}

		return cur.getElement();
	}

	@Override
	public void add(int i, E e)
	{
		if(isEmpty()) {
			tail = new Node<E>(e, null);
			tail.setNext(tail);
		}

		Node<E> cur = tail;

		if(i>0) {
			for(int j=0; j<i; j++)
			{
				cur = cur.next;
			}
		}

		if (size == 0) {
			cur = new Node<>(e, null);
			cur.setNext(cur);                     // link to itself circularly
		} else {
			Node<E> newest = new Node<>(e, cur.getNext());
			cur.setNext(newest);
		}
		size++;
	}

	@Override
	public E remove(int i)
	{
		if(isEmpty()) {
			return null;
		}

		Node<E> cur = tail.next; //aka head
		Node<E> prev = null;

		if(i>0) {
			for(int j=0; j<i; j++)
			{
				prev = cur;
				cur = cur.next;
			}
		}
		if(i==0) {
			prev = tail;
		}

		if (cur == tail) {
			tail = null;
		} else {
			prev.setNext(cur.getNext());
		}
		size--;
		return cur.getElement();

	}

	@Override
	public E removeFirst()
	{
		return remove(0);
	}

	@Override
	public E removeLast()
	{
		return remove(size-1);
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularlyLinkedListIterator();
	}

	private class CircularlyLinkedListIterator implements Iterator<E>
	{
		Node<E> cur;
		public CircularlyLinkedListIterator()
		{
			cur = tail.next;
		}
		public boolean hasNext()
		{
			return cur != null;
		}

		@Override
		public E next()
		{
			E res = (E) cur.getElement();
			cur = cur.getNext();
			return res;
		}
	}

	@Override
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		addFirst(e);
		tail = tail.next;
	}

	public void rotate() {
		if(tail!=null) {
			tail = tail.getNext();
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> node = tail;

		do {
			node = node.getNext();
			sb.append(node.getElement());
			if(node!=tail) {
				sb.append(", ");
			}
		} while(node!=tail);
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addFirst(i);
		}
		ll.add(4, 999);
		System.out.println("Filled: "+ll);


		/*ll.removeFirst();
		System.out.println("First removed: "+ll);

		ll.removeLast();

		ll.rotate();
		System.out.println("Last removed and rotated: "+ll);

		ll.removeFirst();
		ll.rotate();
		System.out.println("First removed and rotated: "+ll);

		ll.removeLast();
		ll.rotate();
		System.out.println("Last removed and rotated: "+ll);

		for(int i=0; i<ll.size(); i++) {
			System.out.println("value:" + ll.get(i));
		}*/
	}
}
