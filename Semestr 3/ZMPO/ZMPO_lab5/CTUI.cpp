#include "CTUI.h"


void show_tab(Figura **tab, int len){
    for (--len; len >= 0; len--){
        if (tab[len])
        cout << tab[len]->sToString() << endl;
    }
}

double field_suma(Figura **tab, int len){
    double sum = 0;
    for (--len; len >= 0; len--){
        if(tab[len])
            sum += tab[len]->dPole();
    }
    return sum;
}

double perimeter_suma(Figura **tab, int len){
    double sum = 0;
    for (--len; len >= 0; len--){
        if(tab[len])
            sum += tab[len]->dObwod();
    }
    return sum;
}

bool CTUI::evaluateCommand(string &psCommand) {
    string &com = psCommand;
    bool wyn = true;
    if (bIsExitCommand(com))
        wyn = false;
    else if (com == "createCircle" || com == "cC")
        createCircle();
    else if (com == "createSquare" || com == "cS")
        createSquare();
    else if (com == "createRectangle" || com == "cR")
        createRectangle();
    else if (com == "createTrapeze" || com == "cT")
        createTrapeze();
    else if (com == "field" || com == "f")
        field();
    else if (com == "perimeter" || com == "p")
        perimeter();
    else if (com == "fieldSum" || com == "fS")
        field_sum();
    else if (com == "perimeterSum" || com == "pS")
        perimeter_sum();
    else if (com == "show" || com == "s")
        show();
    else if (com == "showAll" || com == "sA")
        show_all();
    else if (com == "delete" || com == "del")
        del();
    else{
        cout << "ERROR: Incorrect command" << endl;
        i_incorrect_commands++;
        if (i_incorrect_commands > 5)
            wyn = false;
    } //     else{
    return wyn;
} // bool CTUI::evaluateCommand(string &psCommand) {

bool CTUI::bIsExitCommand(string &comm) {
    return comm == "q" || comm == "quit" || comm == "exit";
}

bool CTUI::bIsFigure(int iIndex) {
    return iIndex >= 0 && iIndex < i_len && tab[iIndex];
}

bool CTUI::bIsPlace(int iIndex) {
    return iIndex >= 0 && iIndex < i_len && !tab[iIndex];
}

void CTUI::createCircle() {
    string wyn = "ERROR";
    int ii;
    string sn;
    double dr;
    cin >> ii >> sn >> dr;
    cout << "!createCircle " << ii << " " << sn << " " << dr << endl;
    if (bIsPlace(ii)){
        tab[ii] = new Kolo(sn, dr);
        wyn = "DONE";
    }
    cout << wyn << endl;
}


void CTUI::createSquare() {
    string wyn = "ERROR";
    int ii;
    string sn;
    double da;
    cin >> ii >> sn >> da;
    cout << "!createSquare " << ii << " " << sn << " " << da << endl;
    if (bIsPlace(ii)){
        tab[ii] = new Kwadrat(sn, da);
        wyn = "DONE";
    }
    cout << wyn << endl;
}

void CTUI::createRectangle() {
    string wyn = "ERROR";
    int ii;
    string sn;
    double da, db;
    cin >> ii >> sn >> da >> db;
    cout << "!createRectangle " << ii << " " << sn << " " << da << " " << db << endl;
    if (bIsPlace(ii)){
        tab[ii] = new Prostokat(sn, da, db);
        wyn = "DONE";
    }
    cout << wyn << endl;
}

void CTUI::createTrapeze() {
    string wyn = "ERROR";
    int ii;
    string sn;
    double da, db, dc, dd, dh;
    cin >> ii >> sn >> da >> db >> dc >> dd >> dh;
    cout << "!createTrapeze " << ii << " " << sn;
    cout << " " << da << " " << db << " " << dc << " " << dd << " " << dh << endl;
    if (bIsPlace(ii)){
        tab[ii] = new Trapez(sn, da, db, dc, dd, dh);
        wyn = "DONE";
    }
    cout << wyn << endl;
}

void CTUI::field() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!field " << ii << endl;
    if (bIsFigure(ii)){
        wyn = to_string(tab[ii]->dPole());
    }
    cout << wyn << endl;
}

void CTUI::perimeter() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!perimeter " << ii << endl;
    if (bIsFigure(ii)){
        wyn = to_string(tab[ii]->dObwod());
    }
    cout << wyn << endl;
}

void CTUI::field_sum() {
    string wyn = "ERROR";
    cout << "!fieldSum" << endl;
    wyn = to_string(field_suma(tab, i_len));
    cout << wyn << endl;
}

void CTUI::perimeter_sum() {
    string wyn = "ERROR";
    cout << "!perimeterSum" << endl;
    wyn = to_string(perimeter_suma(tab, i_len));
    cout << wyn << endl;
}

void CTUI::show() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!delete " << ii << endl;
    if (bIsFigure(ii)){
        wyn = tab[ii]->sToString();
    }
    cout << wyn << endl;
}

void CTUI::show_all() {
    cout << "!showAll" << endl;
    show_tab(tab, i_len);
}


void CTUI::del() {
    string wyn = "ERROR";
    int ii;
    cin >> ii;
    cout << "!show " << ii << endl;
    if (bIsFigure(ii)){
        wyn = "Obiekt " + tab[ii]->getNazwa();
        delete tab[ii];
        tab[ii] = nullptr;
        wyn += " został usunięty";
    } else {
        wyn = "Pod tym adresem nie ma żadnego obiektu";
    }
    cout << wyn << endl;
}

CTUI::CTUI() {

}

CTUI::~CTUI() {
    for (--i_len; i_len >= 0; i_len--)
        delete tab[i_len];

    delete tab;
}

bool CTUI::boot(string &psCommand) {
    bool wyn = false;
    if (psCommand != "go"){
        i_incorrect_commands++;
        if (i_incorrect_commands > 3){
            i_len = 10;
            tab = new Figura *[i_len];
            int ii;
            for (ii = 0; ii < i_len; ii++) {
                tab[ii] = nullptr;
            }
            wyn = true;
            cout << "fails > 3 → go 10 → DONE" << endl;
        }
        return wyn;}
    cin >> i_len;
    cout << "!go " << i_len << endl;
    if (i_len >= 0){
        tab = new Figura *[i_len];
        int ii;
        for (ii = 0; ii < i_len; ii++) {
            tab[ii] = nullptr;
        }
        wyn = true;
        cout << "DONE" << endl;
    } //     if (ip >= 0 && ir >= 0){
    else {
        i_incorrect_commands++;
        cout << "ERROR" << endl;
    }
    return wyn;
}




