package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element{
        E object;
        Element next=null;
        Element prev=null;

		public Element(E e) {
			object = e;
		}
		public Element(E e, Element next, Element prev) {
			object = e;
            this.next = next;
            this.prev = prev;
		}
	}
	
	Element head;
	Element tail;
	// can be realization with the field size or without
	int size;
	
	private class InnerIterator implements Iterator<E>{
		Element curr;
        Element lastReturned;
		// TODO maybe more fields....

		
		public InnerIterator() {
            curr = head;
		}
		@Override
		public boolean hasNext() {
			//TODO
			return curr != null;
		}
		
		@Override
		public E next() {
			//TODO
            if(!hasNext()) throw new NoSuchElementException();
            E currVal = curr.object;
            lastReturned = curr;
            curr = curr.next;
			return currVal;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element next;
        Element prev;
        Element lastReturned;
		// TODO maybe more fields....

        public InnerListIterator(){
            next = head;
            prev = null;
            lastReturned = null;
        }

		@Override
		public void add(E e) {
            //TODO
            if(!hasNext()){
                tail.next = new Element(e,null,tail);
                tail = tail.next;
                size++;
                return;
            }

            if(!hasPrevious()){
                head = new Element(e, head, null);
                size++;
                return;
            }

            next = new Element(e, next, prev);
            size++;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return next != null;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return prev != null;
		}

		@Override
		public E next() {
            if(hasNext()){
                prev = next;
                next = next.next;
                lastReturned = prev;
                return lastReturned.object;
            }

            throw new NoSuchElementException();
			// TODO Auto-generated method stub
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
            if(hasPrevious()){
                next = prev;
                prev = prev.prev;
                lastReturned = next;
                return lastReturned.object;
            }

            throw new NoSuchElementException();
			// TODO Auto-generated method stub
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(E e) {
			lastReturned.object = e;
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {
		// make a head and a tail	
		head=null;
		tail=null;
        size = 0;
	}
	
	@Override
	public boolean add(E e) {
        //TODO
        if(head == null){
            head = new Element(e);
            tail = head;
        }
        else {
            tail.next = new Element(e, null, tail);
            tail = tail.next;
        }
        size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
        if(index > size || index < 0) throw new NoSuchElementException();

        if(index == 0){
            Element newElement = new Element(element,head, null);
            if(head != null) head.prev = newElement;
            else tail = newElement;
            head = newElement;
            size++;
        }

        else if(index == size){
            add(element);
        }

        else{
            Element currElem = head;
            int currIdx = 0;
            while(currIdx != index){
                currIdx++;
                currElem = currElem.next;
            }
            Element newElement = new Element(element,currElem, currElem.prev);
            currElem.prev.next = newElement;
            currElem.prev = newElement;
            size++;
        }
		//TODO
	}

	@Override
	public void clear() {
        head = null;
        tail = null;
        size = 0;

		//TODO
	}

	@Override
	public boolean contains(E element) {
        Element currElement = head;

        while(currElement != null){
            if(currElement.object.equals(element)){
                return true;
            }
            currElement = currElement.next;
        }
		//TODO
		return false;
	}

	@Override
	public E get(int index) {
		//TODO
        if(index > size - 1 || index < 0) throw new NoSuchElementException();
        int currIdx = 0;
        Element currElement = head;
        while (currIdx != index){
            currIdx++;
            currElement = currElement.next;
        }


		return currElement.object;
	}

	@Override
	public E set(int index, E element) {
        //TODO
        if(index > size - 1 || index < 0) throw new NoSuchElementException();
        int currIdx = 0;
        Element currElement = head;
        while (currIdx != index){
            currIdx++;
            currElement = currElement.next;
        }
        E tempElement = currElement.object;
        currElement.object = element;
        return  tempElement;
    }

	@Override
	public int indexOf(E element) {
		//TODO
        if(size == 0) return -1;
        int currIdx = 0;
        Element currElem = head;
        while (currElem != null){
            if(currElem.object.equals(element)) return currIdx;
            currIdx++;
            currElem = currElem.next;
        }

		return -1;
	}

	@Override
	public boolean isEmpty() {
		//TODO

		return head == null;
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
	public E remove(int index) {
        if(index < 0 || index > size - 1) throw new NoSuchElementException();
        Element elementToReturn;

        if(index == 0 && size == 1){
            elementToReturn = head;
            head = null;
            tail = null;
            size--;
            return elementToReturn.object;
        }

        else if(index == 0){
            elementToReturn = head;
            head = head.next;
            head.prev = null;
            size--;
            return elementToReturn.object;
        }

        else if(index == size - 1){
            elementToReturn = tail;
            tail = tail.prev;
            tail.next = null;
            size--;
            return elementToReturn.object;
        }

        else{
            Element currElem = head;
            int currIdx = 0;
            while(currIdx != index){
                currElem = currElem.next;
                currIdx++;
            }
            currElem.prev.next = currElem.next;
            currElem.next.prev = currElem.prev;
            size--;
            return currElem.object;
        }
    }

	@Override
	public boolean remove(E e) {
		//TODO
        if (this.isEmpty()) return false;
        if(head.object.equals(e) && size == 1){
            head = null;
            tail = null;
            size--;
            return true;
        }
        else if (head.object.equals(e)) {
            head = head.next;
            head.prev = null;
            size--;
            return true;
        }
        else{
            Element currElem = head;
            while(currElem != null){
                if(currElem.object.equals(e) && tail == currElem){
                    tail = tail.prev;
                    tail.next = null;
                    size--;
                    return true;
                }
                else if(currElem.object.equals(e)){
                    currElem.prev.next = currElem.next;
                    currElem.next.prev = currElem.prev;
                    size--;
                    return true;
                }
                else{
                    currElem = currElem.next;
                }
            }
            return false;
        }
    }

	@Override
	public int size() {
		//TODO
		return size;
	}
	public String toStringReverse() {
		ListIterator<E> iter=new InnerListIterator();
		while(iter.hasNext())
			iter.next();
		String retStr="";
        while(iter.hasPrevious()){
            retStr += iter.previous().toString();
        }
		//TODO use reverse direction of the iterator 
		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
		//TODO
        if(this.isEmpty()) {
            this.head = other.head;
            this.tail = other.tail;
            size = other.size;
            other.clear();
            return;
        }
        if(other.isEmpty() || other == this) return;

        this.tail.next = other.head;
        other.head.prev = this.tail;
        this.tail = other.tail;
        size += other.size;
        other.clear();
    }

    public void remDup(){
        if(this.isEmpty()) return;
        Element currElem = head;
        while(currElem.next != null){
            while (currElem.next.object.equals(currElem.object)){
                Element newNext = currElem.next.next;
                currElem.next = newNext;
                newNext.prev = currElem;
                size--;
            }
            currElem = currElem.next;
        }


    }
}

