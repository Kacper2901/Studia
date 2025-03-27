#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <map>

struct Point
{
    long long x, y, i;
};
bool point_sort(Point &a, Point &b)
{
    if (a.x != b.x)
        return a.x < b.x;
    return a.y < b.y;
}

void tab_sort(std::vector<std::vector<std::vector<Point>>> &tab, long long root)
{
    for (int i = 0; i < root; i++)
    {
        for (int j = 0; j < root; j++)
        {
            sort(tab[i][j].begin(), tab[i][j].end(), point_sort);
        }
    }
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::cout.tie(nullptr);
    long long N;
    std::cin >> N;

    long long root = std::ceil(std::sqrt(N));
    std::vector<std::vector<std::vector<Point>>> tab(root, std::vector<std::vector<Point>>(root));

    for (int i = 0; i < N; i++)
    {
        long long x, y;
        std::cin >> x >> y;

        long long px = x / root;
        long long py = y / root;
        px = std::min(px, root - 1);
        py = std::min(py, root - 1);

        Point p = {x, y, i + 1};
        tab[py][px].push_back(p);
    }

    tab_sort(tab, root);

    for (int i = 0; i < root; i++)
    {
        if (i % 2 == 0)
        {
            for (int j = 0; j < root; j++)
            {
                for (Point &p : tab[i][j])
                    std::cout << p.i << std::endl;
            }
        }
        else
        {
            for (int j = root - 1; j >= 0; j--)
            {
                for (Point &p : tab[i][j])
                    std::cout << p.i << " ";
            }
        }
    }

    return 0;
}
