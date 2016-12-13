#ifndef ZMPO_LAB5_PROSTOKAT_H
#define ZMPO_LAB5_PROSTOKAT_H

#include "Kwadrat.h"

class Prostokat : public Kwadrat {
protected:
    double d_bok_b;
public:
    Prostokat(){nazwa = "Prostokąt"; d_bok_b = STANDARD_A;}
    Prostokat(double dA, double dB) : Kwadrat("Prostokąt", dA){d_bok_b = dB;}
    Prostokat(string sNazwa, double dA, double dB) : Kwadrat(sNazwa, dA){d_bok_b = dB;}
    double dPole(){ return d_bok_a * d_bok_b;}
    double dObwod(){ return 2*d_bok_a + 2*d_bok_b;}
    string sToString(){
        return Figura::sToString() + ":  bok a = " + to_string(d_bok_a) +
                "\n \t \t bok b = " + to_string(d_bok_b) +
               "\n \t \t pole = " + to_string(dPole()) +
               "\n \t \t obwód = " + to_string(dObwod());
    }



};


#endif //ZMPO_LAB5_PROSTOKAT_H
