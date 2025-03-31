#include <iostream>
#include <vector>

struct Node
{
    std::vector<int> child;
    int sk_w = 0;
    int sk_wo = 0;
};

std::vector<Node> tree;
void dfs(int node, int parent)
{

    for (int n : tree[node].child)
    {
        if (n != parent)
        {
            dfs(n, node);
            tree[node].sk_wo += std::max(tree[n].sk_w, tree[n].sk_wo);
        }
    }
    int connection;
    for (int n : tree[node].child)
    {
        if (n != parent)
        {
            connection = 1 + tree[n].sk_wo + (tree[node].sk_wo - std::max(tree[n].sk_w, tree[n].sk_wo));
            tree[node].sk_w = std::max(connection, tree[node].sk_w);
        }
    }
}

int main()
{

    int N, a, b;
    std::cin >> N;
    tree.resize(N);
    for (int i = 0; i < N - 1; i++)
    {
        std::cin >> a >> b;
        a--;
        b--;
        tree[a].child.push_back(b);
        tree[b].child.push_back(a);
    }

    dfs(0, -1);

    std::cout << std::max(tree[0].sk_w, tree[0].sk_wo);
    return 0;
}