#include <iostream>
#include "CTable.h"
#include "CTUI.h"

using std::cin;

int main() {
    CTable<Osoba> a("tabb", 10);
    Osoba *os = new Osoba("Adam", 13);
    a.vSetElement(1, *os);
    cout << 3;
    Osoba bos = a.pGetElement(1);
    cout << 4;
    bos.setWiek(42);
    cout << endl << bos << endl;
    cout << a.pGetElement(1) << endl;
    cout << a.sToString();
    cout << endl;
    string c;
    cin >> c;
    CTUI *tui = new CTUI();
    while(tui->execute(c));
    delete tui;
    return 0;
}