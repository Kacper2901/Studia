import java.util.Iterator;
import java.util.ListIterator;

public interface IList<E> extends Iterable<E> {
    boolean add(E e); // add an element on the end of the list
    void add(int index, E e); // add element on position index
    void clear(); // remove all elements
    boolean contains(E e); // check if the contains input element (equals())
    E get(int index); // get an element from position index
    E set(int index, E e); // set an element on position index
    int indexOf(E element); // find position of an element (equals())
    boolean isEmpty(); // check ig list is empty
    Iterator<E> iterator(); // return an iterator
    ListIterator<E> listIterator(); // return an listIterator
    E remove(int index); // remove element from position index
    boolean remove(E e); // remove an input element (equals())
    int size();
}
