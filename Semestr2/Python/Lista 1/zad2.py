def silnia(x):
    wynik = 1
    for i in range(2, x + 1):
        wynik *= i
    return wynik


for i in range(4, 101):

    liczba = len(str(silnia(i)))

    if liczba < 5:
        print(str(i) + "! ma", liczba, "cyfry")
    elif liczba < 22 or liczba % 100 == 14 or liczba % 100 == 12 or liczba % 100 == 13:
        print(str(i) + "! ma", liczba, "cyfr")
    else:
        if liczba % 10 == 2 or liczba % 10 == 4 or liczba % 10 == 3:
            print(str(i) + "! ma", liczba, "cyfry")
        else:
            print(str(i) + "! ma", liczba, "cyfr")
