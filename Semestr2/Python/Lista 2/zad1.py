def szachownica(n, k):
    wiersz = 0
    status = -1  # -1 czarny, 1 bialy
    licznik = 0
    for i in range(0, 2 * n * k):
        licznik += 1
        for j in range(0, n):
            if status == -1:
                print(k * "#" + k * " ", end="")
            if status == 1:
                print(k * " " + k * "#", end="")
        if licznik % k == 0:
            status *= -1
        print()


szachownica(4, 4)
