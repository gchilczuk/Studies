// zadanie 2
def twoLast[A] (list:List[A]):(A,A) = 
  if (list.length < 2) throw new Exception("Zbyt krótka lista")
  else if (list.length == 2) (list.head, list.tail.head)
  else twoLast(list.tail)

// zadanie 4
def dwaRowne[A](xs:List[A]):Boolean = 
  if (xs == Nil || xs.tail == Nil) false
  else if (xs.head == xs.tail.head) true
  else dwaRowne(xs.tail)