package projectCode20280;

public class ArrayQueue<E> implements Queue<E> {

	public static final int CAPACITY = 10;

	private E[] data;

	private int size=0;

	int front=0; //index of the front element
	int rear=0;  //index of the rear element

	public ArrayQueue()
	{
		this(CAPACITY); //constructs stack with default capacity
	}

	@SuppressWarnings({"unchecked"})
	public ArrayQueue(int capacity) //constructs stack with given capacity
	{
		data = (E[]) new Object[capacity]; //safe cast; compiler may give warning
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return front==rear;
	}

	@Override
	public void enqueue(E e) {
		int rear = front+size % CAPACITY;
		data[rear] = e;
		rear = rear+1;
		size++;
	}

	@Override
	public E first() {
		return data[front];
	}

	@Override
	public E dequeue() {
		E temp = data[front];
		front = front+1 % CAPACITY;
		size--;
		return temp;
	}

	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();

		for(int i=0; i<size; i++)
		{
			string.append(data[i]).append("  ");
		}

		return string.toString();
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> AQ = new ArrayQueue<Integer>();

		AQ.enqueue(1);
		AQ.enqueue(2);
		AQ.enqueue(3);
		AQ.enqueue(4);
		AQ.enqueue(5);
		System.out.println(AQ.toString());
		System.out.println(AQ.first());
		AQ.dequeue();
		AQ.dequeue();
		System.out.println(AQ.toString());
		System.out.println(AQ.first());
	}
}