import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerIterator implements Iterator {
    int currInt;
    int maxInt;


    IntegerIterator(int maxInt){
        this.currInt = 1;
        this.maxInt = maxInt;
    }

    @Override
    public boolean hasNext() {

        return currInt + 1 <= maxInt;
    }

    @Override
    public Integer next() {
        if(hasNext()) return currInt++;
        else throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
