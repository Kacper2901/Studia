import aisd.stack.*;
import aisd.queue.*;

import java.util.Queue;

public class RPN {
    ListQueue<String> rpnRepresentation;
    ListStack<Integer> numbers;

    RPN(ListQueue<String> rpnRepresentation){
        this.rpnRepresentation = rpnRepresentation;
        numbers = new ListStack<>();
    }

    int evaulate() throws EmptyQueueException, FullStackException, EmptyStackException {
        int result = 0;
        while(!rpnRepresentation.isEmpty()){
            String elem = rpnRepresentation.dequeue();
            if(isNumber(elem)){
                numbers.push(Integer.parseInt(elem));
            }
            else{
                if(numbers.size() < 2) throw new IllegalStateException();
                int a = numbers.pop();
                int b = numbers.pop();
                switch (elem){
                    case "+":
                        numbers.push(b+a);
                        break;
                    case "-":
                        numbers.push(b-a);
                        break;
                    case "*":
                        numbers.push(b*a);
                        break;
                    case "/":
                        if(a == 0) throw new IllegalArgumentException();
                        numbers.push(b/a);
                        break;
                    default: throw new IllegalArgumentException();
                    }

                }
            }
        if(numbers.size()!= 1) throw new IllegalArgumentException();
        return numbers.pop();
        }


    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
