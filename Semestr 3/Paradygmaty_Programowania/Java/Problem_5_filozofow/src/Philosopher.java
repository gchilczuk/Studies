import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private Porter porter;
    private Stick leftStick;
    private Stick rightStick;
    private int counter;

    public Philosopher(Porter porter, Stick leftStick, Stick rightStick, String name) {
        super(name);
        this.porter = porter;
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        counter = 0; // ile razy jadł
    }

    @Override
    public void run() {
        for (int i = 0; i > -10 /*warunek ile razy mają jeść*/; i++){
            try {
                porter.acquire();
                leftStick.acquire();
                rightStick.acquire();
                sleep(5); // czas trwania posiłku
                if (counter % 500 == 0) System.out.println(getName() + " : zjadłem "+i); // co 500 posiłków informuje ile już ich miał
                counter++;
            }catch (InterruptedException e){ e.printStackTrace();}
            finally {
                leftStick.release();
                rightStick.release();
                porter.release();
            }
        }
    }
}
