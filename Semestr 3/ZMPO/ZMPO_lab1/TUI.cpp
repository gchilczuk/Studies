#include <iostream>
#include <string>
#include "CTable.h"

using std::string;
using std::endl;
using std::cout;
using std::cin;
class TUI {
private:
    CTable **tab;
    int isize;

public:
    TUI(int size){
        tab = new CTable *[size];
        isize = size;
    }
    ~TUI(){
        for (int i =  0; i < isize; i++){
            if (tab[i]) delete[] tab[i];
            tab[i] = NULL;
        }
        if (tab) delete[] tab;
        tab = NULL;
    }
    string createDef(int index) {
        tab[index] = new CTable();
        return "Done";
    }

    string create(int index, int rozmiar, string nazwa){
        tab[index] = new CTable(nazwa, rozmiar);
        return "Done";
    }

    string create(int index, string nazwa){
        tab[index] = new CTable(nazwa);
        return "Done!";
    }

    string toString(int index) {
        cout << tab[index]->sToString();
        return "Done!";
    }
};//class TUI

