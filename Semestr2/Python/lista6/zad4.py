def podziel(s):
    tab = []
    slowo = ""
    for znak in s:
        if znak != " ":
            slowo = slowo + znak
        else:
            if slowo != "":
                tab.append(slowo)
                slowo = ''
    if slowo != "":
        tab.append(slowo)
    print(tab)

podziel("  ala    ma kota   ")
podziel("  ala  ma kota")
podziel("ala ma kota")