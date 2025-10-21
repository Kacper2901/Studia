from turtle import *


def kolo(promien, kolor, i):
    penup()

    while i < 13:
        penup()
        setposition(0, -50 + 20 * i)
        pendown()
        fillcolor(kolor[i % 3])
        begin_fill()
        pendown()
        circle(promien, -360, 1000)
        end_fill()

        i += 1
        promien -= 10
        kolo(promien, kolor, i)
    done()

speed("fastest")
setposition(0, -50)
pencolor("black")
pensize(1)
kolory = ["yellow", "green", "blue"]
kolo(150, kolory, 0)
