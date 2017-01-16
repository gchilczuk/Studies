import java.util.concurrent.Semaphore;

class Porter extends Semaphore{
    public Porter() {
        super(4);
    }
    // portier może wpuścić do jadalni maksymalnie 4 filozofów na raz
}

class Stick extends Semaphore{

    public Stick() {
        super(1);
    }
    // pałeczka może być podniesiona tylko przez jednego filozofa
}


public class Main {
    public static void main(String[] args) {
        Philosopher [] philosophers = new Philosopher[5];   // tablica filozofóœ
        Porter porter = new Porter();                       // utworzenie portiera
        Stick [] sticks = new Stick[5];                     // tablica pałeczek

        for (int i = 0; i < 5; i++)
            sticks[i] = new Stick(); // tworzenie 5 pałaczek

        // wszyscy filozofowie mają wspólnego portiera
        // każda pałaczka znajduje się u jednego filozofa jako prawa i u drugiego jako lewa
        philosophers[0] = new Philosopher(porter, sticks[0], sticks[1], "A");
        philosophers[1] = new Philosopher(porter, sticks[1], sticks[2], "B");
        philosophers[2] = new Philosopher(porter, sticks[2], sticks[3], "C");
        philosophers[3] = new Philosopher(porter, sticks[3], sticks[4], "D");
        philosophers[4] = new Philosopher(porter, sticks[4], sticks[0], "E");

        for (Philosopher p : philosophers) {
            p.start(); // włączam wszystkichfilozofów
        }
    }
}
