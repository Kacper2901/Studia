import java.util.*;
class SequenceIterator implements Iterator {
    int currNum;

    public SequenceIterator(int startNum){
        currNum = startNum;
    }
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return currNum++;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}