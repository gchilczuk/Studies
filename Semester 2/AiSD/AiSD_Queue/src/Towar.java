/**
 * Created by on 12.03.16.
 *
 * @author Grzegorz Chilczuk
 */
public class Towar {
    private String nazwa;
    private int ilosc;

    public Towar(String n, int il){
        nazwa = n;
        ilosc = il;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "nawza towaru: "+nazwa+" ilość: "+ilosc;
    }
}
