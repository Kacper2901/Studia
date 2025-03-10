#include <iostream>
int MAX_N = 1000000;
int MOD = 1000000007;
long long szybkie_potegowanie(long long a, long long n){
   long long wynik = 1;
   while (n>0) {
   if (n%2==1){
        wynik = (wynik*a)%MOD;    
   } 
   
    a=(a*a)%MOD;
    n/=2;

   }
   return wynik;
}
long long odwrotnosc(long long x){
    long long wynik = szybkie_potegowanie(x,MOD-2);
    return wynik%MOD;
}

long long dwumian_newtona(long long silnie[], long long x,long long y){
    long long wynik;
    
    wynik = ((silnie[x] * odwrotnosc(silnie[y]) % MOD) * odwrotnosc(silnie[x - y]) % MOD) % MOD;
    return wynik;
}

int main(){
    long long silnie[MAX_N +1], a,b,N;
    for(int i=0;i<=MAX_N;i++){
        silnie[i] = 1;
    }
    for(int i=2;i<=MAX_N;i++){
        silnie[i] = i*silnie[i-1]%MOD;
    }
    std::cin>>N;
    for(int i=0;i<N;i++){
        std::cin>>a>>b;
        std::cout<<dwumian_newtona(silnie,a,b)%MOD<<std::endl;
    }


    return 0;
}