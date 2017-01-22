
#ifndef CTUI_H
#define CTUI_H
#include "CTable.h"


class CTUI {
private:
    CTable<int> **tab;
    int size;
    void resize(int);
    bool isfree(int);

public:
    CTUI();
    CTUI(int);
    ~CTUI();
    string createDef();
    string create();
    string createCopy();
    string createClone();
    string setValue();
    string getValue();
    string setName();
    string getName();
    string setSize();
    string destruct();
    string destructAll();
    string toString();
    string getLen();

    bool execute(string);


};


#endif //CTUI_H
