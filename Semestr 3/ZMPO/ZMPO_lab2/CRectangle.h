#ifndef ZMPO_LAB2_CRECTANGLE_H
#define ZMPO_LAB2_CRECTANGLE_H

#include "CPoint2D.h"
#include <iostream>


class CRectangle {
private:
    CPoint2D *pc_point_one;
    CPoint2D *pc_point_two;
public:
    CRectangle();
    CRectangle(CPoint2D *pcPointOne, CPoint2D *pcPointTwo);
    CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo);
    CRectangle(CRectangle &pcModel);
    ~CRectangle();

    double dArea();
    string sToString();

};


#endif //ZMPO_LAB2_CRECTANGLE_H
