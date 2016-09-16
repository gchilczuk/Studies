import java.security.InvalidAlgorithmParameterException;
import java.util.Scanner;

/**
 * @author Grzegorz Chilczuk
 *         Created on 23 kwi 2016
 */
public class Main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException{
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Wprowadź wyrażenie: ");
        String string = scanner.nextLine();
        while (!string.equalsIgnoreCase("exit")) {
            String a = infixToPostfix.convert(string);
            RPNTree rpnTree = new RPNTree(a);
            System.out.printf("%-10s%s\n", "Infix: ", rpnTree.inorder());
            System.out.printf("%-10s%s\n", "Postfix: ", rpnTree.postorder());
            System.out.printf("%-10s%s\n", "Horyzontalnie: ", rpnTree.horizontalView());
            System.out.printf("%-10s%d\n", "Wartość: ", rpnTree.calculate());
            System.out.printf("%-10s%d\n", "Liście: ", rpnTree.countLeaves());
            System.out.printf("%-10s%d\n", "Węzły: ", rpnTree.countNodes());
            System.out.printf("%-10s%d\n", "Wysokość: ", rpnTree.height());
            System.out.println("———————————");
            System.out.printf("Wprowadź wyrażenie: ");
            string = scanner.nextLine();
        }
    }
}
