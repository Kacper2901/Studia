def sito(n):
    tab = []
    for i in range(0, n + 1):
        tab.append(1)
    tab[0] = 0
    tab[1] = 0
    tab[2] = 1
    for j in range(2, int(n ** (0.5)) + 1):
        if tab[j] == 1:
            k = 2
            while j * k <= n:
                tab[j * k] = 0
                k += 1
    return tab


def palindromy(a, b):
    tab = sito(b)
    pierwsze_palindromy = []
    for i in range(a, b + 1):
        if tab[i] == 1:
            liczba = str(i)
            if liczba == liczba[::-1]:
                pierwsze_palindromy.append(i)
    return pierwsze_palindromy

print(palindromy(10,1000))