//This is my implementation of a LinkedDeque. It implements the Deque ADT from the Deque Interface file.

package projectCode20280;

public class LinkedDeque<E> implements Deque<E> {

	private DoublyLinkedList<E> dll = new DoublyLinkedList<>();

	@Override
	public int size() {
		return dll.size();
	}

	@Override
	public boolean isEmpty() {
		return dll.isEmpty();
	}

	@Override
	public E first() {
		return dll.first();
	}

	@Override
	public E last() {
		return dll.get(size() - 1);
	}

	@Override
	public void addFirst(E e) {
		dll.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		dll.addLast(e);

	}

	@Override
	public E removeFirst() {
		return dll.removeFirst();
	}

	@Override
	public E removeLast() {
		return dll.removeLast();
	}

	public static void main(String[] args) {
		LinkedDeque<Integer> lcq = new LinkedDeque<Integer>();
		for(int i=0; i<5; i++) {
			lcq.addFirst(i);
		}
		System.out.println(lcq.dll.toString());
		lcq.removeFirst();
		lcq.removeFirst();
		System.out.println(lcq.dll.toString());
		lcq.removeLast();
		System.out.println(lcq.dll.toString());
		System.out.println(lcq.last());
		System.out.println(lcq.first());
	}
}