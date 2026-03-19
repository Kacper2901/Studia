package aisd.W01a;

public class Student {
	int nrIndeksu; 
	double stypendium; 
	public Student(int nr, double kwota){
		nrIndeksu=nr; 
		stypendium=kwota; 
	} 
	public void zwiekszStypendium(double kwota){
		stypendium+=kwota; 
	} 
	public void wyswietlDane(){ 
		System.out.printf("%6d %8.2f\n",nrIndeksu,stypendium); 
	}
}

