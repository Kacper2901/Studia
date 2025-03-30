#include <iostream>
#include <vector>

struct Node
{
    std::vector<int> child;
    int sk_w = 0;
    int sk_wo = 0;
};

std::vector<Node> tree;
void dfs(int parent)
{
    int max_sk_W = 0;
    int max_sk_Wo = 0;
    for (int node : tree[parent].child)
    {
        if (node != parent)
        {
            dfs(node);
            if (tree[node].sk_w > max_sk_W)
                max_sk_W = tree[node].sk_w;
            if (tree[node].sk_wo > max_sk_Wo)
                max_sk_Wo = tree[node].sk_wo;
        }
    }
    tree[parent].sk_w += max_sk_Wo + 1;
    tree[parent].sk_wo += max_sk_W;
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

    dfs(0);

    std::cout << std::max(tree[0].sk_w, tree[0].sk_wo);
    return 0;
}