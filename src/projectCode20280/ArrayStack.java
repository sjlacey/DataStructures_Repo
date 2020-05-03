package projectCode20280;

public class ArrayStack<E> implements Stack<E>
{
	public static final int CAPACITY = 1000;

	private E[] data;

	private int size=0;

	public ArrayStack()
	{
		this(CAPACITY); //constructs stack with default capacity - notes
	}

	@SuppressWarnings({"unchecked"})
	public ArrayStack(int capacity) //constructs stack with given capacity - notes
	{
		data = (E[]) new Object[capacity]; //safe cast; compiler may give warning - notes
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size==0;
	}

	@Override
	public void push(E e)
	{
		data[size] = e;
		size++;
	}

	@Override
	public E top()
	{
		return data[size-1];
	}

	@Override
	public E pop()
	{
		if(isEmpty())
		{
			return null;
		}

		E temp = top();

		data[size-1] = null;

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

	public static void main(String[] args)
	{
		ArrayStack<Integer> AS = new ArrayStack<Integer>();

		AS.push(1);
		AS.push(2);
		AS.push(3);
		AS.push(4);
		AS.push(5);

		System.out.println("Initial stack: " + AS.toString());
		AS.pop();
		AS.pop();
		System.out.println("Stack after two pops: " + AS.toString());
		System.out.println("Size: " + AS.size());
	}

}
