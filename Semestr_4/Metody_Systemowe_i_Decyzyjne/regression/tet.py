import numpy as np

def design_matrix(x_train,M):
    '''
    :param x_train: ciag treningowy Nx1
    :param M: stopien wielomianu 0,1,2,...
    :return: funkcja wylicza Design Matrix Nx(M+1) dla wielomianu rzedu M
    '''
    matrix = []

    def f(x):
        x = x[0]
        print(x)
        wyn = [1]
        for i in range(M):
            wyn += [wyn[-1] * x]
        return wyn

    for x in x_train:
        matrix.append(f(x))

    print(matrix)
    return np.array(matrix)

x = np.array([[1],[2],[3]])
# print(x)
a = design_matrix(x, 2)
print(a)