#include <iostream>
#include <vector>

int dfs(std::vector<std::vector<int>> &tree, int root, std::vector<bool> &visited, std::vector<std::pair<int, int>> &srednica)
{
    int curr_l = 0;
    int curr_l2 = 0;
    int l_child;
    int curr_max;
    visited[root] = true;

    if (tree[root].empty())
    {
        srednica[root].first = 1;
        srednica[root].second = 1;
        return 1;
    }

    for (int r : tree[root])
    {
        if (!visited[r] && r != root)
        {
            l_child = dfs(tree, r, visited, srednica);

            if (curr_l < l_child)
            {
                curr_l2 = curr_l;
                curr_l = l_child;
            }
            else if (curr_l2 < l_child)
                curr_l2 = l_child;
        }
    }

    srednica[root].second = curr_l + 1;
    srednica[root].first = curr_l + curr_l2;
    return curr_l + 1;
}
int maximum(std::vector<std::pair<int, int>> &srednica)
{
    int max = 0;

    for (std::pair p : srednica)
    {
        if (max < p.first)
            max = p.first;
    }

    return max;
}

int main()
{
    int a, b, N;
    std::cin >> N;
    std::vector<std::vector<int>> tree(N);
    std::vector<std::pair<int, int>> srednica(N);

    for (int i = 0; i < (N - 1); i++)
    {
        std::cin >> a >> b;
        tree[a - 1].push_back(b - 1);
        tree[b - 1].push_back(a - 1);
    }

    std::vector<bool> visited(N, false);

    dfs(tree, 0, visited, srednica);

    if (N > 1)
        std::cout << maximum(srednica);

    else
        std::cout << 0;

    return 0;
}