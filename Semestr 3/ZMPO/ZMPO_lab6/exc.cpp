#ifndef EXCC
#define EXCC
#include <exception>
using std::exception;

class indexOutOfBoundsEXC: public exception
{
    virtual const char* what() const throw()
    {
        return "Given index is incorrect";
    }
};

#endif