slowo = "banan"

szyfr = {}
licznik = 1

for litera in slowo:
    if litera not in szyfr:
        szyfr[litera] = licznik
        licznik += 1

for litera in slowo:
    print(szyfr[litera],end="")
        
    