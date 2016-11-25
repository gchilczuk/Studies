// Zadanie 1

def lrepeat[A] (k:Int,s:Stream[A]) = {
  def rep[A] (m:Int, llista:Stream[A]):Stream[A] =
    (m, llista) match {
    case (_, Stream.Empty) => Stream.empty
    case (0, h#::xs) => rep (k, xs)
    case (n, h#::xs) => Stream.cons(h, rep (n-1, llista))
  }
  rep (k, s)
}

// zadanie 2
def fibo(a:BigInt,b:BigInt):Stream[BigInt] =
  Stream.cons(a, fibo(b, a+b))

val lfib = {
  def fibo(a:BigInt,b:BigInt):Stream[BigInt] ={
    Stream.cons(a, fibo(b, a+b))}
  fibo(0,1)
}

// zadanie 3

sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]

def lTree (n:Int):lBT[Int] =
  LNode(n, () => lTree(2*n), ()=>lTree(2*n+1))

def breadth[A] (lbt:lBT[A]) = {
    def bb (queue:List[lBT[A]]):Stream[A] = 
        queue match {            
            case Nil => Stream.empty
            case LNode(v, lt, rt)::t => Stream.cons(v,bb(t++List(lt(), rt())))
            case LEmpty::t => bb(t)
       }
    bb (List(lbt))
}  
