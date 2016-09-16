import java.io.FileNotFoundException;

/**
 * @author Grzegorz Chilczuk
 *         Created on 25 maj 2016
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph;
        for (int i = 1; i < 8; i++) {
            System.out.println("——————————————————————————————————————————————————————");
            System.out.println("————————————————————— PLIK da"+i+" ———————————————————————");
            System.out.println("——————————————————————————————————————————————————————");

            graph = new Graph("da"+i);
            System.out.println("Algorytm Kruskala: ");
            System.out.println("Wymagana długość kabla to " + graph.answerPrim() + "cm.");
            System.out.println("—————————————————————————————");

            System.out.println("Algorytm Prima: ");
            System.out.println("Wymagana długość kabla to " + graph.answerKruskal() + "cm.");
            System.out.println();
        }
    }
}
