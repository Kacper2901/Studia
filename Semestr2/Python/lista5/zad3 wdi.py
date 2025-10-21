def nwd(n, m):
    mnoznik = 1
    while m > 0:
        if m > n:
            pom = m
            m = n
            n = pom
        oddcnt = n % 2 + m % 2
        if oddcnt == 2:
            n = n - m
        else:
            if oddcnt == 0:
                mnoznik *= 2
            else:
                if n % 2 == 0:
                    n = n//2
                else:
                    m = m // 2

    return n*mnoznik
