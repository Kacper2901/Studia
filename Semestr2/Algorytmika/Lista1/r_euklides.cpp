#include <iostream>
#include <utility>

long long euklides(int a, int b){
    if(b == 0){
        return a;
    }
    return euklides(b,a%b);
}

std::pair<int, int> r_e(int a, int b) {
    if (b == 0) {
        return {1, 0}; 
    }

    std::pair<int,int>previous = r_e(b,a%b);
    int x = previous.second;
    int y = previous.first - previous.second*(a/b);
    return{x,y};

}

int main(){
    int N, a,b;
    std::cin >> N;
    for(int i = 0; i<N; i++){
        std::cin>>a>>b;
        std::pair<int, int> obliczenia = r_e(a,b);
        std::cout<<obliczenia.first<<" "<<obliczenia.second<<" "<<a*obliczenia.first + b*obliczenia.second<<std::endl; 
    }
    

    return 0;
}