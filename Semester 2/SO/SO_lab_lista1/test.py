"""
Ten plik należy uruchomić, żeby zobaczyć wyniki testów
"""
from symulator import *
from SRTF import *
from RR import *
from rozne_kwanty import rozne_kwanty
from wybierz_kwant import wyb_kwant
# from SRTFplus import SRTFp

losowo_wygenerowane = generuj_kolejke(50, 5, 10, 50) # (n, start=1, koniec=15, zgloszenie=50)


a = deepcopy(losowo_wygenerowane)
b = deepcopy(a)
c = deepcopy(b)
d = deepcopy(c)
e = deepcopy(d)
f = deepcopy(e)
g = deepcopy(f)
h = deepcopy(g)
i = deepcopy(h)
j = deepcopy(i)

symuluj(FCFS, a)
symuluj(SJF, b)
symuluj(SRTF, c)

rozne_kwanty(d)
print()
print()
print()
print("—>—>—>—>—>—>—>—>—>———————————————————wykonanie z optymalnym kwantem:")
symuluj(RR, f, wyb_kwant(f))
# symuluj(RR, g, 15)
# symuluj(RR, h, 30)
# symuluj(RR, i, 45)
# symuluj(RR, j, 60)

# symuluj(SRTFp, d)



# maks = [Proces(1, 50, 0),
#         Proces(2, 15, 15),
#         Proces(3, 6, 16),
#         Proces(4, 10, 60),
#         Proces(5, 1, 61)]
#
# pawel = [Proces(1,5,0),
#          Proces(2,3,1),
#          Proces(3,8,2),
#          Proces(4,6,3)]
#
# test = [Proces(1, 5, 0),
#         Proces(2, 3, 0),
#         Proces(3, 2, 0)]
#
# soj = [Proces(1, 6, 0),
#        Proces(2, 8, 0),
#        Proces(3, 7, 0),
#        Proces(4, 3, 0)]
#
# doj = [Proces(1, 8, 0),
#        Proces(1, 4, 1),
#        Proces(1, 9, 5),
#        Proces(1, 5, 5)]
#
# zad1 = [Proces(1, 8, 0),
#         Proces(2, 5, 2),
#         Proces(3, 3, 3),
#         Proces(4, 4, 9)]
#
# wiki = [Proces(1, 1, 0),
#         Proces(2, 2, 0),
#         Proces(3, 4, 0),
#         Proces(4, 6, 0),
#         Proces(5, 8, 0),
#         Proces(6, 8, 11),
#         Proces(7, 6, 11),
#         Proces(8, 4, 11),
#         Proces(9, 2, 11),
#         Proces(10, 1, 11)]
#
#
# # for i in range(1000):
# #     cos = generuj_kolejke(50, 30, 500)
# #     a = deepcopy(cos)
# #     b = deepcopy(a)
# #     c = deepcopy(b)
# #     d = deepcopy(c)
# #     symuluj(FCFS, a)
# #     symuluj(SJF, b)
# #     symuluj(SRTF, c)
# #     symuluj(RR, d, randint(1,20))
# #     print (">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ", i)
#
#
#
#
#
# # test.reverse()
#
# zaje = [Proces(1, 80, 0),
#         Proces(1, 60, 0),
#         Proces(1, 52, 0)]
#
# # test1 = [Proces(0, 26, 1), Proces(1, 5, 29), Proces(2, 20, 10), Proces(3, 18, 10), Proces(4, 11, 13), Proces(5, 17, 1), Proces(6, 19, 1), Proces(7, 18, 10), Proces(8, 28, 23), Proces(9, 5, 28), Proces(10, 16, 12), Proces(11, 26, 9), Proces(12, 21, 11), Proces(13, 19, 14), Proces(14, 18, 25), Proces(15, 21, 15), Proces(16, 14, 17), Proces(17, 14, 20), Proces(18, 21, 12), Proces(19, 21, 19), Proces(20, 17, 28), Proces(21, 19, 26), Proces(22, 21, 6), Proces(23, 28, 17), Proces(24, 26, 17), Proces(25, 29, 17), Proces(26, 11, 8), Proces(27, 20, 22), Proces(28, 20, 19), Proces(29, 15, 8), Proces(30, 6, 9), Proces(31, 9, 2), Proces(32, 29, 4), Proces(33, 26, 18), Proces(34, 10, 7), Proces(35, 29, 22), Proces(36, 2, 27), Proces(37, 14, 19), Proces(38, 23, 25), Proces(39, 26, 21), Proces(40, 18, 22), Proces(41, 1, 2), Proces(42, 21, 28), Proces(43, 3, 15), Proces(44, 1, 13), Proces(45, 14, 17), Proces(46, 20, 22), Proces(47, 2, 2), Proces(48, 20, 16), Proces(49, 7, 13)]
# # test2 = [Proces(0, 5, 20), Proces(1, 18, 13), Proces(2, 8, 28), Proces(3, 28, 23), Proces(4, 13, 7), Proces(5, 5, 15), Proces(6, 2, 25), Proces(7, 16, 13), Proces(8, 14, 20), Proces(9, 23, 18), Proces(10, 7, 15), Proces(11, 15, 26), Proces(12, 23, 10), Proces(13, 12, 12), Proces(14, 11, 10), Proces(15, 8, 12), Proces(16, 15, 4), Proces(17, 20, 29), Proces(18, 23, 27), Proces(19, 23, 22), Proces(20, 2, 14), Proces(21, 7, 12), Proces(22, 5, 27), Proces(23, 23, 24), Proces(24, 19, 18), Proces(25, 8, 0), Proces(26, 28, 29), Proces(27, 14, 28), Proces(28, 18, 25), Proces(29, 13, 10), Proces(30, 6, 15), Proces(31, 4, 19), Proces(32, 14, 25), Proces(33, 13, 14), Proces(34, 17, 17), Proces(35, 4, 1), Proces(36, 8, 18), Proces(37, 7, 22), Proces(38, 17, 21), Proces(39, 15, 26), Proces(40, 20, 7), Proces(41, 9, 6), Proces(42, 12, 5), Proces(43, 24, 14), Proces(44, 15, 26), Proces(45, 2, 20), Proces(46, 15, 22), Proces(47, 3, 5), Proces(48, 14, 21), Proces(49, 29, 14)]
# # test3 = [Proces(0, 25, 20), Proces(1, 26, 19), Proces(2, 26, 18), Proces(3, 8, 29), Proces(4, 29, 1), Proces(5, 4, 2),
# #          Proces(6, 2, 6),
# #          Proces(7, 26, 0), Proces(8, 6, 8), Proces(9, 17, 27), Proces(10, 12, 1), Proces(11, 13, 5), Proces(12, 23, 23), Proces(13, 28, 3), Proces(14, 25, 22), Proces(15, 22, 25), Proces(16, 4, 29), Proces(17, 4, 11), Proces(18, 25, 27), Proces(19, 22, 6), Proces(20, 27, 20),
# #          Proces(21, 1, 26), Proces(22, 17, 26), Proces(23, 15, 25), Proces(24, 17, 13), Proces(25, 18, 6), Proces(26, 28, 3), Proces(27, 12, 19), Proces(28, 25, 22), Proces(29, 5, 26), Proces(30, 11, 11), Proces(31, 26, 14), Proces(32, 5, 12), Proces(33, 22, 16), Proces(34, 22, 7), Proces(35, 5, 27), Proces(36, 5, 27), Proces(37, 26, 29), Proces(38, 22, 26), Proces(39, 11, 27), Proces(40, 11, 8), Proces(41, 8, 20), Proces(42, 21, 8), Proces(43, 14, 10), Proces(44, 14, 19), Proces(45, 10, 6), Proces(46, 12, 17), Proces(47, 23, 9), Proces(48, 20, 5), Proces(49, 12, 20)]
#
# tety = [Proces(1, 500, 0),
#         Proces(2, 5, 1),
#         Proces(3, 6, 2),]
#
# martyna = [Proces(1,300,0),
#            Proces(2,40,5),
#            Proces(3,8,50),
#            Proces(4,3,1),
#            Proces(5,25,20)]
