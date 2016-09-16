
class RR:
    def __init__(self, kwant=5):
        self.procesy = []
        self.biezacy = None
        self.wykonane = []
        self.czas = 0
        self.kwant = kwant

    def dodaj_proces(self, procesy):
        if type(procesy) != list:
            print (">>>>>>>>>>>> GWÓNO <<<<<<<<<<<<<<<<")
            return None

        self.procesy.extend(procesy)
        if self.biezacy is None and len(self.procesy) > 0:
            self.biezacy = self.procesy[0]

    def wykonaj_cykl(self):
        for proces in self.procesy:
            if proces != self.biezacy:
                proces.oczekiwanie += 1

        # print(self.biezacy.numer)
        if self.biezacy is not None:
            self.biezacy.pozostalo -= 1
            self.czas += 1
            if self.biezacy.pozostalo == 0:
                self.biezacy = self._nastepny(self.biezacy)
            elif self.biezacy.pozostalo < 0:
                print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
                wynik działania tego programu to jest jakaś porażka…""")
                self.biezacy = 1
            elif self.czas == self.kwant:
                self.biezacy = self._nastepny(self.biezacy)


    def _nastepny(self, proces):
        index = self.procesy.index(proces)

        if proces == self.procesy[-1]:
            zwrot = self.procesy[0]
        else:
            zwrot = self.procesy[index+1]

        if proces.pozostalo == 0:
            if zwrot == proces:
                zwrot = None
            self.procesy.remove(proces)
            self.wykonane.append(self.biezacy)

        self.czas = 0
        return zwrot

    def sredni_czas_oczekiwania(self):
        return self.czas_oczekiwania()/len(self.wykonane)

    def czas_oczekiwania(self):
        sr = 0
        for proces in self.wykonane:
            sr += proces.oczekiwanie
        return sr

    def czy_bierny(self):
        return self.biezacy is None

    def __repr__(self):
        return "Algorytm RR"

# class RR2:
#
#     def __init__(self, kwant=5):
#         self.proca = deque([])
#         self.procb = deque([])
#         self.biezacy = None
#         self.wykonane = []
#         self.czas = 0
#         self.kwant = kwant
#
#
#     def dodaj_proces(self, procesy):
#         if type(procesy) != list:
#             print (">>>>>>>>>>>> ŹLE <<<<<<<<<<<<<<<<")
#             return None
#         self.proca.extend(procesy)
#         if self.biezacy is None and len(self.proca) > 0:
#             self.biezacy = self.proca.popleft()
#
#     def wykonaj_cykl(self):
#         # print(self.biezacy)
#
#         for proces in self.proca:
#             proces.oczekiwanie += 1
#
#         # for proces in self.procb:
#         #     proces.oczekiwanie += 1
#
#         if self.biezacy is not None:
#             self.biezacy.pozostalo -= 1
#             self.czas += 1
#
#     def nastepny(self):
#         if self.biezacy is None:
#             if len(self.proca) > 0:
#                 self.biezacy = self.proca.popleft()
#         elif self.biezacy.pozostalo == 0:
#             self._nastepny()
#         elif self.biezacy.pozostalo < 0:
#             print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
#                 wynik działania tego programu to jest jakaś porażka…""")
#             self.biezacy = 1
#         elif self.czas == self.kwant:
#             self._nastepny()
#
#     def _nastepny(self):
#         self.czas = 0
#         if self.biezacy.pozostalo == 0:
#             self.wykonane.append(self.biezacy)
#         else:
#             self.proca.append(self.biezacy)
#
#         if len(self.proca) == 0:
#             self.biezacy = None
#         elif len(self.proca) > 0:
#             self.biezacy = self.proca.popleft()
#
#     def sredni_czas_oczekiwania(self):
#         sr = 0
#         for proces in self.wykonane:
#             sr += proces.oczekiwanie
#         return sr/len(self.wykonane)
#
#     def czy_bierny(self):
#         return self.biezacy is None
#
#     def __repr__(self):
#         return "Algorytm RR"