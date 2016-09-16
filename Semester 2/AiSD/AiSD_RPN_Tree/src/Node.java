/**
 * @author Grzegorz Chilczuk
 *         Created on 23 kwi 2016
 */
public class Node  {
    Character value;

    Node leftBranch;
    Node rightBranch;

    Node parent;

    public Node(Character value){
        this.value = value;
        leftBranch = rightBranch = parent = null;
    }
}
