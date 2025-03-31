#include <iostream>
#include <vector>

struct Node
{
    std::vector<int> child;
    int depth = 0;        // dlugosc galezi + krawedz z rodzicem
    int srednica_max = 0; // dlugosc poddrzew
};

std::vector<Node> tree;
void dfs(int node, int parent)
{
    int max1 = 0;
    int max2 = 0;

    for (int child : tree[node].child)
    {

        if (child != parent)
        {
            dfs(child, node);
            if (max1 < tree[child].depth + 1)
            {
                max2 = max1;
                max1 = tree[child].depth + 1;
            }
            else if (max2 < tree[child].depth + 1)
            {
                max2 = tree[child].depth + 1;
            }
            tree[node].srednica_max = std::max(tree[child].srednica_max, tree[node].srednica_max);
        }
    }
    tree[node].depth = max1;
    tree[node].srednica_max = std::max(max1 + max2, tree[node].srednica_max);
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

    std::cout << tree[0].srednica_max;
    return 0;
}