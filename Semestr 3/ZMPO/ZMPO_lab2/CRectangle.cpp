#include "CRectangle.h"

//CRectangle::CRectangle() : pc_point_one(0,0), pc_point_two(0,0) {}

CRectangle::CRectangle(const CPoint2D &pcPointOne, const CPoint2D &pcPointTwo) :
        pc_point_one(pcPointOne),
        pc_point_two(pcPointTwo) {}

CRectangle::CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo):
        pc_point_one (pdXOne, pdYOne),
        pc_point_two (pdXTwo, pdYTwo){} // CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {


CRectangle::CRectangle(const CRectangle &pcModel):
    pc_point_one (pcModel.pc_point_one),
    pc_point_two (pcModel.pc_point_two){}

double CRectangle::dArea() {
    double x2 = pc_point_two.dGetX();
    double x1 = pc_point_one.dGetX();
    double y2 = pc_point_two.dGetY();
    double y1 = pc_point_one.dGetY();
    return abs((x2-x1)*(y2-y1));
}// CRectangle::CRectangle(CRectangle &pcModel) {

string CRectangle::sToString() {
    return "CRectangle("+pc_point_one.sToString()+", "+pc_point_two.sToString()+")";
}
//
//CRectangle::~CRectangle() {
//    delete pc_point_one;
//    delete pc_point_two;
//}
//

