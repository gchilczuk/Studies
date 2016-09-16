/**
 *
 *
 */
public class RPN {
    Stos<Integer> stos = new Stos<>();

    public int evaluate(String postfix){
        int i = 0;
        try{
        while (i < postfix.length()){
            String part = "";
            Character ch = postfix.charAt(i);
            if (isOperator(ch)) useOperator(ch);
            else if (Character.isDigit(ch) ) {
                String number = "";
                while ((Character.isDigit(ch) || ch == '.' || ch == ',')  && i < postfix.length()) {
                    if (ch == ',') number += '.';
                    else number += ch;
                    i++;
                    ch = postfix.charAt(i);
                }
                stos.push(Integer.parseInt(number));
            }
            i++;

        }
        return stos.pop();
        }
        catch (ArithmeticException e){
            System.out.println("Niepoprawne wyrażenie! Dzielenie przez zero jest niedozwolone!");
            return 0;
        }
    }

    private void useOperator(Character ch){
        int a = stos.pop();
        int b = 0;
        try{
        b = stos.pop();}
        catch (EmptyStackException e){
            if (ch == '-') b=0;
        }
        switch (ch) {
            case '*':
                stos.push(b * a);
                break;
            case '/':
                stos.push(b / a);
                break;
            case '-':
                stos.push(b - a);
                break;
            case '+':
                stos.push(b + a);
                break;
            case '%':
                stos.push(b % a);
        }
    }

    private boolean isOperator(Character ch){
        return "*/+-%".indexOf(ch) >= 0;
    }
}
