import java.util.LinkedList;

/**
 * @author Grzegorz Chilczuk
 *         Created on 28 kwi 2016
 */
public class BSTNode {
    String word;
    LinkedList<Integer> verses;
    BSTNode left;
    BSTNode right;
    BSTNode parent;

    public BSTNode(String word, Integer verse, BSTNode parent) {
        this.word = word;
        this.left = null;
        this.right = null;
        this.parent = parent;
        verses = new LinkedList<>();
        verses.add(verse);
    }

    @Override
    public String toString() {
        return this.word + " " + verses;
    }
}
