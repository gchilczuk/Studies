#ifndef ZMPO_LAB5_TROJKAT_H
#define ZMPO_LAB5_TROJKAT_H

#include "Prostokat.h"

class Trojkat : public Prostokat{
protected:
public:
    Trojkat() : Prostokat("Anonimowy trójkąt", STANDARD_A, STANDARD_A){};
    Trojkat(string sNazwa, double dA, double dB) : Prostokat(sNazwa, dA, dB){}

    virtual double dPole() override {
        return d_bok_a * d_bok_b / 2;
    }

    virtual double dObwod() override {
        return d_bok_a + d_bok_b + sqrt((d_bok_a*d_bok_a + d_bok_b*d_bok_b));
    }

    virtual string sToString() override {
        return Figura::sToString() + ":  bok a = " + to_string(d_bok_a) +
                "\n \t \t bok b = " + to_string(d_bok_b) +
                "\n \t \t bok b = " + to_string(d_bok_b) +
                "\n \t \t pole = " + to_string(dPole()) +
                "\n \t \t obwód = " + to_string(dObwod());
    }
};


#endif //ZMPO_LAB5_TROJKAT_H
