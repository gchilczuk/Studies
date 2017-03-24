import java.util.Random;




class Buffer
{
    private static int a = 0;
    private static int b = 0;

    public synchronized void showA()
    {
        while (a>=b)
        {
            try { wait();} catch (InterruptedException e) {}
        }
        System.out.println('A');
        a++;
    }

    public synchronized void showB()
    {
        System.out.println('B');
        b++;
        notifyAll();
    }
}

class A extends Thread
{
    private Buffer buf;
    public A(Buffer buf)
    {
        this.buf = buf;
    }

    @Override public void run()
    {
        for (int i=1; i<=100; i++)
        {
            buf.showA();
            Random r = new Random();
            try {
                A.sleep(r.nextInt(100)+100);
            } catch (InterruptedException e) {}
        }
    }
}

class B extends Thread
{
    private Buffer buf;
    public B(Buffer buf)
    {
        this.buf = buf;
    }

    @Override public void run()
    {
        for (int i=1; i<=100; i++)
        {
            buf.showB();
            Random r = new Random();
            try {
                B.sleep(r.nextInt(100)+100);
            } catch (InterruptedException e) {}
        }
    }
}

public class Main
{
    public static void main(String[] args)
    {
        Buffer buf = new Buffer();
        new A(buf).start();
        new B(buf).start();
    }

}