#ifndef ZMPO_LAB5_TRAPEZ_H
#define ZMPO_LAB5_TRAPEZ_H


#include "Prostokat.h"

class Trapez : public Prostokat {
protected:
    double d_bok_c;
    double d_bok_d;
    double d_h;

public:
    Trapez() : Prostokat("Trapez anonimowy", STANDARD_A, STANDARD_A),
               d_bok_c(STANDARD_A), d_bok_d(STANDARD_A), d_h(STANDARD_A){}
    Trapez(string sNazwa, double dA, double dB, double dC, double dD, double dH) :
            Prostokat(sNazwa, dA, dB), d_bok_c(dC), d_bok_d(dD), d_h(dH){}

    double dPole() {
        return ((d_bok_a + d_bok_b) * d_h) / 2;
    }

    double dObwod() override {
        return d_bok_a + d_bok_b + d_bok_c + d_bok_d;
    }

    string sToString() override {
        return Figura::sToString() + ":  bok a = " + to_string(d_bok_a) +
               "\n \t \t bok b = " + to_string(d_bok_b) +
                "\n \t \t bok c = " + to_string(d_bok_c) +
                "\n \t \t bok d = " + to_string(d_bok_d) +
                "\n \t \t wysokość = " + to_string(d_h) +
               "\n \t \t pole = " + to_string(dPole()) +
               "\n \t \t obwód = " + to_string(dObwod());
    }

};


#endif //ZMPO_LAB5_TRAPEZ_H
