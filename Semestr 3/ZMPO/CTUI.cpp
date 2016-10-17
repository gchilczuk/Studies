#include "CTUI.h"
#include <iostream>
#include <algorithm>

using std::cout;
using std::cin;
using std::endl;

void CTUI::resize(int newSize) {
    CTable ** newTab = new CTable*[newSize];
    for (int i = 0; i < size; i++){
        newTab[i] = tab[i];
    }
    delete[] tab;
    tab = newTab;
    size = newSize;
}

CTUI::CTUI() {
    tab = new CTable*[10];
    size=10;
    for (int i = 0; i < size; i++){
        tab[i] = NULL;
    }
}

CTUI::CTUI(int len) {
    tab = new CTable*[len];
    size=len;
    for (int i = 0; i < size; i++){
        tab[i] = NULL;
    }
}

CTUI::~CTUI() {
    for (int i = 0; i < size; i++) {
        delete tab[i];
        tab[i] = NULL;
    }
    delete[] tab;
    tab = NULL;
}

string CTUI::createDef() {
    int nr;
    cin >> nr;
    if (nr < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = new CTable();
    return "Done!";
}

string CTUI::create() {
    int nr, len;
    string name;
    cin >> nr >> len >> name;
    if (nr < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = new CTable(name, len);
    return "Done!";
}

string CTUI::createCopy() {
    int nr, nrc;
    cin >> nr >> nrc;
    if (nrc >= size || tab[nrc] == NULL) return "Tablica nie istnieje!";
    if (nr < 0 || nrc < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = new CTable(*tab[nrc]);
    return "Done!";
}

string CTUI::createClone() {
    int nr, nrc;
    cin >> nr >> nrc;
    if (nrc >= size || tab[nrc] == NULL) return "Tablica nie istnieje!";
    if (nr < 0 || nrc < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = tab[nrc]->cGetClone();
    return "Done!";
}

string CTUI::setValue() {
    int nr, index, val;
    cin >> nr >> index >> val;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    if (!tab[nr]->vSetElement(index, val)) return "Błąd danych!";
    return "Done!";
}

string CTUI::getValue() {
    int nr, index;
    cin >> nr >> index;
    if (nr < 0 || nr >= size || tab[nr] == NULL || index < 0 || index >= tab[nr]->iGetLen()) return "Indeks nie istnieje!";
    return std::to_string(tab[nr]->iGetElement(index));
}

string CTUI::setName() {
    int nr;
    string name;
    cin >> nr >> name;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    tab[nr]->vSetName(name);
    return "Done!";
}

string CTUI::getName() {
    int nr;
    cin >> nr;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    return tab[nr]->sGetS_name();
}

string CTUI::setSize() {
    int nr, newSize;
    cin >> nr >> newSize;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    if (newSize < 0) return "Ujemna długość!";
    tab[nr]->bSetSize(newSize);
    return "Done!";
}

string CTUI::destruct() {
    int nr;
    cin >> nr;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    delete tab[nr];
    tab[nr] = NULL;
    return "Done!";
}

string CTUI::destructAll() {
    for (int i = 0; i < size; i++) {
        delete tab[i];
        tab[i] = NULL;
    }
    return "Done!";
}

string CTUI::toString() {
    int nr;
    cin >> nr;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    return tab[nr]->sToString();
}

string CTUI::getLen() {
    int nr;
    cin >> nr;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    return std::to_string(tab[nr]->iGetLen());
}

string CTUI::copyBtoA() {
    int nr,nrc;
    cin >> nr >>nrc;
    if (nr < 0 || nr >= size || tab[nr] == NULL || nrc < 0 || nrc >= size || tab[nrc] == NULL) return "Błędny indeks!";
    tab[nr]->vBtoA(*tab[nr], *tab[nrc]);
    return "Done!";

}

bool CTUI::execute() {
    string command, result;
    cout << "$: ";
    cin >> command;
    std::transform(command.begin(), command.end(), command.begin(), ::tolower);
    if (command == "exit")
        return false;
    else if (command == "createdef")
        result = createDef();
    else if (command == "create")
        result =create();
    else if (command == "createcopy")
        result = createCopy();
    else if (command == "setvalue")
        result = setValue();
    else if (command == "getvalue")
        result = getValue();
    else if (command == "setname")
        result = setName();
    else if (command == "getname")
        result = getName();
    else if (command == "setsize")
        result = setSize();
    else if (command == "destruct")
        result = destruct();
    else if (command == "destructall")
        result = destructAll();
    else if (command == "tostring")
        result = toString();
    else if (command == "createclone")
        result = createClone();
    else if (command == "getlen")
        result = getLen();
    else if (command == "copybtoa")
        result = copyBtoA();
    else result = "Nieprawidłowa komenda!";
    cout << result << endl;
    return true;
}

bool CTUI::isfree(int nr) {
    if (tab[nr] != NULL){
        string s;
        cout << "Miejsce zajęte. Nadpisać? [t/n] ";
        cin >> s;
        std::transform(s.begin(), s.end(), s.begin(), ::tolower);
        if (s == "t")
            delete tab[nr];
        else return false;
    }
    return true;
}







