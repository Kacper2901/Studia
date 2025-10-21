from time import perf_counter

plik = open("lalka-tom-I",encoding="utf-8").read().strip().split()
polskie = ("ą","ę","ć","ł","ń","ó","ś","ż","ź")
def czy_nie_polskie(slowo):
    dlugosc = 0
    for litera in slowo:
        if litera.isalpha():
            dlugosc +=1
        if litera in polskie:
            return False, 0
    return True, dlugosc

zdania = []
obecny = []
dlugosc_obecnego = 0
dlugosc_naj = 0

for slowo in plik:
    if czy_nie_polskie(slowo)[0]:
        if slowo:
            obecny.append(slowo)
            dlugosc_obecnego += czy_nie_polskie(slowo)[1]
    else:
        obecny.append(dlugosc_obecnego)
        zdania.append(obecny)
        obecny = []
        dlugosc_obecnego = 0

posortowane = sorted(zdania,key=lambda x: x[-1], reverse= True)
polskie = open("popularne_slowa2023.txt", encoding="utf-8").read()
k = 0
while True:
    status = 1
    for l in range(0,len(posortowane[k])-1):
        if posortowane[k][l] not in polskie:
            status = 0
    if status == 1:
        for i in range(len(posortowane[k])-1):
            print(posortowane[k][i] + " " ,end = "")
        print(posortowane[k][-1])
        break
    else:
        k += 1



