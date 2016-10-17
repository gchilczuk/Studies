#ifndef CTABLE_H
#define CTABLE_H
#include <string>

using std::string;

class CTable {
private:
    string s_name;
    int *pi_table;
    int i_table_size;
    const int _DEFAULT_TABLE_SIZE = 10;
    const string _DEFAULT_NAME = "Unnamed table";
/*
protected:
    int *copyTable(int *, int, int);
*/
public:
    CTable();
    CTable(string);
    CTable(string, int);
    CTable(CTable &);
    ~CTable();
    void vSetName(string);
    bool bSetSize(int);
    bool vSetElement(int, int);
    int iGetElement(int);
    int iGetLen();
    CTable *cGetClone();
    void vCopyTable(CTable &);
    string sToString();
    void vBtoA(CTable&, CTable&);
    const string &sGetS_name() const;
};


#endif //CTABLE_H
