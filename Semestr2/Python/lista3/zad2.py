def czy_pierwsza(n):
    if n <= 1:
        return False
    for i in range(2, int(n ** 0.5 + 1)):
        if n % i == 0:
            return False
    return True


licznik = 0  # (7777777)000 (7777777)001 (7777777)002 (7777777)003 (7777777)004 (7777777)005
tab = []
sprawdzone = []

for i in range(100, 1000):
    tab.append(str(i))

for liczba in tab:

    for miejsce in range(0, 4):
        liczba = list(liczba)
        liczba.insert(miejsce, "7777777")
        nowy = int(''.join(liczba))

        del liczba[miejsce]

        if nowy not in sprawdzone:
            sprawdzone.append(nowy)
            if czy_pierwsza(nowy) == True:
                licznik += 1

tab2 = []
for k in range(0, 10):
    tab2.append(int("777777700" + str(k)))
for m in range(10, 100):
    tab2.append(int("77777770" + str(m)))

for liczba_2 in tab2:
    if liczba_2 not in sprawdzone:
        sprawdzone.append(liczba_2)
        if czy_pierwsza(liczba_2) == True:
            licznik += 1

print(licznik)
