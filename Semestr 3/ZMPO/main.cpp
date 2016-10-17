#include <iostream>
#include "CTUI.h"

using std::cin;
using std::cout;
using std::endl;




int main() {
    CTUI *tui = new CTUI();
    while(tui->execute());
    delete tui;
    return 0;
}