/**
 * Created on 12.03.16.
 *
 * @author Grzegorz Chilczuk
 */
public class Magazyn {
    Kolejka<Klient> magazyn;
    String nazwa;

    public Magazyn(String nazwa, Integer maxSize){
        this.nazwa = nazwa;
        magazyn = new Kolejka<>(maxSize);
    }

    public void addKlient(Klient k){
        magazyn.insert(k);
    }

    public void realizuj(){
        if (!magazyn.isEmpty())
            System.out.println(nazwa);
        while (!magazyn.isEmpty()){
            Klient k = magazyn.remove();
            Kolejka<Towar> produkty = k.getZakupy();
            int ilosc = 0;
            while (!produkty.isEmpty()){
                ilosc += produkty.remove().getIlosc();
            }
            System.out.println(k.getNazwa()+": zlecenie zrealizowane; "+ilosc+" produktów");
        }
    }

    public boolean isFull(){
        return magazyn.isFull();
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
