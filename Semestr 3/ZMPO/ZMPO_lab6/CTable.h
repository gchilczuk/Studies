#ifndef CTABLE_H
#define CTABLE_H
#include <string>
#include <iostream>
#include <sstream>
#include "Osoba.h"

using std::string;
using std::cout;
using std::endl;
using std::stringstream;

template <class T> class CTable {
private:
    string s_name;
    T *p_table;
    int i_table_size;
    const int DEFAULT_TABLE_SIZE = 5;
    const string DEFAULT_NAME = "Unnamed_table";
    T DEFAULT_VALUE = T();
    void defVal();
    static string from_out_to_str(T &obj);

public:
    CTable();
    CTable(string sName);
    CTable(string sName, int iSize);
    CTable(CTable &pcOther);
    ~CTable();


    void vSetName(string sName);
    bool bSetSize(int iSize);
    void vSetElement(int iIndex, T iValue);

    T pGetElement(int iIndex);
    int iGetLen();
    CTable *cGetClone();

    const string &sGetS_name() const;

    void vSetTable(CTable & pcOther);
    string sToString();

    template <class S> friend std::ostream &operator<<(std::ostream &os, const CTable<S> &table);
    friend class CTUI;

};


template <class T> void CTable<T>::defVal() {
    for (int i = 0; i < i_table_size; i++){
        p_table[i] = DEFAULT_VALUE;
    }
}

template <class T> CTable<T>::CTable() {
    s_name = DEFAULT_NAME;
    p_table = new T[DEFAULT_TABLE_SIZE];
    i_table_size = DEFAULT_TABLE_SIZE;
    defVal();
    cout << "bezp: " << s_name << endl;
}


template <class T> CTable<T>::CTable(string sName) {
    s_name = sName;
    p_table = new T[DEFAULT_TABLE_SIZE];
    i_table_size = DEFAULT_TABLE_SIZE;
    defVal();
    cout << "1 param: " << s_name << endl;
}

template <class T> CTable<T>::CTable(string sName, int iSize) {
    s_name = sName;
    p_table = new T[iSize];
    i_table_size = iSize;
    defVal();
    cout << "2 params: " << s_name << endl;
}

template <class T> CTable<T>::CTable(CTable &pcOther) {
    s_name = pcOther.s_name + "_copy";
    vSetTable(pcOther);
    cout << "kopiuj: " << s_name << endl;
}

template <class T> CTable<T>::~CTable() {

    delete[] p_table;
}

template <class T> void CTable<T>::vSetTable(CTable & pcOther) {
    delete[] p_table;

    p_table = new T[pcOther.i_table_size];
    i_table_size = pcOther.i_table_size;
    for (int i = 0; i < pcOther.i_table_size; i++){
        p_table[i] = pcOther.p_table[i];
    }
}

template <class T> void CTable<T>::vSetName(string sName) {
    s_name = sName;
}

template <class T> bool CTable<T>::bSetSize(int iSize) {
    if (iSize <0) return false;
    T * newTable = new T[iSize];
    for (int i = 0; i< iSize; i++){
        newTable[i] = DEFAULT_VALUE;
    }
    for (int i = 0; i < i_table_size && i < iSize; i++){
        newTable[i] = p_table[i];
    }
    delete [] p_table;
    p_table = newTable;
    i_table_size = iSize;
    return true;
}

template <class T> void CTable<T>::vSetElement(int iIndex, T iValue) {
    if (iIndex < 0 || iIndex >= i_table_size) throw indexOutOfBoundsEXC();
    p_table[iIndex] = iValue;
}

template <class T> T CTable<T>::pGetElement(int iIndex) {
    if (iIndex < 0 || iIndex >= i_table_size) throw indexOutOfBoundsEXC();
    return p_table[iIndex];
}

template <class T> int CTable<T>::iGetLen() {
    return i_table_size;
}

template <class T>CTable<T> *CTable<T>::cGetClone() {
    CTable *table = new CTable(*this);
    table->s_name = s_name;
    return table;
}

template <class S> std::ostream &operator<<(std::ostream &os, const CTable<S> &table) {
    os << "s_name: " << table.s_name << " p_table: ";
    for (int i = 0; i < table.i_table_size; i++){
        os << table.p_table[i] << " | ";
    }
    os << " i_table_size: " << table.i_table_size;
    return os;
}

template <class T> const string &CTable<T>::sGetS_name() const {
    return s_name;
}

template <class T> string CTable<T>::sToString() {

    string string1 =  s_name + " len: " + std::to_string(i_table_size) + " values: ";
    for (int i = 0; i < i_table_size-1; i++){
        string1 += from_out_to_str(p_table[i]) + ", ";
    }
    string1 += from_out_to_str(p_table[i_table_size-1]);
    return string1;
}

template <class T> string CTable<T>::from_out_to_str(T &obj) {
    stringstream stream;
    stream << obj;
    return stream.str();
}


#endif //CTABLE_Hnamespace std {
