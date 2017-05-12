# --------------------------------------------------------------------------
# ------------  Metody Systemowe i Decyzyjne w Informatyce  ----------------
# --------------------------------------------------------------------------
#  Zadanie 3: Regresja logistyczna
#  autorzy: A. Gonczarek, J. Kaczmar, S. Zareba
#  2017
# --------------------------------------------------------------------------

import numpy as np

def sigmoid(x):
    '''
    :param x: wektor wejsciowych wartosci Nx1
    :return: wektor wyjściowych wartości funkcji sigmoidalnej dla wejścia x, Nx1
    '''

    def sig(x):
        return 1/(1+np.exp(-x))

    return np.array(list(map(sig, x)))

def logistic_cost_function(w, x_train, y_train):
    '''
    :param w: parametry modelu Mx1
    :param x_train: ciag treningowy - wejscia NxM
    :param y_train: ciag treningowy - wyjscia Nx1
    :return: funkcja zwraca krotke (val, grad), gdzie val oznacza wartosc funkcji logistycznej, a grad jej gradient po w
    '''

    N = x_train.shape[0]
    M = x_train.shape[1]
    def sign(xn):
        return sigmoid(w.transpose()@xn)

    sig_all_n = sigmoid(x_train @ w) # list(map(sign, x_train.tolist()))
    snyn =  list(zip(sig_all_n, y_train, x_train))
    pees = map(lambda xs: xs[0]**xs[1] * (1-xs[0])**(1-xs[1]), snyn)
    pe = np.prod(list(pees))
    # pees = map(lambda xs: xs[1]*np.log(xs[0]) + (1-xs[1])*np.log(1-xs[0]), zip(sig_all_n, y_train) )
    # pe = sum(list(pees))

    grad = list(map(lambda i: -sum(map(lambda xs: (xs[1] - xs[0])*xs[2][i], snyn))/N, range(M)))
    return (-np.log(pe)/N), np.array(grad)


def gradient_descent(obj_fun, w0, epochs, eta):
    '''
    :param obj_fun: funkcja celu, ktora ma byc optymalizowana. Wywolanie val,grad = obj_fun(w).
    :param w0: punkt startowy Mx1
    :param epochs: liczba epok / iteracji algorytmu
    :param eta: krok uczenia
    :return: funkcja wykonuje optymalizacje metoda gradientu prostego dla funkcji obj_fun. Zwraca krotke (w,func_values),
    gdzie w oznacza znaleziony optymalny punkt w, a func_valus jest wektorem wartosci funkcji [epochs x 1] we wszystkich krokach algorytmu
    '''
    w = w0
    func_values = []
    val, grad = obj_fun(w)
    w = w - eta*grad
    for i in range(1, epochs):
        val, grad = obj_fun(w)
        w = w - eta*grad
        func_values += [[val]]

    val, grad = obj_fun(w)
    func_values += [[val]]

    return w, np.array(func_values)

def stochastic_gradient_descent(obj_fun, x_train, y_train, w0, epochs, eta, mini_batch):
    '''
    :param obj_fun: funkcja celu, ktora ma byc optymalizowana. Wywolanie val,grad = obj_fun(w,x,y), gdzie x,y oznaczaja podane
    podzbiory zbioru treningowego (mini-batche)
    :param x_train: dane treningowe wejsciowe NxM
    :param y_train: dane treningowe wyjsciowe Nx1
    :param w0: punkt startowy Mx1
    :param epochs: liczba epok
    :param eta: krok uczenia
    :param mini_batch: wielkosc mini-batcha
    :return: funkcja wykonuje optymalizacje metoda stochastycznego gradientu prostego dla funkcji obj_fun. Zwraca krotke (w,func_values),
    gdzie w oznacza znaleziony optymalny punkt w, a func_values jest wektorem wartosci funkcji [epochs x 1] we wszystkich krokach algorytmu. Wartosci
    funkcji do func_values sa wyliczane dla calego zbioru treningowego!
    '''

    x = x_train
    y = y_train
    w = w0
    func_values = []
    mbx = []
    mby = []
    while len(x) + len(y) >0:
        mbx += [x[:mini_batch]]
        x = x[mini_batch:]
        mby += [y[:mini_batch]]
        y = y[mini_batch:]

    minibatches = list(zip(mbx, mby))
    for k in range(epochs):
        for bx, by in minibatches:
            _, grad = obj_fun(w, bx, by)
            w = w - eta*grad
        val, _ = obj_fun(w, x_train, y_train)
        func_values += [[val]]

    return w, np.array(func_values)

