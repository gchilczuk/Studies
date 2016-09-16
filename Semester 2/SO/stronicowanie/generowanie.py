from random import *



def gen_old(n, x):
    k = randint(0,x)
    ret = [k]
    for i in range(1,n):
        a = list(range(x+1))
        a += [k] * int((3*x/5))
        a += [k-1] * int((9*x/10)) if k-1 in a else []
        a += [k+1] * int((9*x/10)) if k+1 in a else []
        a += [k-2] * int((5*x/6)) if k-2 in a else []
        a += [k+2] * int((5*x/6)) if k+2 in a else []
        a += [k-3] * int((4*x/5)) if k-3 in a else []
        a += [k+3] * int((4*x/5)) if k+3 in a else []
        k = choice(a)
        ret += [k]
    return ret

def gen(len, zakres):
    k = randint(0,zakres)
    ret = [k]
    ile = zakres*3
    for i in range(1,len):
        ile = int(ile * 47/51)
        a = list(range(0,zakres+1))
        for e in range(7):
            c = k + e
            d = k - e
            if c in a:
                a += [c] * ile
            if d in a:
                a += [d] * ile
        k = choice(a)
        ret += [k]
    return ret

def genv2(len, zakres):
    k = randint(0,zakres)
    ret = [k]
    ile = zakres*3
    for i in range(1,len):
        ile = int(ile * 47/51)
        a = list(range(0,zakres+1))
        for e in range(7):
            b = []
            mnoz = 1
            c = k + e
            d = k - e
            if c in a:
                b += [c] * ile
            else:
                mnoz += 1
            if d in a:
                b += [d] * ile
            else:
                mnoz += 1
            b *= mnoz
            a.extend(b)
        k = choice(a)
        ret += [k]
    return ret