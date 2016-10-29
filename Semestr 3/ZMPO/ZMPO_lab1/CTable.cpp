#include "CTable.h"
#include <iostream>

using std::cout;
using std::endl;


CTable::CTable() {
    s_name = _DEFAULT_NAME;
    pi_table = new int[_DEFAULT_TABLE_SIZE]();
    i_table_size = _DEFAULT_TABLE_SIZE;
    defVal();
    cout << "bezp: " << s_name << endl;
}

CTable::CTable(string sName) {
    s_name = sName;
    pi_table = new int[_DEFAULT_TABLE_SIZE]();
    i_table_size = _DEFAULT_TABLE_SIZE;
    defVal();
    cout << "parametr: " << sName << endl;
}

CTable::CTable(string sName, int iSize) {
    s_name = sName;
    i_table_size = iSize;
    pi_table = new int[iSize];
    defVal();
    cout << "parametry: " << sName <<", "<< iSize << endl;
}

CTable::CTable(CTable &pcOther) {
    s_name = pcOther.s_name + "_copy";
    vCopyTable(pcOther);
    cout << "kopiuj: " << s_name << endl;
}

CTable::~CTable() {
    if (pi_table){
        delete[] pi_table;
        pi_table = NULL;
    }
}

void CTable::vSetName(string sName) {
    s_name = sName;
}

bool CTable::vSetElement(int iIndex, int iValue) {
    if (iIndex < 0 || iIndex >= i_table_size)
        return false;
    pi_table[iIndex] = iValue;
    return true;
}

bool CTable::bSetSize(int iSize) {
    if (iSize < 0)
        return false;
    int *newTable = new int[iSize]();
    for (int i = 0; i< iSize; i++){
        newTable[i] = _DEFAULT_VALUE;
    }
    for (int i = 0; i < i_table_size && i < iSize; i++){
        newTable[i] = pi_table[i];
    }
    delete [] pi_table;
    pi_table = newTable;
    i_table_size = iSize;
    return true;
}

int CTable::iGetElement(int index, bool &a) {
    if (index < 0 || index >= i_table_size){
        a = false;
        return -997;
    }
    a = true;
    return pi_table[index];
}

CTable *CTable::cGetClone() {
    CTable *table = new CTable(*this);
    table->s_name = s_name;
    return table;
}

const string &CTable::sGetS_name() const {
    return s_name;
}

int CTable::iGetLen() { return i_table_size; }

void CTable::vBtoA(CTable &A, CTable &B) {
    A.vCopyTable(B);
}

void CTable::vCopyTable(CTable &pcOther) {
    delete[] pi_table;
    pi_table = new int[pcOther.i_table_size]();
    for (int i = 0; i < pcOther.i_table_size; i++){
        pi_table[i] = pcOther.pi_table[i];
    }
    i_table_size =pcOther.i_table_size;
}

string CTable::sToString() {
    string string1 =  s_name + " len: " + std::to_string(i_table_size) + " values: ";
    for (int i = 0; i < i_table_size-1; i++){
        string1 += std::to_string(pi_table[i]) + ", ";
    }
    string1 += std::to_string(pi_table[i_table_size-1]);
    return string1;
}

void CTable::defVal() {
    for (int i = 0; i < i_table_size; i++){
        pi_table[i] = _DEFAULT_VALUE;
    }
}






