import java.io.FileNotFoundException;

/**
 * @author Grzegorz Chilczuk
 *         Created on 29 kwi 2016
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        RBT rbt;
        BST bst;

        WordCounter wordCounter = new WordCounter();
        bst = wordCounter.buildBSTTree("tekst.txt");
        rbt = wordCounter.buildRBTTree("tekst.txt");
        System.out.println(rbt.toStringInorder());
        System.out.println("——————————");
        rbt.delete_word("najczystszym");
        System.out.println(rbt.breadthOrder());

       /* rbt = new RBT();
        rbt.add_word("c",0);
        rbt.add_word("b",0);
        rbt.add_word("a",0);
        rbt.add_word("d",0);
        System.out.println(rbt.breadthOrder());*/

        System.out.println(rbt.correctBlack(rbt.root));

         /*
        ———BIG TEST———
        RBT rbt = new RBT();
        rbt.add_word("k", 0);
        rbt.add_word("b", 0);
        rbt.add_word("a", 0);
        rbt.add_word("g", 0);
        rbt.add_word("e", 0);
        rbt.add_word("h", 0);
        rbt.add_word("n", 0);
        rbt.add_word("o", 0);

        RBTNode nil = rbt.nil;
        RBTNode k = new RBTNode("k", 0, 'B', nil);
        RBTNode b = new RBTNode("b", 0, 'R', k);
        RBTNode n = new RBTNode("n", 0, 'B', k);
        RBTNode a = new RBTNode("a", 0, 'B', b);
        RBTNode g = new RBTNode("g", 0, 'B', b);
        RBTNode o = new RBTNode("o", 0, 'R', n);
        RBTNode e = new RBTNode("e", 0, 'R', g);
        RBTNode h = new RBTNode("h", 0, 'R', g);
        k.parent = nil;
        k.left = b; k.right = n;
        b.parent = k; b.left = a; b.right = g;
        n.parent = k; n.left = nil; n.right = o;
        o.parent = n; o.left = o.right = nil;
        a.parent = b; a.left = a.right = nil;
        g.parent = b; g.left = e; g.right = h;
        e.parent = h.parent = g;
        e.left = e.right = h.left = h.right = nil;
        rbt.root = k;
        System.out.println(rbt.root);
        System.out.println(k.left + " || " + k.right);
        System.out.println(k.left.left + " || " + k.left.right + " || " + k.right.right);
        System.out.println(k.left.right.left + " || " + k.left.right.right);
        System.out.println("——————————————————————————————————————— THERE");
        rbt.add_word("d", 0);

        System.out.println("W poprzek:");
        System.out.println(rbt.BreadthOrder());*/
    }
}
