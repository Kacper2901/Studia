package aisd.W01a;

import java.util.Iterator;

import aisd.util.ArrayIterator;

public class ArrayStudentModificationDemo {

	public static void Demo1(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów: 
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
    	for (Student student:s) 
		  student=new Student(10,1000);
		for (Student student:s) 
		  student.showData();
		
	}

	public static void Demo2(){
		// Za³ó¿my, ¿e mamy nastêpuj¹c¹ listê studentów: 
		Student [] s = new Student[5];
		s[0]=new Student(1,500);
		s[1]=new Student(2,400);
		s[2]=new Student(3,0);
		s[3]=new Student(4,500);
		s[4]=new Student(5,700); 
    	Iterator<Student> iter=new ArrayIterator<Student>(s);
    	Student stud=iter.next();
    	stud=new Student(10,1000);
		for (Student student:s) 
		  student.showData();
		
	}

	public static void main(String[] args) {
		Demo1();

	}

}
