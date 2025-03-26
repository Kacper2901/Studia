#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>

const int N_max = 200000;
const int log_max = 18;
long long tab[N_max][log_max];
long long liczby[N_max];

long long minimum(long long tab[N_max][log_max], int l, int r, int N, int Q)
{
    int len = r - l + 1;
    int log = (int)log2(len);

    long long min_l = tab[l][log];
    long long min_r = tab[r - log][log];

    return std::min(min_l, min_r);
}

int main()
{
    int Q, N, l, p;
    long long x;
    int log;

    std::cin >> N >> Q;
    log = (int)log2(N) + 1;

    for (int i = 0; i < N; i++)
    {
        std::cin >> x;
        liczby[i] = x;
        tab[i][0] = x;
    }

    for (int j = 1; j < log; j++)
    {
        for (int i = 0; i + (1 << j) <= N; i++)
        {
            tab[i][j] = std::min(tab[i][j - 1], tab[i + (1 << (j - 1))][j - 1]);
        }
    }

    for (int i = 0; i < log; i++)
    {
        for (int j = 0; j < N; j++)
        {
            std::cout << tab[j][i] << " ";
        }
        std::cout << std::endl;
    }

    for (int i = 0; i < Q; i++)
    {
        std::cin >> l >> p;
        std::cout << minimum(tab, l, p, N, Q) << std::endl;
    }

    return 0;
}