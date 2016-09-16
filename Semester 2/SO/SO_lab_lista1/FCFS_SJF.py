from collections import deque


class FCFS:
    """
    First Come First Served
    Algorytmm planujący przydział dostępu do procesora orczekującym procesom
    wg kolejności zgłaszania się kolejnych procesów
    """

    def __init__(self):
        self.procesy = deque([])
        self.biezacy = None
        self.wykonane = []

    def dodaj_proces(self, procesy):
        self.procesy.extend(procesy)
        if self.biezacy is None and len(self.procesy) > 0:
                self.biezacy = self.procesy.popleft()

    def wykonaj_cykl(self):
        for proces in self.procesy:
            proces.oczekiwanie += 1
        if self.biezacy is None and len(self.procesy) != 0 :
            self.biezacy = self.procesy.popleft()

        if self.biezacy is not None:
            self.biezacy.pozostalo -= 1
            if self.biezacy.pozostalo == 0:
                self.wykonane.append(self.biezacy)
                self.biezacy = None
            elif self.biezacy.pozostalo < 0:
                print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
                wynik działania tego programu to jest jakaś porażka… FCFS""")
                self.biezacy = 1


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
        return "Algorytm FCFS"


class SJF:
    """
    Shortest Job First
    Algorytmm planujący przydział dostępu do procesora orczekującym procesom
    kolejność jest ustalana od najkrótszego procesu
    """
    def __init__(self):
        self.procesy = deque([])
        self.biezacy = None
        self.wykonane = []
        # self.wat = True
        # self.ob = 1

    def dodaj_proces(self, procesy):
        for proc in procesy:
            i = 0
            while i < len(self.procesy) and self.procesy[i].dlugosc <= proc.dlugosc:
                i += 1

            self.procesy.insert(i, proc)
        # if(self.wat): druk_nr(self.procesy, self.ob)

        if self.biezacy is None and len(self.procesy) > 0:
            self.biezacy = self.procesy.popleft()


    def wykonaj_cykl(self):
        # self.ob += 1
        for proces in self.procesy:
            proces.oczekiwanie += 1
        if self.biezacy is None and len(self.procesy) != 0 :
            self.biezacy = self.procesy.popleft()
        if self.biezacy is not None:
            self.biezacy.pozostalo -= 1
            if self.biezacy.pozostalo == 0:
                # print(self.biezacy.numer)
                if self.biezacy.numer == 17:
                    self.wat = False
                self.wykonane.append(self.biezacy)
                self.biezacy = None # self.procesy.popleft() if len(self.procesy) != 0 else None
            elif self.biezacy.pozostalo < 0:
                print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
                wynik działania tego programu to jest jakaś porażka…""")
                self.biezacy = 1

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
        return "Algorytm SJF"


def druk_nr(tab, ob):
    wyn = []
    for i in tab:
        wyn += [i.numer]
    print(ob, wyn)




# class SJFWywlaszcz:
#
#     """
#     Shortest Job First z wywłaszczaniem
#     Algorytmm planujący przydział dostępu do procesora orczekującym procesom
#     kolejność jest ustalana od najkrótszego procesu
#     jeżeli nowo zgłoszony proces ma krótszy czas wykonania niż aktualnie wykonywany
#     bieżący proces zostaje wywłaszczony
#     """
#
#     def __init__(self):
#         self.procesy = deque([])
#         self.biezacy = None
#         self.wykonane = []
#         #self.wywlaszczone = []
#
#     def dodaj_proces(self, proces):
#         if type(proces) != list:
#             procesy = [proces]
#         else:
#             procesy = proces
#         for proc in procesy:
#             i = 0
#             while i < len(self.procesy) and self.procesy[i].pozostalo <= proc.pozostalo:
#                 i += 1
#             self.procesy.insert(i, proc)
#         if self.biezacy is None :
#             if len(self.procesy) > 0:
#                 self.biezacy = self.procesy.popleft()
#         elif len(self.procesy) > 0 and self.biezacy.pozostalo > self.procesy[0].pozostalo:
#             self.wywlaszczone.append(self.biezacy)
#             self.biezacy = self.procesy.popleft()
#
#     def wykonaj_cykl(self):
#         for proces in self.procesy:
#             proces.oczekiwanie += 1
#         for proces in self.wywlaszczone:
#             proces.oczekiwanie += 1
#         if self.biezacy is not None:
#             self.biezacy.pozostalo -= 1
#             if self.biezacy.pozostalo == 0:
#                 self.wykonane.append(self.biezacy)
#                 self.biezacy = self._nastepny()
#             elif self.biezacy.pozostalo < 0:
#                 print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
#                 wynik działania tego programu to jest jakaś porażka…""")
#                 self.biezacy = 1
#
#     def _nastepny(self):
#         porces = None
#         if len(self.wywlaszczone) > 0:
#             if len(self.procesy) > 0:
#                 if self.wywlaszczone[-1].pozostalo > self.procesy[0].pozostalo:
#                     porces = self.procesy.popleft()
#                 else:
#                     porces = self.wywlaszczone.pop()
#             else:
#                 porces = self.wywlaszczone.pop()
#         else:
#             if self.procesy:
#                 porces = self.procesy.popleft()
#         return porces
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
#         return "Algorytm SJF wywłaszczający"
#
#
# class aRR:
#
#     def __init__(self, kwant=4):
#         self.procesy = []
#         self.biezacy = None
#         self.wykonane = []
#         self.czas = 0
#         self.kwant = kwant
#
#     def dodaj_proces(self, proces):
#         if type(proces) == list:
#             self.procesy.extend(proces)
#         else:
#             self.procesy.append(proces)
#         if self.biezacy is None and len(self.procesy) > 0:
#             self.biezacy = self.procesy[0]
#
#     def wykonaj_cykl(self):
#         self.czas+=1
#         for proo in self.procesy:
#             if proo != self.biezacy:
#                 proo.oczekiwanie += 1
#         if self.biezacy is not None:
#             self.biezacy.pozostalo -= 1
#             if self.biezacy.pozostalo == 0:
#                 self.wykonane.append(self.biezacy)
#                 self.biezacy = self._nastepny(self.biezacy)
#                 self.czas = 0
#             elif self.biezacy.pozostalo < 0:
#                 print("""Dobry wieczór. Coś się… Coś się popsuło i nie było mnie słychać, więc powtórzę jeszcze raz:
#                 wynik działania tego programu to jest jakaś porażka…""")
#                 self.biezacy = 1
#             elif self.czas % self.kwant == 0:
#                 self.biezacy = self._nastepny(self.biezacy)
#
#
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
#     def _nastepny(self, proces):
#         zwrot = None
#         index = self.procesy.index(proces)
#
#         if proces == self.procesy[-1]:
#             zwrot = self.procesy[0]
#         else:
#             zwrot = self.procesy[index+1]
#
#         if proces.pozostalo == 0:
#             if zwrot == proces:
#                 zwrot = None
#             self.procesy.remove(proces)
#         return zwrot
#
#     def __repr__(self):
#         return "Algorytm aRR"
