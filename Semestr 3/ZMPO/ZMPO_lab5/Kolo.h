#ifndef ZMPO_LAB5_KOLO_H
#define ZMPO_LAB5_KOLO_H

#include "Figura.h"

class Kolo : public Figura {

protected:
    double d_promien;

public:
    Kolo():Figura("Koło anonimowe"){d_promien = STANDARD_A;}
    Kolo(double dr):Figura("Koło anonimowe"){ d_promien = dr;}
    Kolo(string sNazwa, double dr):Figura(sNazwa) {d_promien = dr;}

    double dPole() {return d_promien * d_promien * M_PI;}
    double dObwod() {return 2 * M_PI * d_promien;}

    string sToString(){
        return Figura::sToString() + ":  promień = " + to_string(d_promien) +
               "\n \t \t pole = " + to_string(dPole()) +
               "\n \t \t obwód = " + to_string(dObwod());
    }

};


#endif //ZMPO_LAB5_KOLO_H
