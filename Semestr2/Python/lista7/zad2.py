import turtle,random


def kwadrat(n, r, g, b):
    turtle.setheading(0)
    turtle.fillcolor(r, g, b)
    turtle.pendown()
    turtle.pencolor(r, g, b)
    turtle.begin_fill()
    for i in range(4):
        turtle.forward(n)
        turtle.left(90)
    turtle.end_fill()
    turtle.penup()


turtle.speed("fastest")
turtle.Screen().setup(width=1.0, height=1.0)
# turtle.tracer(0)
wysokosc = 63
turtle.colormode(255)
x = 90
y = 90
n = 5
turtle.penup()
turtle.goto(x, y)
plik = open("obraz z tekstu").read().split()
szerokosc = int(len(plik) / 63)

for m in range(len(plik)):
    nowy = ""
    for znak in plik[m]:
        if znak != "(" and znak != ")":
            nowy += znak
    nowy = nowy.split(",")
    plik[m] = nowy

zbior = {}
for i in range(wysokosc):
    wspolrzedna_y = y
    for j in range(int(szerokosc)):
        wspolrzedna_y -= n
        indeks_tablicy = i * szerokosc + j
        zbior[indeks_tablicy] = (x, wspolrzedna_y)
    x = x - n

elementy_tablicy = list(zbior.keys())

while len(elementy_tablicy) > 0:
    losowy_element = random.choice(elementy_tablicy)
    wartosc_elementu = zbior[losowy_element]
    elementy_tablicy.remove(losowy_element)

    turtle.penup()
    turtle.goto(wartosc_elementu[0], wartosc_elementu[1])
    r = int(plik[losowy_element][0])
    g = int(plik[losowy_element][1])
    b = int(plik[losowy_element][2])
    kwadrat(n, r, g, b)

# turtle.update()
input()
