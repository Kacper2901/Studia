package aisd.W01a;

import java.util.Iterator;

import aisd.util.*;

public class StudentDemo {

	public static void StudentIteratorDemo(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów:
		// Let assume we have followed list of students:
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
		// Zwiêkszenie stypendium wszystkim studentom: 
		// Increasing scolarship for all students:	
		Iterator<Student> iterStud=new ArrayIterator<Student>(s);
		while(iterStud.hasNext())
			iterStud.next().increaseScholarship(50); 
		// Wyœwietlenie listy stypendiów: 
		iterStud=new ArrayIterator<Student>(s);
		while(iterStud.hasNext())
			iterStud.next().showData();		
	}



	public static void StudentForDemo(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów: 
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
		// Zwiêkszenie stypendium wszystkim studentom o kwotê dodatek: 
		for (int i = 0; i< s.length; i++) 
			s[i].increaseScholarship(50); 
		// Wyœwietlenie listy stypendiów: 
		for (int i = 0; i< s.length; i++) 
			s[i].showData();		
	}

	public static void StudentForEachDemo(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów: 
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
		// Zwiêkszenie stypendium wszystkim studentom o kwotê dodatek: 
		for (Student student:s) 
			student.increaseScholarship(50); 
		// Wyœwietlenie listy stypendiów: 
		for (Student student:s) 
			student.showData();

	}

	public static void StudentFilterDemo(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów: 
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
		// Zwiêkszenie stypendium wszystkim studentom, który maj¹ stypendium: 
		Iterator<Student> iterStud=new ArrayIterator<Student>(s);
		class MyPredicate implements Predicate<Student>{
			@Override
			public boolean accept(Student student) {
				return student.scholarship>0;
			}
		}
		iterStud=new FilterIterator<Student>(iterStud, new MyPredicate());
		while(iterStud.hasNext())
			iterStud.next().increaseScholarship(50); 
		// Wyœwietlenie listy stypendiów: 
		iterStud=new ArrayIterator<Student>(s);
		while(iterStud.hasNext())
			iterStud.next().showData();


	}

	// to samo co StudentFilterDemo, ale z klas¹ anonimow¹ po interfejsie Predicate<Student>
	public static void StudentFilterDemoWithAnonymousClass(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów: 
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
		// Zwiêkszenie stypendium wszystkim studentom, który maj¹ stypendium: 

		Iterator<Student> iterStud=new FilterIterator<Student>(
				new ArrayIterator<Student>(s), 
				// klasa anonimowa
				new Predicate<Student>(){
					@Override
					public boolean accept(Student student) {
						return student.scholarship>0;
					}
				});
		while(iterStud.hasNext())
			iterStud.next().increaseScholarship(50); 
		// Wyœwietlenie listy stypendiów: 
		iterStud=new ArrayIterator<Student>(s);
		while(iterStud.hasNext())
			iterStud.next().showData();


	}

	public static void main(String[] args) {
		StudentForDemo();
		StudentIteratorDemo();
		StudentForEachDemo();

	}

}
