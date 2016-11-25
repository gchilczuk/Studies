sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node[+A](elem:A, left:BT[A], right:BT[A]) extends BT[A]

val tt = Node(1, Node(2, Node(4, Empty, Empty ), Empty ), Node(3, Node(5, Empty, Node(6, Empty, Empty ) ), Empty ) ) 

// zadanie 2
def bre[A] (bt:BT[A]) = {
    def bb (queue:List[BT[A]]):List[A] = 
        queue match {
            case Nil => Nil
            case Node(v, lt, rt)::t => v::bb(t++List(lt, rt))
            case Empty::t => bb(t)
       }
    bb (List(bt))
}

// zadanie 2 wersja efektywna
def breadthBT[A] (bt:BT[A]) = {
    def bb (q1:List[BT[A]], q2:List[BT[A]]):List[A] = 
        (q1,q2) match {
            case (Nil,Nil) => Nil
            case (Nil, xs) => bb(xs.reverse, Nil)
            case (Node(v, lt, rt)::t,xs) => v::bb(t, rt::lt::xs)
            case (Empty::t,xs) => bb(t, xs)
       }
    bb (List(bt), Nil)
}