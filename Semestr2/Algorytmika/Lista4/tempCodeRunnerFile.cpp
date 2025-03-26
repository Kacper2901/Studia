#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>

const int N_max = 200000;
const int log_max = 18;
long long tab[N_max][log_max];
long long liczby[N_max];

int main()
{
    int Q, N;
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
        for (int i = 0; i + (1 << j) - 1 < N; i++)
        {
            tab[i][j] =
                std::min(tab[i][j - 1], tab[i + (1 << (j - 1))][j - 1]);
        }
    }

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < log; j++)
        {
            std::cout << tab[i][j] << " ";
        }
        std::cout << std::endl;
    }

    return 0;
}