/**
 * Created by widelec on 14.03.16.
 */
public class Firma {

    private Integer magMaxSize;
    private final Kolejka<Magazyn> magazynKolejka = new Kolejka<>();
    private Magazyn firstNotFull;
    private int firmaMax;
    private int _size = 1;
    private final Kolejka<Magazyn> pusteMagazyny = new Kolejka<>();

    public Firma(Integer magazynMax, int firmaMax){
        this.magMaxSize = magazynMax;
        this.firmaMax = firmaMax;
        firstNotFull = new Magazyn("Magazyn nr: "+_size, magMaxSize);
        _size++;
    }

    public void addKlient(Klient k){
        firstNotFull.addKlient(k);
        if (firstNotFull.isFull()){
            magazynKolejka.insert(firstNotFull);
            if (!pusteMagazyny.isEmpty())
                firstNotFull = pusteMagazyny.remove();
            else{
                if (_size < firmaMax)
                    firstNotFull = new Magazyn("Magazyn nr: "+_size, magMaxSize);
                else
                    System.out.println("Firma jest pełna. Nie można zbudować więcej magazynów!");
            }

        }
    }

    public void realizuj(){
        while (!magazynKolejka.isEmpty()){
            Magazyn mag = magazynKolejka.remove();
            mag.realizuj();
            pusteMagazyny.insert(mag);
        }
        firstNotFull.realizuj();
    }
}
