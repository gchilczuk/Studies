from ramka import *
from generowanie import *

ilosc_stron = 100
ilosc_ramek = 3
dlugosc_ciagu = 1000

# book = [7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1]
los = {}
los['gen'] = gen( dlugosc_ciagu, ilosc_stron)
# los['genv2'] = genv2( dlugosc_ciagu, ilosc_stron)
# los['gen_old'] = gen_old( dlugosc_ciagu, ilosc_stron)
# los['book'] = book

for x in los.keys():
    # print()
    # print("——————————————————————————————————",x,"——————————————————————————————————")
    wolania = los[x]
    print(wolania)
    for ilosc_ramek in range(3,11):
        fifo = Memory(ilosc_ramek, Memory.fifo)
        lru = Memory(ilosc_ramek, Memory.lru)
        alru = Memory(ilosc_ramek, Memory.alru)
        opt = Memory(ilosc_ramek, Memory.opt)
        rand = Memory(ilosc_ramek, Memory.random)

        # różnica procentowa

        for j in range(len(wolania)):
            i = wolania[j]
            fifo.obsluga(i)
            lru.obsluga(i)
            alru.obsluga(i)
            opt.obsluga(i, wolania[j+1:])
            rand.obsluga(i)

        print('Ilość ramek:', ilosc_ramek,'| fifo:',fifo.braki,"| lru :",lru.braki,'| alru:', alru.braki,
              '| rand:',rand.braki,'| opt :', opt.braki)


