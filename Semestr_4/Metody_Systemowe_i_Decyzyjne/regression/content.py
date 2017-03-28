# --------------------------------------------------------------------------
# ------------  Metody Systemowe i Decyzyjne w Informatyce  ----------------
# --------------------------------------------------------------------------
#  Zadanie 1: Regresja liniowa
#  autorzy: A. Gonczarek, J. Kaczmar, S. Zareba
#  2017
# --------------------------------------------------------------------------

import numpy as np
from utils import polynomial

def mean_squared_error(x, y, w):
    '''
    :param x: ciag wejsciowy Nx1
    :param y: ciag wyjsciowy Nx1
    :param w: parametry modelu (M+1)x1
    :return: blad sredniokwadratowy pomiedzy wyjsciami y
    oraz wyjsciami uzyskanymi z wielowamiu o parametrach w dla wejsc x
    '''
    pred = polynomial(x, w)
    N = len(y)
    sum = 0;
    for i in range(N):
        sum += (y[i][0] - pred[i][0])**2
    return sum/N



def design_matrix(x_train,M):
    '''
    :param x_train: ciag treningowy Nx1
    :param M: stopien wielomianu 0,1,2,...
    :return: funkcja wylicza Design Matrix Nx(M+1) dla wielomianu rzedu M
    '''
    matrix = []

    def f (x):
        x = x[0]
        wyn = [1]
        for i in range(M):
            wyn += [wyn[-1]*x]
        return wyn

    for x in x_train:
        matrix.append(f(x))

    return np.array(matrix)


def least_squares(x_train, y_train, M):
    '''
    :param x_train: ciag treningowy wejscia Nx1
    :param y_train: ciag treningowy wyjscia Nx1
    :param M: rzad wielomianu
    :return: funkcja zwraca krotke (w,err), gdzie w sa parametrami dopasowanego wielomianu, a err blad sredniokwadratowy
    dopasowania
    '''
    fi = design_matrix(x_train, M)
    fi_trans = fi.transpose()
    w = np.linalg.inv(fi_trans @ fi) @ fi_trans @ y_train
    err = mean_squared_error(x_train, y_train, w)
    return (w, err)


def regularized_least_squares(x_train, y_train, M, regularization_lambda):
    '''
    :param x_train: ciag treningowy wejscia Nx1
    :param y_train: ciag treningowy wyjscia Nx1
    :param M: rzad wielomianu
    :param regularization_lambda: parametr regularyzacji
    :return: funkcja zwraca krotke (w,err), gdzie w sa parametrami dopasowanego wielomianu zgodnie z kryterium z regularyzacja l2,
    a err blad sredniokwadratowy dopasowania
    '''
    fi = design_matrix(x_train, M)
    fi_trans = fi.transpose()
    w = np.linalg.inv(fi_trans @ fi + regularization_lambda*np.eye(M+1)) @ fi_trans @ y_train
    err = mean_squared_error(x_train, y_train, w)
    return (w, err)


def model_selection(x_train, y_train, x_val, y_val, M_values):
    '''
    :param x_train: ciag treningowy wejscia Nx1
    :param y_train: ciag treningowy wyjscia Nx1
    :param x_val: ciag walidacyjny wejscia Nx1
    :param y_val: ciag walidacyjny wyjscia Nx1
    :param M_values: tablica stopni wielomianu, ktore maja byc sprawdzone
    :return: funkcja zwraca krotke (w,train_err,val_err), gdzie w sa parametrami modelu, ktory najlepiej generalizuje dane,
    tj. daje najmniejszy blad na ciagu walidacyjnym, train_err i val_err to bledy na sredniokwadratowe na ciagach treningowym
    i walidacyjnym
    '''
    good_w, good_train_err = least_squares(x_train, y_train, M_values[0])
    good_val_err = mean_squared_error(x_val,y_val, good_w)
    for M in M_values[1:]:
        act_w, act_train_err = least_squares(x_train, y_train, M)
        act_val_err = mean_squared_error(x_val, y_val, act_w)

        if act_val_err < good_val_err:
            good_w, good_train_err = act_w, act_train_err
            good_val_err = act_val_err
            
    return good_w, good_train_err, good_val_err


def regularized_model_selection(x_train, y_train, x_val, y_val, M, lambda_values):
    '''
    :param x_train: ciag treningowy wejscia Nx1
    :param y_train: ciag treningowy wyjscia Nx1
    :param x_val: ciag walidacyjny wejscia Nx1
    :param y_val: ciag walidacyjny wyjscia Nx1
    :param M: stopien wielomianu
    :param lambda_values: lista ze wartosciami roznych parametrow regularyzacji
    :return: funkcja zwraca krotke (w,train_err,val_err,regularization_lambda), gdzie w sa parametrami modelu, ktory najlepiej generalizuje dane,
    tj. daje najmniejszy blad na ciagu walidacyjnym. Wielomian dopasowany jest wg kryterium z regularyzacja. train_err i val_err to
    bledy na sredniokwadratowe na ciagach treningowym i walidacyjnym. regularization_lambda to najlepsza wartosc parametru regularyzacji
    '''
    good_l = lambda_values[0]
    good_w, good_train_err = regularized_least_squares(x_train, y_train, M, good_l)
    good_val_err = mean_squared_error(x_val, y_val, good_w)

    for lv in lambda_values[1:]:
        act_w, act_train_err = regularized_least_squares(x_train, y_train, M, lv)
        act_val_err = mean_squared_error(x_val, y_val, act_w)

        if act_val_err < good_val_err:
            good_l = lv
            good_w, good_train_err = act_w, act_train_err
            good_val_err = act_val_err

    return good_w, good_train_err, good_val_err, good_l
    