#ifndef ZMPO_LAB2_CPOINT2D_H
#define ZMPO_LAB2_CPOINT2D_H

#include <string>

using std::string;
using std::to_string;

class CPoint2D {
private:
    double *pd_x;
    double *pd_y;

public:

//    CPoint2D();
    CPoint2D(double dX, double dY);
    CPoint2D(const CPoint2D &pcModel);
    ~CPoint2D();

    double dGetX() const;
    double dGetY() const;

    void setX(double dX);
    void setY(double dY);

    string sToString();

    CPoint2D &operator=(CPoint2D &pcOther);

};


#endif //ZMPO_LAB2_CPOINT2D_H
