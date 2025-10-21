def czy_pierwsza(n):
    if n <= 1:
        return False
    for i in range(2, int(n ** 0.5 + 1)):
        if n % i == 0:
            return False

    return True


def czy_szczesliwa(n):
    if "777" in str(n):
        return True
    else:
        return False


licznik = 0
for i in range(100000):
    if czy_szczesliwa(i) and czy_pierwsza(i):
        print(i)
        licznik += 1
print("jest", licznik, "takich liczb")
