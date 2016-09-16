from time import time
import random

class Memory:

    def __init__(self, miejsce, alg):
        self.ramki = [-1] * miejsce
        self.braki = 0
        self.ostatnio = -1
        self.alg = alg
        self.inf = [0] * miejsce
        self.rozmiar = miejsce

    def obsluga(self, nr, look=None):
        # print('inf:',self.inf, 'ostatnio:',self.ostatnio)
        gdzie = self.alg(self, nr) if look is None else self.alg(self, nr, look)
        if gdzie is not None:
            self.braki += 1
            self.ostatnio = gdzie
            self.ramki[self.ostatnio] = nr
            # print('ram:',self.ramki)

    def fifo(self, nr):
        if nr in self.ramki: return None
        ret = 0
        if self.ostatnio < self.rozmiar-1: ret = self.ostatnio + 1
        return ret

    def opt(self, nr, look):
        if nr in self.ramki:
            return None
        beda = []
        u = None
        for x in self.ramki:
            if x not in look:
                u = x
                break
            beda += [(self.ramki.index(x),look.index(x))]
        if u is not None:
            ret = self.ramki.index(u)
        else:
            x = beda[0]
            for n in beda:
                if n[1] > x[1]:
                    x = n
            ret = x[0]
        return ret

    def lru(self, nr):
        if nr in self.ramki:
            self.inf[self.ramki.index(nr)] = time()
            return None
        mini = 0
        for i in range(1, self.rozmiar):
            if self.inf[i] < self.inf[mini]:
                mini = i
        self.inf[mini] = time()
        return mini

    def alru(self, nr):
        if nr in self.ramki:
            for i in range(self.rozmiar):
                if self.ramki[i] == nr:
                    self.inf[i] = 1
                    self.ostatnio = i
            return None
        if self.ostatnio >= self.rozmiar-1:
            self.ostatnio = -1
        ost = self.ostatnio
        while self.inf[ost+1] == 1:
            self.inf[ost+1] = 0
            ost += 1
            if ost >= self.rozmiar-1:
                ost = -1
        ost += 1
        self.inf[ost] = 1
        return ost

    def random(self, nr):
        if nr in self.ramki: return None
        for i in range(self.rozmiar):
            if self.ramki[i] == -1:
                return i
        return random.randint(0,self.rozmiar-1)

    def opt_gen(self):


