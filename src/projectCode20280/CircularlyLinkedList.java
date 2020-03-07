package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private Node<E> tail = null;
	private int size = 0;

	public CircularlyLinkedList() { }

	private class Node<E>
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

		//Mutators
		public void setNext(Node<E> n)
		{
			next = n;
		}
	}

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
		Node<E> cur = tail.getNext();
		Node<E> prev = tail;

		if(size==0)
		{
			tail = new Node<E>(e, null);
			tail.setNext(tail);
		}
		else
		{
			for(int j=0; j<i; j++)
			{
				prev = cur;
				cur = cur.next;
			}

			Node<E> newest = new Node<E>(e, tail.getNext());
			tail.setNext(newest);
		}
	}

	@Override
	public E remove(int i)
	{
		Node<E> cur = tail.getNext();
		Node<E> prev = tail;

		if(isEmpty()) throw new RuntimeException("Cannot delete as list is empty.");
		Node<E> head = tail.getNext();
		for(int j=0; j<i; j++)
		{
			prev = cur;
			cur = cur.next;
		}
		if(head==tail) tail = null;
		else tail.setNext(head.getNext());
		size--;
		return head.getElement();
	}

	@Override
	public E removeFirst()
	{
		remove(0);
		return null;
	}

	@Override
	public E removeLast()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub

	}

	public void rotate() {
				
	}
	
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();

		ll.rotate();
		System.out.println(ll);

		ll.removeFirst();
		ll.rotate();
		System.out.println(ll);

		ll.removeLast();
		ll.rotate();
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}

	}
}
