# --------------------------------------------------------------------------
# ------------  Metody Systemowe i Decyzyjne w Informatyce  ----------------
# --------------------------------------------------------------------------
#  Zadanie 2: k-NN i Naive Bayes
#  autorzy: A. Gonczarek, J. Kaczmar, S. Zareba
#  2017
# --------------------------------------------------------------------------

from __future__ import division
import numpy as np
from utils import *
from collections import Counter
import scipy.spatial

def hamming_distance(X, X_train):
    """
    :param X: zbior porownwanych obiektow N1xD
    :param X_train: zbior obiektow do ktorych porownujemy N2xD
    Funkcja wyznacza odleglosci Hamminga obiektow ze zbioru X od
    obiektow X_train. ODleglosci obiektow z jednego i drugiego
    zbioru zwrocone zostana w postaci macierzy
    :return: macierz odleglosci pomiedzy obiektami z X i X_train N1xN2
    """

    def funfun(x):
        wyn = []
        for xt in X_train.toarray():
            wyn.append(sum(el1 != el2 for el1, el2 in zip (x.toarray(), xt)))
        # print(wyn)
        return wyn


    mac_wyn = []
    for x in X.toarray():
        list_wyn = []

        for xt in X_train.toarray():
            list_wyn.append(sum(el1 != el2 for el1, el2 in zip (x, xt)))

        mac_wyn.append(list_wyn)

    return  np.array(mac_wyn)
    #
    # X = X.toarray()
    # X_train = X_train.toarray()
    # return np.absolute(X.dot(X_train.T - 1) + (X - 1).dot(X_train.T))


def sort_train_labels_knn(Dist, y):
    """
    Funkcja sortujaca etykiety klas danych treningowych y
    wzgledem prawdopodobienstw zawartych w macierzy Dist.
    Funkcja zwraca macierz o wymiarach N1xN2. W kazdym
    wierszu maja byc posortowane etykiety klas z y wzgledem
    wartosci podobienstw odpowiadajacego wiersza macierzy
    Dist
    :param Dist: macierz odleglosci pomiedzy obiektami z X
    i X_train N1xN2
    :param y: wektor etykiet o dlugosci N2
    :return: macierz etykiet klas posortowana wzgledem
    wartosci podobienstw odpowiadajacego wiersza macierzy
    Dist. Uzyc algorytmu mergesort.
    """

    result = []
    for distances in Dist.tolist():
        zipped = list(zip(distances,y))
        zipped_sorted = merge_sort(zipped, predd)
        unzipped = unzip2_seq(zipped_sorted)
        result.append(unzipped)
        # print(unzipped)
    return np.array(result)

def p_y_x_knn(y, k):
    """
    Funkcja wyznacza rozklad prawdopodobienstwa p(y|x) dla
    kazdej z klas dla obiektow ze zbioru testowego wykorzystujac
    klasfikator KNN wyuczony na danych trenningowych
    :param y: macierz posortowanych etykiet dla danych treningowych N1xN2
    :param k: liczba najblizszuch sasiadow dla KNN
    :return: macierz prawdopodobienstw dla obiektow z X
    """

    pmatrix = []
    for row in y:
        p_for_obj = []
        counter = Counter(row[:k])
        for i in range(1,5):
            p_for_obj += [counter[i]/k]
        pmatrix.append(p_for_obj)
    return np.array(pmatrix)

def classification_error(p_y_x, y_true):
    """
    Wyznacz blad klasyfikacji.
    :param p_y_x: macierz przewidywanych prawdopodobienstw
    :param y_true: zbior rzeczywistych etykiet klas 1xN.
    Kazdy wiersz macierzy reprezentuje rozklad p(y|x)
    :return: blad klasyfikacji
    """

    def highest_p_class(seq):
        max = 0
        for i in range(1,4):
            if seq[i] >= seq[max]:
                max = i
        return max+1

    N = len(p_y_x)
    errors = 0
    for row_index in range(N):
        errors += highest_p_class(p_y_x[row_index]) != y_true[row_index]
    return errors/N

def model_selection_knn(Xval, Xtrain, yval, ytrain, k_values):
    """
    :param Xval: zbior danych walidacyjnych N1xD
    :param Xtrain: zbior danych treningowych N2xD
    :param yval: etykiety klas dla danych walidacyjnych 1xN1
    :param ytrain: etykiety klas dla danych treningowych 1xN2
    :param k_values: wartosci parametru k, ktore maja zostac sprawdzone
    :return: funkcja wykonuje selekcje modelu knn i zwraca krotke (best_error,best_k,errors), gdzie best_error to najnizszy
    osiagniety blad, best_k - k dla ktorego blad byl najnizszy, errors - lista wartosci bledow dla kolejnych k z k_values
    """
    distance_matrix = hamming_distance(Xval, Xtrain)
    sorted_labels = sort_train_labels_knn(distance_matrix, ytrain)
    best_k = k_values[0]
    best_error = classification_error(p_y_x_knn(sorted_labels, best_k), yval)
    errors = [best_error]

    for k in k_values[1:]:
        current_p_matrix = p_y_x_knn(sorted_labels, k)
        current_error_value = classification_error(current_p_matrix, yval)
        errors += [current_error_value]
        if current_error_value < best_error:
            best_error = current_error_value
            best_k = k

    return best_error, best_k, errors

