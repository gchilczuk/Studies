#include <iostream>
#include "CTUI.h"

using std::cin;
using std::cout;
using std::endl;

int main() {
    string c;
    cin >> c;
    CTUI *tui = new CTUI();
    while(tui->execute(c));
    delete tui;
    return 0;
}