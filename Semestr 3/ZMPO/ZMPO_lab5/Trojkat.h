#ifndef ZMPO_LAB5_TROJKAT_H
#define ZMPO_LAB5_TROJKAT_H

#include "Prostokat.h"

class Trojkat : public Prostokat{
protected:
    double d_bok_c;
public:
    Trojkat() : Prostokat("Anonimowy trójkąt", STANDARD_A, STANDARD_A),
                d_bok_c(STANDARD_A){};
    Trojkat(string sNazwa, double dA, double dB, double dC) : Prostokat(sNazwa, dA, dB){d_bok_c = dC;}

    virtual double dPole() override {
        double p = dObwod()/2;
        return sqrt(p*(p-d_bok_a)*(p-d_bok_b)*(p-d_bok_c));
    }

    virtual double dObwod() override {
        return d_bok_a + d_bok_b + d_bok_c;
    }

    virtual string sToString() override {
        return Figura::sToString() + ":  bok a = " + to_string(d_bok_a) +
                "\n \t \t bok b = " + to_string(d_bok_b) +
                "\n \t \t bok b = " + to_string(d_bok_b) +
                "\n \t \t bok c = " + to_string(d_bok_c) +
                "\n \t \t pole = " + to_string(dPole()) +
                "\n \t \t obwód = " + to_string(dObwod());
    }
};


#endif //ZMPO_LAB5_TROJKAT_H
