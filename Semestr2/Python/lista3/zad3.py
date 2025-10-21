def usun_nawias(txt):
    status = 0
    for litera in txt:
        if litera == "(":
            status = 1
        if litera == ")":
            status = 0
        if status == 0 and litera != ")":
            print(litera, end="")
    print()


usun_nawias("(Ala) ma kota (perskiego)!")
usun_nawias("A(la ma kota perskiego)!")
usun_nawias("Ala ma kota (perskiego)!")
usun_nawias("Ala (ma) kota (perskiego)!")
