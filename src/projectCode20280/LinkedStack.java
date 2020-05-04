//This is my implementation of a LinkedStack. It implements the Stack ADT from the Stack Interface file. It implements the necessary functionality and performs as desired.

package projectCode20280;

public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> linkedlist = new SinglyLinkedList<>(); //creating an empty list to store our stack elements

	@Override
	public int size() {
		return linkedlist.size();
	}

	@Override
	public boolean isEmpty() {
		return linkedlist.isEmpty();
	}

	@Override
	public void push(E e) {
		linkedlist.addFirst(e);
	}

	@Override
	public E top() {
		return linkedlist.get(0);
	}

	@Override
	public E pop() {
		E temp = top();

		linkedlist.removeLast();
		return temp;
	}

	@Override
	public String toString()
	{
		return linkedlist.toString();
	}

	public static void main(String[] args) {
		LinkedStack LS = new LinkedStack();

		LS.push(1);
		LS.push(2);
		LS.push(3);
		LS.push(4);
		LS.push(5);
		System.out.println("Initial stack: " + LS.toString());
		LS.pop();
		LS.pop();
		System.out.println("Stack after two pops: " + LS.toString());
		System.out.println("Size: " + LS.size());
	}
}
