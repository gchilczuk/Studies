===SCALA===

// zadanie 1
def flatten[A](list:List[List[A]]):List[A] = 
  if (list == Nil) List()
  else list.head ++ flatten(list.tail)
 
// zadanie 2
def count[A](p:(A,List[A])):Int =
  if (p._2 == Nil) 0
  else if (p._2.head == p._1) 1 + count(p._1, p._2.tail)
  else count(p._1, p._2.tail)
  
// zadanie 3  
 def replicate[A](p:(A,Int)):List[A] = 
   if (p._2 < 0) throw new Exception("Nieprawidłowa liczba powtórzeń!")
   else if (p._2 == 0) List()
   else List(p._1) ++ replicate(p._1, p._2 - 1)

// zadanie 4
def sqrList(list:List[Int]):List[Int] = 
  if (list == Nil) List()
  else List(list.head * list.head) ++ sqrList(list.tail)
  
// zadanie 5
def palindrome[zg] (list:List[zg]) = list == list.reverse
  
// zadanie 6
def listLength[A] (list:List[A]):Int =
  if (list == Nil) 0
  else 1 + listLength(list.tail)

  
  
  