def F(n):
    if n % 2 == 0:
        return n / 2
    else:
        return 3 * n + 1


def energia(n):
    energia = 1

    while F(n) != 1:
        n = F(n)
        energia += 1

    return energia


def wszystkie_energie(a, b):
    tab = []

    for i in range(a, b + 1):
        tab.append(energia(i))
    tab.sort()

    return tab


def mediana(lista):
    ilosc = len(lista)

    if ilosc % 2 == 0:
        mediana = (lista[ilosc // 2] + lista[ilosc // 2 + 1]) / 2
    else:
        mediana = lista[ilosc // 2]

    return mediana


def srednia(lista):
    suma = sum(lista)
    ilosc = len(lista)
    return suma / ilosc


energie = wszystkie_energie(3, 10)

print("min:", energie[0], "\nmax:", energie[len(energie) - 1])
print("mediana: ", mediana(energie))
print("srednia: ", srednia(energie))
