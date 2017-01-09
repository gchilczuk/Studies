#ifndef SZABLONY
#define SZABLONY

#include <iostream>
#include <complex>
#include "Egzample.h"

using std::cout;
using std::cin;
using std::endl;
using std::complex;

template <class T> T suma(T tab[], int len){
    T suma = tab[0];
    for (int i = 1; i < len; i++)
        suma = suma + tab[i];
    return suma;
}


#endif ///SZABLONY