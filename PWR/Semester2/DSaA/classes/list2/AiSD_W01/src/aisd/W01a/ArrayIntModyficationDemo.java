package aisd.W01a;

public class ArrayIntModyficationDemo {

	public static void main(String[] args) {
		int [] array={1,2,3,4,5,6};
		for(int elem:array)
			System.out.print(elem+",");
		System.out.println();

		for(int elem:array)
			elem+=10;

		for(int elem:array)
			System.out.print(elem+",");
		System.out.println();

	}

}
