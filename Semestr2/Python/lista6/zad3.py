from PIL.ImagePalette import random


def czy_pierwsza(n):
    if n <= 1:
        return False
    if n == 2:
        return True
    for i in range(2,int(n**(0.5))+1):
        if n % i == 0:
            return False
    return True

def dzielniki_pierwsze(n):
    zbior = set()
    if czy_pierwsza(n):
        zbior.add(n)
    for i in range(2, int(n**(0.5))+1):
        if czy_pierwsza(i) and n % i == 0:
            zbior.add(i)
            if czy_pierwsza(n/i):
                zbior.add(n//i)
    return zbior

print(dzielniki_pierwsze(123))


