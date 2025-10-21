import turtle


def kwadrat(n):
    turtle.pencolor("black")
    turtle.begin_fill()
    for i in range(4):
        turtle.forward(n)
        turtle.left(90)
    turtle.end_fill()
    turtle.penup()
    turtle.forward(n)
    turtle.pendown()


window = turtle.Screen()
window.tracer(0)
turtle.colormode(255)

r = 250
g = 20
b = 250
klocek = 2
turtle.fillcolor((r,g,b))
kwadrat(10)

for i in range(18):
    m = 1
    while m < klocek:
        turtle.fillcolor((max(int(r), 0), min(int(g), 255), max(int(b), 0)))
        kwadrat(10)
        m += 1
        r = r * 0.995
        b = b * 0.995
        g = g * 1.015

    klocek += 1
    turtle.backward(10)
    turtle.right(90)

window.update()
window.exitonclick()
