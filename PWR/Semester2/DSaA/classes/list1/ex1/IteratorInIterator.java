import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorInIterator<T> implements Iterator<T> {
    ArrayIterator<T> iterator;
    private int k;
    private T bufforNext;
    private boolean hasNextWasUsed;
    private boolean hasNextVal;


    public IteratorInIterator(ArrayIterator iterator, int k){
        this.iterator = iterator;
        this.k = k;
        hasNextWasUsed = false;

    }

    @Override
    public boolean hasNext() {
        if(hasNextWasUsed) return hasNextVal;
        for(int i = 0; i < k - 1; i++){
            if(iterator.hasNext()) iterator.next();
            else{
                hasNextVal = false;
                hasNextWasUsed = true;
                return false;
            }
        }
        hasNextWasUsed = true;
        hasNextVal = iterator.hasNext();
        bufforNext = iterator.next();

        return hasNextVal;
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public T next() throws NoSuchElementException {
        if(hasNext()) {
            hasNextWasUsed = false;
            return bufforNext;
        }
        else throw new NoSuchElementException();
    }
}
