#include <iostream>

#include "Para.h"
using namespace std;

Para<double, int> szukajMin(double tab[], int len){
    double x = tab[0];
    int i, k = 0;
    for (i = 1; i < len; i++) {
        if (tab[i] < x) {
            x = tab[i];
            k = i;
        }
    }
    return Para<double, int> (x, k);
};

Para<Para<double, int>, Para<double, int>> extrema(double tab[], int len){
    double min = tab[0], max = tab[0];
    int imin = 0, imax =0, i;
    for (i = 1; i < len; i++) {
        if (tab[i] < min) {
            min = tab[i];
            imin = i;
        }
        if (tab[i] > max){
            max = tab[0];
            imax = i;
        }
    }
    return Para<Para<double, int>, Para<double, int>> (Para<double,int>(min, imin), Para<double, int>(max,imax));
};

int main() {
    double tab[] = {1,3,2,0,-5,6,8,4};
    int len = 8;
    Para<double, int> mi = szukajMin(tab, len);
    mi.show();

    Para<Para<double, int>, Para<double, int>> mu = extrema(tab, len);
    cout << "MIN: " ;
    mu.getWsk().show();
    cout <<endl << " MAX: ";
    mu.getWart().show();


    return 0;
}