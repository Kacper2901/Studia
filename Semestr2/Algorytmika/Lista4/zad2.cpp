#include <iostream>
#include <vector>

std::vector<long long> num;
std::vector<long long> tree;
int n;

void build(int node, int l, int r)
{
    if (l == r)
    {
        tree[node] = num[l];
    }
    else
    {
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        tree[node] = std::min(tree[2 * node], tree[2 * node + 1]);
    }
}

long long minim(int node, int n_l, int n_r, int l, int r)
{
    if (l > r)
        return 1000000001;
    if (l == n_l && r == n_r)
        return tree[node];
    int mid = (n_l + n_r) / 2;
    return std::min(
        minim(2 * node, n_l, mid, l, std::min(r, mid)),
        minim(2 * node + 1, mid + 1, n_r, std::max(l, mid + 1), r));
}

int main()
{
    int Q, a, b;
    std::cin >> n >> Q;
    num.resize(n);
    tree.resize(4 * n);
    for (int i = 0; i < n; i++)
        std::cin >> num[i];
    build(1, 0, n - 1);
    for (int i = 0; i < Q; i++)
    {
        std::cin >> a >> b;
        std::cout << minim(1, 0, n - 1, a - 1, b - 1) << std::endl;
    }
    return 0;
}
