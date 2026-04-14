package dsaa.lab02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class OneWayLinkedListTest {

    private OneWayLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new OneWayLinkedList<>();
    }



    @Test
    void add() {
        assertTrue(list.add("test1"));
        assertEquals(1, list.size());
        assertEquals("test1", list.get(0));
    }


    @Test
    void testAdd() {
        list.add("A");
        list.add("C");
        list.add(1, "B");
        list.add(0, "First");

        assertEquals(4, list.size());
        assertEquals("First", list.get(0));
        assertEquals("B", list.get(2));
    }

    @Test
    void clear() {
        list.add("data1");
        list.add("data2");
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        list.add("findMe");
        assertTrue(list.contains("findMe"));
        assertFalse(list.contains("notInList"));
    }

    @Test
    void get() {
        list.add("val0");
        list.add("val1");
        assertEquals("val0", list.get(0));
        assertEquals("val1", list.get(1));

    }

    @Test
    void set() {
        list.add("oldVal");
        assertEquals("oldVal", list.set(0, "newVal"));
        assertEquals("newVal", list.get(0));

    }

    @Test
    void indexOf() {
        list.add("A");
        list.add("B");
        list.add("A");
        assertEquals(0, list.indexOf("A"));
        assertEquals(1, list.indexOf("B"));
        assertEquals(-1, list.indexOf("Z"));
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.add("sth");
        assertFalse(list.isEmpty());
    }

    @Test
    void remove() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("A", list.remove(0));
        assertEquals("C", list.remove(1));
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }


    @Test
    void testRemove() {
        list.add("del");
        assertTrue(list.remove("del"));
        assertFalse(list.remove("del"));
        assertEquals(0, list.size());
    }

    @Test
    void size() {
        assertEquals(0, list.size());
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals(2, list.size());
    }
}