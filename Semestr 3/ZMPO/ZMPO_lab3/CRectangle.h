#ifndef ZMPO_LAB2_CRECTANGLE_H
#define ZMPO_LAB2_CRECTANGLE_H

#include "CPoint2D.h"
#include <iostream>
#include <cmath>

using std::abs;
using std::min;
using std::max;


class CRectangle {
private:
    bool b_tmp;
    CPoint2D *pc_point_one;
    CPoint2D *pc_point_two;
    double d_min_x();
    double d_min_y();
    double d_max_x();
    double d_max_y();
    double *dtab_minmax(CRectangle &pcOne, CRectangle &pcTwo);

public:
    //    CRectangle();
    CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo);
    CRectangle(CPoint2D &pcPointOne, CPoint2D &pcPointTwo);
    CRectangle(CRectangle &pcOther);
    ~CRectangle();

    double dArea();
    string sToString();

    CRectangle &operator=(CRectangle &pcOther);
    CRectangle &operator+(CRectangle &pcOther);
    CRectangle &operator+(CPoint2D &pcPoint);

};


#endif //ZMPO_LAB2_CRECTANGLE_H
