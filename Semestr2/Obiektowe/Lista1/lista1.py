#Kacper Gęśla
#Lista01
#zadania: 1,2
#Python 3.12.3 64-bit



#-------zad1----------

def silnia_imp(x):
    wynik = 1
    if x == 0:
        return 1
    for i in range(1,x+1):
        wynik*=i
    return wynik

def silnia_fun(x):
    if x == 0:
        return 1
    if x == 1:
        return x
    return x*silnia_fun(x-1)


def binom(n, k):
    return silnia_imp(n)//(silnia_imp(k)*silnia_imp(n-k))


def trojkat_pascala(n):
    for i in range(n+1):
        print(binom(n,i), end=" ")
    print()

print("ZADANIE 1:\n")
print("9-ty wiersz: ",end="")
print("5! imperatywnie: ", silnia_imp(5))
print("4! funkcyjnie: ", silnia_imp(4))
print("9-ty wiersz trojkata pascala: ",end="")
trojkat_pascala(9)
print()
print("-----------------")
print()

#-------zad 2--------------

def gcd_imp(a,b):
    while b != 0:
        a,b = b,a%b
    return a

def gcd_fun(a,b):
    if b == 0:
        return a
    return gcd_fun(b,a%b)


def wzgl_pierwsze_imp(n):
    for i in range(1,n+1):
        if gcd_imp(n,i) == 1:
            print(i,end=" ")
            
def wzgl_pierwsze_fun(n,k):
    if k == n:
        return
    else:
        if gcd_fun(n,k) == 1:
            print(k, end=" ")
        wzgl_pierwsze_fun(n,k+1)

print("ZADANIE 2:\n")
print("GCD(36,15) imperatywnie: ", gcd_imp(36,15))
print("GCD(33,121) funkcyjnie: ", gcd_fun(33,121))
print()
print("wzglednie pierwsze dla liczby 9 imperatywnie: ",end="")
wzgl_pierwsze_imp(9)
print()
print("wzglednie pierwsze dla liczby 10 funkcyjnie: ",end="")
wzgl_pierwsze_fun(10,1)
print()
print()