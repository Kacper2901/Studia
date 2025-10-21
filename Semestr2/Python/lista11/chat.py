import turtle, time

# Funkcja rysująca koło
def kolo(x, y, kolory):
    turtle.penup()
    turtle.goto(x, y - 10)  # Przesunięcie do środka koła
    turtle.pendown()
    turtle.fillcolor(kolory["r"], kolory["g"], kolory["b"])
    turtle.begin_fill()
    turtle.circle(10)
    turtle.end_fill()

# Funkcja rysująca żółwia
def zolw(rysik, x, y):
    def rysuj_kolo(x, y, promien, kolor):
        rysik.penup()
        rysik.goto(x, y - promien)
        rysik.pendown()
        rysik.fillcolor(kolor)
        rysik.begin_fill()
        rysik.circle(promien)
        rysik.end_fill()

    rysik.clear()
    # Głowa
    rysuj_kolo(x, y + 15, 7.5, "green")
    # Ciało
    rysuj_kolo(x, y - 7.5, 15, "darkgreen")
    # Nogi
    rysuj_kolo(x - 12.5, y - 22.5, 5, "green")
    rysuj_kolo(x + 12.5, y - 22.5, 5, "green")
    rysuj_kolo(x - 12.5, y + 7.5, 5, "green")
    rysuj_kolo(x + 12.5, y + 7.5, 5, "green")
    # Oczy
    rysuj_kolo(x - 3.5, y + 17.5, 1.5, "white")
    rysuj_kolo(x + 3.5, y + 17.5, 1.5, "white")
    rysuj_kolo(x - 3.5, y + 17.5, 0.75, "black")
    rysuj_kolo(x + 3.5, y + 17.5, 0.75, "black")

# Funkcja do zmiany kolorów
def colour(kolory):
    for element in kolory:
        if kolory[element] == 254:
            kolory[element] -= 1
        elif kolory[element] == 0:
            kolory[element] += 2
        else:
            kolory[element] += (-1 if kolory[element] % 2 == 1 else 1)
    return kolory

# Ustawienia ekranu
turtle.tracer(0)
turtle.hideturtle()
turtle.colormode(255)
screen = turtle.Screen()
screen.setup(width=600, height=400)

# Tworzenie żółwia
rysik_zolwia = turtle.Turtle()
rysik_zolwia.hideturtle()
rysik_zolwia.speed(0)

# Inicjalizacja zmiennych
x, y = 200, -100
kolory = {"r": 0, "g": 101, "b": 101}

# Główna pętla
while True:
    # Aktualizacja koloru
    kolory = colour(kolory)
    
    # Rysowanie przeszkody (koła)
    turtle.clear()
    kolo(x, y, kolory)
    
    # Rysowanie żółwia
    zolw(rysik_zolwia, -200, -100)
    
    # Aktualizacja ekranu
    turtle.update()
    
    # Przesunięcie koła
    x -= 5
    if x < -310:
        x = 200
