/**
 * Created on 12.03.16.
 *
 * Queue implementation using SinglyLinkedList
 *
 * @author Grzegorz Chilczuk
 */
public class Kolejka<E> implements Queue<E>{
    private final SinglyLinkedList<E> _sll;
    private Integer _maxSize = null;

    /**
     * Simple constructor,
     * create new empty queue
     */
    public Kolejka(){
        _sll = new SinglyLinkedList<>();
    }

    public Kolejka(int s){
        _sll = new SinglyLinkedList<>();
        _maxSize = s;
    }

    /**
     * Make new queue base on SinglyLinkedList
     * (converts list to queue)
     * @param sll list to convert
     */
    public Kolejka(SinglyLinkedList<E> sll){
        _sll = sll;
    }

    @Override
    public void insert(E element) {
        if (!isFull()) _sll.add(element);
    }

    @Override
    public E remove() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Kolejka jest pusta");
        return _sll.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return _sll.isEmpty();
    }

    @Override
    public boolean isFull() {
        return _maxSize != null && size() >= _maxSize;
    }

    /**
     * Returns number of elements in Queue
     * @return number of elements in Queue
     */
    public int size(){
        return _sll.size();
    }

    /**
     * Clear queue
     */
    public void clear(){
        _sll.clear();
    }

    @Override
    public int hashCode() {
        return _sll.hashCode()*3 - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return _sll.equals(((Kolejka)o).extract_sll());
    }

    @Override
    public String toString() {
        return _sll.toString();
    }

    /**
     * Extract list from queue
     * @return list from queue
     */
    public SinglyLinkedList<E> extract_sll() {
        return _sll;
    }

    /**
     * Set mac size of queue
     * @param _maxSize
     */
    public void set_maxSize(Integer _maxSize) {
        this._maxSize = _maxSize;
    }
}
