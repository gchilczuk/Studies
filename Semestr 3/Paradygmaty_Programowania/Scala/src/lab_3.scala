def potega (a:Float, n:Int):Float =
  if (n < 0) potega (1/a, -n)
  else n match{
    case 0 => 1
    case 1 => a
    case x => a*potega(a, n-1)
}

def potegaO (a:Float, n:Int):Float = {
  def pot (n:Int, acc:Float):Float =
    n match{
    case x if x < 0 => potegaO (1/a, -n)
    case 0 => 1
    case 1 => acc
    case x =>pot(n-1, a*acc)
  }
  pot(n, a)
}
  
  