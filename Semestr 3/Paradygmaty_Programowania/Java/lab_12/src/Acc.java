import java.util.concurrent.Semaphore;

public class Acc {
    private int balance;
    public Semaphore semaphore;
    public Acc(int x){
        semaphore = new Semaphore(1);
        balance = x;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
