from losowanie_fragmentow import losuj_fragment


def losuj_haslo(n):
    while True:
        czesc = losuj_fragment()
        if len(czesc) <= n and (n - len(czesc)) != 1:  # if len(czesc)<n
            n = n - len(czesc)
            print(czesc, end="")
            break

    return n


print("hasla o dlugosci 8: ")
print("-" * 50)

for i in range(0, 10):
    n = 8
    while n > 0:
        n = losuj_haslo(n)
    print()

print("-" * 50)
print()
print("hasla o dlugosci 12: ")
print("-" * 50)

for i in range(0, 10):
    n = 12
    while n > 0:
        n = losuj_haslo(n)
    print()
print("-" * 50)
# pomoc chatu gpt, nie losował wystarczająco długich haseł i pętla była nieskończona (bład zaznaczam powyzej)
