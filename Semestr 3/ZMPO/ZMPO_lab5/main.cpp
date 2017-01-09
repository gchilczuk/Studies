#include "Kolo.h"
#include "Trapez.h"
#include "CTUI.h"
#include "Szablony.cpp"

void zada(){
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
}

void zadb(){
    typedef complex<double> dcomp;

    dcomp tt[] = { {0.0,1.0}, {0.0,1.0}};
    int tab[] = {1,2,3};
    char ttab[] = {'G', 'H'};
    cout << suma(tt, 2);

    cout << endl << "Egzample: " << endl;

    Egzample ttt[3];
    ttt[0] = Egzample(1);
    ttt[1] = Egzample(2);
    ttt[2] = Egzample(3);

    cout << suma(ttt, 3);

}


int main() {
//    zada();
//    zadb();
    return 0;
}