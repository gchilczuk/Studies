import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Grzegorz Chilczuk
 *         Created on 29 kwi 2016
 */
public class BST {
    BSTNode root;

    public BST (){
        root = null;
    }


    public BSTNode add_word(BSTNode node, String word, Integer verse, BSTNode parent){
        if (node == null)
            return new BSTNode(word, verse, parent);
        int compare = node.word.compareToIgnoreCase(word);
        if (compare > 0)
            node.left = add_word(node.left, word, verse, node);
        else if (compare == 0)
            node.verses.add(verse);
        else
            node.right = add_word(node.right, word, verse, node);
        return node;
    }

    public void add_word(String word, Integer verse){
        root = add_word(root, word, verse, null);
    }

    public void delete_word(String word){
        delete_node(find(word,root));
    }

    public BSTNode find(String word, BSTNode node){
        if (node == null)
            return null;
//        System.out.println("Sprawdzam: "+node.word+"  … wynik: "+node.word.equals(word));
        if (node.word.equals(word))
            return node;
        BSTNode newnode = find(word, node.left);
        if (newnode == null)
            newnode = find(word, node.right);
        return newnode;
    }

    public void delete_node(BSTNode node){
        if (node.left == null && node.right == null)
            if (node.parent.left == node){
                node.parent.left = null;
            }
            else node.parent.right = null;
        else if (node.left == null) {
            node.right.parent = node.parent;
            if (node.parent == null)
                root = node.right;
            else if (node.parent.left == node) {
                node.parent.left = node.right;
            }else node.parent.right = node.right;
        }
        else if (node.right == null) {
            node.left.parent = node.parent;
            if (node.parent == null)
                root = node.left;
            else if (node.parent.left == node) {
                node.parent.left = node.left;
            }else node.parent.right = node.left;
        } else {
            BSTNode successor;
            successor = min(node.right);
            node.word = successor.word;
            node.verses = successor.verses;
            delete_node(successor);
        }
    }

    public BSTNode min(BSTNode node){
        if (node.left == null)
            return node;
        return min(node.left);
    }

    public String toStringInorder(BSTNode node) {
        if (node == null) return "";
        return toStringInorder(node.left)
                + node.toString() + "\n"
                + toStringInorder(node.right);
    }

    public String toStringInorder(){
        return toStringInorder(root);
    }

    public String breadthOrder(){
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        String string = "";
        while(!queue.isEmpty()){
            BSTNode node = queue.poll();
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
            string += node.toString() + "\n";
        }
        return string;

    }


}
