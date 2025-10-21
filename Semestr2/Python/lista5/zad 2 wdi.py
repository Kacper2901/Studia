#we: n - liczba naturalna, tab[k] - ilosc cyfr w zapisie silniowym (ostatnia najbardziej znaczaca)
#tablica  z liczba w systemie silniowym (pierwsza najbardziej znaczaca)

def zapis_silniowy(n,k):
    k=4
    tab = [0,0,0,0]
    silnia = 2
    miejsce = k - 1
    while n > 0:
        reszta = n % silnia
        tab[miejsce] = reszta
        n = n // silnia
        silnia += 1
        miejsce -= 1
    print(tab)
zapis_silniowy(100,4)