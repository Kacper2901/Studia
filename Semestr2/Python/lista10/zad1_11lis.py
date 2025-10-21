#Wprawka nr 3 (ŁH, piątek 8:30) lista 8 lub 9

a = [1,3,4,5,5,8]
b = [2,3,6,7,8,9]
posortowana =[]
while a and b:
    if a[0]<=b[0]:
       posortowana.append(a[0])
       a.pop(0)
    else:
        posortowana.append(b[0])
        b.pop(0)
print (posortowana)