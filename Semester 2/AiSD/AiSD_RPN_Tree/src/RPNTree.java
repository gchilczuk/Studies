import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Grzegorz Chilczuk
 *         Created on 23 kwi 2016
 */
public class RPNTree {
    Node _root;

    RPNTree(String postfix){
        Stos<Node> stos = new Stos<>();
        for (int i = 0; i < postfix.length(); i++){
            Character character = postfix.charAt(i);
            if (character == ' ') continue;
            Node node = new Node(character);
            if (isOperator(character)){
                node.rightBranch = stos.pop();
                node.leftBranch = stos.pop();
            }
            stos.push(node);
        }
        _root = stos.pop();
    }

    String horizontalView(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(_root);
        String string = "";
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if (node.leftBranch != null)
                queue.add(node.leftBranch);
            if (node.rightBranch != null)
                queue.add(node.rightBranch);
            string += node.value;
        }
        return string;

    }


    public int height(){
        return height(_root);
    }

    public int height(Node node){
        if (node.leftBranch == null && node.rightBranch == null ) return 0;
        return 1 + Math.max(height(node.leftBranch), height(node.rightBranch)) ;
    }

    public int calculate(){
        return calculate(_root);
    }

    private int calculate(Node node){
        if (!isOperator(node.value)) return Integer.parseInt(node.value.toString());
        return useOperator(calculate(node.leftBranch),
                calculate(node.rightBranch), node.value);
    }

    public String postorder(){
        return postorder(_root);
    }

    public String postorder(Node node){
        StringBuffer postfix = new StringBuffer();
        if (node.leftBranch != null)
            postfix.append(postorder(node.leftBranch).concat(" "));
        if (node.rightBranch != null)
            postfix.append(postorder(node.rightBranch).concat(" "));
        postfix.append(node.value);
        return postfix.toString();
    }

    public String inorder(){
        return inorder(_root);
    }

    public String inorder(Node node){
        StringBuffer infix = new StringBuffer();
        if (node.leftBranch != null)
            infix.append("("+inorder(node.leftBranch).concat(")"));
        infix.append(node.value);
        if (node.rightBranch != null)
            infix.append("("+inorder(node.rightBranch).concat(")"));
        return infix.toString();
    }

    public int countLeaves(){
        return countLeaves(_root);
    }

    public int countLeaves(Node node){
        if (node.rightBranch == null || node.leftBranch == null) return 1;
        return countLeaves(node.rightBranch) + countLeaves(node.leftBranch);
    }

    public int countNodes(){
        return countNodes(_root);
    }
    public int countNodes(Node node){
        if (node == null) return 0;
        return 1 + countNodes(node.rightBranch) + countNodes(node.leftBranch);
    }

    private int useOperator(int a, int b, Character ch) {
        switch (ch) {
            case '*':
                return a * b;
            case '/':
                try {
                    return a / b;
                } catch (ArithmeticException e) {
                    System.out.println("Dzielenie przez zero");
                }
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '%':
                return a % b;
            default:
                return 0;
        }
    }

    private boolean isOperator(Character ch){
        return "*/+-%".indexOf(ch) >= 0;
    }




}
