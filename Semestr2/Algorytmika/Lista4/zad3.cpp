#include <iostream>
#include <vector>
#include <algorithm>

int N, Q;
std::vector<std::pair<long long, int>> suf;
std::vector<long long> num;
std::vector<std::tuple<int, int, int>> query; // a,b, id

long long find_min(int a_val, const std::vector<std::pair<long long, int>> &suf)
{
    int l = 0, r = suf.size() - 1;
    int res = -1;

    while (l <= r)
    {
        int m = (l + r) / 2;
        if (suf[m].second <= a_val)
        {
            res = m;
            l = m + 1;
        }
        else
        {
            r = m - 1;
        }
    }

    if (res != -1)
        return suf[res].first;
    else
        return -1; // lub np. LLONG_MAX
}

bool sort_pair(const std::tuple<int, int, int> &a, const std::tuple<int, int, int> &b)
{
    return std::get<1>(a) < std::get<1>(b); // sortujemy po b
}

int main()
{
    std::cin >> N >> Q;
    num.resize(N);
    for (int i = 0; i < N; i++)
        std::cin >> num[i];

    for (int i = 0; i < Q; i++)
    {
        int a, b;
        std::cin >> a >> b;
        query.emplace_back(a - 1, b - 1, i);
    }

    std::sort(query.begin(), query.end(), sort_pair);

    std::vector<long long> res(Q);
    int j = 0;
    int curr_b = std::get<1>(query[0]);
    int prev_b = std::get<1>(query[0]);

    for (int i = 0; i < N; i++)
    {
        curr_b = std::get<1>(query[i]);
        if (prev_b != curr_b)
        {
            while (std::get<1>(query[j]) == i)
            {
                res[std::get<2>(query[j])] = find_min(std::get<0>(query[j]), suf);
                j++;
            }
        }
        prev_b = curr_b;

        while (!suf.empty() && suf.back().first >= num[i])
            suf.pop_back();
        suf.push_back({num[i], i});
    }
    
    for (int i = 0; i < Q; i++)
    {
        std::cout << res[i] << std::endl;
    }

    return 0;
}
