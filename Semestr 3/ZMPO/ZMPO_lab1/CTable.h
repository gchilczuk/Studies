#ifndef CTABLE_H
#define CTABLE_H
#include <string>

using std::string;

class CTable {
private:
    string s_name;
    int *pi_table;
    int i_table_size;
    const int _DEFAULT_TABLE_SIZE = 5;
    const string _DEFAULT_NAME = "Unnamed_table";
    const int _DEFAULT_VALUE = -1;
    void defVal();

public:
    CTable();
    CTable(string);
    CTable(string, int);
    CTable(CTable &);
    ~CTable();

    void vSetName(string);
    bool bSetSize(int);
    bool vSetElement(int, int);

    int iGetElement(int, bool&);
    int iGetLen();
    CTable *cGetClone();
    const string &sGetS_name() const;

    void vCopyTable(CTable &);
    string sToString();
    void vBtoA(CTable&, CTable&);
};


#endif //CTABLE_H