def regularized_logistic_cost_function(w, x_train, y_train, regularization_lambda):
    '''
    :param w: parametry modelu Mx1
    :param x_train: ciag treningowy - wejscia NxM
    :param y_train: ciag treningowy - wyjscia Nx1
    :param regularization_lambda: parametr regularyzacji
    :return: funkcja zwraca krotke (val, grad), gdzie val oznacza wartosc funkcji logistycznej z regularyzacja l2,
    a grad jej gradient po w
    '''


    wminus0 = w[1:]

    val, grad = logistic_cost_function(w, x_train, y_train)
    val += (regularization_lambda/2)*wminus0.transpose()@wminus0
    grad[1:] += regularization_lambda*w[1:]
    return val[0][0], grad

def prediction(x, w, theta):
    '''
    :param x: macierz obserwacji NxM
    :param w: wektor parametrow modelu Mx1
    :param theta: prog klasyfikacji z przedzialu [0,1]
    :return: funkcja wylicza wektor y o wymiarach Nx1. Wektor zawiera wartosci etykiet ze zbioru {0,1} dla obserwacji z x
     bazujac na modelu z parametrami w oraz progu klasyfikacji theta
    '''

    sig = sigmoid((w.transpose() @ x.transpose()).transpose())
    pred = []
    for el in sig:
        pred.append(el >= theta)
    return np.array(pred, int)


def f_measure(y_true, y_pred):
    '''
    :param y_true: wektor rzeczywistych etykiet Nx1
    :param y_pred: wektor etykiet przewidzianych przed model Nx1
    :return: funkcja wylicza wartosc miary F
    '''
    y_true = y_true.astype(bool)
    y_pred = y_pred.astype(bool)
    TP = sum(sum(y_pred * y_true))
    FP = sum(sum(y_pred * ~y_true))
    FN = sum(sum(~y_pred * y_true))
    return 2*TP / (2*TP + FP + FN)


def model_selection(x_train, y_train, x_val, y_val, w0, epochs, eta, mini_batch, lambdas, thetas):
    '''
    :param x_train: ciag treningowy wejsciowy NxM
    :param y_train: ciag treningowy wyjsciowy Nx1
    :param x_val: ciag walidacyjny wejsciowy Nval x M
    :param y_val: ciag walidacyjny wyjsciowy Nval x 1
    :param w0: wektor poczatkowych wartosci parametrow
    :param epochs: liczba epok dla SGD
    :param eta: krok uczenia
    :param mini_batch: wielkosc mini batcha
    :param lambdas: lista wartosci parametru regularyzacji lambda, ktore maja byc sprawdzone
    :param thetas: lista wartosci progow klasyfikacji theta, ktore maja byc sprawdzone
    :return: funckja wykonuje selekcje modelu. Zwraca krotke (regularization_lambda, theta, w, F), gdzie regularization_lambda
    to najlpszy parametr regularyzacji, theta to najlepszy prog klasyfikacji, a w to najlepszy wektor parametrow modelu.
    Dodatkowo funkcja zwraca macierz F, ktora zawiera wartosci miary F dla wszystkich par (lambda, theta). Do uczenia nalezy
    korzystac z algorytmu SGD oraz kryterium uczenia z regularyzacja l2.
    '''

    best_lambda = 0
    best_theta = 0
    best_w = w0
    best_F = 0
    F = []

    for current_lambda in lambdas:
        def nowa(w, x, y):
            return regularized_logistic_cost_function(w, x, y, current_lambda)
        w, func_values = stochastic_gradient_descent(nowa,x_train, y_train, w0, epochs, eta, mini_batch)
        cF = []
        for current_theta in thetas:
            current_y_pred = prediction(x_val, w, current_theta)
            current_F = f_measure(y_val, current_y_pred)
            cF.append(current_F)
            if current_F > best_F:
                best_lambda = current_lambda
                best_theta = current_theta
                best_w = w
                best_F = current_F
        F.append(cF)

    return best_lambda, best_theta, best_w, F


