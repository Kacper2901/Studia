import aisd.stack.*;

public class VTS<E> extends ListStack<E>{
    private Integer cursor = 0;


    public E peek() throws EmptyStackException{
        //TODO
        if(_list.isEmpty()) throw new EmptyStackException();
        return _list.get(cursor);
    }

    public void cursorTop(){
        //TODO
        cursor = 0;
    }

    public void cursorDown() throws BottomOfTheListException{
        //TODO
        if(_list.isEmpty() || cursor >= size()-1) throw new BottomOfTheListException();
        cursor++;
    }

    @Override
    public E pop() throws aisd.stack.EmptyStackException {
        if(_list.isEmpty()) throw new aisd.stack.EmptyStackException();
        E value=_list.remove(0);
        cursor = 0;
        return value;
    }

    @Override
    public void push(E elem) throws FullStackException {
        cursor = 0;
        _list.add(0,elem);
    }

}