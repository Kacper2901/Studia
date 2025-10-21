plik = open("popularne_slowa2023.txt", encoding = "utf-8").read().split()
plik = set(plik)
licznik = 0
while plik:
    slowo = plik.pop()
    odwrocone_slowo = slowo[::-1]
    if odwrocone_slowo in plik:
        licznik +=1
        print(odwrocone_slowo,slowo)

print(licznik)