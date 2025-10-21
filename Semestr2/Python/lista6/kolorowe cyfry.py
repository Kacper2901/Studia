import turtle, random
from duze_cyfry import cyfry
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
    return dlc


def kwadrat(n):
    for i in range(4):
        turtle.forward(n)
        turtle.left(90)
    turtle.forward(n)


window = turtle.Screen()
window.tracer(0)
pozycja = turtle.position()
bok = 10
cyfry = 773210
kolory = ["red", "green", "blue", "yellow", "purple", "orange"]
liczba = duze_liczby_calkowite(cyfry)
ilosc_cyfr = len(str(cyfry))
kolor = []

for i in range(ilosc_cyfr + 2):
    losowy_kolor = random.choice(kolory)
    kolor.append(losowy_kolor)

for linia in liczba:
    ktora_cyfra = 0
    licznik = 1
    for znak in linia:
        turtle.fillcolor(kolor[ktora_cyfra])
        licznik += 1
        if znak == "#":
            turtle.begin_fill()
            kwadrat(bok)
            turtle.end_fill()
        else:
            turtle.penup()
            turtle.forward(bok)
            turtle.pendown()
        if licznik % 6 == 0:
            ktora_cyfra += 1

    turtle.penup()
    turtle.setposition(pozycja)
    turtle.right(90)
    turtle.forward(bok)
    turtle.left(90)
    pozycja = turtle.position()
    turtle.pendown()

window.exitonclick()
