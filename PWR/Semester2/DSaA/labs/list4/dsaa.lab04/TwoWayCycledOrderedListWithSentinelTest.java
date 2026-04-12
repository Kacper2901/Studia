package dsaa.lab04;

import org.junit.jupiter.api.BeforeEach;

import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class TwoWayCycledOrderedListWithSentinelTest {
    TwoWayCycledOrderedListWithSentinel<String> list;

    @BeforeEach
    void prepare(){
        list = new TwoWayCycledOrderedListWithSentinel<String>();
        list.add("b");
        list.add("d");
        list.add("t");
        list.add("k");
        list.add("m");
        list.add("p");
        list.add("z");
        list.add("s");
    }
    @org.junit.jupiter.api.Test
    void add() {
        ListIterator<String> it = list.listIterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @org.junit.jupiter.api.Test
    void testAdd() {
    }

    @org.junit.jupiter.api.Test
    void clear() {
    }

    @org.junit.jupiter.api.Test
    void contains() {
    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void set() {
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }

    @org.junit.jupiter.api.Test
    void iterator() {
    }

    @org.junit.jupiter.api.Test
    void listIterator() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

    @org.junit.jupiter.api.Test
    void testRemove() {
    }

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void testAdd1() {
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
    }
}