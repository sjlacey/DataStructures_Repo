package projectCode20280;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CircularlyLinkedListTest {

    @Test
    void testSize() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        assertEquals(0, ll.size());
        ll.addFirst(0);
        assertEquals(1, ll.size());
    }

    @Test
    void testIsEmpty() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        assertEquals(true, ll.isEmpty());
        ll.addFirst(0);
        assertEquals(false, ll.isEmpty());
        ll.removeFirst();
        assertEquals(true, ll.isEmpty());
    }

    @Test
    void testFirst() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        ll.addFirst(-1);
        assertEquals(-1, ll.head());

        ll.removeFirst();
        assertEquals(null, ll.tail());


    }

    @Test
    void testLast() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        ll.addFirst(-1);

        assertEquals(-1, ll.tail());

        ll.addFirst(-2);
        assertEquals(-1, ll.tail());

        ll.addLast(-3);
        assertEquals(-3, ll.tail());
    }


    @Test
    void testRemoveLast() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        ll.addFirst(-1);
        ll.addFirst(-2);
        assertEquals(-1, ll.removeLast());
    }

    @Test
    void testGet() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        assertEquals(1, ll.get(1));
    }

    @Test
    void testRemove() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) {
            ll.addLast(i);
            System.out.println(i);
        }

        ll.remove(1);
        assertEquals("[0, 2, 3, 4]", ll.toString());
    }

    @Test
    void testAdd() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        ll.add(2, -1);
        assertEquals("[0, 1, -1, 2, 3, 4]", ll.toString());
    }

    @Test
    void testToString() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        assertEquals("[0, 1, 2, 3, 4]", ll.toString());
    }

    @Test
    void testIterator() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        ArrayList<Integer> buf = new ArrayList<>();
        for(int i=0; i<ll.size(); i++) {
            int j = ll.get(i);
            buf.add(j);
        }
        assertEquals("[0, 1, 2, 3, 4]", buf.toString());
    }

}
