#coding utf-8


def zamien_znaki(slowo):
    polskawe = {"ż":"z",
                'ó':'o',
                'ł':'l',
                'ć':"c",
                'ę':'e',
                'ś':'s',
                'ą':'a',
                'ź':'z',
                'ń':'n'}
    bez_znakow = ''
    for litera in slowo:
        if litera in polskawe.keys():
            litera = polskawe.get(litera)
            bez_znakow += litera
        else:
            bez_znakow += litera
    return bez_znakow
plik = open("popularne_slowa2023.txt", encoding="utf-8").read().split()
plik = set(plik)
wynik = {}
for slowo in plik:
    if zamien_znaki(slowo) not in wynik.keys():
        wynik[zamien_znaki(slowo)] = [slowo]
    else:
        wynik[zamien_znaki(slowo)].append(slowo)
for klucz,wartosc in wynik.items():
    if len(wartosc) >= 4:
        print(wartosc)
