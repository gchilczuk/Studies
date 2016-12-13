#ifndef ZMPO_LAB5_FIGURA_H
#define ZMPO_LAB5_FIGURA_H
#define STANDARD_A 5

#include <string>
#include <cmath>
#include <iostream>


using std::string;
using std::to_string;

class Figura {

protected:
    string nazwa;

public:
    Figura(){std::cout << "FIGURA"<<std::endl;}
    Figura(string sNazwa){nazwa = sNazwa;std::cout << "FIGURA - parametr"<<std::endl;}
    virtual ~Figura(){};
    virtual double dPole() = 0;
    virtual double dObwod() = 0;
    virtual string sToString() { return nazwa;}

};


#endif //ZMPO_LAB5_FIGURA_H
