#include <iostream>
#include <vector>

const int MAX_N = 200000;
int graph[MAX_N];
int* graph_ptr[MAX_N];




int main(){
    int N,M;
    std::cin>>N>>M;
    for(int i = 0; i<M;i++){
        int a,b;
        std::cin>>a>>b;
        graph[a] = a;
        graph[b] = b;
        if(graph[a]== NULL){
            graph[b] = graph[a] ;
        }
        else if(graph[b]== NULL){
            *graph[b] = *graph[a];
        }
        else {
            
            *graph[b] = &graph[a];
    }


    return 0;
}