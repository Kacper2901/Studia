package aisd.stack;


public interface IStack<T>{
	  boolean isEmpty();
	  boolean isFull();
	  T pop() throws EmptyStackException;
	  void push(T elem) throws FullStackException;
	  int size();  // zwraca liczb� elementw na stosie
	  T top() throws EmptyStackException; 
	     // zwraca element ze szczutu stosu bez usuwania go
}

