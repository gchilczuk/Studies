sealed trait Graphs[A]
case class Graph[A](succ: A=>List[A]) extends Graphs[A]

val g = Graph((i: Int) => i match {
case 0 => List(3)
case 1 => List(0,2,4)
case 2 => List(1)
case 3 => Nil
case 4 => List(0,2)
case n => throw new Exception("Graph g: node" + n + " doesn't exist")
})



sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node[+A](elem:A, left:BT[A], right:BT[A]) extends BT[A]

val tt = Node(1, Node(2, Node(4, Empty, Empty ), Empty ), Node(3, Node(5, Empty, Node(6, Empty, Empty ) ), Empty ) ) 

// zadanie 3 rprzechodzenie drzewa wszerz
def breadthBT[A] (bt:BT[A]) = {
    def bb (queue:List[BT[A]]):List[A] = 
        queue match {
            case Nil => Nil
            case Node(v, lt, rt)::t => v::bb(t++List(lt, rt))
            case Empty::t => bb(t)
       }
    bb (List(bt))
}

// zadanie 4
def wew[A] (bt:BT[A]) = {
    def ww (node:BT[A], x:Int):Int =
        node match {
            case Empty => 0
            case Node(_, l, r) => x + ww(l, x+1) + ww(r, x+1)
        }
    ww (bt, 0)
}

def zew[A] (bt:BT[A]) = {
    def zz(node:BT[A], x:Int):Int =
        node match {
            case Empty => x
            case Node(_, l, r) => zz(l, x+1) + zz(r, x+1)
        }
    zz (bt, 0)
}

def nodes[A](bt:BT[A]):Int = 
    bt match {
        case Empty => 0
        case Node(_, tl, tr) => 1 + nodes(tl) + nodes(tr)
    }

def zew2[A] (bt:BT[A]) = {
    def zz2 (node:BT[A])(i:Int, n:Int):(Int, Int) = 
        node match {
            case Empty => (0,0)
            case Node(_, tl, r) => 
                            val (a,c) = zz2(tl)(i+1,0)
                            val (b,d) = zz2(r)(i+1,0)
                            (i+a+b, 1+c+d)
        }
    val (i,n) = zz2(bt)(0,0)
   i+2*n
}

Dowód:
zał: e = i + 2n
1°  n = 1; i = 0; e = 2
2°  e' = i' + 2(n+1)
    e' = e-d + 2(d+1)
    i' = i+d 
    e-d + 2d + 2 = i + d + 2n + 2
    e = i + 2n cnd.

// zadanie 5
def breadthSearch[A] (g: Graph[A]) (startNode: A): List[A] = {
    def search(visited: List[A])(toVisit: List[A]): List[A] = toVisit match {
	case Nil => Nil
	case h::t =>
		if (visited contains h) search(visited)(t)
		else h::search(h::visited)((g succ h)++t)
	}
	search (Nil) (List(startNode))
}