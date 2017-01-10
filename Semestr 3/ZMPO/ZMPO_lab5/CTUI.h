
#ifndef ZMPO_LAB5_CTUI_H
#define ZMPO_LAB5_CTUI_H

#include "Kolo.h"
#include "Trapez.h"

using std::cout;
using std::cin;
using std::endl;


class CTUI {
private:
    Figura **tab;
    int i_len;
    int i_incorrect_commands;

    void createCircle();
    void createSquare();
    void createRectangle();
    void createTrapeze();

    void field();
    void perimeter();
    void field_sum();
    void perimeter_sum();
    void show();
    void show_all();
    void del();

    bool bIsFigure(int iIndex);
    bool bIsPlace(int iIndex);
    bool bIsExitCommand(string &comm);

public:
    CTUI();
    ~CTUI();

    bool boot(string &psCommand);
    bool evaluateCommand(string &psCommand);


};


#endif //ZMPO_LAB5_CTUI_H
