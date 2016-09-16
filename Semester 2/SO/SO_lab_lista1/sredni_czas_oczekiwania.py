from test import*

def sco(dawka, powt):
    fcfs = []
    sjf = []
    srtf = []
    rr = []

    for i in range(powt):
        yup = generuj_kolejke(dawka, 5, 50, 500) # (n, start=1, koniec=15, zgloszenie=50)

        a = deepcopy(yup)
        b = deepcopy(a)
        c = deepcopy(b)
        d = deepcopy(c)

        fcfs += [symuluj(FCFS, a)]
        sjf += [symuluj(SJF, b)]
        srtf += [symuluj(SRTF, c)]
        rr += [symuluj(RR, d, 3)]


    print()
    print("———————————————————————————————————————————————————————————————")
    print("Wykonano",powt,"wywołań algorytmów po",dawka,"procesów w każdym wywołaniu.")
    print("Średni czas oczekiwania dla FCFS:", sum(fcfs) / (dawka*powt))
    print("Średni czas oczekiwania dla SJF:", sum(sjf) / (dawka*powt))
    print("Średni czas oczekiwania dla SRTF:", sum(srtf) / (dawka*powt))
    print("Średni czas oczekiwania dla RR:", sum(rr) / (dawka*powt))


sco(50,50)