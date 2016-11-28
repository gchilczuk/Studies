#include "CPoint2D.h"

//CPoint2D::CPoint2D() : pd_x(new double(0)), pd_y(new double(0)) {}

CPoint2D::CPoint2D(double dX, double dY):
    pd_x (new double(dX)),
    pd_y (new double(dY)){}

CPoint2D::CPoint2D(const CPoint2D &pcModel):
    pd_x (new double(*pcModel.pd_x)),
    pd_y (new double(*pcModel.pd_y)){}

CPoint2D::~CPoint2D() {
    delete pd_x;
    delete pd_y;
}

double CPoint2D::dGetX() const {
    return *pd_x;
}

double CPoint2D::dGetY() const {
    return *pd_y;
}

void CPoint2D::setX(double pdX) {
    *pd_x = pdX;
}

void CPoint2D::setY(double pdY) {
    *pd_y = pdY;
}

string CPoint2D::sToString() {
    return "CPoint("+to_string(*pd_x)+","+to_string(*pd_y)+")";
}

CPoint2D &CPoint2D::operator=(CPoint2D &pcOther) {
    delete pd_x;
    delete pd_y;
    pd_x = new double (*pcOther.pd_x);
    pd_y = new double (*pcOther.pd_y);
    return *this;
}



