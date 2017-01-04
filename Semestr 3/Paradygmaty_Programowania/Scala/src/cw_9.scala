// zadanie 1

class Time(var h:Int){ 
if (h<0) h = 0;
def hour = h // akcesor
def hour_=(x: Int) { // mutator
if (x<0) h=0 
else h = x
}
}

object Time{
  def apply (h:Int) = new Time(h)
}

// zadanie 2 a

class Time (var godz:Int, var min:Int){
  require(0<=godz && godz<24 && 0<=min && min<60)
  override def toString = godz + ":" + min
  def time:(Int,Int) = (godz,min)
  def hour:Int = godz
  def minute:Int = min
  def hour_= (h:Int){
    require(0<=h && h<24)
    godz = h
  }
  def minute_=(m:Int){
    require(0<=m && m<24)
    min = min
  }
  def before(other:Time) = 
    godz < other.godz ||
    (other.godz == godz && min < other.min) 
}


// zadanie 2 b


class Time (godz:Int, var min:Int){
  require(0<=godz && godz<24 && 0<=min && min<60)
  min = min + godz*60
  override def toString = min/60 + ":"+min+":" + min%60
  def time:(Int,Int) = (min/60,min%60)
  def hour:Int = min/60
  def minute:Int = min%60
  def hour_= (h:Int){
    require(0<=h && h<24)
    min = (min%60) + (h*60)
  }
  
  def minute_=(m:Int){
    require(0<=m && m<60)
    min = (min/60)*60 + m
  }
  
  def before(other:Time) = 
    min < other.min
}



// zadanie 3

class Pojazd(val producent:String, val model:String, 
    val rok_prod:Int=-1, var rejestr:String=""){
//  def this(producent:String, model:String, rok_prod:Int){
//    this(producent, model, rok_prod, "")
//  }
  def this(producent:String, model:String, numer_rejestr:String){
    this(producent, model, -1, numer_rejestr)
//  }
//  def this(producent:String, model:String){
//    this(producent, model, -1, "")
//  }
}

// zadanie 4 

object UzycieWyjatkow {
  def main(args: Array[String]) {
    try {
      metoda1()
    } catch {
      case e: Exception =>
        System.err.println(e.getMessage + "\n")
        e.printStackTrace()
    }
  }
 
  def metoda1() {
    metoda2()
  }
 
  def metoda2() {
    metoda3()
  }
 
  def metoda3() {
    throw new Exception("Wyjatek zgloszony w metoda3")
  }
}