import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseStackTest {

    @Test
    void reverseStack() throws FullStackException, EmptyStackException {
        ReverseStack<Integer> stack = new ReverseStack<>();
        for(int i = 0; i < 50; i++){
            stack.push(i);
        }

        stack.reverseStack();

        for (int i = 0; i < 50; i++){
            assertEquals(i, stack.pop());
        }

    }
}