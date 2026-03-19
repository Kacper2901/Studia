package aisd.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayReverseIterator<T> implements Iterator<T> {
	  private T array[];
	  private int pos;

	  public ArrayReverseIterator(T anArray[]) {
	    array = anArray;
	    pos = array.length;
	  }
	  public boolean hasNext() {
	    return pos > 0;
	  }
	  public T next() throws NoSuchElementException {
	    if (hasNext())
	      return array[--pos];
	    else
	      throw new NoSuchElementException();
	  }
	  public void remove() {
	    throw new UnsupportedOperationException();
	  }
	}

