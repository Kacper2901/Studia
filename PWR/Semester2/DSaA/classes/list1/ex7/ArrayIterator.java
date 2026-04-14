import java.util.*;

public class ArrayIterator<T> implements Iterator<T> {
    private T array[];
    private int pos;
    public ArrayIterator(T anArray[], int pos) {
        array = anArray;
        this.pos = pos;
    }
    public boolean hasNext() {return pos < array.length;}
//    public boolean hasNext() {return pos >= 0;}

    public T next() throws NoSuchElementException {
        if (hasNext())
            return array[pos++];
//            return array[pos--];
        else
            throw new NoSuchElementException();
    }
    public void remove() {
        throw new UnsupportedOperationException();
    }
}