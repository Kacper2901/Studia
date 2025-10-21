import random
import time

K = 1000
N = 1_000_000

def losowa_sekwencja():
    return [random.randint(0, K - 1) for _ in range(N)]

def sortowanie_przez_zliczanie(lista):
    posortowana = []
    zliczone = [0]*K
    for i in range(len(lista)):
        zliczone[lista[i]] += 1
    for j in range(len(zliczone)):
        for i in range(zliczone[j]):
            posortowana.append(j)
    return posortowana
    
lista = losowa_sekwencja()
# print(sortowanie_przez_zliczanie(lista))

print()
print("-------------------------")
t0 = time.time()
posortowana = sortowanie_przez_zliczanie(lista)
czas_sortowania = time.time() - t0
print('Czas sortowania przez zliczanie:', czas_sortowania)

t0 = time.time()
posortowana_bibliotecznie = sorted(lista)
czas_biblioteczny = time.time() - t0
print('Czas sortowania bibliotecznego:', czas_biblioteczny)
print("-------------------------")

# print(posortowana_bibliotecznie)
# print(sortowanie_przez_zliczanie(lista))

if posortowana_bibliotecznie == sortowanie_przez_zliczanie(lista):
    print("zgadza sie")

