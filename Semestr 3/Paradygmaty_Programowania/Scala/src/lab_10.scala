class IncorrectDate(message: String = null) extends Exception(message)

/*
class DayInYear(private var dayy:Int, private var monthh:Int){
  if (dayy < 1) dayy = 0; 
  else if (dayy > 30){
    dayy = 29;
  }
  else dayy = dayy -1;
  
  if (monthh < 1) monthh = 0;
  else if (monthh > 12) monthh = 11;
  else monthh -= 1;
  
  override def toString = dayy+1 + "." + (monthh+1);
  
  def day = dayy + 1;
  def month = monthh + 1;
  def day_=(d:Int){
    if (d < 1 || d > 30) throw new IncorrectDate("Niepoprawny dzień");
    dayy = d - 1;
  }
  def month_=(m:Int){
    if (m < 1 || m > 12) throw new IncorrectDate("Niepoprawny miesiąc");
    monthh = m - 1;
  }
  
  def przesun(d:Int){
    monthh += d / 30
    dayy += (d%30);
    if (dayy > 29){
      dayy -= 30;
      monthh += 1;
    }
    monthh %= 12;
  }
}

object DayInYear{
  def apply(dayy:Int, monthh:Int) = new DayInYear(dayy:Int, monthh:Int);
}*/

// zadanie 2
class DIY(var dni:Int,  monthh:Int){
  if (dni < 1) day = 1;
  else if (dni > 30) day = 30;
  else day = dni;
  
  if (monthh < 1) month = 1;
  else if (monthh > 12) month = 12;
  else month = monthh;
  
  override def toString = day + "." + month;
  
  def day = (dni % 30) + 1
  def day_= (d:Int){
    if (d < 1 || d > 30) throw new IncorrectDate("Niepoprawny dzień");
     dni = (dni / 30) * 30 + d-1;
  }
  
  def month = (dni / 30) + 1
  def month_=(m:Int){
    if (m < 1 || m > 12) throw new IncorrectDate("Niepoprawny miesiąc");
    dni = (dni%30) + (m-1)*30; 
  }
  
  def przesun(d:Int){
    dni += d;
    dni %= 360;
  }
 
}

object DIY{
  def apply(dayy:Int, monthh:Int) = new DIY(dayy:Int, monthh:Int);
}


/*

class Student(val indeks:Int, val imie:String,
    var nazwisko:String, var miasto:String = "", var dochody:Int = 0){
  override def toString = indeks+" | "+imie+" | "+nazwisko +"\n"+miasto + " | "+dochody;
}

new Student(123, "Adam", "Nowak");
new Student(123, "Adam", "Nowak", "Wrocław");
new Student(123, "Adam", "Nowak", "Wrocław", 2000);
new Student(123, "Adam", "Nowak", dochody=2000);*/