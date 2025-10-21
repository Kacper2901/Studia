from locale import windows_locale
from operator import index
from time import perf_counter


def wieza(n):
    tab = []
    i = 1
    ile_klockow = -1
    ile_spacji = n
    while i <= n:
        ile_klockow += 2
        ile_spacji -= 1
        for l in range(3):
            tab.append(ile_spacji*" "+ile_klockow*"*")
        i += 1
    return tab


def kilka_wiez(tab):
    maksymalna = max(tab)
    miejsce_na_pietro = 2*maksymalna - 1
    pietro = maksymalna
    for i in range(1,maksymalna):
        for element in tab:
            if element < pietro:
                print(" "*miejsce_na_pietro)
            else:
                spacje = int((miejsce_na_pietro - (2*i-1))/2)
                print(" " * spacje + (2 * i - 1) * "*" + " " * spacje, end="")
        pietro -= 1

def rysowanie(n):
    wieza1 = wieza(n)
    for poziom in wieza1:
        print(poziom)

lista = [3,1,5]





kilka_wiez(lista)

# rysowanie(3)
# print()
# rysowanie(5)