def dzielniki(n):
    ile = 2
    for i in range(2, int(n ** (1 / 2) + 1)):
        if n % i == 0 and i * i != n:
            ile += 2
        if i * i == n:
            ile += 1

    return ile




def zliczanie(i):
    zbior = [0]*100000
    for i in range(i):
        zbior[dzielniki(i)] += 1
    print(zbior)

zliczanie(100000000)