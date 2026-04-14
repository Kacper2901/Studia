import aisd.stack.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VTSTest {
    private VTS<Integer> vts;

    @BeforeEach
    void setUp() {
        vts = new VTS<>();
    }

    @Test
    void testDownAndTop() throws FullStackException, EmptyStackException, BottomOfTheListException {
        for (int i = 10; i >= 1; i--) {
            vts.push(i);
        }

        assertEquals(1, vts.peek());
        vts.cursorDown();
        assertEquals(2, vts.peek());
        vts.cursorDown();
        assertEquals(3, vts.peek());
        vts.cursorDown();
        assertEquals(4, vts.peek());
        vts.cursorDown();
        assertEquals(5, vts.peek());
        vts.cursorTop();
        assertEquals(1, vts.peek());
    }

    @Test
    void testCursorAfterPushAndPop() throws FullStackException, EmptyStackException, BottomOfTheListException {
        vts.push(100);
        vts.push(200);
        vts.push(300);

        vts.cursorDown();
        vts.cursorDown();
        assertEquals(100, vts.peek());

        vts.push(400);
        assertEquals(400, vts.peek());

        vts.cursorDown();
        vts.cursorDown();
        assertEquals(200, vts.peek());

        vts.pop();
        assertEquals(300, vts.peek());
    }

    @Test
    void testTopAndException() throws FullStackException, EmptyStackException, BottomOfTheListException {
        vts.push(5);
        vts.push(4);
        vts.push(3);
        vts.push(2);
        vts.push(1);

        vts.cursorDown();
        vts.cursorDown();
        vts.cursorDown();
        assertEquals(4, vts.peek());

        vts.cursorTop();
        assertEquals(1, vts.peek());

        vts.cursorDown();
        vts.cursorDown();
        vts.cursorDown();
        vts.cursorDown();
        assertEquals(5, vts.peek());

        assertThrows(BottomOfTheListException.class, vts::cursorDown);
    }

    @Test
    void testEmptyAndSingleElement() throws FullStackException, BottomOfTheListException, EmptyStackException {
        assertThrows(EmptyStackException.class, vts::peek);
        assertThrows(BottomOfTheListException.class, vts::cursorDown);

        vts.push(999);
        assertEquals(999, vts.peek());
        assertThrows(BottomOfTheListException.class, vts::cursorDown);

        vts.pop();
        assertThrows(EmptyStackException.class, vts::peek);
    }

}