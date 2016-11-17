sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node[+A](elem:A, left:BT[A], right:BT[A]) extends BT[A]

def height[A](bt:BT[A]):Int = 
    bt match {
        case Empty => 0
        case Node(_, tl, tr) => 1 + Math.max(height(tl), height(tr))
    }
