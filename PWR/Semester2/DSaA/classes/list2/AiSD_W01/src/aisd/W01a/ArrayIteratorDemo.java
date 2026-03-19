package aisd.W01a;

import java.util.Iterator;

import aisd.util.ArrayIterator;
import aisd.util.ArrayReverseIterator;

public class ArrayIteratorDemo {

	public static void main(String[] args) {
		Integer [] array={1,2,3,4,5,6,7};
		
		System.out.println("ArrayIterator:");
		Iterator<Integer> iter=new ArrayIterator<Integer>(array);		
		while(iter.hasNext())
			System.out.print(iter.next()+",");
		System.out.println();
		
		System.out.println("ArrayReverseIterator:");
		iter=new ArrayReverseIterator<Integer>(array);
		while(iter.hasNext())
			System.out.print(iter.next()+",");
		System.out.println();
		
		System.out.println("pêtla foreach:");
		for(Integer x:array)
			System.out.print(x+",");
		System.out.println();
			

	}

}
