from RR import *
from Proces import *
from random import randint
from copy import deepcopy

def wyb_kwant(tab):
    # sr = suma_dlugosci(tab)/len(tab)
    # naj = policz_czas(deepcopy(tab), int(0.2*sr))
    # be = policz_czas(deepcopy(tab), int(0.4*sr))
    # if be < naj:
    #     naj = be
    # be = policz_czas(deepcopy(tab), int(0.6*sr))
    # if be < naj:
    #     naj = be
    # be = policz_czas(deepcopy(tab), int(0.8*sr))
    # if be < naj:
    #     naj = be
    # return naj


    sr = suma_dlugosci(tab)/len(tab)
    # print(sr)
    najlepszy =  sr
    czas_naj = policz_czas(deepcopy(tab), sr)

    akt = 0.8
    while akt > 0.2:
        akt -= 0.01
        teraz = policz_czas(deepcopy(tab), int(akt*sr))
        # print(teraz)
        if teraz < czas_naj:
            czas_naj = teraz
            najlepszy = int(akt*sr)



    return najlepszy


def policz_czas(kolejka, kwant):
    czas = 0
    planista = RR(kwant)

    znaj = znajdz_procesy(kolejka, czas)
    planista.dodaj_proces(znaj)

    while len(kolejka) > 0 or not planista.czy_bierny():
        czas += 1
        planista.wykonaj_cykl()

        znaj = znajdz_procesy(kolejka, czas)

        planista.dodaj_proces(znaj)

    return planista.sredni_czas_oczekiwania()


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

def suma_dlugosci(tab):
    suma = 0
    for i in tab:
        suma += i.dlugosc
    return suma

def generuj_kolejke(n, start=1, koniec=15, zgloszenie=50, ):
    kolejka = []

    a = int(0.8*n)
    b = n - a
    for i in range(a):
        kolejka.append(Proces(i, randint(start, koniec), randint(0, zgloszenie)))
    for i in range(b):
        kolejka.append(Proces(a+i, randint(10*start + 4, 10*koniec + 4), randint(0, zgloszenie)))
    return kolejka