import java.util.Random;

/**
 * Main class
 *
 * @author Grzegorz Chilczuk
 */
public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Card> talia = new SinglyLinkedList<>();

        Random generator = new Random();
        int value = generator.nextInt(15);
        int color = generator.nextInt(4);
        while (value>0){
            Card card = new Card(value, color);
            talia.sortedAdd(card);
            value = generator.nextInt(15);
            color = generator.nextInt(4);
        }

        System.out.println(talia);
        System.out.println("Lista ma "+talia.size()+" elementów.");
        Iterator it = talia.iterator();


        System.out.println("Wg koloru:");
        for (it.first(); !it.isDone(); it.next())
            if (((Card)it.current()).getColor() == 1 && ((Card)it.current()).getValue() != 14)
                System.out.println(it.current());

        System.out.println("————————");
        System.out.println("Wg wartości");

        for (it.first(); !it.isDone(); it.next())
            if (((Card)it.current()).getValue() == 14)
                System.out.println(it.current());

        //System.out.println("Odkrytych: "+talia.howMuchVisible());
        //System.out.println("Zakrytych: "+talia.howMuchHidden());
        System.out.println(talia);
        talia.howMuchCards();
        talia.cutHidden();
        System.out.println(talia);
    }
}
