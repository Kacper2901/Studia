#include <iostream>
#include <vector>

int find(long long v, std::vector<long long> &parents)
{
    if (v == parents[v])
    {
        return v;
    }
    return parents[v] = find(parents[v], parents);
}

long long find_union(long long a, long long b, std::vector<long long> &parents, std::vector<std::vector<long long>> &dane, std::vector<long long> &rozmiar)
{
    long long rep_a = find(a, parents);
    long long rep_b = find(b, parents);
    long long min_a = dane[rep_a][1];
    long long min_b = dane[rep_b][1];
    long long max_a = dane[rep_a][0];
    long long max_b = dane[rep_b][0];
    long long il_a = dane[rep_a][2];
    long long il_b = dane[rep_b][2];
    if (rep_a == rep_b)
    {
        dane[rep_b] = {max_b, min_b, il_b + 1};
        return (max_a - min_a) * (il_a + 1);
    }

    if (rozmiar[rep_a] <= rozmiar[rep_b])
    {
        parents[rep_a] = rep_b;
        if (max_a > max_b)
            max_b = max_a;
        if (min_a < min_b)
            min_b = min_a;
        dane[rep_b] = {max_b, min_b, il_a + il_b + 1};
        rozmiar[rep_a] += rozmiar[rep_b];
        rozmiar[rep_b] += rozmiar[rep_a];
        return (max_b - min_b) * dane[rep_b][2];
    }
    else
    {
        parents[rep_b] = rep_a;
        if (max_b > max_a)
            max_a = max_b;
        if (min_b < min_a)
            min_a = min_b;
        dane[rep_a] = {max_a, min_a, il_a + il_b + 1};
        rozmiar[rep_a] += rozmiar[rep_b];
        rozmiar[rep_b] += rozmiar[rep_a];
        return (max_a - min_a) * dane[rep_a][2];
    }
}

int main()
{
    long long N, M, a, b;
    std::cin >> N >> M;
    std::vector<long long> parents(N, 0);
    std::vector<std::vector<long long>> dane(N, std::vector<long long>(3, 0));
    std::vector<long long> rozmiar(N, 1);

    for (int i = 0; i < N; i++)
    {
        parents[i] = i;
        dane[i][0] = i; // max
        dane[i][1] = i; // min
        dane[i][2] = 0; // liczba k
    }
    for (int i = 0; i < M; i++)
    {
        std::cin >> a >> b;
        std::cout << find_union(a - 1, b - 1, parents, dane, rozmiar) << std::endl;
    }
    return 0;
}