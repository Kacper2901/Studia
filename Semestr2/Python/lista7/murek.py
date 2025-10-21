from turtle import *

def kwadrat(bok):
    begin_fill()
    for i in range(4):
        fd(bok)
        rt(90)
    end_fill()

def murek(s, bok):
    for a in s:
        if a in 'cvopg':
            kolor(a)
        if a == 'f':
            kwadrat(bok)
            fd(bok)
        elif a == 'b':
            kwadrat(bok)
            fd(bok)
        elif a == 'l':
            bk(bok)
            lt(90)
        elif a == 'r':
            rt(90)
            fd(bok)

def kolor(s):
    for a in s:
        if a == 'c':
            color('black', 'red')
        elif a == 'v':
            color('black', 'green')
        elif a == 'o':
            color('black', 'purple')
        elif a == 'p':
            color('black', 'yellow')
        elif a == 'g':
            color('black', 'blue')

ht()

tracer(0, 0)  # szybkie rysowanie
penup()
goto(-350,-50)
pendown()
kolory = "cvopg"
i = 9
bok = 15
murek("c",15)
while i > 0:
    murek("f" * i, 15)
    murek("r", 15)
    for n in range(0,2):
        murek("f" * (i), 15)
        murek("r", 15)
    murek("f" * (i), 15)
    murek("r", 15)

    murek(kolory[i%5], 15)
    i -= 1

i = 20
penup()
goto(100,-100)
pendown()

for n in range(0, 20):
    for m in range(0,n):
        murek("f", 15)
        murek(kolory[n%5],15)
    murek("r",15)


update()


done()
