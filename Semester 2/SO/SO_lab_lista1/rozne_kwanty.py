from symulator import *
from RR import *

def rozne_kwanty(yup):
    # yup = generuj_kolejke(50, 5, 25, 500)
    a = suma_dlugosci(yup)/50

    print("Średni czas: ", a)
    symuluj(RR, deepcopy(yup), int(0.2*a))
    symuluj(RR, deepcopy(yup), int(0.4*a))
    symuluj(RR, deepcopy(yup), int(0.6*a))
    symuluj(RR, deepcopy(yup), int(0.8*a))

def suma_dlugosci(tab):
    suma = 0
    for i in tab:
        suma += i.dlugosc
    return suma

# rozne_kwanty()

