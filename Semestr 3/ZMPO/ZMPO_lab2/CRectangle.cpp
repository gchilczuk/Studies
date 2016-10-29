#include "CRectangle.h"

CRectangle::CRectangle() : pc_point_one(new CPoint2D()), pc_point_two(new CPoint2D()) {}

CRectangle::CRectangle(CPoint2D *pcPointOne, CPoint2D *pcPointTwo) : pc_point_one(pcPointOne),
                                                                        pc_point_two(pcPointTwo) {}

CRectangle::CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo) {
    pc_point_one = new CPoint2D(pdXOne, pdYOne);
    pc_point_two = new CPoint2D(pdXTwo, pdYTwo);
} // CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {


CRectangle::CRectangle(CRectangle &pcModel) {
    pc_point_one = new CPoint2D(*pcModel.pc_point_one);
    pc_point_two = new CPoint2D(*pcModel.pc_point_two);
}

double CRectangle::dArea() {
    double x2 = *pc_point_two->getX();
    double x1 = *pc_point_one->getX();
    double y2 = *pc_point_two->getY();
    double y1 = *pc_point_one->getY();
    std::cout << "x1: " << x1 << " | x2: " << x2 << " | y1: " << y1 << " | y2: " << y2 << std::endl;
    return (x2-x1)*(y2-y1);
}// CRectangle::CRectangle(CRectangle &pcModel) {

string CRectangle::sToString() {
    return "CRectangle("+pc_point_one->sToString()+", "+pc_point_two->sToString()+")";
}

CRectangle::~CRectangle() {
    if (pc_point_one){
        delete pc_point_one;
        pc_point_one = nullptr;
    }
    if (pc_point_two){
        delete pc_point_two;
        pc_point_two = nullptr;
    }
}


