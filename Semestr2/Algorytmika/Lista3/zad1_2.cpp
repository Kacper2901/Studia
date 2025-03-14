#include <iostream>
#include <vector>
#include <cmath>

void zmiana(std::vector<std::vector<long long>> &bloki, std::vector<long long> &sumy, long long a, long long u, int N)
{
    long long root = ceil(sqrt(N));
    long long blok_a = a / root;
    long long pos_a = a % root;

    sumy[blok_a] = sumy[blok_a] - bloki[blok_a][pos_a] + u;
    bloki[blok_a][pos_a] = u;
}

long long s_przedzialu(std::vector<std::vector<long long>> &bloki, std::vector<long long> &sumy, long long a, long long b, int N)
{
    long long root = ceil(sqrt(N));
    long long s = 0;
    long long blok_a = a / root;
    long long pos_a = a % root;
    long long blok_b = b / root;
    long long pos_b = b % root;

    if (blok_a == blok_b)
    {
        for (int i = pos_a; i <= pos_b; i++)
        {
            s += bloki[blok_a][i];
        }
        return s;
    }

    for (int i = blok_a + 1; i < blok_b; i++)
    {
        s += sumy[i];
    }

    for (int i = pos_a; i < bloki[blok_a].size(); i++)
    {
        s += bloki[blok_a][i];
    }

    for (int i = 0; i <= pos_b; i++)
    {
        s += bloki[blok_b][i];
    }

    return s;
}
int main()
{

    long long N, Q, a, s, b, typ;
    std::cin >> N >> Q;
    long long root = ceil(sqrt(N));
    std::vector<std::vector<long long>> bloki;
    std::vector<long long> elementy;
    std::vector<long long> sumy;
    s = 0;

    for (int i = 0; i < N; i++)
    {
        if (i % root == 0 && i > 0)
        {
            bloki.push_back(elementy);
            elementy.clear();
            sumy.push_back(s);
            s = 0;
        }
        std::cin >> a;
        elementy.push_back(a);
        s += a;
    }
    if (!elementy.empty())
    {
        bloki.push_back(elementy);
        sumy.push_back(s);
    }
    for (int i = 0; i < Q; i++)
    {
        std::cin >> typ >> a >> b;
        if (typ == 1)
            zmiana(bloki, sumy, a - 1, b, N);
        else
            std::cout << s_przedzialu(bloki, sumy, a - 1, b - 1, N) << std::endl;
    }

    return 0;
}