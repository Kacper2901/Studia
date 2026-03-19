package aisd.W01a;

public class RekurencjaDemo {

	public static void drukujCiagLiczb(int k){
		  if (k==0) System.out.println(k); 
		  else { 
			System.out.print(k+" "); 
			drukujCiagLiczb(k-1); 
		  } 
		} 
	public static void drukujPiramide(int n){
		  if (n==0) System.out.println(n); 
		  else {
		    drukujCiagLiczb(n);
		    drukujPiramide(n-1);
		  }
		}

	public static void main(String[] args) {
		drukujPiramide(7);

	}

}
