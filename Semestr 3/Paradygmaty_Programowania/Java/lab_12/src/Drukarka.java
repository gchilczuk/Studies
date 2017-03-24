
public class Drukarka {
    int A;
    int B;
    Drukarka(){
        A = 0;
        B = 0;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    void drukuj(String napis){
        if (napis.equals("A")) {
            if (A < B);
            A++;
            System.out.println(napis);
        }
        else if (napis.equals("B")) {
            B++;
            System.out.println(napis);
            notify();
        }
    }

}
