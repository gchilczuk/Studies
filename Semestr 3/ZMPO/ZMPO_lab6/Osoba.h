#ifndef ZMPO_LAB6_OSOBA_H
#define ZMPO_LAB6_OSOBA_H
#include <string>
#include <ostream>


using std::string;
using std::stringstream;

class Osoba {
    string *imie = new string();
    int wiek;

public:
    Osoba(){*imie = "."; wiek = -1;}
    Osoba(string im, int wiek) : wiek(wiek) {*imie = im;}
    Osoba(const Osoba &obj){
        *imie = *obj.imie;
        wiek = obj.wiek;
    }

    ~Osoba() {
       delete imie;
    }

    void setWiek(int wiek) {
        Osoba::wiek = wiek;
    }
    Osoba operator=(const Osoba &os){
//        delete imie;
        *imie = *os.imie;//new string(*os.imie);
        wiek = os.wiek;
        return *this;
    }

    friend std::ostream &operator<<(std::ostream &os, const Osoba &osoba) {
        os << "IM: " << *osoba.imie << " W: " << osoba.wiek;
        return os;
    }
    friend std::istream& operator>>(std::istream& in, Osoba& obj) // input
    {
        string nazwisko;
        int wiek;
        in >> nazwisko >> wiek;
        *obj.imie = nazwisko;
        obj.wiek = wiek;
        return in;
    }
};





#endif //ZMPO_LAB6_OSOBA_H
