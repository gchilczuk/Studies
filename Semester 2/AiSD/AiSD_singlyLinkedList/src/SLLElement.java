/**
 * SLLElement of SinglyLinkedList
 *
 * @author Grzegorz Chilczuk
 */
public class SLLElement<T extends Comparable> implements Comparable<SLLElement> {
    /**
     * Value of Element
     */
    private T value;

    /**
     * Next SLLElement in List
     */
    private SLLElement<T> next;

    /**
     * Constructor
     * @param value value of element
     */
    public SLLElement(T value) {
        this.value = value;
    }

    /**
     * Set next element in list
     * @param next next element in list
     */
    public void setNext(SLLElement<T> next) {
        this.next = next;
    }

    /**
     * Return next element in List
     * @return next element in List
     */
    public SLLElement<T> getNext() {
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
    public int compareTo(SLLElement sllElement) {
        return this.value.compareTo(sllElement.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SLLElement<T> that = (SLLElement<T>) o;

        return value.equals(that.value) && next.getValue().equals(that.getNext().getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
