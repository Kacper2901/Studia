def koperta(n):
    for i in range(0, 2 * n + 1):
        if i == 0 or i == 2 * n:
            print((2 * n + 1) * "*", end="")
        else:
            for j in range(0, 2 * n + 1):
                if j == i or j == 2 * n - i or j == 0 or j == 2 * n:
                    print("*", end="")
                else:
                    print(" ", end="")
        print()


koperta(10)
