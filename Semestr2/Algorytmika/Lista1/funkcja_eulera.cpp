#include <iostream>
#include <vector>

const int MAX_X = 1000000;
std::vector<int> euler(MAX_X + 1);

void f_euler() {
    for (int i = 0; i <= MAX_X; i++)
        euler[i] = i;

    for (int i = 2; i <= MAX_X; i++) {
        if (euler[i] == i) {
            for (int j = i; j <= MAX_X; j += i) {
                euler[j] -= euler[j] / i;
            }
        }
    }
}

int main() {
    f_euler();
    int N, x;
    std::cin >> N;
    while (N--) {
        std::cin >> x;
        std::cout << euler[x] << "\n";
    }
    return 0;
}
