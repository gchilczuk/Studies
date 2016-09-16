from collections import deque

class SRTFp:

    def __init__(self):
        self.procesy = deque([])
        self.biezacy = None
        self.wykonane = []
        self.wywlaszczone = []

    def dodaj_proces(self, procesy):
        if type(procesy) != list:
            print (">>>>>>>>>>>> ŹŁE <<<<<<<<<<<<<<<<")
            return None

        for proces in procesy:
            i = 0
            while i < len(self.procesy) and self.procesy[i].pozostalo <= proces.pozostalo:
                i += 1
            self.procesy.insert(i, proces)

        if self.biezacy is None:
            if len(self.procesy) > 0:
                self.biezacy = self.procesy.popleft()
        elif len(self.procesy) > 0 and self.biezacy.pozostalo > self.procesy[0].pozostalo:
            self.wywlaszczone.append(self.biezacy)
            self.biezacy = self.procesy.popleft()

    def wykonaj_cykl(self):
        for proces in self.procesy:
            proces.oczekiwanie += 1

        self._nast()



        if self.biezacy is not None:
            self.biezacy.pozostalo -= 1
            if self.biezacy.pozostalo == 0:
                self.wykonane.append(self.biezacy)
                self.biezacy = None # if len(self.procesy) == 0 else self.procesy.popleft()
                self._nast()
            elif self.biezacy.pozostalo < 0:
                print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
                wynik działania tego programu to jest jakaś porażka…""")
                self.biezacy = 1

    def _nast(self):
        if self.biezacy is None:
            if len(self.procesy) != 0 :
                if len(self.wywlaszczone) != 0:
                    if self.wywlaszczone[-1].pozostalo < self.procesy[0].pozostalo:
                        self.biezacy = self.wywlaszczone.pop()
                    else:
                        self.biezacy = self.procesy.popleft()
                else:
                    self.biezacy = self.procesy.popleft()
            elif len(self.wywlaszczone) != 0:
                self.biezacy = self.wywlaszczone.pop()

    def sredni_czas_oczekiwania(self):
        return self.czas_oczekiwania()/len(self.wykonane)

    def czas_oczekiwania(self):
        sr = 0
        for proces in self.wykonane:
            sr += proces.oczekiwanie
        return sr

    def czy_bierny(self):
        return self.biezacy is None and not self.wywlaszczone and not self.procesy

    def __repr__(self):
        return "Algorytm SRTF z oddzielną kolejką wywłaszczonych"
