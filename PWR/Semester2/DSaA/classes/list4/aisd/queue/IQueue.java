package aisd.queue;


public interface IQueue<T> {
	  boolean isEmpty();
	  boolean isFull();
	  T dequeue() throws EmptyQueueException;
	  void enqueue(T elem) throws FullQueueException;
	  int size();  // zwraca liczbê elementów w kolejce 
      /** zwraca pierwszy element kolejki bez usuwania go */
	  T first() throws EmptyQueueException; 
}
