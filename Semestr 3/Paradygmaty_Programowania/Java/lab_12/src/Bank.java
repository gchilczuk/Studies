import java.util.concurrent.ThreadLocalRandom;

public class Bank extends Thread{
    Acc[] acclist;
    int howmany;

    public Bank(Acc[] accList, int howmany){
        this.acclist = accList;
        this.howmany = howmany+1;
    }

    @Override
    public void run() {
        int from, to, tmp1, tmp2;
        while (howmany-->0){
            from = ThreadLocalRandom.current().nextInt(0, acclist.length);
            to=ThreadLocalRandom.current().nextInt(0, acclist.length);
            while(to==from){
                to=ThreadLocalRandom.current().nextInt(0, acclist.length);
            }

            try {
                acclist[from].semaphore.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            tmp1 = acclist[from].getBalance();
            acclist[from].setBalance(tmp1 - 1);

            acclist[from].semaphore.release();

            try {
                acclist[to].semaphore.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            tmp2 = acclist[to].getBalance();
            acclist[to].setBalance(tmp2 + 1);

            acclist[to].semaphore.release();

        }
    }
}
