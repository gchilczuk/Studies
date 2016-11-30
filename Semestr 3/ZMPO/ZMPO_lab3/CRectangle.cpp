#include "CRectangle.h"

//CRectangle::CRectangle() : pc_point_one(new CPoint2D()), pc_point_two(new CPoint2D()) {}

CRectangle::CRectangle(const CPoint2D &pcPointOne, const CPoint2D &pcPointTwo) :
        pc_point_one(min(pcPointOne.dGetX(), pcPointTwo.dGetX()), min(pcPointOne.dGetY(),pcPointTwo.dGetY())),
        pc_point_two(max(pcPointOne.dGetX(), pcPointTwo.dGetX()), max(pcPointOne.dGetY(),pcPointTwo.dGetY()))
        {}

CRectangle::CRectangle(double dXOne, double dYOne, double dXTwo, double dYTwo):
        pc_point_one (min(dXOne, dXTwo), min(dYOne, dYTwo)),
        pc_point_two (max(dXTwo, dXOne), max(dYTwo, dYOne))
        {} // CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {


CRectangle::CRectangle(const CRectangle &pcModel):
        pc_point_one (pcModel.pc_point_one),
        pc_point_two (pcModel.pc_point_two)
        {}

double CRectangle::dArea() {
    double x2 = pc_point_two.dGetX();
    double x1 = pc_point_one.dGetX();
    double y2 = pc_point_two.dGetY();
    double y1 = pc_point_one.dGetY();
    return (x2-x1)*(y2-y1);
//    return abs((x2-x1)*(y2-y1));
}// CRectangle::CRectangle(CRectangle &pcModel) {

string CRectangle::sToString() {
    return "CRectangle("+pc_point_one.sToString()+", "+pc_point_two.sToString()+")";
}

//CRectangle::~CRectangle() {
//    delete pc_point_one;
//    delete pc_point_two;
//}

CRectangle &CRectangle::operator=(CRectangle &pcOther) {
//    delete pc_point_one;
//    delete pc_point_two;
    pc_point_one = pcOther.pc_point_one;
    pc_point_two = pcOther.pc_point_two;
    return (*this);
}

CRectangle &CRectangle::operator+(CRectangle &pcOther) {
    CRectangle pc_tmp(*this);
    pc_tmp.operator+(pcOther.pc_point_one);
    pc_tmp.operator+(pcOther.pc_point_two);
//    double *minmax = dtab_minmax(*this, pcOther);
//    CRectangle pc_tmp = CRectangle(minmax[0], minmax[1], minmax[2], minmax[3]);
//    delete[] minmax;
    return pc_tmp;
}

CRectangle &CRectangle::operator+(CPoint2D &pcPoint) {
    double px = pcPoint.dGetX(), py = pcPoint.dGetY();
    double minx = min(px, d_min_x()), miny = min(py, d_min_y());
    double maxx = max(px, d_max_x()), maxy = max(py, d_max_y());
    CRectangle pc_tmp = CRectangle(minx, miny, maxx, maxy);
    return pc_tmp;
}


double CRectangle::d_min_x() {
    return pc_point_one.dGetX();
//    return min(pc_point_one.dGetX(), pc_point_two.dGetX());
}

double CRectangle::d_min_y() {
    return pc_point_one.dGetY();
//    return min(pc_point_one.dGetY(), pc_point_two.dGetY());
}

double CRectangle::d_max_x() {
    return pc_point_two.dGetX();
//    return max(pc_point_one.dGetX(), pc_point_two.dGetX());
}

double CRectangle::d_max_y() {
    return pc_point_two.dGetY();
//    return max(pc_point_one.dGetY(), pc_point_two.dGetY());
}

double *CRectangle::dtab_minmax(CRectangle &pcOne, CRectangle &pcTwo) {
    double *minmax = new double[4];
    minmax[0] = min(pcOne.d_min_x(), pcTwo.d_min_x());
    minmax[1] = min(pcOne.d_min_y(), pcTwo.d_min_y());
    minmax[2] = max(pcOne.d_max_x(), pcTwo.d_max_x());
    minmax[3] = max(pcOne.d_max_y(), pcTwo.d_max_y());
    return minmax;
}


