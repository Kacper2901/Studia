from math import sqrt  # pierwiastek kwadratowy


def potega(a, n):
    wynik = 1  # zmienna lokalna
    for i in range(n):
        wynik = wynik * a  # albo: wynik *= a
    return wynik


def kwadrat(n):
    for i in range(n-2):
        print("#")



def kwadrat2(n):
    for i in range(2+2*n):
        print()

    # wcześniej były definicje, poniżej jest część która się wykonuje


for i in range(10):
    print(i, 2 ** i, potega(2, i), sqrt(
        i))  # drukujemy kolejne liczby wraz z kolejnymi potęgami dwójki oraz kolejnymi pierwiastkami



for i in range(10):
    print("Przebieg:", i)
    print(20 * "-")
    if i <= 3:  # parzysta
        kwadrat2(i)
    else:  # czyli nieparzysta
        kwadrat(i)
