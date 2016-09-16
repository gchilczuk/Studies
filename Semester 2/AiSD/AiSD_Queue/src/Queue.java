/**
 * Created on 12.03.16.
 *
 * Queue interface
 *
 * @author Grzegorz Chilczuk
 */
public interface Queue<E> {

    /**
     * Add an element at the end of the queue
     * @param element element to insert
     */
    void insert(E element);

    /**
     * Pops element from queue
     * @return element from head of queue
     */
    E remove() throws EmptyQueueException;

    /**
     * Tells if queue is empty
     * @return true if queue is empty; else: true
     */
    boolean isEmpty();

    /**
     * Tells if queue is full
     * @return true if queue is full; else: false
     */
    boolean isFull();

}
