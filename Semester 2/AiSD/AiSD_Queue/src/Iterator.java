/**
 * Iterator interface
 *
 * @author Grzegorz Chilczuk
 */
public interface Iterator<E> {

    /**
     * Moves to next object ini iterator
     */
    void next();

    /**
     * Sets first object of the iterator
     */
    void first();

    /**
     * Tells if iteration is completed
     * @return true if iteratrion is complited (or eventually hasn't started yet); false if iteration is in progress
     */
    boolean isDone();

    /**
     * Return current iterated object
     * @return current iterated object
     */
    E current();
}
