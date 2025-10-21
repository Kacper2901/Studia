def kwadrat1(n, znak):
    for i in range(0, 3 * n):
        for j in range(0, 3 * n):
            if j <= n - 1 and i < n:
                print(" ", end="")
            elif j < 2 * n and i < n:
                print(znak, end="")
            elif j < 3 * n and i < n:
                print(" ", end="")

            if i >= n and i < 2 * n:
                print(znak, end="")
            elif i >= 2 * n:
                if j <= n - 1:
                    print(" ", end="")
                elif j < 2 * n:
                    print(znak, end="")
                elif j < 3 * n:
                    print(" ", end="")
        print()


kwadrat1(8, "*")
