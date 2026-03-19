import java.util.Iterator;
import java.util.ListIterator;

public class ArrayList<E> extends AbstractList<E> {


    private class InnerListIterator implements ListIterator<E> {
        int _lastReturned = -1;
        int _pos = 0;

        @Override
        public void add(E Value) { //TODO
            if(_lastReturned != -1) {
                ArrayList.this.add(_pos, Value);
                ++_pos;
                _lastReturned = -1;
            }
            else throw new IllegalStateException();
        }

        @Override
        public boolean hasNext() {
            return _pos < _size;
        }

        @Override
        public boolean hasPrevious() {
            return _pos >= 0;
        }

        @Override
        public E next() {
            _lastReturned = _pos;
            return _array[_pos++];
        }

        @Override
        public int nextIndex() {
            return _pos;
        }

        @Override
        public E previous() {
            _lastReturned = _pos;
            return _array[--_pos];
        }

        @Override
        public int previousIndex() {
            return _pos - 1;
        }

        @Override
        public void remove() { //TODO
            if (_lastReturned == -1) throw new IllegalStateException();

            ArrayList.this.remove(_lastReturned);
            if (_lastReturned < _pos) _pos--;
            _lastReturned = -1;
        }

        @Override
        public void set(E e) { //TODO
            if (_lastReturned != -1) _array[_lastReturned] = e;

            else throw new IllegalStateException();
        }
    }
    /**
     * <b> Default </b> size of an initial array.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * <b> Initial </b> size of array.
     */
    private final int _initialCapacity;
    /**
     * A reference to an array with elements
     */
    private E[] _array;
    /**
     * Size of the array used for a the list.
     */
    private int _size;

    //@SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity <= 0)
            capacity = DEFAULT_INITIAL_CAPACITY;
        _initialCapacity = capacity;
        _array = (E[]) (new Object[capacity]);
        _size = 0;
    }

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public int size() {
        return _size;
    }

    /**
     * Extension of the array if no space in the current.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        if (_array.length < capacity) {
            E[] copy = (E[]) (new Object[capacity + capacity / 2]);
            System.arraycopy(_array, 0, copy, 0, _size);
            _array = copy;
        }
    }

    // validation of the index
    private void checkOutOfBounds(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= _size) throw new IndexOutOfBoundsException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        _array = (E[]) (new Object[_initialCapacity]);
        _size = 0;
    }

    @Override
    public boolean add(E value) {
        ensureCapacity(_size + 1);
        _array[_size] = value;
        _size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > _size) throw new IndexOutOfBoundsException();
        ensureCapacity(_size + 1);
        if (index != _size)
            System.arraycopy(_array, index, _array, index + 1, _size - index);
        _array[index] = value;
        _size++;
    }

    @Override
    public int indexOf(E value) {
        int i = 0;
        while (i < _size && !value.equals(_array[i])) ++i;
        return i < _size ? i : -1;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public E get(int index) {
        checkOutOfBounds(index);
        return _array[index];
    }

    @Override
    public E set(int index, E element) {
        checkOutOfBounds(index);
        E retValue = _array[index];
        _array[index] = element;
        return retValue;
    }

    @Override
    public E remove(int index) {
        checkOutOfBounds(index);
        E retValue = _array[index];
        int copyFrom = index + 1;
        if (copyFrom < _size) System.arraycopy(_array, copyFrom, _array, index, _size - copyFrom);
        --_size;
        return retValue;
    }

    @Override
    public boolean remove(E value) {
        int pos = 0;
        while (pos < _size && !_array[pos].equals(value))
            pos++;
        if (pos < _size) {
            remove(pos);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }
    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }
    private class InnerIterator implements Iterator<E> {
        int _pos = 0;

        @Override
        public boolean hasNext() {
            return _pos < _size;
        }

        @Override
        public E next() {
            return _array[_pos++];
        }
    }
}