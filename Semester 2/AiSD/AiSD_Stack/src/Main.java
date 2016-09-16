import java.security.InvalidAlgorithmParameterException;
import java.util.Scanner;

/**
 * @author Grzegorz Chilczuk
 *         Created on 18 mar 2016
 */
public class Main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException {
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        RPN rpn = new RPN();
        String post;
        post = infixToPostfix.convert("77%5");
        System.out.println("Działanie: "+post);
        System.out.println("Wynik: "+rpn.evaluate(post));
        System.out.println("————————");
        post = infixToPostfix.convert("(2*2)*2");
        System.out.println("Działanie: "+post);
        System.out.println("Wynik: "+rpn.evaluate(post));
        System.out.println("————————");
        post = infixToPostfix.convert("(3/2)+5*2");
        System.out.println("Działanie: "+post);
        System.out.println("Wynik: "+rpn.evaluate(post));
        System.out.println("————————");

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Podaj wyrażenie");
            String infix = scanner.nextLine();
            if (!infixToPostfix.nawiasy(infix)){
                System.out.println("Nieprawidłowe nawiasy w wyrażeniu!");
                continue;
            }
            post = infixToPostfix.convert(infix);
            System.out.println("Działanie: "+post);
            System.out.println("Wynik: "+rpn.evaluate(post));
            System.out.println("————————");
        }

    }
}
