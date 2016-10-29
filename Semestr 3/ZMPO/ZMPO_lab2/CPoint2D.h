#ifndef ZMPO_LAB2_CPOINT2D_H
#define ZMPO_LAB2_CPOINT2D_H


class CPoint2D {
private:
    double *pd_coordx;
    double *pd_coordy;
public:
    CPoint2D(double *pdCoordX, double *pdCoordY);
    CPoint2D(CPoint2D &pcModel);
    CPoint2D();
    ~CPoint2D();

    double *getX() const;
    double *getY() const;
    void setX(double *pdCoordX);
    void setY(double *pdCoordY);
};


#endif //ZMPO_LAB2_CPOINT2D_H
