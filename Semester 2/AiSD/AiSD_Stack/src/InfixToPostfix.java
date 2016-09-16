import java.security.InvalidAlgorithmParameterException;

/**
 *
 * @author Grzegorz Chilczuk
 * Created on 18 mar 2016
 */
public class InfixToPostfix {
    Stack<Character> operators;
    String postfix;

    public String convert(String infix) throws InvalidAlgorithmParameterException{
        if (!nawiasy(infix)){
            System.out.println("Nieprawidłowe nawiasy w wyrażeniu!");
            return null;
        }
        operators = new Stos<>();
        postfix = "";
        for (int i = 0; i < infix.length(); i++){
            char ch = infix.charAt(i);
            if (Character.isDigit(ch) || ch == '.') postfix += ch;
            else if (ch == ',') postfix += '.';
            else if (isOperator(ch)) {
                proccessOperator(ch);
                if (precedence(ch) > 0) postfix += " ";
            }
            else if (ch == ' ');
            else throw new InvalidAlgorithmParameterException("Incorrect infix!");
        }
        while (!operators.isEmpty())postfix += " " + operators.pop();
        return postfix;
    }

    private void proccessOperator(char op){
        if(operators.isEmpty() || "{[(".indexOf(op) >= 0)
            operators.push(op);
        else {
            char topOp = operators.peek();
            if (precedence(op) > precedence(topOp))
                operators.push(op);
            else {
                while (!operators.isEmpty() && precedence(op) <= precedence(topOp)) {
                    operators.pop();
                    if ("{[(".indexOf(topOp) >= 0){ break;}
                    postfix += " " + topOp;
                    if (!operators.isEmpty())
                        topOp = operators.peek();
                }
                if ("}])".indexOf(op) < 0) operators.push(op);
            }
        }
    }
    private int precedence(char op){
        int prior = 0;
        switch (op){
            case '+':case '-': prior = 1; break;
            case '*':case '/':case '%': prior = 2; break;
            case '(':case ')':case '{':case '}':case '[':case ']':prior = -1;
        }
        return prior;
    }

    private boolean isOperator(Character ch){
        return "*/+-%(){}[]".indexOf(ch) >= 0;
    }

    /**
     *
     * @param infix
     * @return true jeżeli nawiasy prawidłowo
     */
    public boolean nawiasy(String infix){
        Stos<Character> nawiasy = new Stos<>();
        boolean ret = true;
        for (int i = 0; i < infix.length(); i++){
            char znak = infix.charAt(i);
            switch (znak){
                case '{':case '[':case '(':
                    nawiasy.push(znak);
                    break;
                case '}':
                    if (nawiasy.isEmpty() || nawiasy.pop() != '{') ret = false;
                    break;
                case ']':
                    if (nawiasy.isEmpty() || nawiasy.pop() != '[') ret = false;
                    break;
                case ')':
                    if (nawiasy.isEmpty() || nawiasy.pop() != '(') ret = false;
                    break;
            }
        }
        return ret;
    }

}
