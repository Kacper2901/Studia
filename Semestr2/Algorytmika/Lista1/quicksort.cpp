#include <iostream>
#include <ctime>
#include <cstdlib>

bool same(long long a[], int l, int r){
    long long pierwsza = a[l];
    for(int i = l;i<=r;i++){
        if(a[i] != pierwsza) return false;
    }
    return true;
}

long long partition(long long a[], int l, int r){
    if (same(a,l,r)) return (l+r)/2;

    int pivot_i = l + rand()%(r-l+1);
    long long pivot_val =a[pivot_i];
    a[pivot_i] = a[r];
    a[r] = pivot_val;

    int i = l-1;
    for(int j = l; j<r; j++){
        if(a[j]<=pivot_val){
            i++;
            long long temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    i++;
    long long temp = a[i];
    a[i] = a[r];
    a[r] = temp;

    return i;

}
void qs(long long a[], int l, int r){
    if (l>=r){
        return;
    }
    int pivot = partition(a,l,r);
    qs(a,l,pivot-1);
    qs(a,pivot +1, r);
}


int main(){
    int n;
    std::cin>>n;
    long long* a = new long long[n];


    for(int i = 0; i<n; i++){
        std::cin>>a[i];
    }

    qs(a,0,n-1);
    for(int i = 0; i<n; i++) {
        std::cout<<a[i]<<" ";
    }


}