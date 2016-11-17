// zadanie 2
def curry3v1 [A,B,C,D](f1:(A,B,C)=>D):A=>(B=>C=>D) = a => b => c => f1(a,b,c)
def curry3v2 [A,B,C,D](f:(A,B,C) => D) = (x:A) => (y:B) => (z:C) => f(x,y,z)
val plus = (x:Int, y:Int, z:Int) => x+y+z

def uncurry3v1[A,B,C,D](f:A=>B=>C=>D):((A,B,C)=>D) = (x, y, z) => f (x) (y) (z)
def uncurry3v2[A,B,C,D](f:A=>B=>C=>D) = (x:A, y:B, z:C) => f (x) (y) (z)

// zadanie 3
def sumProd (list:List[Int]) = (list foldLeft (0,1)) ((acc:(Int,Int), h:Int) => (h+acc._1, h*acc._2))   

// zadanie 5 insert
def insertionsort[A] (f:A=>A=>Boolean)(list:List[A]):List[A] = {
  list match {
    case Nil => Nil
    case h::t =>
      def insert (el:A)(list:List[A]):List[A] = {
        list match {
          case Nil => el::Nil
          case x::xs => if (f(x)(el)) el::list else x::(insert(el)(xs))
        }
      }
    insert (h) (insertionsort (f)(t))
  }
}


// zadanie 5 merge
def halve[A] (list:List[A]):(List[A],List[A]) = 
  list match{
  case Nil => (Nil,Nil)
  case List(_)=> (list, Nil)
  case h::t => { val (l1,l2) = halve (t);
    (h::l2,l1)}
}


def merge[A] (f:(A=>A=>Boolean)) (l1:List[A]) (l2:List[A]):List[A] =
  (l1,l2) match{
  case (Nil, Nil) => Nil
  case (Nil, _) => l2
  case (_, Nil) => l1
  case (h1::t1, h2::t2) => 
    if (f(h1)(h2)) h1::(merge (f)(t1)(l2))
    else h2::(merge (f)(l1)(t2))
}

def mergesort[A] (f:(A=>A=>Boolean)) (list:List[A]):List[A] =
  list match{
  case Nil => Nil
  case List(_) => list
  case l => { val (l1,l2) = halve (list);
    merge (f) (mergesort(f)(l1)) (mergesort(f)(l2))
  }
}


def merges2[A] (f:(A=>A=>Boolean)) (li:List[A]):List[A] = {
  def sort (list:List[List[A]]):List[List[A]] =
    list match {
      case Nil => Nil
      case List(a) => List(a)
      case h1::h2::t => sort(merge(f)(h1)(h2)::sort(t))
    }
  val List(x)= sort (li map (x=>List(x)))
  x    
}

merges2 ((x:Int) => (y:Int) => x < y) (List(8,2,1,3,0))
merges2 ((x:(Int,Int)) => (y:(Int,Int)) => x._1 <= y._1) (List((33,1),(-2,1),(1,1),(1,2),(2,1),(-15,1)))