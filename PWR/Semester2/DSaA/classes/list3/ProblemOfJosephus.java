import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.queue.ListQueue;
import aisd.stack.EmptyStackException;

public class ProblemOfJosephus {
    ListQueue<Integer> queue = new ListQueue<>();

    ProblemOfJosephus(int numberOfPeople) throws FullQueueException {
        for(int i = 0; i<numberOfPeople; i++){
            queue.enqueue(i);
        }
    }


    int solveProblem(int k) throws EmptyQueueException, FullQueueException {
        while (queue.size() != 1){
            for(int i = 0; i < k - 1; i++){
                Integer temp = queue.dequeue();
                queue.enqueue(temp);
            }
            queue.dequeue();
        }
        return queue.first();
    }


}

void main() throws FullQueueException, EmptyQueueException {
    ProblemOfJosephus p = new ProblemOfJosephus(20);
    int solution = p.solveProblem(3);
    System.out.println(solution);
}
