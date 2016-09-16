import java.util.LinkedList;

/**
 * @author Grzegorz Chilczuk
 *         Created on 30 kwi 2016
 */
public class RBTNode {
    String word;
    LinkedList<Integer> verses;
    char color;
    RBTNode parent;
    RBTNode left;
    RBTNode right;

    public RBTNode(String word, Integer verse, char color, RBTNode parent) {
        this.word = word;
        this.left = null;
        this.right = null;
        this.color = color;
        this.parent = parent;
        verses = new LinkedList<>();
        verses.add(verse);
    }

    public RBTNode(){
        color = 'B';
        parent = left = right = this;
        word = "nil";
    }



    @Override
    public String toString() {
        return String.format("%-14s",this.word) /*+ " " + color + " " + "Ojciec: " + parent.word */+ verses ;
    }

}
