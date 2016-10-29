// zadanie 2
def nTy[A] (list:List[A], index:Int):A =
  index match{
  case x if x < 1 || list == Nil => throw new Exception("Niepoprawny index")
  case 1 => list.head
  case x => nTy(list.tail, index-1)
}

// zadanie 4
def podzielPoN[A] (lista:List[A], dlugosc:Int):(List[A], List[A]) =
  (lista, dlugosc) match{
  case (Nil, x) if x > 0 => throw new Exception("Niepoprawna dlugosc")
  case (_, x) if x < 0 => throw new Exception("Niepoprawna dlugosc")
  case (l, x) if x == 0 => (Nil, l)
  case (h::t, x) => {val (a,b) = podzielPoN(t, x-1); (h::a, b) }
}










def pocz[A] (lista:List[A], dlugosc:Int):List[A] =
  (lista, dlugosc) match{
  case (_, x) if x <= 0 => Nil
  case (Nil, x) if x > 0 => Nil
  case (h::t, x) => h::pocz(t,x-1)
}

def kon[A] (lista:List[A], dlugosc:Int):List[A] =
  (lista, dlugosc) match{
  case (l, x) if x <= 0 => l
  case (Nil, x) if x > 0 =>Nil
  case (h::t, x) => kon(t, x-1)
}

def podzielPoN[A] (lista:List[A], dlugosc:Int):(List[A], List[A]) = {
  (pocz(lista, dlugosc), kon(lista,dlugosc))
}








