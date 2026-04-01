import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;

public class ReverseStack<E> extends ListStack<E>{

    @Override
    public boolean isEmpty() {
        return _list.isEmpty();
    }
    @Override
    public boolean isFull() {
        return false;}
    @Override
    public E pop() throws EmptyStackException {
        E value=_list.remove(0);
        if(value==null) throw new EmptyStackException();
        return value;}
    @Override
    public void push(E elem) throws FullStackException {
        _list.add(0,elem);
    }
    @Override
    public int size() {
        return _list.size();}
    @Override
    public E top() throws EmptyStackException {
        E value=_list.get(0);
        if(value==null) throw new EmptyStackException();
        return value;
    }

    public void reverseStack() throws EmptyStackException,FullStackException{
        ListStack<E> tempStack1 = new ListStack<>();
        ListStack<E> tempStack2 = new ListStack<>();

        while(!isEmpty()){
            tempStack1.push(pop());
        }

        while(!tempStack1.isEmpty()){
            tempStack2.push(tempStack1.pop());
        }

        while(!tempStack2.isEmpty()){
            push(tempStack2.pop());
        }
    }
}
