import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Grzegorz Chilczuk
 *         Created on 29 kwi 2016
 */
public class WordCounter {

    public BST buildBSTTree (String filename)throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        BST tree = new BST();
        Integer lineCounter = 0;
        while (scanner.hasNext()){
            lineCounter++;
            String line = scanner.nextLine();
            LinkedList<String> words = separateWords(line);
            for (String word : words)
                tree.add_word(word, lineCounter);
        }
        scanner.close();
        return tree;
    }

    public RBT buildRBTTree (String filename)throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(filename));
        RBT tree = new RBT();
        Integer lineCounter = 0;
        while (scanner.hasNext()){
            lineCounter++;
            String line = scanner.nextLine();
            LinkedList<String> words = separateWords(line);
            for (String word : words)
                tree.add_word(word, lineCounter);
        }
        scanner.close();
        return tree;
    }

    public LinkedList<String> separateWords(String line){
        LinkedList<String> list = new LinkedList<>();
        String word = "";
        for (int i = 0; i < line.length(); i++){
            char ch = line.charAt(i);
            if (isLetter(ch))
                word += ch;
            else if (word.length() > 0){
                list.add(word);
                word = "";
            }
        }
        if (word.length() > 0)
            list.add(word);
        return list;
    }

    public boolean isLetter(char ch){
        return ".,:;!?-(){}[] ".indexOf(ch) < 0;
    }
}
