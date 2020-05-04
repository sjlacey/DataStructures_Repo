package projectCode20280;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class LinkedCircularQueueTest {

    @Test
    void testSize() {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<Integer>();
        assertEquals(0, lcq.size());
        lcq.enqueue(0);
        assertEquals(1, lcq.size());
    }

    @Test
    void testIsEmpty() {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<Integer>();
        assertTrue(lcq.isEmpty());
        lcq.enqueue(0);
        assertFalse(lcq.isEmpty());
        lcq.dequeue();
        assertTrue(lcq.isEmpty());
    }

    @Test
    void testEnqueue() {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<Integer>();
        lcq.enqueue(3);
        lcq.enqueue(1);
        assertEquals(3, lcq.first());
    }

    @Test
    void testFirst() {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<Integer>();
        lcq.enqueue(-1);
        assertEquals(-1, lcq.first());
    }

    @Test
    void testDequeue() {
        LinkedCircularQueue<Integer> lcq = new LinkedCircularQueue<Integer>();
        lcq.enqueue(3);
        lcq.enqueue(1);
        assertEquals(3, lcq.dequeue());
    }

}
