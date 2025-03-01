#include <iostream>
#include <cmath>

int divisors(int a){
    int i = 2;
    int potega = 0; //ile razy da sie podzielic
    int d = 1; //ilosc dzielnikow
    while(a%2 == 0){
        potega ++;
        a /= 2;
    }
    d *= potega + 1;
    
    for(int m = 3; m*m <= a; m+=2){
        potega = 0;
        while(a%m == 0){
            potega ++;
            a /= m;
        }
        d *= potega + 1;
        
    }
    if(a>1){
        return d*2;
    }

    return d;
}

int main(){
    int n,a;
    std::cin>>n;
    for(int i = 0; i<n; i++){
        std::cin>>a;
        std::cout<<divisors(a)<<std::endl;
    }

    return 0;
}