
class Proces:

    def __init__(self, numer, dlugosc, zgloszenie,):
        self.numer = numer
        self.dlugosc = dlugosc
        self.zgloszenie = zgloszenie
        self.oczekiwanie = 0
        self.pozostalo = dlugosc

    #def __repr__(self):
    #    return str(""" Proces numer %d
    #    Długość: %d
    #    Moment zgłoszenia: %d
    #    Oczekiwał: %d""",[self.numer, self.dlugosc, self.zgloszenie, self.oczekiwanie])


    # def __repr__(self):
    #     return "Proces numer: "+str(self.numer)

    def __repr__(self):
        # return "Proces numer: "+str(self.numer)
        return ("""Proces numer: {}
        Długość: {}
        Moment zgłoszenia: {}
        Oczekiwał: {}
        Pozostało: {}""".format(self.numer, self.dlugosc, self.zgloszenie, self.oczekiwanie, self.pozostalo))

