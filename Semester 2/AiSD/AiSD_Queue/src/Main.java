import java.util.Scanner;

/**
 * Created by widelec on 11.03.16.
 */
public class Main {
    public static void main(String[] args) {
        Firma firma = new Firma(3, 10);

        Klient k1 = new Klient("Grzegorz");

        k1.addTowar(new Towar("Jajka", 8));
        k1.addTowar(new Towar("Mleko", 10));
        k1.addTowar(new Towar("Mąka", 12));

        firma.addKlient(k1);

        Klient k2 = new Klient("Paweł");

        k2.addTowar(new Towar("Jajka", 0));
        k2.addTowar(new Towar("Mleko", 1));
        k2.addTowar(new Towar("Mąka", 2));

        firma.addKlient(k2);

        Klient k3 = new Klient("Martyna");

        k3.addTowar(new Towar("Jajka", 18));
        k3.addTowar(new Towar("Mleko", 19));
        k3.addTowar(new Towar("Mąka", 20));

        firma.addKlient(k3);

        Klient k4 = new Klient("Paulina");

        k4.addTowar(new Towar("Jajka", 80));
        k4.addTowar(new Towar("Mleko", 100));
        k4.addTowar(new Towar("Mąka", 120));

        firma.addKlient(k4);


        firma.realizuj();

        Scanner scanner = new Scanner(System.in);
        String czyDalej = "T";
        while (czyDalej.equals("T") || czyDalej.equals("t")){
            System.out.println("Podaj nazwę klienta");
            Klient k = new Klient(scanner.next());
            String czyTowar = "T";
            while (czyTowar.equals("T") || czyTowar.equals("t")){
                System.out.println("Podaj nazwę towaru");
                String naz = scanner.next();
                System.out.println("Podaj ilość");
                int il = scanner.nextInt();
                k.addTowar(new Towar(naz,il));
                System.out.println("Następny towar?");
                czyTowar = scanner.next();
            }
            firma.addKlient(k);
            System.out.println("Naspętny klient?");
            czyDalej = scanner.next();
        }

        firma.realizuj();




    }
}
