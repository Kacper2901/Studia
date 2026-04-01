import aisd.stack.ArrayStack;
import aisd.stack.EmptyStackException;

public class SinkingStack<T> {
    private int defaultCapacity;
    private int topIndex;
    T array[];
    private int size;

    public SinkingStack(int defaultCapacity){
        this.defaultCapacity = defaultCapacity;
        topIndex = -1;
        array = (T[])(new Object[defaultCapacity]);
        size = 0;
    }

    public int size(){
        return size;
    }

    public void pushEfficient(T elem){
        topIndex = (topIndex + 1) % defaultCapacity;
        array[topIndex] = elem;
        if(size < defaultCapacity) size++;
    }

    public T popEfficient() throws EmptyStackException{
        if(size == 0) throw new EmptyStackException();
        T tempVal = array[topIndex];
        array[topIndex] = null;
        size--;
        if(size > 0){
            topIndex = (topIndex - 1 + defaultCapacity) % defaultCapacity;
        }
        else topIndex = -1;
        return tempVal;
    }

    public void pushInefficient(T elem){
        if(topIndex == defaultCapacity - 1){
            for(int i = 0; i < defaultCapacity - 1; i ++){
                array[i] = array[i + 1];
            }
        }
        if(size < defaultCapacity) {
            size++;
            topIndex++;
        }
        array[topIndex] = elem;
    }

    public T popInefficient() throws EmptyStackException{
        if(size == 0) throw new EmptyStackException();
        T tempVal = array[topIndex];
        array[topIndex] = null;
        topIndex--;
        size--;
        return tempVal;
    }

    public boolean isEmpty(){
        return size == 0;
    }


}
