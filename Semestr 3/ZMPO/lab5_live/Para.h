//
// Created by widelec on 13.12.16.
//

#ifndef LAB5_LIVE_PARA_H
#define LAB5_LIVE_PARA_H


template <class X, class Y> class Para {
private:
    X *wsk;
    Y wart;

public:
    Para(X arg1, Y arg2) : wart(arg2){ wsk = new X(arg1); }
    Para (const Para<X,Y> &other):wart(other.wart){
        wsk = new X(*other.wsk);
    }
    ~Para(){ delete wsk; }

    X getWsk() const { return *wsk; }

    void setWsk(X wskaznik) {
        *wsk = wskaznik;
    }

    Y getWart() const { return wart; }
    void setWart(Y wartosc) { wart = wartosc; }

    Para<X,Y> &operator=(Para<X,Y> &other){
        *wsk = other.wsk;
        wart = other.wart;
        return *this;
    }


    void show(){
        std::cout <<"wska: " << *wsk << " wart: " << wart << std::endl;

    }

};


#endif //LAB5_LIVE_PARA_H
