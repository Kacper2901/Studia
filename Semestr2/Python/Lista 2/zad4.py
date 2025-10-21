from duze_cyfry import daj_cyfre


def duze_liczby_calkowite(tm):
    liczba = list(str(tm))
    dlc = ["", '', '', '', '']

    for cyfra in liczba:
        tab = daj_cyfre(int(cyfra))
        licznik = 0
        for r in tab:
            licznik += 1
            dlc[licznik - 1] += tab[licznik - 1] + " "

    for element in dlc:
        print(element)


duze_liczby_calkowite(1234)
