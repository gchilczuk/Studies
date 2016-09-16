from random import randint
from Proces import Proces
from FCFS_SJF import *
from copy import deepcopy


def symuluj(algorytm, kolejka, kwant=None):
    czas = 0
    if kwant is not None:
        planista = algorytm(kwant)
    else:
        planista = algorytm()

    znaj = znajdz_procesy(kolejka, czas)
    planista.dodaj_proces(znaj)

    while len(kolejka) > 0 or not planista.czy_bierny():
        czas += 1
        planista.wykonaj_cykl()

        znaj = znajdz_procesy(kolejka, czas)

        planista.dodaj_proces(znaj)

        # print(czas)


    print("——————————")
    print(""" {}
    Wykonano {} procesów, zajęło to w sumie {} cykli zegara.
    Średni czas oczekiwania procesu wyniósł: {}""".format(algorytm(), len(planista.wykonane), czas,
                                                          planista.sredni_czas_oczekiwania()))
    if kwant is not None:
        print(" Kwant czasu wynosił:", kwant)

    return planista.czas_oczekiwania()




def generuj_kolejke(n, start=1, koniec=15, zgloszenie=50, ):
    """
    Losuje procesy
    :param n: ilość procesów do wylosowania
    :param dlugosc: maksymalna długość procesu
    :param zgloszenie: maksymalny najpóźniejszy czas zgłoszenia procesu
    :return: lista wylosowanych procesów
    """
    kolejka = []
    a = int(0.8*n)
    b = n - a
    for i in range(a):
        kolejka.append(Proces(i, randint(start, koniec), randint(0, zgloszenie)))
    for i in range(b):
        kolejka.append(Proces(a+i, randint(10*start, 10*koniec), randint(0, zgloszenie)))

    for i in range(randint(4,8)):
        kolejka[randint(0, n-1)].zgloszenie = 0

    return kolejka


def znajdz_procesy(kolejka, zgloszenie):
    """
    Znajduje procesy o zadanym momencie zgłoszenia
    :param kolejka: lista procesów do przeanalizowania
    :param zgloszenie: szukany moment zgłoszenia
    :rtype: list
    """
    znalezione = []
    indeksy = []
    i = 0
    while i < len(kolejka):
        while i < len(kolejka) and kolejka[i].zgloszenie == zgloszenie:
            znalezione.append(kolejka.pop(i))
            indeksy.append(i)
        i += 1
    return znalezione


def wyswietl_procesy(tab):
    for i in tab:
        print(i)



# def symulujRR(algorytm, kolejka, kwant):
#     czas = 0
#     planista = algorytm(kwant)
#     znaj = znajdz_procesy(kolejka, czas)
#     planista.dodaj_proces(znaj)
#
#     while len(kolejka) > 0 or not planista.czy_bierny():
#         czas += 1
#         # print(czas)
#         planista.wykonaj_cykl()
#
#         znaj = znajdz_procesy(kolejka, czas)
#         planista.dodaj_proces(znaj)
#
#         planista.nastepny()
#
#     print("——————————")
#     print(""" {}
#     Wykonano {} procesów, zajęło to w sumie {} cykli zegara.
#     Średni czas oczekiwania procesu wyniósł: {}""".format(algorytm(), len(planista.wykonane), czas,
#                                                           planista.sredni_czas_oczekiwania()))
#     print("    Kwant czasu wynosił: ", kwant)
#
#     # print("——————————")




