#include <iostream>

long long NWD(long long a, long long b){
    
    while (b != 0) {
        long long temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}

int main()
{
    long long a,b;
    std::cin>>a>>b;
    long long wynik = NWD(a,b);
    std::cout<<wynik;

    return 0;
}

