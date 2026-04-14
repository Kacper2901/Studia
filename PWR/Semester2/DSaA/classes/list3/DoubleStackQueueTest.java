import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleStackQueueTest {

    @Test
    void enqueue() throws FullQueueException, FullStackException, EmptyStackException, EmptyQueueException {
        DoubleStackQueue<Integer> queue = new DoubleStackQueue<>();
        for(int i = 0; i < 50; i++){
            queue.enqueue(i);
        }
        for(int i = 0; i < 50; i++){
            assertEquals(queue.dequeue(), i);
        }
    }
}