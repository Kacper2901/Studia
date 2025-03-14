#include <iostream>
#include <vector>

long long find_set(int v, std::vector<int> &parent)
{
    if (v == parent[v])
        return v;
    return parent[v] = find_set(parent[v], parent);
}

long long unionn(int a, int b, std::vector<int> &parent, std::vector<long long> &wielkosc, int &spojne)
{
    int rep_a, rep_b;
    rep_a = find_set(a, parent);
    rep_b = find_set(b, parent);
    if (rep_a == rep_b)
    {
        return wielkosc[rep_a];
    }
    if (wielkosc[rep_a] >= wielkosc[rep_b])
    {

        parent[rep_b] = rep_a;
    }
    else
    {
        parent[rep_a] = rep_b;
    }

    spojne -= 1;
    wielkosc[rep_a] += wielkosc[rep_b];
    wielkosc[rep_b] = wielkosc[rep_a];
    return wielkosc[rep_a];
}

int main()
{
    int N, M, a, b, obecne;
    int maksymalne = 1;
    std::cin >> N >> M;
    int spojne = N;
    std::vector<long long> wielkosc(N, 1);
    std::vector<int> parent(N, 0);
    for (int i = 0; i < N; i++)
    {
        parent[i] = i;
    }

    for (int i = 0; i < M; i++)
    {
        std::cin >> a >> b;
        obecne = unionn(a - 1, b - 1, parent, wielkosc, spojne);
        if (obecne > maksymalne)
        {
            maksymalne = obecne;
        }
        std::cout << spojne << " " << maksymalne << std::endl;
    }
    return 0;
}