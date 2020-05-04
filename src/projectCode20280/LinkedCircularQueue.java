package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

	CircularlyLinkedList<E> cll = new CircularlyLinkedList<E>();

	@Override
	public int size() {
		return cll.size();
	}

	@Override
	public boolean isEmpty() {
		return cll.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		cll.addLast(e);
	}

	@Override
	public E first() {
		return cll.get(0);
	}

	@Override
	public E dequeue() {
		return cll.removeFirst();
	}

}
