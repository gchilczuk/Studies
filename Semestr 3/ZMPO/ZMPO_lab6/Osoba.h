#ifndef ZMPO_LAB6_OSOBA_H
#define ZMPO_LAB6_OSOBA_H
#include <string>
#include <ostream>

using std::string;

class Osoba {
    string imie;
    int wiek;

public:
    Osoba(){imie = "."; wiek = 0;}
    Osoba(string imie, int wiek) : imie(imie), wiek(wiek) {}

    ~Osoba() {
//       delete imie;
    }

    void setWiek(int wiek) {
        Osoba::wiek = wiek;
    }
    Osoba operator=(Osoba &os){
//        delete imie;
        imie = os.imie;//new string(*os.imie);
        wiek = os.wiek;
        return *this;
    }

    friend std::ostream &operator<<(std::ostream &os, const Osoba &osoba) {
        os << "imie: " << osoba.imie << " wiek: " << osoba.wiek;
        return os;
    }
};


#endif //ZMPO_LAB6_OSOBA_H
