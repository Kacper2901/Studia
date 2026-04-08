package dsaa.lab03;


import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TwoWayUnorderedListWithHeadAndTailTest {

    @Test
    public void add_shouldAddSingleElementToEmptyList() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        boolean result = list.add(10);

        assertTrue(result);
        assertEquals(1, list.size());
        assertEquals(10, list.get(0));
    }

    @Test
    public void add_shouldPreserveOrderForMultipleElements() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void addAtIndex_shouldAddAtBeginningToEmptyList() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        list.add(0, 5);

        assertEquals(1, list.size());
        assertEquals(5, list.get(0));
    }

    @Test
    public void addAtIndex_shouldAddAtBeginningToNonEmptyList() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(2);
        list.add(3);

        list.add(0, 1);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void addAtIndex_shouldAddInMiddle() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(3);

        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void addAtIndex_shouldAddAtEnd() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);

        list.add(2, 3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void addAtIndex_shouldThrowWhenIndexIsNegative() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        assertThrows(NoSuchElementException.class, () -> list.add(-1, 10));
    }

    @Test
    public void addAtIndex_shouldThrowWhenIndexIsGreaterThanSize() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);

        assertThrows(NoSuchElementException.class, () -> list.add(2, 10));
    }

    @Test
    public void addList_shouldAddEmptyOtherListWithoutChanges() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list1 = new TwoWayUnorderedListWithHeadAndTail<>();
        TwoWayUnorderedListWithHeadAndTail<Integer> list2 = new TwoWayUnorderedListWithHeadAndTail<>();

        list1.add(1);
        list1.add(2);

        list1.add(list2);

        assertEquals(2, list1.size());
        assertEquals(1, list1.get(0));
        assertEquals(2, list1.get(1));
        assertTrue(list2.isEmpty());
    }

    @Test
    public void addList_shouldAddToEmptyList() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list1 = new TwoWayUnorderedListWithHeadAndTail<>();
        TwoWayUnorderedListWithHeadAndTail<Integer> list2 = new TwoWayUnorderedListWithHeadAndTail<>();

        list2.add(7);
        list2.add(8);

        list1.add(list2);

        assertEquals(2, list1.size());
        assertEquals(7, list1.get(0));
        assertEquals(8, list1.get(1));
        assertTrue(list2.isEmpty());
    }

    @Test
    public void addList_shouldAppendOtherListAtTheEnd() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list1 = new TwoWayUnorderedListWithHeadAndTail<>();
        TwoWayUnorderedListWithHeadAndTail<Integer> list2 = new TwoWayUnorderedListWithHeadAndTail<>();

        list1.add(1);
        list1.add(2);

        list2.add(3);
        list2.add(4);

        list1.add(list2);

        assertEquals(4, list1.size());
        assertEquals(1, list1.get(0));
        assertEquals(2, list1.get(1));
        assertEquals(3, list1.get(2));
        assertEquals(4, list1.get(3));
        assertTrue(list2.isEmpty());
    }

    @Test
    public void addList_shouldDoNothingWhenAddingListToItself() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);

        list.add(list);

        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void removeByIndex_shouldRemoveOnlyElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(99);

        int removed = list.remove(0);

        assertEquals(99, removed);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeByIndex_shouldRemoveFirstElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(0);

        assertEquals(1, removed);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void removeByIndex_shouldRemoveMiddleElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(1);

        assertEquals(2, removed);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void removeByIndex_shouldRemoveLastElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int removed = list.remove(2);

        assertEquals(3, removed);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void removeByIndex_shouldThrowWhenListIsEmpty() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        assertThrows(NoSuchElementException.class, () -> list.remove(0));
    }

    @Test
    public void removeByIndex_shouldThrowWhenIndexIsNegative() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);

        assertThrows(NoSuchElementException.class, () -> list.remove(-1));
    }

    @Test
    public void removeByIndex_shouldThrowWhenIndexIsTooLarge() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);

        assertThrows(NoSuchElementException.class, () -> list.remove(1));
    }

    @Test
    public void removeByValue_shouldReturnFalseForEmptyList() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        boolean result = list.remove((Integer) 5);

        assertFalse(result);
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeByValue_shouldRemoveOnlyElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(5);

        boolean result = list.remove((Integer) 5);

        assertTrue(result);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeByValue_shouldRemoveFirstMatchingElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean result = list.remove((Integer) 1);

        assertTrue(result);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void removeByValue_shouldRemoveMiddleMatchingElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean result = list.remove((Integer) 2);

        assertTrue(result);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void removeByValue_shouldRemoveLastMatchingElement() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean result = list.remove((Integer) 3);

        assertTrue(result);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void removeByValue_shouldReturnFalseWhenElementDoesNotExist() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(3);

        boolean result = list.remove((Integer) 99);

        assertFalse(result);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void removeByValue_shouldRemoveOnlyFirstOccurrence() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);

        boolean result = list.remove((Integer) 2);

        assertTrue(result);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void sequenceOfAddAndRemove_shouldKeepListConsistent() {
        TwoWayUnorderedListWithHeadAndTail<Integer> list = new TwoWayUnorderedListWithHeadAndTail<>();

        list.add(1);
        list.add(2);
        list.add(0, 0);
        list.add(3, 3);

        assertEquals(4, list.size());
        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));

        assertEquals(0, list.remove(0));
        assertTrue(list.remove((Integer) 2));
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }
}