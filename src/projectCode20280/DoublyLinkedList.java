//This is my implementation of a DoublyLinkedList. It implements the basic list ADT, overriding all necessary functions, which all work as desired.

package projectCode20280;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoublyLinkedList<E> implements List<E>
{
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;

	public E first()
	{
		if(header == null) {
			return null;
		} else {
			return header.getElement();
		}
	}
	public E last() { return get(size-1); }

	public DoublyLinkedList()
	{
		header = new Node<E>(null, null, null);
		trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}

	private static class Node<E>
	{
		private E element;

		private Node<E> prev;
		private Node<E> next;

		public Node(E e, Node<E> p, Node<E> n)
		{
			element = e;
			prev = p;
			next = n;
		}

		//Accessors
		public Node<E> getNext()
		{
			return next;
		}
		public Node<E> getPrev()
		{
			return prev;
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
		public void setPrev(Node<E> p)
		{
			prev = p;
		}
	}
	
	private void addBetween(E e, Node<E> predecessor, Node<E> successor)
	{
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public E get(int i)
	{
		Node<E> cur = header;
		Node<E> prev = null;

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
		Node<E> cur;
		Node<E> prev;

		if(isEmpty())
		{
			header = new Node<E>(e, null, null);
			trailer = header;
			size++;
		}
		else if(i==0)
		{
			header = new Node<E>(e, null, header);
			size++;
		}
		else
		{
			cur=header.getNext();
			prev=header;
			for(int j=1; j<=i; j++)
			{
				if(i==j)
				{
					prev.setNext(new Node<E>(e, prev, cur));
					size++;
					break;
				}
				else
				{
					prev = cur;
					cur = cur.getNext();
				}
			}
		}
	}

	@Override
	public E remove(int i)
	{
		Node<E> cur = header;
		Node<E> prev = null;

		if(header==null)
		{
			throw new RuntimeException("Cannot delete as the list is empty!");
		}

		for(int j=0; j<i; j++)
		{
			prev = cur;
			cur = cur.getNext();
		}

		if(cur == null)
		{
			throw new RuntimeException("Cannot delete");
		}

		//delete cur node
		E value = cur.getElement();

		if(i==0) {
			header = cur.getNext();
		} else {
			try {
				prev.setNext(cur.getNext());
			} catch(NullPointerException ignored) { }
		}

		size--;
		return value;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new DoublyLinkedListIterator();
	}

	private class DoublyLinkedListIterator implements Iterator<E>
	{
		Node<E> cur;
		public DoublyLinkedListIterator()
		{
			cur = header;
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
	public E removeFirst()
	{
		return remove(0);
	}

	@Override
	public E removeLast()
	{
		if(isEmpty()) {
			return null;
		}
		return remove(size-1);
	}
	

	@Override
	public void addFirst(E e)
	{
		add(0, e);
	}

	@Override
	public void addLast(E e)
	{
		add(size, e);
	}

	@Override
	public String toString()
	{
		Node<E> cur = header;
		StringBuilder s= new StringBuilder("[");

		while(cur != null && cur.getElement() != null)
		{
			s.append(cur.getElement().toString());
			cur = cur.getNext();

			if(cur!=null){
				s.append(", ");
			}
		}

		s.append("]");

		return s.toString();
	}

	
	public static void main(String[] args)
	{
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();

		ll.addFirst(0);
	    ll.addFirst(1);
	    ll.addFirst(2);
	    ll.addLast(-1);
	    System.out.println(ll);

	    ll.removeFirst();
	    System.out.println(ll);

	    ll.removeLast();
	    System.out.println(ll);

	    for(Integer e: ll)
	    {
	    	System.out.println("value: " + e);
	    }
	}

	
}
