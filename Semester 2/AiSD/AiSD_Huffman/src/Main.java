import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Grzegorz Chilczuk
 *         Created on 08 maj 2016
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Mors mors = new Mors();
        mors.decode(".-\\.-..\\.-\\..\\---\\.-..\\.-\\");




      /*  Huffman huffman = new Huffman("text");
        System.out.println("Kody:");
        huffman.show_code();
        System.out.println("\nOdkodowany tekst:");
        huffman.decode("wyj.txt");*/
//        huffman.count();
//        System.out.println(huffman.count("ala i ola."));
    }
}
