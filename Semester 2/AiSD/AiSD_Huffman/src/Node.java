import javax.swing.*;
import java.util.Comparator;

/**
 * @author Grzegorz Chilczuk
 *         Created on 08 maj 2016
 */
public class Node implements Comparable<Node> {
    Character ch;
    int count;
    Node left;
    Node right;

    public Node(Character ch) {
        this.ch = ch;
        this.count = 1;
        left = right = null;
    }

    public Node(int count, Node left, Node right) {
        this.count = count;
        this.left = left;
        this.right = right;
        ch = null;
    }

    @Override
    public int compareTo(Node node) {
        return count - node.count;
    }

    @Override
    public String toString() {
        return "<" + (ch != null? ch:"ə")+ " " + "—" + " " + count+ ">";
    }
}
