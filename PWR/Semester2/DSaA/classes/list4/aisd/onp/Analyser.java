package aisd.onp;
import java.io.*;

import aisd.queue.ArrayQueue;
import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.queue.IQueue;

public class Analyser
{
	StreamTokenizer wej= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	IQueue<Object> onp = new ArrayQueue<Object>();
	IQueue<Object> analyze() throws IOException
	{ 
		// ustawiamy analizator tak aby nowa linia (EOL) '/' i '-' by�y traktowane jako samodzielne tokeny
		// pod poj�ciem liczby rozumiemy liczb bez znak
		wej.eolIsSignificant(true);
		wej.ordinaryChar('/');
		wej.ordinaryChar('-');
		System.out.println(" Wprowadz wyra�enie ");
		wej.nextToken();
		// ka�da metoda startuje z wczytanym piewrszym tokenem
		try {
			expression();
		} catch (FullQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return onp;
	}

	void expression()throws IOException, FullQueueException
	{ 
		int oper=wej.ttype;
		if(oper!='+' && oper!='-') // liczba lub wyrazenie w () - czyli skladnik
		{
			term(); 
			oper=wej.ttype;
		}
		else 
			onp.enqueue(new Double(0.0));
		while(oper=='+' || oper=='-')
		{ 
			wej.nextToken();
			term();
			onp.enqueue(new Character((char)oper));
			oper=wej.ttype;
		}
	}

	void term() throws IOException, FullQueueException
	{
		factor();
		int oper=wej.ttype;
		while(oper=='*' ||oper=='/')
		{ 
			wej.nextToken();
			factor();
			onp.enqueue(new Character((char)oper));
			oper=wej.ttype;
		}
	}
	void factor()throws IOException, FullQueueException
	{ 
		if(wej.ttype==StreamTokenizer.TT_NUMBER) 
			onp.enqueue(new Double(wej.nval));
		// je�li nie liczba to musi by� wyra�enie w nawiasach
		else {
			wej.nextToken(); 
			expression();
		}
		wej.nextToken();
	}

	static String toString(IQueue<Object> onpQueue){
		String str="";
		IQueue<Object> copyQueue=new ArrayQueue<Object>(onpQueue.size());
		try {
			while(!onpQueue.isEmpty()){
				Object elem;
				elem = onpQueue.dequeue();
				copyQueue.enqueue(elem);
				str+=elem.toString()+", ";
			}
			while(!copyQueue.isEmpty()){
				onpQueue.enqueue(copyQueue.dequeue());
			}
		} catch (EmptyQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FullQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return str;
	}

	public static void main(String[] args) {
		Analyser analyser=new Analyser();
		try {
			IQueue<Object> onpQueue=analyser.analyze();
			System.out.println(toString(onpQueue));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

