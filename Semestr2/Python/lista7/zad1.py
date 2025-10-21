import turtle
from time import perf_counter


def kwadrat(n, r, g, b):
    turtle.fillcolor(r, g, b)
    turtle.pendown()
    turtle.pencolor(r, g, b)
    turtle.begin_fill()
    for i in range(4):
        turtle.forward(n)
        turtle.left(90)
    turtle.end_fill()
    turtle.penup()
    turtle.right(90)
    turtle.forward(n)
    turtle.left(90)
turtle.Screen().setup(width=1.0,height=1.0)
turtle.tracer(0)
plik = open("obraz z tekstu").read().split()
for m in range(len(plik)):
    nowy = ""
    for znak in plik[m]:
        if znak != "(" and znak != ")":
            nowy += znak
    nowy = nowy.split(",")
    plik[m] = nowy
print(plik)
wysokosc = 63
szerokosc = int(len(plik) / 63)
turtle.colormode(255)
x = 90
y = 90
turtle.penup()
turtle.goto(x,y)
n = 5
for i in range(wysokosc):
    for j in range(int(szerokosc)):
        indeks_tablicy = i * szerokosc + j
        r = int(plik[indeks_tablicy][0])
        g = int(plik[indeks_tablicy][1])
        b = int(plik[indeks_tablicy][2])
        kwadrat(n, r, g, b)
    x = x - n
    turtle.goto(x,y)
turtle.update()
input()

