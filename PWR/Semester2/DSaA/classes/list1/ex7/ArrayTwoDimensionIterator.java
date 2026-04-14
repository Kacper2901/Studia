import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTwoDimensionIterator<T> implements Iterator<T> {
    private T array[][];
    private T[] currSubArray;
    private int currSubArrayIdx;
    private ArrayIterator<T> it;
    private int pos;

    public ArrayTwoDimensionIterator(T anArray[][]) {
        array = anArray;

//        currSubArrayIdx = anArray.length - 1;
        currSubArrayIdx = 0;

        currSubArray = array[currSubArrayIdx];

//        pos = currSubArray.length - 1;
        pos = 0;

        it = new ArrayIterator<>(currSubArray, pos);
    }

    public boolean hasNext() {
        if(it.hasNext()) return true;
        else{

//            currSubArrayIdx--;
            currSubArrayIdx++;

            if (currSubArrayIdx < array.length){ //currSubArrayIdx >= 0  currSubArrayIdx < array.length

//                pos = currSubArray.length - 1;
                pos = 0;

                currSubArray = array[currSubArrayIdx];
                it = new ArrayIterator<>(currSubArray, pos);
                return hasNext();
            }
        }
        return false;
    }

    public T next() throws NoSuchElementException {
        if (hasNext())
            return it.next();
        else
            throw new NoSuchElementException();
    }
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
