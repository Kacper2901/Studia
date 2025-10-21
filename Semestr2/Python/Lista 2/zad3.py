def kolo(n, wyrownanie):
    srodek = n / 2
    promien = n / 2

    for i in range(0, n):
        print(" " * wyrownanie, end="")
        for j in range(0, n):
            if (srodek - i - 0.5) ** 2 + (srodek - j - 0.5) ** 2 <= promien ** 2:
                print("*", end="")
            else:
                print(" ", end="")
        print()


def srodek(n):
    return n // 2


def balwan(x, y, z):
    max_sr = srodek(z)
    kolo(x, max_sr - srodek(x))
    kolo(y, max_sr - srodek(y))
    kolo(z, 0)


balwan(6, 8, 30)
