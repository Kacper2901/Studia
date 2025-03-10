#include <iostream>
#include <vector>

using namespace std;

long long extended_gcd(long long a, long long b, long long &x, long long &y)
{
    if (b == 0)
    {
        x = 1;
        y = 0;
        return a;
    }
    long long g = extended_gcd(b, a % b, x, y);
    long long tmp = x;
    x = y;
    y = tmp - (a / b) * y;
    return g;
}

long long mod_inverse(long long a, long long m)
{
    long long x, y;
    long long g = extended_gcd(a, m, x, y);
    return (x % m + m) % m;
}
int main()
{
    int N;
    cin >> N;

    while (N--)
    {
        int K;
        cin >> K;

        vector<long long> p(K), a(K);
        long long M = 1;

        for (int i = 0; i < K; i++)
        {
            cin >> p[i] >> a[i];
            M *= p[i];
        }

        long long result = 0;

        for (int i = 0; i < K; i++)
        {
            long long Mi = M / p[i];
            long long yi = mod_inverse(Mi, p[i]);
            result = (result + a[i] * Mi * yi) % M;
        }

        cout << result << endl;
    }

    return 0;
}
