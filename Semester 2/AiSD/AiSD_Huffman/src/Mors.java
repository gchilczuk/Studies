import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Grzegorz Chilczuk
 *         Created on 16 maj 2016
 */
public class Mors {
    Node root;

    Mors() throws IOException{
        root = new Node('\\');
        build();
//        show();

    }
    void build() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("kody.txt"));
        String lit, kod;
        Node node;
        Node parent;
        while (scanner.hasNext()){
            parent = root;
            lit = scanner.next();
            kod = scanner.next();
            node = new Node(lit.charAt(0));
            int i;
            for (i = 0; i < kod.length()-1; i++){
                if (kod.charAt(i) == '.')
                    parent = parent.left;
                else parent = parent.right;
            }
            if (kod.charAt(i) == '.')
                parent.left = node;
            else {
//                System.out.println(node.ch);
                parent.right = node;
            }
//            System.out.println(lit + " " + kod);
        }
    }

    void decode(String kod, Node node, int index){
        if(index >= kod.length())
            return;
        if(kod.charAt(index) == '\\'){
            System.out.printf(node.ch.toString());
            decode(kod, root, ++index);
        }
        else if(kod.charAt(index) == '.'){
            decode(kod, node.left, ++index);
        }
        else decode(kod, node.right, ++index);
    }

    void decode(String kod){
        decode(kod, root, 0);
    }

    void show(Node node, String kod){
        if (node == null) return;
        System.out.println(node.ch + " : " + kod);
        show(node.left, kod + ".");
        show(node.right, kod + "-");
    }

    void show(){
        show(root, "");
    }
}
