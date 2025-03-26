#include <iostream>
#include <vector>
#include <cmath>
long long sum(long long a, long long b, long long N, std::vector<long long> &liczby, std::vector<std::vector<long long>> &tab)

{
    long long root = floor(sqrt(N));
    long long suma = liczby[a];

    if (b >= root)
    {
        for (int i = a + b; i < N; i = i + b)
        {
            suma += liczby[i];
        }
    }
    else
    {
        suma = tab[b - 1][a];
    }
    return suma;
}

int main()
{
    std::vector<long long> liczby;
    std::vector<long long> suma_bloku;
    long long N, Q, a, b;
    long long suma = 0;
    std::cin >> N;
    long long root = ceil(sqrt(N));
    std::vector<std::vector<long long>> tab;
    for (int i = 0; i < N; i++)
    {
        std::cin >> a;
        liczby.push_back(a);
    }

    for (int i = 1; i < root; i++)
    {
        std::vector<long long> blok(N, 0);
        for (int j = N - 1; j >= 0; j--)
        {
            blok[j] = liczby[j];
            if (i + j < N)
                blok[j] += blok[j + i];
        }
        tab.push_back(blok);
    }
    std::cin >> Q;
    for (int i = 0; i < Q; i++)
    {
        std::cin >> a >> b;
        std::cout << sum(a - 1, b, N, liczby, tab) << std::endl;
    }

    return 0;
}