#include "CTUI.h"

CTUI::CTUI() {
    i_incorrect_commands = 0;
}

CTUI::~CTUI() {
    int ii;
    for (ii = 0; ii < i_point_size; ii++) {
        delete points[ii];
    }

    for (ii = 0; ii < i_rect_size; ii++){
        delete rectangles[ii];
    }

    delete points;
    delete rectangles;
} // CTUI::~CTUI() {

bool CTUI::boot(string &psCommand) {
    bool wyn = false;
    int ip,ir;
    if (psCommand != "go"){
        i_incorrect_commands++;
        if (i_incorrect_commands > 3){
            ip = ir = 10;
            i_point_size = ip;
            i_rect_size = ir;
            points = new CPoint2D *[ip];
            rectangles = new CRectangle*[ir];
            int ii;
            for (ii = 0; ii < ip; ii++) {
                points[ii] = nullptr;
            }
            for (ii = 0; ii < ir; ii++) {
                rectangles[ii] = nullptr;
            }
            wyn = true;
            cout << "fails > 3 → go 10 10 → DONE" << endl;
        }
        return wyn;}
    cin >> ip >> ir;
    cout << "!go " << ip << " " << ir << endl;
    if (ip >= 0 && ir >= 0){
        i_point_size = ip;
        i_rect_size = ir;
        points = new CPoint2D *[ip];
        rectangles = new CRectangle*[ir];
        int ii;
        for (ii = 0; ii < ip; ii++) {
            points[ii] = nullptr;
        }
        for (ii = 0; ii < ir; ii++) {
            rectangles[ii] = nullptr;
        }
        wyn = true;
        cout << "DONE" << endl;
    } //     if (ip >= 0 && ir >= 0){
    else cout << "ERROR" << endl;
    return wyn;
} // bool CTUI::boot(string command) {

bool CTUI::bPreparePlace(string sTabName, int iIndex) {
    bool wyn = false;
    if (sTabName == "p"){
        if (iIndex >= 0 && iIndex <= i_point_size){
            if (points[iIndex]) {
                delete points[iIndex];
                cout << "overwriting | ";
            }
            wyn = true;
        }
    } //     if (sTabName == "p"){
    else if(sTabName == "r"){
        if (iIndex >= 0 && iIndex <= i_rect_size) {
            if (rectangles[iIndex]){
                delete rectangles[iIndex];
                cout << "overwriting | ";
            }
            wyn = true;
        }
    } //     else if(sTabName == "r"){
    return wyn;
} // bool CTUI::bPreparePlace(string sTabName, int iIndex) {

bool CTUI::bIsPoint(int iIndex) {
    return iIndex >= 0 && iIndex <= i_point_size && points[iIndex];
}

bool CTUI::bIsRectangle(int iIndex) {
    return iIndex >= 0 && iIndex <= i_rect_size && rectangles[iIndex];
}

void CTUI::createPoint() {
    string wyn = "ERROR";
    int ii;
    double dx, dy;
    cin >> ii >> dx >> dy;
    cout << "!createPoint " << ii << " " << dx << " " << dy << endl;
    if (bPreparePlace("p", ii)){
        points[ii] = new CPoint2D(dx,dy);
        wyn = "DONE";
    }
    cout << wyn << endl;
}// string CTUI::createPoint() {

void CTUI::createPointCopy() {
    string wyn = "ERROR";
    int iito, iifrom;
    cin >> iito >> iifrom;
    cout << "!createPointCopy " << iito << " " << iifrom << endl;
    if (iito != iifrom && iifrom >= 0 && iifrom < i_point_size && points[iifrom] && bPreparePlace("p", iito)){
        points[iito] = new CPoint2D(*points[iifrom]);
        wyn = "DONE";
    }else{
        cout << i_point_size << " " << points[iifrom] << endl;
    }
    cout << wyn << endl;
} // void CTUI::createPointCopy() {

void CTUI::setPoint() {
    string wyn = "ERROR";
    int ii;
    double dx, dy;
    cin >> ii >> dx >> dy;
    cout << "!setPoint " << ii << " " << dx << " " << dy << endl;
    if (bIsPoint(ii)){
        points[ii]->setX(dx);
        points[ii]->setY(dy);
        wyn = "DONE";
    }
    cout << wyn << endl;
} // void CTUI::setPoint() {

void CTUI::getPointX() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!getPointX " << ii << endl;
    if (bIsPoint(ii)){
        wyn = to_string(points[ii]->dGetX());
    }
    cout << wyn << endl;
} // void CTUI::getPointX() {

void CTUI::getPointY() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!getPointY " << ii << endl;
    if (bIsPoint(ii)){
        wyn = to_string(points[ii]->dGetY());
    }
    cout << wyn << endl;
} // void CTUI::getPointY() {

void CTUI::createRectDouble() {
    string wyn = "ERROR";
    int ii;
    double dx1, dx2, dy1, dy2;
    cin >> ii >> dx1 >> dy1 >> dx2 >> dy2;
    cout << "!createRectDouble " << ii << " " << dx1 << " " << dy1 << " " << dx2 << " " << dy2 << endl;
    if (bPreparePlace("r",ii)){
        rectangles[ii] = new CRectangle(dx1, dy1, dx2, dy2);
        wyn = "DONE";
    }
    cout << wyn << endl;
} // void CTUI::createRectDouble() {

