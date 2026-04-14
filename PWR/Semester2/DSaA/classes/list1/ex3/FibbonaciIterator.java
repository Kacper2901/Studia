import java.util.*;
public class FibbonaciIterator implements Iterator{
    private int prev1;
    private int prev2;
    FibbonaciIterator(){
        prev1 = 0;
        prev2 = 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int temp = prev2;
        prev2 += prev1;
        prev1 = temp;
        return prev1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}