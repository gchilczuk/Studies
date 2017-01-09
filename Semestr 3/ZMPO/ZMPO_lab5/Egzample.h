
#ifndef ZMPO_LAB5_EGZAMPLE_H
#define ZMPO_LAB5_EGZAMPLE_H


#include <ostream>

class Egzample {
private:
    int i_i;
public:
    Egzample():i_i(0){}
    Egzample(int iI):i_i(iI){}

    Egzample operator+ (Egzample &pcOther){
        return Egzample(i_i+pcOther.i_i);
    }

    Egzample & operator= (Egzample pcOther){
        i_i = pcOther.i_i;
        return *this;
    }

    friend std::ostream &operator<<(std::ostream &os, const Egzample &egzample) {
        os << "i_i: " << egzample.i_i;
        return os;
    }

};


#endif //ZMPO_LAB5_EGZAMPLE_H
