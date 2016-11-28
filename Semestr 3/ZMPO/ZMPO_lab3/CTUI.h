#ifndef ZMPO_LAB2_CTUI_H
#define ZMPO_LAB2_CTUI_H

#include <iostream>
#include <string>
#include "CPoint2D.h"
#include "CRectangle.h"

using std::cout;
using std::cin;
using std::endl;
using std::to_string;


class CTUI {

private:
    CPoint2D **points;
    CRectangle **rectangles;
    int i_point_size;
    int i_rect_size;
    int i_incorrect_commands;

    bool bPreparePlace(string sTabName, int iIndex);
    bool bIsPoint(int iIndex);
    bool bIsRectangle(int iIndex);

    void createPoint();
    void createPointCopy();
    void setPoint();
    void getPointX();
    void getPointY();
    void createRectDouble();
    void createRectPoints();
    void createRectCopy();
    void fieldRect();
    void showRect();

    void addPoint();
    void addRect();
    void assignRect();
    void assignPoint();
    void showPoint();

    bool bIsExitCommand(string &comm);

public:

    CTUI();
    ~CTUI();

    bool boot(string &psCommand);
    bool evaluateCommand(string &psCommand);

};


#endif //ZMPO_LAB2_CTUI_H
