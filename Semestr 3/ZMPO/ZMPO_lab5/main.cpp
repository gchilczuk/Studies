#include <iostream>
//#include "Kolo.h"
#include "Kwadrat.h"
#include "Prostokat.h"
#include <complex>

using namespace std;
using std::cout;
using std::cin;
using std::endl;

template <class T> T suma(T tab[], int len){
    T suma = tab[0];
    for (int i = 1; i < len; i++)
        suma += tab[i];
    return suma;
}

int main() {
    /*Prostokat prostokat( 2, 3);
    Kwadrat &k = prostokat;
    cout << k.sToString() << endl;
    */
    typedef complex<double> dcomp;

    dcomp tt[] = { {0.0,1.0}, {0.0,1.0}};
    int tab[] = {1,2,3};
    char ttab[] = {'G', 'H'};
    cout << suma(tt, 2);

    return 0;
}