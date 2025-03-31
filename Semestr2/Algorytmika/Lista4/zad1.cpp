#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>

const int N_max = 200000;
const int log_max = 20;
long long tab[N_max][log_max];
long long liczby[N_max];

long long minimum(long long tab[N_max][log_max], int l, int r)
{
    int len = r - l + 1;
    int log = (int)log2(len);
    return std::min(tab[l][log], tab[r - (1 << log) + 1][log]);
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

    for (int i = 0; i < Q; i++)
    {
        std::cin >> l >> p;
        std::cout << minimum(tab, l - 1, p - 1) << std::endl;
    }

    return 0;
}
