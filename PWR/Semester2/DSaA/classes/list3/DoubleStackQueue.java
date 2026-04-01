import aisd.list.*;
import java.util.*;

import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.queue.IQueue;
import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;

public class DoubleStackQueue<E> {
    private ListStack<E> queueIn;
    private ListStack<E> queueOut;

    DoubleStackQueue(){
        queueIn = new ListStack<>();
        queueOut = new ListStack<>();
    }

    public boolean isEmpty() {
        return queueIn.isEmpty() && queueOut.isEmpty();
    }

    public boolean isFull() {
        return false;
    }

    public E dequeue() throws EmptyQueueException, EmptyStackException,FullStackException {
        if(size() == 0) throw new EmptyQueueException();
        if(!queueOut.isEmpty()){
            return queueOut.pop();
        }
        else{
            shiftData();
            return queueOut.pop();
        }
    }

    public void enqueue(E elem) throws FullQueueException, FullStackException {
        queueIn.push(elem);
    }

    public int size() {
        return queueIn.size() + queueOut.size();
    }

    public E first() throws EmptyQueueException, EmptyStackException, FullStackException {
        if(size() == 0) throw new EmptyQueueException();
        if(!queueOut.isEmpty()){
            return queueOut.top();
        }
        else{
            shiftData();
            return queueOut.top();
        }
    }

    private void shiftData() throws EmptyStackException, FullStackException {
        while(!queueIn.isEmpty()){
            queueOut.push(queueIn.pop());
        }
    }
}