####

def estimate_a_priori_nb(ytrain):
    """
    :param ytrain: etykiety dla dla danych treningowych 1xN
    :return: funkcja wyznacza rozklad a priori p(y) i zwraca p_y - wektor prawdopodobienstw a priori 1xM
    """
    N = ytrain.shape[0]
    counter = Counter(ytrain)
    result = []
    for i in range(1,5):
        result += [counter[i]/N]

    return np.array(result)

def estimate_p_x_y_nb(Xtrain, ytrain, a, b):
    """
    :param Xtrain: dane treningowe NxD
    :param ytrain: etykiety klas dla danych treningowych 1xN
    :param a: parametr a rozkladu Beta
    :param b: parametr b rozkladu Beta
    :return: funkcja wyznacza rozklad prawdopodobienstwa p(x|y) zakladajac, ze x przyjmuje wartosci binarne i ze elementy
    x sa niezalezne od siebie. Funkcja zwraca macierz p_x_y o wymiarach MxD.
    """
    N = Xtrain.shape[0]
    D = Xtrain.shape[1]
    M = 4

    divider = []
    counter = Counter(ytrain)
    for i in range(1,M+1):
        divider += [counter[i]+a+b-2]

    # create empty matrix
    result = np.zeros((M, D))
    for row_index in range(N):
        for col_index in range(D):
            result[ytrain[row_index]-1,col_index] += Xtrain[row_index, col_index]

    result = np.array(result)
    for i in range(M):
        result[i] += a-1
        result[i] = result[i] / divider[i]

    # print(result)

    return result

def p_y_x_nb(p_y, p_x_1_y, X):
    """
    :param p_y: wektor prawdopodobienstw a priori o wymiarach 1xM
    :param p_x_1_y: rozklad prawdopodobienstw p(x=1|y) - macierz MxD
    :param X: dane dla ktorych beda wyznaczone prawdopodobienstwa, macierz NxD
    :return: funkcja wyznacza rozklad prawdopodobienstwa p(y|x) dla kazdej z klas z wykorzystaniem klasyfikatora Naiwnego
    Bayesa. Funkcja zwraca macierz p_y_x o wymiarach NxM.
    """

    M = p_x_1_y.shape[0]
    D = X.shape[1]
    X = X.toarray().tolist()

    def small_p_x_y(ob_x, p_words_y):
        pxy = 1
        for word_index in range(D):
            theta = p_words_y[word_index]
            x = ob_x[word_index]
            pxy *= theta**x * (1-theta)**(1-x)
        return pxy

    def small_p_y_x(ob_x):
        liczniki = []
        for kat in range(M):
            liczniki += [small_p_x_y(ob_x, p_x_1_y[kat]) * p_y[kat]]
        mianownik = sum(liczniki)

        pyx = []
        for licznik in liczniki:
            pyx += [licznik / mianownik]
        return pyx

    p_y_x_matrix = []
    for ob_x in X:
        p_y_x_matrix += [small_p_y_x(ob_x)]

    return np.array(p_y_x_matrix)


def model_selection_nb(Xtrain, Xval, ytrain, yval, a_values, b_values):
    """
    :param Xtrain: zbior danych treningowych N2xD
    :param Xval: zbior danych walidacyjnych N1xD
    :param ytrain: etykiety klas dla danych treningowych 1xN2
    :param yval: etykiety klas dla danych walidacyjnych 1xN1
    :param a_values: lista parametrow a do sprawdzenia
    :param b_values: lista parametrow b do sprawdzenia
    :return: funkcja wykonuje selekcje modelu Naive Bayes - wybiera najlepsze wartosci parametrow a i b. Funkcja zwraca
    krotke (error_best, best_a, best_b, errors) gdzie best_error to najnizszy
    osiagniety blad, best_a - a dla ktorego blad byl najnizszy, best_b - b dla ktorego blad byl najnizszy,
    errors - macierz wartosci bledow dla wszystkich par (a,b)
    """
    p_y = estimate_a_priori_nb(ytrain)
    error_best =  float('inf')
    best_a = 0
    best_b = 0

    errors = []

    i = -1
    for a in a_values:
        errors += [[]]
        i += 1
        for b in b_values:
            epxy = estimate_p_x_y_nb(Xtrain,ytrain, a, b)
            pyx = p_y_x_nb(p_y, epxy, Xval)
            current_error = classification_error(pyx, yval)
            errors[i] += [current_error]
            if current_error < error_best:
                best_a = a
                best_b = b
                error_best = current_error

    return error_best, best_a, best_b, errors




