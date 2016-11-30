def lpow (n:Int) = {
  def lpor (acc:Int):Stream[Int] =
    Stream.cons(acc, lpor (acc*n))
  lpor (1)
}