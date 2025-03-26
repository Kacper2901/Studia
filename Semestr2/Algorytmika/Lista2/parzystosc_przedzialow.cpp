#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 1000000;
const int MAX_M = 300000;

int parent[MAX_N + 1], parity[MAX_N + 1];

int find(int x)
{
    if (parent[x] == x)
        return x;
    int root = find(parent[x]);
    parity[x] ^= parity[parent[x]];
    return parent[x] = root;
}

bool merge(int a, int b, int p)
{
    int rootA = find(a), rootB = find(b);
    if (rootA == rootB)
    {
        return ((parity[a] ^ parity[b]) == p);
    }
    parent[rootA] = rootB;
    parity[rootA] = parity[a] ^ parity[b] ^ p;
    return true;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    int N, M;
    cin >> N >> M;

    for (int i = 1; i <= N; i++)
    {
        parent[i] = i;
        parity[i] = 0;
    }

    int max_w = 0;
    for (int i = 1; i <= M; i++)
    {
        int a, b, p;
        cin >> a >> b >> p;
        if (!merge(a, b, p))
            break;
        max_w = i;
    }

    cout << max_w << "\n";
    return 0;
}
