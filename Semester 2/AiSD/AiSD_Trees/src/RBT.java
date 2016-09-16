import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Grzegorz Chilczuk
 *         Created on 29 kwi 2016
 */
public class RBT {
    RBTNode root;
    RBTNode nil;

    public RBT() {
        nil = new RBTNode();
        root = nil;
    }

    public void add_word(String word, Integer verse){
//        System.out.println();
        boolean inserted = false;
        if (root == nil) {
            root = new RBTNode(word, verse, 'R', nil);
            root.left = root.right = nil;
            fixup(root);
            inserted = true;
        }
        if (!inserted) {
            RBTNode node = root;
            RBTNode parent = nil;
            while (!inserted) {
                int compare = node.word.compareToIgnoreCase(word);
                if (compare == 0) {
                    node.verses.add(verse);
//                    TU BYŁA DIBELSKA LINIJKA
                    inserted = true;
                } else if (compare > 0) {
                    parent = node;
                    node = node.left;
                    if (node == nil) {
                        node = new RBTNode(word, verse, 'R', parent);
                        node.left = node.right = nil;
                        parent.left = node;
                        fixup(node);
                        inserted = true;
                    }
                } else if (compare < 0) {
                    parent = node;
                    node = node.right;
                    if (node == nil) {
                        node = new RBTNode(word, verse, 'R', parent);
                        node.left = node.right = nil;
                        parent.right = node;
                        fixup(node);
                        inserted = true;
                    }
                }
            }
        }
    }

    public void fixup(RBTNode z){
//        System.out.println("FIXUP: " + z.word);
        if (z.parent == nil){
            z.color = 'B';
//            System.out.println("Root");
        }
        else if (z.parent.color == 'B');
//            System.out.println("NIC SIĘ NIE DZIEJE");
        else if (z.parent.parent.left == z.parent)
            fixupL(z);
        else fixupR(z);
//        System.out.println("——————————————————— koniec fixup: " + z.word);
    }

    void fixupL(RBTNode z){
//        System.out.println("FIXUP lewy ojciec");
        if (z.parent.parent.right.color == 'R') {
//            System.out.println("czerwony wyjek");
            z.parent.color = 'B';
            z.parent.parent.right.color = 'B';
            z = z.parent.parent;
            z.color = 'R';
            fixup(z);
        }else if(z.parent.right == z){
//            System.out.println("prawy syn");
            z = z.parent;
            rotateLeft(z);
            fixup(z);
        }else{
//            System.out.println("lewy syn");
            z.parent.color = 'B';
            z.parent.parent.color = 'R';
            rotateRight(z.parent.parent);
        }

    }

    void fixupR(RBTNode z){
//        System.out.println("FIXUP prawy ojciec");
        if (z.parent.parent.left.color == 'R') {
//            System.out.println("Czerwony wujek");
            z.parent.color = 'B';
            z.parent.parent.left.color = 'B';
            z = z.parent.parent;
            z.color = 'R';
            fixup(z);
        }else if(z.parent.left == z){
//            System.out.println("lewy syn");
            z = z.parent;
            rotateRight(z);
            fixup(z);
        }else{
//            System.out.println("prawy syn");
            z.parent.color = 'B';
            z.parent.parent.color = 'R';
            rotateLeft(z.parent.parent);
        }

    }

