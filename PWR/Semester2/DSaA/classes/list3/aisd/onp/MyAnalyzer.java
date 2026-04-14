package aisd.onp;

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

	private class Operator{
		final char _ch;
		public Operator(char ch) {
			_ch=ch;
		}
		int getPriority(){
			if(_ch=='*' || _ch=='/') return 2;
			if(_ch=='+' || _ch=='-') return 1;
			return 0;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ""+_ch;
		}
	}
	private class LeftBracket{
		@Override
		public String toString() {
			return "(";
		}
	}

	// na razie mamy tylko implementacje stosu i kolejki
	// o ograniczonej pojemnoœci
	public final int MAX_NUMBER_OF_TOKENS=100;

	IQueue<Object> analyze(String inputStr){
		StreamTokenizer st= new StreamTokenizer(new StringReader(inputStr));
		//znak '/' jest domyœlnie traktowany specjalnie, 
		// ma byæ traktowany jako zwyk³y znak
		st.ordinaryChar('/');
		//znak '-' jest domyœlnie do³¹czony do liczby (ujemnej),
		// ma byæ traktowany jako zwyk³y znak
		st.ordinaryChar('-');
		IQueue<Object> queue=new ArrayQueue<Object>(MAX_NUMBER_OF_TOKENS);
		IStack<Object> stack=new ArrayStack<Object>(MAX_NUMBER_OF_TOKENS);
		try {
			while (st.nextToken() != st.TT_EOF){
				if (st.ttype == st.TT_NUMBER){
					queue.enqueue(new Double(st.nval));
				}
				else if(st.ttype=='(')
					stack.push(new LeftBracket());
				else if(st.ttype==')'){
					Object elem;
					do
					{
						elem=stack.pop();
						if(!(elem instanceof LeftBracket))
							queue.enqueue(elem);

					}while(!(elem instanceof LeftBracket));
				}
				else // Operator
				{
					Operator oper1=new Operator((char)st.ttype);
					int priorityOper1=oper1.getPriority();
					Object topElem;
					while(!stack.isEmpty() && 
							((topElem=stack.top()) instanceof Operator) && 
							((Operator) topElem).getPriority()>=priorityOper1){
						queue.enqueue(stack.pop());
					}
					stack.push(oper1);
				}
			}
			// na koniec przerzucenie elementów ze stosu na koniec kolejki
			while(!stack.isEmpty()){
				queue.enqueue(stack.pop());
			}
		} catch (IOException | 
				FullQueueException | 
				EmptyStackException | FullStackException e){
			e.printStackTrace();
		}

		return queue;
	}

	// przyda³by siê iterator w kolejce
	static public String toONPString(IQueue<Object> queue){
		StringBuffer buffer = new StringBuffer();
		IQueue<Object> copyQueue=new ArrayQueue<Object>();
		try {
			while(!queue.isEmpty()){
				Object elem;
				elem = queue.dequeue();
				buffer.append(elem).append(", ");
				copyQueue.enqueue(elem);
			}
			while(!copyQueue.isEmpty())
				queue.enqueue(copyQueue.dequeue());

		} catch (EmptyQueueException | FullQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer.setLength(buffer.length() - 2);
		return buffer.toString();
	}


	public static void main(String[] args) {
		MyAnalyzer analyzer=new MyAnalyzer();
		String inputStr="(2+4)*(3.7-9/3)";
		System.out.println("for: "+inputStr);
		IQueue<Object> queue=analyzer.analyze(inputStr);
		System.out.println(toONPString(queue));
	}

}
