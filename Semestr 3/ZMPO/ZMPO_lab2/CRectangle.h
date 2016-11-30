#ifndef ZMPO_LAB2_CRECTANGLE_H
#define ZMPO_LAB2_CRECTANGLE_H

#include "CPoint2D.h"
#include <iostream>
#include <cmath>

using std::abs;


class CRectangle {
private:
    CPoint2D pc_point_one;
    CPoint2D pc_point_two;
public:
//    CRectangle();
    CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo);
    CRectangle(const CPoint2D &pcPointOne, const CPoint2D &pcPointTwo);
    CRectangle(const CRectangle &pcModel);
//    ~CRectangle();

    double dArea();
    string sToString();

};


#endif //ZMPO_LAB2_CRECTANGLE_H
