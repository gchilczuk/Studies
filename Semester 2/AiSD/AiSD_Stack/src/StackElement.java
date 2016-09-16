/**
 * Element of Stack
 *
 * @author Grzegorz Chilczuk
 */
public class StackElement<T> {
    /**
     * Value of Element
     */
    private T value;

    /**
     * Next StackElement in Stack
     */
    private StackElement<T> next;

    /**
     * Constructor
     * @param value value of element
     */
    public StackElement(T value) {
        this.value = value;
    }

    /**
     * Set next element in stack
     * @param next next element in stack
     */
    public void setNext(StackElement<T> next) {
        this.next = next;
    }

    /**
     * Return next element in stack
     * @return next element in stack
     */
    public StackElement<T> getNext() {
        return next;
    }

    /**
     * Set value of element
     * @param value value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Return value of element
     * @return value of element
     */
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StackElement<T> that = (StackElement<T>) o;

        return value.equals(that.value) && next.getValue().equals(that.getNext().getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}