package aisd.rpn;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

import aisd.queue.ArrayQueue;
import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.queue.IQueue;
import aisd.stack.ArrayStack;
import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;
import aisd.stack.IStack;

public class MyAnalyzer {
	private static class Operator {
		final char _ch;

		public Operator(char ch) {
			_ch = ch;
		}

		int getPriority() {
			if (_ch == '*' || _ch == '/')
				return 2;
			if (_ch == '+' || _ch == '-')
				return 1;
			return 0;
		}

		@Override
		public String toString() {
			return "" + _ch;
		}
	}

	private static class LeftBracket {
		@Override
		public String toString() {
			return "(";
		}
	}

	// na razie mamy tylko implementacje stosu i kolejki
	// o ograniczonej pojemnoœci
	public static final int MAX_NUMBER_OF_TOKENS = 100;

	public static IQueue<Object> analize(String inputStr) {
		StreamTokenizer st = new StreamTokenizer(new StringReader(inputStr));
		st.ordinaryChar('/'); // traktuj ‘/’ jako zwyk³y znak
		st.ordinaryChar('-'); // traktuj ‘-’ jako zwyk³y znak
		IQueue<Object> queue = new ArrayQueue<Object>(MAX_NUMBER_OF_TOKENS);
		IStack<Object> stack = new ArrayStack<Object>(MAX_NUMBER_OF_TOKENS);
		try {
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				if (st.ttype == StreamTokenizer.TT_NUMBER)
					queue.enqueue(new Double(st.nval));
				else if (st.ttype == '(')
					stack.push(new LeftBracket());
				else if (st.ttype == ')') {
					Object elem;
					do {
						elem = stack.pop();
						if (!(elem instanceof LeftBracket))
							queue.enqueue(elem);
					} while (!(elem instanceof LeftBracket));
				} else // Operator
				{
					Operator oper1 = new Operator((char) st.ttype);
					int priorityOper1 = oper1.getPriority();
					Object topElem;
					while (!stack.isEmpty() && ((topElem = stack.top()) instanceof Operator)
							&& ((Operator) topElem).getPriority() >= priorityOper1) {
						queue.enqueue(stack.pop());
					}
					stack.push(oper1);
				}
			}
			// na koniec przerzucenie elementów ze stosu na koniec kolejki
			while (!stack.isEmpty())
				queue.enqueue(stack.pop());
		} catch (IOException | FullQueueException | EmptyStackException | FullStackException e) {
			e.printStackTrace();
		}
		return queue;
	}

	static public String toRPNString(IQueue<Object> queue) {
		StringBuffer buffer = new StringBuffer();
		IQueue<Object> copyQueue = new ArrayQueue<Object>();
		try {
			while (!queue.isEmpty()) {
				Object elem;
				elem = queue.dequeue();
				buffer.append(elem).append(", ");
				copyQueue.enqueue(elem);
			}
			while (!copyQueue.isEmpty())
				queue.enqueue(copyQueue.dequeue());
		} catch (EmptyQueueException | FullQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer.setLength(buffer.length() - 2);
		return buffer.toString();
	}

	public static void main(String[] args) {
		String inputStr = "(2+4)*(3.7-9/3)";
		System.out.println("for: " + inputStr);
		IQueue<Object> queue = MyAnalyzer.analize(inputStr);
		System.out.println(toRPNString(queue));
	}

}
