#include "CTUI.h"
#include <iostream>
#include <algorithm>


using std::cout;
using std::cin;
using std::endl;
using std::to_string;

//typedef CTable<S>

void CTUI::resize(int newSize) {
    CTable<S> ** newTab = new CTable<S>*[newSize];
    for (int i = 0; i < size; i++){
        newTab[i] = tab[i];
    }
    delete[] tab;
    tab = newTab;
    size = newSize;
}

CTUI::CTUI() {
    tab = new CTable<S>*[10];
    size=10;
    for (int i = 0; i < size; i++){
        tab[i] = NULL;
    }
}

CTUI::CTUI(int len) {
    tab = new CTable<S>*[len];
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
    tab[nr] = new CTable<S>();
    return "Done! (createDef[" + to_string(nr)+"])";
}

string CTUI::create() {
    int nr, len;
    string name;
    cin >> nr >> len >> name;
    if (nr < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = new CTable<S>(name, len);
    return "Done! (create["+to_string(nr)+"])";
}

string CTUI::createCopy() {
    int nr, nrc;
    cin >> nr >> nrc;
    if (nrc >= size || tab[nrc] == NULL) return "Tablica nie istnieje!";
    if (nr < 0 || nrc < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = new CTable<S>(*tab[nrc]);
    return "Done! (createCopy["+to_string(nr)+"])";
}

string CTUI::createClone() {
    int nr, nrc;
    cin >> nr >> nrc;
    if (nrc >= size || tab[nrc] == NULL) return "Tablica nie istnieje!";
    if (nr < 0 || nrc < 0) return "Ujemny indeks!";
    else if (nr >= size) resize(nr+10);
    if (!isfree(nr)) return "Odmowa";
    tab[nr] = tab[nrc]->cGetClone();
    return "Done! (createClone["+to_string(nr)+"])";
}

string CTUI::setValue() {
    int nr, index;
    S val;
    cin >> nr >> index >> val;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    try{
        if (!tab[nr]->vSetElement(index, val)) return "Błąd danych!";
    }catch (char czar){
        if (czar == 'a') return "Czary Mary";
    }
    return "Done! (setValue)";
}

string CTUI::getValue() {
    int nr, index;
    S ivalue;
    string ret;
    cin >> nr >> index;
    if (nr < 0 || nr >= size || tab[nr] == NULL || index < 0 || index >= tab[nr]->iGetLen()) return "Indeks nie istnieje!";
    ivalue = tab[nr]->pGetElement(index);
    ret ="tab["+to_string(nr)+"]["+to_string(index)+"] = " + CTable<S>::from_out_to_str(ivalue);
    return ret;
}

string CTUI::setName() {
    int nr;
    string name;
    cin >> nr >> name;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    tab[nr]->vSetName(name);
    return "Done! (setName)";
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
    return "Done! (setSize)";
}

string CTUI::destruct() {
    int nr;
    cin >> nr;
    if (nr < 0 || nr >= size || tab[nr] == NULL) return "Błędny indeks!";
    delete tab[nr];
    tab[nr] = NULL;
    return "Done! (destruct)";
}

string CTUI::destructAll() {
    for (int i = 0; i < size; i++) {
        delete tab[i];
        tab[i] = NULL;
    }
    return "Done! (destructAll)";
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
    return "tab["+to_string(nr)+"].size = "+to_string(tab[nr]->iGetLen());
}


bool CTUI::execute(string who) {
    string command, result;
    if (who != "COMP") cout << "$: ";
    cin >> command;
    std::transform(command.begin(), command.end(), command.begin(), ::tolower);
    if (command == "exit" || command == "quit" || command == "q")
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
    else if (command == "setsize" || command == "setlen")
        result = setSize();
    else if (command == "destruct")
        result = destruct();
    else if (command == "destructall")
        result = destructAll();
    else if (command == "tostring" || command == "print")
        result = toString();
    else if (command == "createclone")
        result = createClone();
    else if (command == "getlen" || command == "getsize")
        result = getLen();
    else result = "Nieprawidłowa komenda! " + command;
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







