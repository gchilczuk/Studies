// zadanie 2
val fib1:(Int => Int) = (x) =>
  x match{
    case y if y < 0 => throw new Exception("Podana liczba jest ujemna!")
    case 0 => 0
    case 1 => 1
    case n => fib1 (n-1) + fib1(n-2)
  }

def fib2 (x:Int) = 
  x match{
    case y if y < 0 => throw new Exception("Podana liczba jest ujemna!")
    case 0 => 0
    case 1 => 1
    case y => { def fibi (n:Int, a:Int, b:Int):Int = 
      n match{
      case 2 => b
      case x => fibi(n-1, b, a+b)
    }
    fibi(y,1,1)
    }
  }

def isEnough (x:Double, a:Double) = Math.abs(Math.pow(x,3) - a) < (Math.pow(10,-15) * Math.abs(a))

// zadanie 3
def root3 (a:Double) = {
  def root3r (a:Double, acc:Double):Double = 
    if (isEnough(acc, a)) acc     
    else root3r (a, acc + (a / Math.pow(acc,2))/3)
  root3r(a, if (a < 1.0) a else a/3.0)
}


// zadanie 4
val List(_,_,x,_,_) = List(-2,-1,0,1,2)

val List(_,(x,_)) = List((1,2),(0,1))

// zadanie 5
def initSegment[A] (l1:List[A], l2:List[A]):Boolean = 
  (l1, l2) match{
//  case (Nil, Nil) => true
  case (Nil, _) => true
  case (_, Nil) => false
  case (x,y) => val h1::t1 = x
  val h2::t2 = y
  if (h1 == h2) initSegment(t1,t2) else false
}

// zadanie 6
def replaceNth[A] (list:List[A], n:Int, el:A):List[A] =
  n match{
  case x if x < 0 => throw new Exception ("Nope!")
  case 0 => val h::t = list 
    el::t
  case x => val h::t = list 
    h::replaceNth(t,n-1,el)
}








