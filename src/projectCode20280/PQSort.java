package projectCode20280;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class PQSort
{

    public PQSort() { }

    public static Integer removeMin(LinkedList<Integer> ll)
    {
        int minIndex = 0;
        Integer minValue = ll.get(minIndex);
        for(int i=1; i<ll.size(); ++i)
        {
            Integer curr = ll.get(i);
            if(curr < minValue)
            {
                minValue = curr;
                minIndex = i;
            }
        }
        ll.remove(minIndex);
        return minValue;
    }

    public static boolean isSorted(Integer[] array, int length) //recursive method to check if an array is sorted
    {
        if(array==null || length<2)
            return true;
        if(array[length-2].compareTo(array[length-1]) > 0)
            return false;
        return isSorted(array, length-1);
    }

    public static void main_n2(String[] args)
    {
        //SinglyLinkedList<Integer> array = new SinglyLinkedList<Integer>();

        int size=500;

        while(size < 100000)
        {
            LinkedList<Integer> array = new Random().ints(0, 100000).distinct().limit(size).boxed().collect(Collectors.toCollection(LinkedList::new));

            long startTime = System.nanoTime();
            //first phase
            //move all elements from Array -> PQ
            LinkedList<Integer> PQ = new LinkedList<Integer>();
            while (!array.isEmpty())
            {
                PQ.addLast(array.removeFirst());
            }

            //move all elements from PQ -> Array (removeMin()), this is how the sorting works, the sorting is done by removeMin()
            while (!PQ.isEmpty())
            {
                array.addLast(removeMin(PQ));
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            boolean isSorted = isSorted(array.toArray(new Integer[array.size()]), array.size());
            System.out.println(size + ", " + elapsedTime + ", " + isSorted);

            //increase n
            size = (int) (size*1.1);
        }

    }


    public static void main(String[] args)
    {
        //SinglyLinkedList<Integer> array = new SinglyLinkedList<Integer>();

        int size=500;

        while(size < 100000)
        {
            LinkedList<Integer> array = new Random().ints(0, 100000).distinct().limit(size).boxed().collect(Collectors.toCollection(LinkedList::new));

            long startTime = System.nanoTime();
            //first phase
            //move all elements from Array -> PQ

            HeapPriorityQueue<Integer, Integer> PQ = new HeapPriorityQueue<Integer, Integer>();

            while (!array.isEmpty())
            {
                Integer value = array.removeFirst();
                PQ.insert(value, value);
            }

            //Integer [] array_array = array.toArray(Integer[]::new);
            //HeapPriorityQueue<Integer, Integer> PQ = new HeapPriorityQueue<Integer, Integer>(array_array, array_array);

            //move all elements from PQ -> Array (removeMin()), this is how the sorting works, the sorting is done by removeMin()
            while (!PQ.isEmpty())
            {
                array.addLast(PQ.removeMin().getKey());
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            boolean isSorted = isSorted(array.toArray(new Integer[array.size()]), array.size());
            System.out.println(size + ", " + elapsedTime + ", " + isSorted);

            //increase n
            size = (int) (size*1.1);
        }

    }




}
