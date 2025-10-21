import random

def randomperm(n):
    nowa = []
    liczby = []

    for i in range(0, n):
        liczby.append(i)

    for j in range(0, n):
        wylosowane = random.choice(liczby)
        liczby.remove(wylosowane)
        nowa.append(wylosowane)
    print(nowa)


randomperm(10)
randomperm(10)
randomperm(10)
randomperm(10)
randomperm(10)
