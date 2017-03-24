
public class Zlecenie extends Thread {
    static Drukarka drukarka = new Drukarka();
    Zlecenie(){
    }
}

class ZlecenieA extends Zlecenie{
    int howmany;

    @Override
    public void run() {
        while (true){
           synchronized (drukarka){
               drukarka.drukuj("A");
//               sleep();
            }

        }
    }
}

class ZlecenieB extends Zlecenie{
    @Override
    public void run() {
        while (true){
            synchronized (drukarka){
                drukarka.drukuj("B");
            }
        }
    }
}
