using System.ComponentModel.DataAnnotations;

class LazyList{
    public List<int> lista = new List<int>();

    public virtual int element(int i){
        int len = this.size();
        if(len>=i) return lista[i];
        else{
            while (len<i){
                
                lista.Add(len);
                len = len+1;                
            }
            return len;
        }
    }
    
    public int size(){
        return lista.Count();
    }

}

class LazyPrimeList:LazyList{
    public List<int> pierwsze = new List<int>(1000);
    public LazyPrimeList(){
        for(int i = 0; i<1000; i++){
            pierwsze.Add(1);
        }
        pierwsze[0] = 0;
        pierwsze[1] = 0;
        for(int i = 2; i*i<1000; i++){
            if(pierwsze[i] == 1){
                int k = 2;
                while(k*i<1000){
                    pierwsze[k*i] = 0;
                    k = k+1;
                }
            }
        }
    }
    override public int element(int i){
         int len = this.size();
        if(len>=i) return lista[i-1];
        else{
            if(len == 0) {
                lista.Add(2);
                len++;
                }
            int temp = lista[len-1];

            while (len<i){
                temp=temp+1;
                if(pierwsze[temp] == 1){
                    lista.Add(temp);
                    len = len+1; 
                }           
            }
            
            return lista[i-1];
        }

    }

}

class Zad2{
    public static void Main(){
        LazyList lista = new LazyList();
        LazyPrimeList pi = new LazyPrimeList();
        Console.WriteLine(lista.size());
        Console.WriteLine(lista.element(32));
        Console.WriteLine(lista.element(12));
        Console.WriteLine(lista.element(46));
        Console.WriteLine(lista.element(6));
        Console.WriteLine(" \n");
        Console.WriteLine(pi.element(5));
        Console.WriteLine(pi.element(3));
        Console.WriteLine(pi.element(4));
        Console.WriteLine(pi.element(10));
        Console.WriteLine(pi.element(9));



    }
}