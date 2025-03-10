#include <iostream>
#include <cmath>

int divisors(int a){
    if(a == 0){
        return 0;
    }
    int dzielniki = 0;
    for(int i = 1; i*i<=a; i++){
        if(a%i == 0 && a/i != i){
            dzielniki+=2;
        }
        else{
            if (a%i == 0 && a/i ==i){
                dzielniki++;
            }
        }
    }

    return dzielniki;
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