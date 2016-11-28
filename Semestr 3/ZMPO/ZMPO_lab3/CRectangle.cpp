#include "CRectangle.h"

//CRectangle::CRectangle() : pc_point_one(new CPoint2D()), pc_point_two(new CPoint2D()) {}

CRectangle::CRectangle(CPoint2D &pcPointOne, CPoint2D &pcPointTwo) :
        pc_point_one(new CPoint2D(pcPointOne)),
        pc_point_two(new CPoint2D(pcPointTwo)),
        b_tmp(false) {}

CRectangle::CRectangle(double pdXOne, double pdYOne, double pdXTwo, double pdYTwo):
        pc_point_one (new CPoint2D(pdXOne, pdYOne)),
        pc_point_two (new CPoint2D(pdXTwo, pdYTwo)),
        b_tmp(false){} // CRectangle::CRectangle(double *pdXOne, double *pdYOne, double *pdXTwo, double *pdYTwo) {


CRectangle::CRectangle(CRectangle &pcOther):
    pc_point_one (new CPoint2D(*pcOther.pc_point_one)),
    pc_point_two (new CPoint2D(*pcOther.pc_point_two)),
    b_tmp(pcOther.b_tmp){}

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

CRectangle &CRectangle::operator=(CRectangle &pcOther) {
    delete pc_point_one;
    delete pc_point_two;
    pc_point_one = new CPoint2D(*pcOther.pc_point_one);
    pc_point_two = new CPoint2D(*pcOther.pc_point_two);
    if (pcOther.b_tmp) delete &pcOther;
    return (*this);
}

CRectangle &CRectangle::operator+(CRectangle &pcOther) {
    double *minmax = dtab_minmax(*this, pcOther);
    CRectangle *pc_tmp = new CRectangle(minmax[0], minmax[1], minmax[2], minmax[3]);
    pc_tmp->b_tmp = true;
    if (pcOther.b_tmp) delete &pcOther;
    delete[] minmax;
    return *pc_tmp;
}

double CRectangle::d_min_x() {
    std::cout << "d_min_x" << min(pc_point_one->dGetX(), pc_point_two->dGetX()) << std::endl;
    return min(pc_point_one->dGetX(), pc_point_two->dGetX());
}

double CRectangle::d_min_y() {
    std::cout << "d_min_y" << min(pc_point_one->dGetY(), pc_point_two->dGetY()) << std::endl;
    return min(pc_point_one->dGetY(), pc_point_two->dGetY());
}

double CRectangle::d_max_x() {
    std::cout << "d_max_xy" << max(pc_point_one->dGetX(), pc_point_two->dGetX()) << std::endl;
    return max(pc_point_one->dGetX(), pc_point_two->dGetX());
}

double CRectangle::d_max_y() {
    std::cout << "d_max_y" << max(pc_point_one->dGetY(), pc_point_two->dGetY()) << std::endl;
    return max(pc_point_one->dGetY(), pc_point_two->dGetY());
}

double *CRectangle::dtab_minmax(CRectangle &pcOne, CRectangle &pcTwo) {
    double *minmax = new double[4];
    minmax[0] = min(pcOne.d_min_x(), pcTwo.d_min_x());
    minmax[1] = min(pcOne.d_min_y(), pcTwo.d_min_y());
    minmax[2] = min(pcOne.d_max_x(), pcTwo.d_max_x());
    minmax[3] = min(pcOne.d_max_y(), pcTwo.d_max_y());
    return minmax;
}

CRectangle &CRectangle::operator+(CPoint2D &pcPoint) {
    double px = pcPoint.dGetX(), py = pcPoint.dGetY();
    double minx = min(px, d_min_x()), miny = min(py, d_min_y());
    double maxx = max(px, d_max_x()), maxy = max(py, d_max_y());
    CRectangle *pc_tmp = new CRectangle(minx, miny, maxx, maxy);
    pc_tmp->b_tmp = true;
    return *pc_tmp;
}


