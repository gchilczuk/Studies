#include "Kolo.h"
#include "Trapez.h"
//#include "Szablony.cpp"
#include "CTUI.h"


int main() {
    CTUI *tui = new CTUI();
    string *command = new string();
    bool expr;
    cout << "READY"<<endl;
    do {
        cin >> *command;
    }while (!tui->boot(*command));

    do {
        cin >> *command;
        expr = tui->evaluateCommand(*command);
    }while(expr);

    delete tui;
    delete command;
    cout << "Koniec programu";

    return 0;
}