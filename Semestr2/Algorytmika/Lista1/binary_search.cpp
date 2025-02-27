#include <iostream>

int bs(int l, int r, int a[], int x){
    int srodek;
    
    if(l > r){
        return -1;
    }
    srodek = (l + r) /2;
    if(a[srodek] == x){
        return srodek + 1;
    }
    if(a[srodek]>x){
        return bs(l,srodek-1,a,x);
    }
    else{
        return bs(srodek+1,r,a,x);
    }
}
int main(){
    int l,r,x,m;

    std::cin>>r;
    int a[r];

    for(int i = 0; i<r;i++){
        std::cin>>a[i];
    }
    std::cin>>m;
    for(int i = 0; i < m; i++){
        std::cin>>x;
        std::cout<<bs(0,r-1,a,x)<<std::endl;
    }
    return 0;
}
