import java.security.InvalidParameterException;

/**
 * Represents playing card
 *
 * @author Grzegorz Chilczuk
 */
public class Card implements Comparable<Card> {
    /**
     * Value of card  (1-13)
     */
    private int value;

    /**
     * Color of card (0-3)
     */
    private int color;

    /**
     * True if we can see a card
     */
    private boolean marker;

    /**
     * Constructor
     * @param value value of card
     * @param color color of card
     */
    public Card(int value, int color){
        if (value < 1 || value > 14 || color < 0 || color > 3)
            throw new InvalidParameterException("Invalid value or color");
        this.marker = (!(value == 14));
        this.value = value;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return (value != card.value) && (color == card.color);
    }

    @Override
    public int hashCode() {
        return value + color*13;
    }

    @Override
    public int compareTo(Card card) {
        int result = this.value - card.value;
        if (result == 0) result = this.color - card.color;
        return result;
    }

    @Override
    public String toString() {
        if (!marker) return "()";
        String[] cards = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Walet", "Dama", "Król"};
        String[] colors = {"kier", "karo", "trefl", "pik"};
        return cards[value-1]+" "+colors[color];
    }

    /**
     * Return color of card
     * @return color of card
     */
    public int getColor() {
        return color;
    }

    /**
     * Return value of card
     * @return value of card
     */
    public int getValue() {
        return value;
    }

    /**
     * Return value of marker
     * @return value of marker
     */
    public boolean isMarker() {
        return marker;
    }
}
