package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{
	
	private class Element{
		public Element(E e) {
			this.object=e;
		}
		E object;
		Element next=null;
	}
	
	Element sentinel;

    private class InnerIterator implements Iterator<E> {
        Element currNode;
        Element prevNode;
        Element lastRet = null;

        public InnerIterator() {
            currNode = sentinel;
        }

        @Override
        public boolean hasNext() {
            return currNode.next != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            prevNode = currNode;
            currNode = currNode.next;
            lastRet = currNode;
            return currNode.object;

        }

        @Override
        public void remove() {
            if (lastRet == null) {
                throw new IllegalStateException();
            }

            prevNode.next = currNode.next;
            currNode = prevNode;
            lastRet = null;
        }
    }
	
	public OneWayLinkedList() {
        // TODO
		this.sentinel = new Element(null);
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E e) {
        Element curr = sentinel;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = new Element(e);
        return true;
        // TODO Auto-generated method stub
	}

	@Override
	public void add(int index, E element) throws NoSuchElementException {
        if(index < 0) throw new NoSuchElementException();

        int currIdx = -1;
        Element currElement = sentinel;

        while(currIdx < index - 1){
            currElement = currElement.next;
            if(currElement == null) throw new NoSuchElementException();
            currIdx++;
        }

        Element tempNext = currElement.next;
        currElement.next = new Element(element);
        currElement.next.next = tempNext;

		// TODO Auto-generated method stub
	}

	@Override
	public void clear() {
        sentinel.next = null;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contains(E element) {
        Element curr = sentinel.next;
        while (curr != null){
            if(curr.object.equals(element)) return true;
            curr = curr.next;
        }
		// TODO Auto-generated method stub
		return false;
	}

	@Override
        public E get(int index) throws NoSuchElementException {
            if(index < 0) throw new NoSuchElementException();

            int currIdx = -1;
            Element currElement = sentinel;

            while(currIdx < index){
                currElement = currElement.next;
                if(currElement == null) throw new NoSuchElementException();
                currIdx++;
            }

            return currElement.object;
            // TODO Auto-generated method stub
        }

	@Override
	public E set(int index, E element) throws NoSuchElementException {
        if(index < 0) throw new NoSuchElementException();

        int currIdx = -1;
        Element currElement = sentinel;

        while(currIdx < index){
            currElement = currElement.next;
            if(currElement == null) throw new NoSuchElementException();
            currIdx++;
        }

        E tempCurrVal = currElement.object;
        currElement.object = element;

		// TODO Auto-generated method stub
		return tempCurrVal;
	}

	@Override
	public int indexOf(E element) {
        int currIdx = 0;
        Element curr = sentinel.next;
        while (curr != null){
            if(curr.object.equals(element)) return currIdx;
            currIdx++;
            curr = curr.next;
        }
        // TODO Auto-generated method stub
        return -1;
	}

	@Override
	public boolean isEmpty() {
        return sentinel.next == null;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
		// TODO Auto-generated method stub
        if(index < 0) throw new NoSuchElementException();

        int currIdx = -1;
        Element currElement = sentinel;

        while(currIdx < index - 1){
            currElement = currElement.next;
            if(currElement == null) throw new NoSuchElementException();
            currIdx++;
        }
        if(currElement.next == null) throw new NoSuchElementException();
        E tempNextVal = currElement.next.object;
        currElement.next = currElement.next.next;
        return tempNextVal;
	}

	@Override
	public boolean remove(E e) {
        Element curr = sentinel;
        while (curr.next != null){
            if(curr.next.object.equals(e)){
                curr.next = curr.next.next;
                return true;
            }
            curr = curr.next;
        }
        return false;
        // TODO Auto-generated method stub
	}

	@Override
	public int size() {
        int size = 0;
        Element curr = sentinel;
        while (curr.next != null){
            size++;
            curr = curr.next;
        }
        return size;
        // TODO Auto-generated method stub
	}




    public void removeEven(){
        InnerIterator it = new InnerIterator();
        int i = -1; //sentinel
        while(it.hasNext()){
            i++;
            it.next();
            if(i % 2 == 0){
                it.remove();
            }
        }
    }

}

