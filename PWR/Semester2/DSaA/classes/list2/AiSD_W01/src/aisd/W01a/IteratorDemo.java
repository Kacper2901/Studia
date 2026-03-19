package aisd.W01a;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IteratorDemo {
	
	public static Collection<String> generate(){
		LinkedList<String> col=new LinkedList<String>();
		col.add("Val0");
		col.add("Val1");
		col.add("Val2");
		col.add("Val3");
		return col;
	}
	
	
	
	public static void iterTest1(){
		System.out.println("Test 1");
		Collection<String> col=generate();
		Iterator<String> iter=col.iterator();
		String value;
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		iter.remove();
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		System.out.println(iter.hasNext());
		//value=iter.next();
		//System.out.println(value);

	}

	// 
	public static void iterTest2(){
		Collection<String> col=generate();
		Iterator<String> iter=col.iterator();
		String value;
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		iter.remove();
		try{
			iter.remove();
		}
		finally{
			
		}
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);
		System.out.println(iter.hasNext());
		value=iter.next();
		System.out.println(value);

	}
	public static void main(String[] args) {
		iterTest1();
	}

}
