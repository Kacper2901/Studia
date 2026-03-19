package aisd.W01a;

import aisd.util.*;

public class FilterIteratorDemo {

	public static void testFilterIter(Integer[] array, Predicate<Integer> predicate){
		ArrayIterator<Integer> arrayIter=new ArrayIterator<Integer>(array);

		FilterIterator<Integer> filterIter=new FilterIterator<Integer>(arrayIter, predicate);

		while(filterIter.hasNext())
			System.out.print(filterIter.next()+",");
		System.out.println();
	}

	public static void testFilterIter1(){
		Integer[] tab={1,5,4,3,6,3,7,9,2};
		Predicate<Integer> predicate=new Predicate<Integer>() {		
			@Override
			public boolean accept(Integer arg) {
				return arg>3;
			}
		};
		testFilterIter(tab, predicate);

	}

	public static void testFilterIter2(){

		testFilterIter(new Integer[]{1,5,4,3,6,3,7,9,2}, 
			new Predicate<Integer>() {		
				@Override
				public boolean accept(Integer arg) {
					return arg>30;
				}
			}
		);

	}

	public static void testFilterIter3(){

		testFilterIter(new Integer[]{}, 
			new Predicate<Integer>() {		
				@Override
				public boolean accept(Integer arg) {
					return arg>30;
				}
			}
		);

	}
	
	public static void testFilterIter4(){

		testFilterIter(new Integer[]{2,3,5,0,2,3,5,7,4}, 
			new Predicate<Integer>() {		
				@Override
				public boolean accept(Integer arg) {
					return arg>=0;
				}
			}
		);

	}
	public static void main(String[] args) {
		testFilterIter1();
		testFilterIter2();
		testFilterIter3();
		testFilterIter4();

	}

}
