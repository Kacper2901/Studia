import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator {
    private IntegerIterator iterator;
    private int maxInt;
    private int bufforNext;
    private boolean isHasNextUsed;
    private boolean hasNextVal;

    PrimeIterator(int maxInt){
        this.iterator = new IntegerIterator(maxInt);
        this.maxInt = maxInt;
        this.isHasNextUsed = false;
    }
    @Override
    public boolean hasNext() {
        if(isHasNextUsed) return hasNextVal;
        else {
            int temp = 0;
            while(!isPrime(temp)) {
                if (iterator.hasNext()) {
                    temp = iterator.next();
                }
                else{
                    isHasNextUsed = true;
                    hasNextVal = false;
                    return hasNextVal;
                }
            }
            hasNextVal = true;
            isHasNextUsed = true;
            bufforNext = temp;
            return hasNextVal;
        }
    }

    @Override
    public Object next() {
        if(hasNext()){
            isHasNextUsed = false;
            return bufforNext;
        }
        else throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    boolean isPrime(int n){
        if(n < 2) return false;
        if(n == 2) return true;
        for(int i = 2; i*i <= n; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
}
