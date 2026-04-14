import java.util.*;

public class ArrayIterator implements Iterator{
    Integer[] arr;
    int currIdx;

    ArrayIterator(Integer[] arr){
        this.arr = arr;
        this.currIdx = 0;
    }

    @Override
    public boolean hasNext() {
        return currIdx < arr.length;
    }

    @Override
    public Integer next() {
        if(hasNext()){
            return arr[currIdx++];
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}