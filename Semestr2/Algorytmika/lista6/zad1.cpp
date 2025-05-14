#include <iostream>
#include <vector>

const int BASE = 1 << 20; // przesunięcie indeksów
const int MAX_SIZE = BASE * 2;
std::vector<int> tree(MAX_SIZE);

int suffix_sum(int i, int l, int r, int wanted_l)
{
}

void update_tree(int i)
{
}

int main()
{
    int N, x;
    std::cin >> N;

    long long inwersje = 0;

    for (int i = 0; i < N; ++i)
    {
        std::cin >> x;
    }

    std::cout << inwersje << '\n';
    return 0;
}
