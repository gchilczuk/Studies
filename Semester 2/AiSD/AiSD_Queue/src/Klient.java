/**
 * Created on 12.03.16.
 *
 * @author Grzegorz Chilczuk
 */
public class Klient {
    private Kolejka<Towar> zakupy = new Kolejka<>();
    private String nazwa;

    public Klient(String nazwa) {
        this.nazwa = nazwa;
    }

    public Kolejka<Towar> getZakupy() {
        return zakupy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void addTowar(Towar towar){
        zakupy.insert(towar);
    }

}
