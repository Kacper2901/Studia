import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleIterator implements Iterator {
    final private int ITERATOR1 = 1;
    final private int ITERATOR2 = 2;
    private ArrayIterator it1;
    private ArrayIterator it2;
    private int currIt;

    DoubleIterator(ArrayIterator it1, ArrayIterator it2){
        this.it1 = it1;
        this.it2 = it2;
        currIt = ITERATOR1;
    }

    @Override
    public boolean hasNext() {
        switch (currIt){
            case ITERATOR1:
                return it1.hasNext();
            case ITERATOR2:
                return it2.hasNext();
            default:
                throw new NoSuchElementException();
        }
    }

    @Override
    public Object next() {
        if(hasNext()) {
            switch (currIt) {
                case ITERATOR1:
                    currIt = ITERATOR2;
                    return it1.next();
                case ITERATOR2:
                    currIt = ITERATOR1;
                    return it2.next();
                default:
                    throw new NoSuchElementException();
            }
        }
        else throw new NoSuchElementException();
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

