#ifndef ZMPO_LAB5_KOLO_H
#define ZMPO_LAB5_KOLO_H

#include "Figura.h"

class Kolo : public Figura {

private:
    double d_promien;

public:
    Kolo(){nazwa = "Koło"; d_promien = STANDARD_A;}
    Kolo(double dr){nazwa = "Koło"; d_promien = dr;}
    Kolo(string sNazwa, double dr) {nazwa = sNazwa; d_promien = dr;}
    double Kolo::dPole() {return d_promien * d_promien * M_PI;}
    double Kolo::dObwod() {return 2 * M_PI * d_promien;}
    string sToString(){
        return Figura::sToString() + ":  promień = " + to_string(d_promien) +
               "\n \t \t pole = " + to_string(dPole()) +
               "\n \t \t obwód = " + to_string(dObwod());
    }

};


#endif //ZMPO_LAB5_KOLO_H
