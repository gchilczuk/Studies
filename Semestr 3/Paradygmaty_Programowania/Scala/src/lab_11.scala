class UnderflowException(msg: String) extends Exception(msg)

class Queue[+T] private (private val rep: List[T], 
  private val tail:List[T] = Nil) {
    def enqueue[S>:T](x: S) = rep match{
      case Nil => new Queue(List(x))
      case _ => new Queue(rep, x::tail)
    }
    def first = rep match {
      case x::_ => x
      case Nil => throw new UnderflowException("Empty Queue")
    }
    def dequeue() = (rep, tail) match {
      case (Nil, Nil) => new Queue(Nil)
      case (_::Nil, _) => new Queue(tail.reverse)
      case (_::xs, _) => new Queue(xs, tail)
    }

    def isEmpty = rep == Nil
  }

object Queue {
// companion object
  def apply[T](xs: T*) = new Queue[T](xs.toList.reverse)
  def empty[T] = new Queue[T](Nil)
}

class Point(
var x:Double = 0.0,
var y:Double = 0.0
) {
override def toString = "[" + x + ", " + y + "]"
}
import java.awt.Color
class Pixel(
xp:Double=0.0,
yp:Double=0.0,
var color:Color = Color.BLACK
) extends Point(xp, yp) {
override def toString = super.toString + " " + color
}

// NIŻEJ SĄ TESTY
// ABY BYŁY DOBRZE CZYTELNE KAŻDY BLOK TESTY
// (ODDZIELONE PUSTĄ LINIĄ)
// POWIENIEN ZOSTAĆ WYWOŁANY ODDZIELNIE

var QofPoint = Queue(new Point())
var QofPixel = Queue(new Pixel(2,2))

QofPoint = QofPixel

println(QofPoint.first.asInstanceOf[Pixel])

QofPoint = QofPoint.enqueue(new Point(1,1))
QofPoint = QofPoint.enqueue(new Pixel(3,3))

println(QofPoint.first)
QofPoint = QofPoint.dequeue()

println(QofPoint.first)
QofPoint = QofPoint.dequeue()

println(QofPoint.first)
QofPoint = QofPoint.dequeue()


QofPoint = QofPoint.enqueue(new Point(4,4))
QofPoint = QofPoint.enqueue(new Pixel(5,5))

//QofPoint.first
//QofPoint = QofPoint.dequeue()

