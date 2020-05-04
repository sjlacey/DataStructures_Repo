package projectCode20280;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class LinkedDequeTest {

    @Test
    void testSize() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        assertEquals(0, ld.size());
        ld.addFirst(0);
        assertEquals(1, ld.size());
    }

    @Test
    void testIsEmpty() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        assertTrue(ld.isEmpty());
        ld.addFirst(0);
        assertFalse(ld.isEmpty());
        ld.removeFirst();
        assertTrue(ld.isEmpty());
    }

    @Test
    void testFirst() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        ld.addFirst(-1);
        assertEquals(-1, ld.first());

        ld.removeFirst();
        assertNull(ld.first());

    }



    @Test
    void testLast() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        ld.addFirst(-1);

        assertEquals(-1, ld.last());

        ld.addFirst(-2);
        assertEquals(-1, ld.last());

        ld.addLast(-3);
        assertEquals(-3, ld.last());
    }

    @Test
    void testAddFirst() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();

        ld.addFirst(24);
        ld.addLast(2);

        assertEquals(24, ld.first());
    }

    @Test
    void testAddLast() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();

        ld.addFirst(24);
        ld.addLast(2);

        assertEquals(2, ld.last());
    }

    @Test
    void testRemoveFirst() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        ld.addFirst(-1);
        ld.addFirst(-2);
        assertEquals(-2, ld.removeFirst());
    }

    @Test
    void testRemoveLast() {
        LinkedDeque<Integer> ld = new LinkedDeque<>();
        ld.addFirst(-1);
        ld.addFirst(-2);
        assertEquals(-1, ld.removeLast());
    }

}