void CTUI::createRectPoints() {
    string wyn = "ERROR";
    int ii, iip1, iip2;
    cin >> ii >> iip1 >> iip2;
    cout << "!createRectPoints " << ii << " " << iip1 << " " << iip2 << endl;
    if (bPreparePlace("r", ii) && bIsPoint(iip1) && bIsPoint(iip2)){
        rectangles[ii] = new CRectangle(*(points[iip1]), *(points[iip2]));
        wyn = "DONE";
    }
    cout << wyn << endl;
} // void CTUI::createRectPoints() {

void CTUI::createRectCopy() {
    string wyn = "ERROR";
    int iito, iifrom;
    cin >> iito >> iifrom;
    cout << "!createRectCopy " << iito << " " << iifrom << endl;
    if (iito != iifrom && iifrom >= 0 && iifrom < i_rect_size && rectangles[iifrom] && bPreparePlace("r", iito)){
        rectangles[iito] = new CRectangle(*rectangles[iifrom]);
        wyn = "DONE";
    }
    cout << wyn << endl;
} // void CTUI::createRectCopy() {

void CTUI::fieldRect() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!fieldRect " << ii << endl;
    if (bIsRectangle(ii))
        wyn = to_string(rectangles[ii]->dArea());
    cout << wyn << endl;
} // void CTUI::fieldRect() {

void CTUI::showRect() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!showRect " << ii << endl;
    if (bIsRectangle(ii))
        wyn = rectangles[ii]->sToString();
    cout << wyn << endl;
} // void CTUI::showRect() {

void CTUI::addPoint() {
    string wyn = "ERROR";
    int ir, ip;
    cin >> ir >> ip;
    cout << "!addPoint " << ir << " " << ip << endl;
    if (bIsRectangle(ir) && bIsPoint(ip)){
        *rectangles[ir] = *rectangles[ir] + *points[ip];
        wyn = "DONE";
    }
    cout << wyn << endl;
}


void CTUI::addRect() {
    string wyn = "ERROR";
    int ir, irp;
    cin >> ir >> irp;
    cout << "!addRect " << ir << " " << irp << endl;
    if (bIsRectangle(ir) && bIsRectangle(irp)){
        *rectangles[ir] = *rectangles[ir] + *rectangles[irp];
        wyn = "DONE";
    }
    cout << wyn << endl;
}

void CTUI::assignRect() {
    string wyn = "ERROR";
    int ir, irp;
    cin >> ir >> irp;
    cout << "!assignRect " << ir << " " << irp << endl;
    if (bIsRectangle(ir) && bIsRectangle(irp)){
        *rectangles[ir] = *rectangles[irp];
        wyn = "DONE";
    }
    cout << wyn << endl;
}

void CTUI::assignPoint() {
    string wyn = "ERROR";
    int ir, irp;
    cin >> ir >> irp;
    cout << "!assignPoint " << ir << " " << irp << endl;
    if (bIsPoint(ir) && bIsPoint(irp)){
        *points[ir] = *points[irp];
        wyn = "DONE";
    }
    cout << wyn << endl;
}

void CTUI::showPoint() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!showPoint " << ii << endl;
    if (bIsPoint(ii))
        wyn = (*points[ii]).sToString();
    cout << wyn << endl;
}



bool CTUI::evaluateCommand(string &psCommand) {
    string &com = psCommand;
    bool wyn = true;
    if (bIsExitCommand(com))
        wyn = false;
    else if (com == "createPoint" || com == "cP")
        createPoint();
    else if (com == "createPointCopy" || com == "cPC")
        createPointCopy();
    else if (com == "setPoint" || com == "sP")
        setPoint();
    else if (com == "getPointX" || com == "gPX")
        getPointX();
    else if (com == "getPointY" || com == "gPY")
        getPointY();
    else if (com == "createRectDouble" || com == "cRD")
        createRectDouble();
    else if (com == "createRectPoints" || com == "cRP")
        createRectPoints();
    else if (com == "createRectCopy" || com == "cRC")
        createRectCopy();
    else if (com == "fieldRect" || com == "fR")
        fieldRect();
    else if (com == "showRect" || com == "sR")
        showRect();
    else if (com == "addPoint" || com =="addP")
        addPoint();
    else if (com == "addRect" || com =="addR")
        addRect();
    else if (com == "assignRect" || com =="assR")
        assignRect();
    else if (com == "assignPoint" || com =="assP")
        assignPoint();
    else if (com == "showPoint" || com =="sP")
        showPoint();
    else{
        cout << "ERROR: Incorrect command" << endl;
        i_incorrect_commands++;
        if (i_incorrect_commands > 3)
            wyn = false;
    } //     else{
    return wyn;
} // bool CTUI::evaluateCommand(string &psCommand) {

bool CTUI::bIsExitCommand(string &comm) {
    return comm == "q" || comm == "quit" || comm == "exit";
}



