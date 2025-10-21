def cezary(slowo, klucz):
    alfabet = "aąbcćdeęfghijklłmnńoóprsśtuwyzźż"
    wynik = ""
    for litera in slowo:
        if litera in alfabet:
            nowy_indeks = (alfabet.index(litera) + klucz) % len(alfabet)
            wynik += alfabet[nowy_indeks]
        else:
            wynik += litera
    return wynik

print(cezary("jamnik", 3))


def cezar(slowo,klucz):
        alfabet = "aąbcćdeęfghijklłmnńoóprsśtuwyzźż"
        przesuniety = alfabet[klucz:] + alfabet[:klucz]
        zaszyforwane = dict(zip(alfabet,przesuniety))
        for litera in slowo:
             print(zaszyforwane[litera],end='')
cezar("jamnik",3)