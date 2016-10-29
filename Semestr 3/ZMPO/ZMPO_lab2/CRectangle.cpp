#include "CRectangle.h"

CRectangle::CRectangle() : pc_point_one(new CPoint2D()), pc_point_two(new CPoint2D()) {}

CRectangle::CRectangle(CPoint2D *pcPointOne, CPoint2D *pcPointTwo) : pc_point_one(pcPointOne),
                                                                        pc_point_two(pcPointTwo) {}

CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {
    pc_point_one = new CPoint2D(pdXOne, pdYOne);
    pc_point_two = new CPoint2D(pdXTwo, pdYTwo);
} // CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {


CRectangle::CRectangle(CRectangle &pcModel) {
    pc_point_one = new CPoint2D(*pcModel.pc_point_one);
    pc_point_two = new CPoint2D(*pcModel.pc_point_two);
} // CRectangle::CRectangle(CRectangle &pcModel) {


