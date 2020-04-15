package projectCode20280;

import javax.swing.*;
import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E>
{

	int size = 0;
	E e;
	//E head; old code
	Node<E> head;

	static class Node<E> //*** Node class is very simple, just holding references
	{                            //*** to the data and the pointer to the next node!
		private E element;

		private Node<E> next;

		public Node(E e, Node<E> n)
		{
			element = e;
			next = n;
		}

		//Accessors
		public E getElement()
		{
			return element;
		}
		public Node<E> getNext()
		{
			return next;
		}

		//Mutators
		public void setNext(Node<E> n)
		{
			next = n;
		}
		public void setElement(E e) { this.element = e; }
	}

	@Override
	public boolean isEmpty()
	{
		//in an empty list the head points to null
		return size == 0;
	}

	@Override
	public E get(int i) //return element at index i
	{
		if(i<0 || i>(size-1))
		{
			throw new IllegalArgumentException("The index value i is invalid for this list.");
		}
		Node<E> cur = head;
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
			head = new Node<E>(e, null);
			size++;
		}
		else if(i==0)
		{
			head = new Node<E>(e, head);
			size++;
		}
		else
		{
			cur=head.getNext();
			prev=head;
			for(int j=1; j<=i; j++)
			{
				if(i==j)
				{
					prev.setNext(new Node<E>(e, cur));
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
		Node<E> cur = head;
		Node<E> prev = null;

		if(head==null)
		{
			throw new RuntimeException("Cannot delete as the list is empty!");
		}

		if(cur.getElement().equals(i))
		{
			head=head.next;
			return head.getElement();
		}
		for(int j=0; j<i; j++)
		{
			prev = cur;
			cur = cur.next;
		}
		if(cur == null)
		{
			throw new RuntimeException("Cannot delete");
		}

		//delete cur node
		assert prev != null;
		prev.next = cur.next;

		return cur.getElement();
	}


	@Override
	public Iterator<E> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<E>
	{
		Node<E> cur;
		public ListIterator()
		{
			cur = head;
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
	public int size()
	{
		return size;
	}


	@Override
	public E removeFirst()
	{
		head=head.next;
		return head.getElement();
	}

	@Override
	public E removeLast()
	{
		Node<E> cur = head;
		Node<E> prev = null;

		if(head==null)
		{
			throw new RuntimeException("Cannot delete as the list is empty!");
		}
		while (cur.next != null)
		{
			prev = cur;
			cur = cur.next;
		}

		//delete cur node
		assert prev != null;
		prev.next = null;

		return cur.getElement();
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
		Node<E> cur = head;
		StringBuilder s= new StringBuilder("(  ");

		while(cur != null)
		{
			s.append(cur.getElement().toString());
			cur = cur.getNext();

			s.append("  ");
		}

		s.append(")");

		return s.toString();
	}

	public static void main(String[] args)
	{
		SinglyLinkedList <Integer> ll = new SinglyLinkedList <Integer >();

		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(3);
		ll.addFirst(4);
		ll.addFirst(5);
		ll.add(3, 2);
		System.out.println(ll);

		ll.addFirst(-100);
		ll.addLast(+100);
		System.out.println(ll);

		ll.removeFirst();
		ll.removeLast();
		System.out.println(ll);

		//Removes the item in the specified index
		ll.remove(2);
		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();
		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.addFirst(9999);
		ll.addFirst(8888);
		ll.addFirst(7777);

		System.out.println(ll);
		System.out.println(ll.get(0));
		System.out.println(ll.get(1));
		System.out.println(ll.get(2));
		System.out.println(ll);
	}


}
