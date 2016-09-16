import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Grzegorz Chilczuk
 *         Created on 08 maj 2016
 */
public class Huffman {

    Node root;
    String filename;
    HashMap<Character, String> code_book;
    SortedMap<Character, Integer> counter;
    LinkedList<Character> alfabet;

    Huffman(String filename) throws IOException{
        this.filename = filename;
        code_book = new HashMap<>();
        counter = new TreeMap<>();
        alfabet = new LinkedList<>();
        LinkedList<Node> chars = count();
        root = build(chars);
        make_book();
        cypher();

    }

    LinkedList<Node> count () throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        LinkedList<Node> list = new LinkedList<>();
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            for (int i = 0; i < str.length(); i++) {
                Character c = str.charAt(i);
                boolean is = false;
                for (Node node : list) {
                    if (node.ch == c) {
                        node.count++;
                        is = true;
                    }
                }
                if (!is) list.add(new Node(c));
            }
        }
        scanner.close();
        for (Node n:list){
            counter.put(n.ch, n.count);
        }
//        System.out.println("Drukuję zawartość listy: "+list);
        return list;
    }
    

    Node build (LinkedList<Node> list){
        PriorityQueue queue = new PriorityQueue();
//        System.out.println("Pusta PQ: "+queue);
        for (Node node : list) {
//            System.out.println("Dodaję do PQ: "+node);
            queue.addf(node);
        }
//        System.out.println("PQ, przed budową alfabetu: "+queue.size+" "+queue);
        make_alphabet(queue);
        while (queue.size > 1){
//            System.out.println("PQ, etap: "+queue.size+" "+queue);
            Node a = queue.poll();
            Node b = queue.poll();
            queue.add(new Node(a.count + b.count, a, b));
        }
        return queue.poll();
    }

    void show_code(){
//        System.out.println("Alfabet: "+alfabet);
        for (Character c : alfabet){
            System.out.println(c + " — " + counter.get(c) + "   " + code_book.get(c));
        }
    }

    void make_book(){
        make_book(root, "");
    }

    void make_book(Node node, String code){
        if (node.ch == null){
            make_book(node.left, code+"0");
            make_book(node.right, code+"1");
        } else {
            code_book.put(node.ch, code);
        }
    }

    void make_alphabet(PriorityQueue queue){
        for (int i = 0; i < queue.size; i++){
            Character character = queue.get(i).ch;
//            System.out.println("Dodaję do alfabetu: "+character);
            alfabet.addFirst(character);
        }
    }

    void cypher() throws IOException{
        FileWriter writer = new FileWriter("wyj.txt");
        Scanner scanner = new Scanner(new File(filename));
        String code;
        String str;
        while (scanner.hasNext()){
            str = scanner.nextLine();
            code = "";
            for (int i = 0; i < str.length(); i++)
                code += code_book.get(str.charAt(i));
            code+="\n";
            writer.write(code);
        }
        writer.close();
    }

    void decode(String filename) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        String code;
        String text;
        int index;
        while (scanner.hasNext()) {
            code  = scanner.nextLine();
            text = "";
            index = 0;
            while (index < code.length()) {
                text += decypher(root, code, index);
                index += code_book.get(text.charAt(text.length() - 1)).length();
            }
            System.out.println(text);
        }

    }

    Character decypher(Node node, String code, int index){
        if (node.ch != null) return node.ch;
        if (code.charAt(index) == '0') return decypher(node.left, code, ++index);
        else return decypher(node.right, code, ++index);

    }



}
