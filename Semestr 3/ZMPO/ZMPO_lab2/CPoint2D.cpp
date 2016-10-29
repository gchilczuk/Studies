#include "CPoint2D.h"

CPoint2D::CPoint2D(double *pdCoordX, double *pdCoordY) : pd_coordx(pdCoordX), pd_coordy(pdCoordY) {}

CPoint2D::CPoint2D() : pd_coordx(0), pd_coordy(0){}

CPoint2D::CPoint2D(CPoint2D &pcModel) {
    pd_coordx = pcModel.pd_coordx;
    pd_coordy = pcModel.pd_coordy;
} //CPoint2D::CPoint2D(CPoint2D &pcModel) {


CPoint2D::~CPoint2D() {
    if (pd_coordx) {
        delete pd_coordx;
        pd_coordx = nullptr;
    }

    if (pd_coordy) {
        delete pd_coordy;
        pd_coordy = nullptr;
    }
} // CPoint2D::~CPoint2D() {


double *CPoint2D::getX() const {
    return pd_coordx;
}

double *CPoint2D::getY() const {
    return pd_coordy;
}

void CPoint2D::setX(double *pdCoordX) {
    pd_coordx = pdCoordX;
}

void CPoint2D::setY(double *pdCoordY) {
    pd_coordy = pdCoordY;
}

