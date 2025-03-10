#include <iostream>
#include <vector>
#include <set>

const int MAX_N = 200000;
int colors[MAX_N];
std::vector<int> tree[MAX_N+1]; 
std::set<int> color_sets[MAX_N+1]; //tablica setow kolorow z poddrzew
int results[MAX_N+1]; //ilosc kolorow  w poddrzewach

void dfs(int node,int parent){
    color_sets[node].insert(colors[node]);

    for(int child: tree[node]){
        if (child == parent) continue;

        dfs(child,node);

        if(color_sets[child].size()>color_sets[node].size()){ //lepiej wrzucic mniejszy zbior do wiekszego
            swap(color_sets[node],color_sets[child]);
        }

        color_sets[node].insert(color_sets[child].begin(),color_sets[child].end());
    }
    results[node] = color_sets[node].size();
}

int main(){
    int N;
    std::cin>>N;
    for(int i = 1; i<=N; i++){
        std::cin>>colors[i];
    }
    for(int i = 1; i<=N-1; i++){
        int a,b;
        std::cin>>a>>b;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    dfs(1,-1);


    for(int i = 1; i<=N; i++){
        std::cout<<results[i]<<" ";
    }


 
}