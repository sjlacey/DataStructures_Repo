//This is my implementation of a LinkedCircularQueue. It implements the Queue ADT.

package projectCode20280;

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

	public static void main(String[] args) {
		LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<Integer>();
		for(int i=0; i<5; i++) {
			lcq.enqueue(i);
		}
		System.out.println(lcq.cll.toString());
		lcq.dequeue();
		lcq.dequeue();
		System.out.println(lcq.cll.toString());
	}
}