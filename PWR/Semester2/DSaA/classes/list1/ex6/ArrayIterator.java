import java.util.*;

public class ArrayIterator<T> implements Iterator<T> {
    private T array[];
    private int pos = 0;
    private boolean wasRemoved;
    private int idxToRemove;

    public ArrayIterator(T anArray[]) {
        wasRemoved = false;
        array = anArray;
        idxToRemove = -1;
    }
    public boolean hasNext() {
        while (pos < array.length && array[pos] == null) {
            pos++;
        }
        return pos < array.length;
    }
    public T next() throws NoSuchElementException {
        if(hasNext()){
            wasRemoved = false;
            idxToRemove = pos;
            return array[pos++];
        }
        else {
            throw new NoSuchElementException();
        }
    }
    public void remove() {
        if(!wasRemoved && idxToRemove != -1){
            array[idxToRemove] = null;
            wasRemoved = true;
        }
    }
}