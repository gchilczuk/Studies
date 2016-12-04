// zadanie 1
def whileLoop(warunek: =>Boolean, wyr: =>Unit):Unit = {
  if (warunek)
  {wyr; whileLoop(warunek,wyr)}
}
var i = 0
whileLoop(i < 10, { println(i); i += 1 })

//zadanie 2

def swap[A](tab:Array[A]) (i:Int) (j:Int) = {
  val tmp = tab(i)
  tab(i) = tab(j)
  tab(j) = tmp
}

def choose_pivot[A] (tab:Array[A]) (m:Int) (n:Int) = {
  tab((m+n)/2)
}

def partition (tab:Array[Int]) (l:Int) (r:Int) = {
  var i = l
  var j = r
  val pivot = choose_pivot (tab) (l) (r)
  while (i <= j){
    while (tab(i) < pivot) {i+=1}
    while (tab(j) > pivot) {j-=1}
    if (i <= j){swap (tab) (i) (j); i+=1; j-=1}
  }
  (i,j)
}

def quick (tab:Array[Int]) (l:Int) (r:Int):Unit = {
  if (l < r) {
    val (i,j) = partition (tab) (l) (r)
    if (j-l < r-i){quick (tab) (l) (j); quick (tab) (i) (r)}
    else {quick (tab) (i) (r); quick (tab) (l) (j)}
  }
}

def quicksort (tab:Array[Int]) = quick (tab) (0) (tab.length-1)

