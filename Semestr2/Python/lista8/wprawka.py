liczby = {
    1: 'jeden',

    2: 'dwa',

    3: 'trzy',

    4: 'cztery',

    5: 'pięć',

    6: 'sześć',

    7: 'siedem',

    8: 'osiem',

    9: 'dziewięć',

    10: 'dziesięć',

    11: 'jedenaście',

    12: 'dwanaście',

    13: 'trzynaście',

    14: 'czternaście',

    15: 'piętnaście',

    16: 'szesnaście',

    17: 'siedemnaście',

    18: 'osiemnaście',

    19: 'dziewiętnaście',

    20: 'dwadzieścia',

    30: 'trzydzieści',

    40: 'czterdzieści',

    50: 'pięćdziesiąt',

    60: 'sześćdziesiąt',

    70: 'siedemdziesiąt',

    80: 'osiemdziesiąt',

    90: 'dziewięćdziesiąt',

    100: 'sto',

    200: 'dwieście',

    300: 'trzysta',

    400: 'czterysta',

    500: 'pięćset',

    600: 'sześćset',

    700: 'siedemset',

    800: 'osiemset',

    900: 'dziewięćset'
}


def zamiana(x):
    dlugosc = len(str(x))
    x = str(x)
    if x == "0":
        return print("zero")

    if dlugosc == 3:
        print(liczby[int(x[0]) * 100], end=" ")
        if x[1] != "0":
            if x[1] == "1":
                print(liczby[int(x[1:3])],end = " ")
            else:
                print(liczby[int(x[1]) * 10], end=" ")
        if x[2] != "0" and x[1] != "1":
            print(liczby[int(x[2])], end=" ")

    if dlugosc == 2 and int(x) >= 20:
        print(liczby[int(x[0]) * 10], end=" ")
        if x[1] != "0":
            print(liczby[int(x[1])], end=" ")
    if dlugosc == 2 and int(x) < 20:
        print(liczby[int(x)], end=" ")
    if dlugosc == 1:
        print(liczby[int(x[0])], end=" ")


def tekst(tab):
    for slowo in tab:
        try:
            zamiana(int(slowo))
        except:
            print(slowo, end=" ")


for i in range(0, 1000):
    zamiana(i)
    print()

print()
zdanie = ['Ala', 'ma', "113", 'kotów', 'i', '557', 'kanarków']

tekst(zdanie)
print()