    void rotateLeft(RBTNode x){
//        System.out.println("Rotuję w lewo: "+x.word);
        RBTNode y = x.right;
        x.right = y.left; // x.R = B
        if (x.right != nil) // B != nil
            x.right.parent = x;
        y.parent = x.parent;
        if (y.parent == nil)
            root = y;
        else if (x.parent.left == x)
            x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    void rotateRight(RBTNode y) {
//        System.out.println("Rotuję w prawo: "+y.word);
        RBTNode x = y.left;
        y.left = x.right; // y.L = B
        if (y.left != nil) // B != nil
            y.left.parent = y;
        x.parent = y.parent;
        if (x.parent == nil)
            root = x;
        else if (y.parent.left == y)
            y.parent.left = x;
        else y.parent.right = x;
        x.right = y;
        y.parent = x;
    }

    public String toStringInorder(RBTNode node) {
        if (node == nil) return "";
        return toStringInorder(node.left)
                + node.toString() + "\n"
                + toStringInorder(node.right);
    }

    public String toStringInorder(){
        return toStringInorder(root);
    }

    public String breadthOrder(){
        Queue<RBTNode> queue = new LinkedList<>();
        queue.add(root);
        String string = "";
        while(!queue.isEmpty()){
            RBTNode node = queue.poll();
            if (node.left != nil)
                queue.add(node.left);
            if (node.right != nil)
                queue.add(node.right);
//            if (node.color == 'R' && (node.left.color == 'R' || node.right.color == 'R'))
            string += node.toString() + " | " + node.color + "\n";
        }
        return string;
    }

    public RBTNode find(String word, RBTNode node){
        if (node == nil)
            return nil;
//        System.out.println("Sprawdzam: "+node.word+"  … wynik: "+node.word.equals(word));
        if (node.word.equals(word))
            return node;
        RBTNode newnode = find(word, node.left);
        if (newnode == nil)
            newnode = find(word, node.right);
        return newnode;
    }

    public void delete_word(String word){
        delete_node(find(word,root));
    }

    void deleteBLeft(RBTNode z){
        RBTNode brother = brother(z);
        RBTNode parent = z.parent;
        remove(z);
        if (z.parent.right.color == 'R'){
            RBTNode node = z.parent;
            rotateLeft(node);
            node.color = 'R';
            node.parent.color = 'B';
        } else if (brother.left.color == 'B' && brother.right.color == 'B'){
            brother.color = 'R';
            parent.color = 'B';
            checkColor(parent);
        } else if (brother.left.color == 'R' && brother.right.color == 'B'){
            brother.left.color = 'B';
            brother.color = 'R';
            rotateRight(brother);
        } else if (brother.left.color == 'B' && brother.right.color == 'R'){
            brother.color = parent.color;
            brother.right.color ='B';
            parent.color = 'B';
            rotateLeft(parent);
        }

    }

    void deleteBRight(RBTNode z){
        RBTNode brother = brother(z);
        RBTNode parent = z.parent;
        remove(z);
        if (brother.color == 'R'){
            rotateRight(parent);
            parent.color = 'R';
            parent.parent.color = 'B';
        } else if (brother.left.color == 'B' && brother.right.color == 'B'){
            brother.color = 'R';
            parent.color = 'B';
            checkColor(parent);
        } else if (brother.left.color == 'R' && brother.right.color == 'B'){
            brother.left.color = 'B';
            brother.color = 'R';
            rotateLeft(brother);
        } else if (brother.left.color == 'B' && brother.right.color == 'R'){
            brother.color = parent.color;
            brother.right.color ='B';
            parent.color = 'B';
            rotateRight(parent);
        }

    }

    void checkColor(RBTNode x){
        RBTNode brother = brother(x);
        x.color = 'B';
        brother.color = 'R';
        x.parent.color = 'B';
        if (x.parent.parent != nil)
            checkColor(x.parent);
    }

    RBTNode brother(RBTNode node){
        if(node.parent.left == node)
            return node.parent.right;
        return node.parent.left;
    }

    public void delete_node(RBTNode node){
        if (node.color == 'B'){
            if (node.parent.left == node)
                deleteBLeft(node);
            else deleteBRight(node);
        }
        else if (node.color == 'R'){
            remove(node);
        }
    }

    void remove (RBTNode node){
        if (node.left == nil && node.right == nil)
            if (node.parent.left == node){
                node.parent.left = nil;
            }
            else node.parent.right = nil;
        else if (node.left == nil) {
            node.right.parent = node.parent;
            if (node.parent == nil)
                root = node.right;
            else if (node.parent.left == node) {
                node.parent.left = node.right;
            }else node.parent.right = node.right;
        }
        else if (node.right == nil) {
            node.left.parent = node.parent;
            if (node.parent == nil)
                root = node.left;
            else if (node.parent.left == node) {
                node.parent.left = node.left;
            }else node.parent.right = node.left;
        } else {
            RBTNode successor;
            successor = min(node.right);
            node.word = successor.word;
            node.verses = successor.verses;
            node.color = successor.color;
            remove(successor);
        }
    }

    public RBTNode min(RBTNode node){
        if (node.left == nil)
            return node;
        return min(node.left);
    }


    public int maxHeight(RBTNode node){
        if (node.left == nil && node.right == nil ) return 0;
        return 1 + Math.max(maxHeight(node.left), maxHeight(node.right)) ;
    }

    public int minHeight(RBTNode node){
        if (node.left == nil && node.right == nil ) return 0;
        return 1 + Math.min(minHeight(node.left), minHeight(node.right)) ;
    }

    public boolean isCorrectTree(){
        return isHeightCorrect(root) && root.color == 'B' && correctColors(root) && correctBlack(root);
    }

    public boolean correctBlack(RBTNode node){
        if (node == nil) return true;
        boolean b = countBlackLeft(node.left) == countBlackRight(node.right);
        return b && correctBlack(node.left) && correctBlack(node.right);

    }

    public int countBlackLeft(RBTNode node){
        if (node == nil) return 0;
        int n = 0;
        if (node.color == 'B') n++;
        return n + countBlackLeft(node.left);
    }

    public int countBlackRight(RBTNode node){
        if (node == nil) return 0;
        int n = 0;
        if (node.color == 'B') n++;
        return n + countBlackRight(node.right);
    }

    public boolean correctColors(RBTNode node){
        if (node == nil) return true;
        boolean b;
        if (node.color == 'B')
            b = true;
        else b = (node.left.color == 'B' && node.right.color == 'B');
        return correctColors(node.left) && correctColors(node.right) && b;
    }


    public boolean isHeightCorrect(RBTNode node){
        if(node == nil)
            throw new NullPointerException("We can't say if nil is correct");
        return maxHeight(node) <= 2 * minHeight(node);
    }



}
