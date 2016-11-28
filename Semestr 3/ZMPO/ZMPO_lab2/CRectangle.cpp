#include "CRectangle.h"

//CRectangle::CRectangle() : pc_point_one(new CPoint2D()), pc_point_two(new CPoint2D()) {}

CRectangle::CRectangle(CPoint2D &pcPointOne, CPoint2D &pcPointTwo) :
        pc_point_one(new CPoint2D(pcPointOne)),
        pc_point_two(new CPoint2D(pcPointTwo)) {}

CRectangle::CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo):
        pc_point_one (new CPoint2D(pdXOne, pdYOne)),
        pc_point_two (new CPoint2D(pdXTwo, pdYTwo)){} // CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {


CRectangle::CRectangle(CRectangle &pcModel):
    pc_point_one (new CPoint2D(*pcModel.pc_point_one)),
    pc_point_two (new CPoint2D(*pcModel.pc_point_two)){}

double CRectangle::dArea() {
    double x2 = pc_point_two->dGetX();
    double x1 = pc_point_one->dGetX();
    double y2 = pc_point_two->dGetY();
    double y1 = pc_point_one->dGetY();
    return abs((x2-x1)*(y2-y1));
}// CRectangle::CRectangle(CRectangle &pcModel) {

string CRectangle::sToString() {
    return "CRectangle("+pc_point_one->sToString()+", "+pc_point_two->sToString()+")";
}

CRectangle::~CRectangle() {
    delete pc_point_one;
    delete pc_point_two;
}


