#ifndef ZMPO_LAB5_KWADRAT_H
#define ZMPO_LAB5_KWADRAT_H

#include "Figura.h"

class Kwadrat : public Figura{
protected:
    double d_bok_a;
public:
    Kwadrat():Figura("Kwadrat"){ d_bok_a = STANDARD_A;std::cout << "KWADRAT"<<std::endl;}
    Kwadrat(double dA):Figura("Kwadrat"){d_bok_a = dA;std::cout << "KWADRAT - 1 param"<<std::endl;}
    Kwadrat(string sName, double dA):Figura(sName){ d_bok_a = dA;std::cout << "KWADRAT - 2 param"<<std::endl;}
    double dPole(){ return d_bok_a * d_bok_a;}
    double dObwod(){ return 4*d_bok_a;}
    string sToString(){
        return Figura::sToString() + ":  bok = " + to_string(d_bok_a) +
               "\n \t \t pole = " + to_string(dPole()) +
               "\n \t \t obwód = " + to_string(dObwod());
    }


};


#endif //ZMPO_LAB5_KWADRAT_H
