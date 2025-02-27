#include <iostream>

long long pot(long long a,long long n){
    long long mod= 1000000000;
    long long wynik = 1;
    a = a % mod;
    while(n>0){
        if(n % 2 == 1){ 
            wynik = (a*wynik)%mod;
        }
        a = (a*a)%mod;
        n /= 2;
    }
    return wynik;

    }

int main(){
    long long a,b,potega;
    std::cin>>a>>b;
    potega = pot(a,b);
    std::cout<<potega;
    return 0;
}