from turtle import *


def kwadrat(n):
    home()
    penup()
    forward(n / 2)
    left(90)
    forward(n / 2)
    pendown()
    left(90)
    forward(n)
    left(90)
    forward(n)
    left(90)
    forward(n)
    left(90)
    forward(n)
    penup()


licznik = 50
for i in range(0, 10):
    kwadrat(licznik)
    licznik = licznik * 1.2
