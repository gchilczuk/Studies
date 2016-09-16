
/**
 * Singly Linked List implementation
 *
 * @author Grzegorz Chiliczuk
 */
public class SinglyLinkedList<E extends Comparable> extends AbstractList<E> {

    /**
     * HEAD of List
     */
    private final SLLElement<E> _first = new SLLElement<>(null);

    /**
     * Size of List
     */
    private int _size;

    public SinglyLinkedList() {
        clear();
    }

    @Override
    public void add(E value) {
        insert(size(), value);
    }

    @Override
    public void insert(int index, E value) throws IndexOutOfBoundsException {
        if (index < 0 || index >_size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        SLLElement<E> element = new SLLElement<>(value);
        if (index == 0){
            element.setNext(_first.getNext());
            _first.setNext(element);
        } else if (index == _size){
            element.setNext(null);
            getElement(index-1).setNext(element);
        } else {
            element.setNext(getElement(index));
            getElement(index-1).setNext(element);
        }
        _size++;
    }

    public void insertAfter(SLLElement<E> element, E value){
        SLLElement<E> newElement = new SLLElement<>(value);
        newElement.setNext(element.getNext());
        element.setNext(newElement);
    }

    public void insertBefore(SLLElement<E> element, E value){
        SLLElement<E> newElement = new SLLElement<>(value);
        newElement.setNext(element);
        SLLElement current = _first.getNext();
        //if (current.equals())
        //while ()
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        try{
            checkIndex(index);
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        SLLElement<E> removedElement = getElement(index);
        if (index == 0) {
            _first.setNext(removedElement.getNext());
        }
        else {
            getElement(index-1).setNext(removedElement.getNext());
        }
        _size--;
        return removedElement.getValue();
    }

    @Override
    public void clear() {
        _first.setNext(null);
        _size = 0;
    }

    @Override
    public E set(int index, E value) throws IndexOutOfBoundsException {
        checkIndex(index);
        SLLElement<E> sllElement = getElement(index);
        E oldValue = sllElement.getValue();
        sllElement.setValue(value);
        return oldValue;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        return getElement(index).getValue();
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        for (   SLLElement<E> element = _first.getNext();
                index < _size && !value.equals(element.getValue());
                index++, element = element.getNext()
                );

        return index == _size ? -1 : index;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new SLLIterator();
    }

    /**
     * Returns element on specified position
     * @param index specified index
     * @return element on specified index
     */
    public SLLElement<E> getElement(int index) {
        checkIndex(index);
        SLLElement<E> sllElement = _first.getNext();
        for (; index>0; index--) sllElement = sllElement.getNext();
        return sllElement;
    }

    /**
     * Test if index is valid
     * @param index index to check
     * @throws IndexOutOfBoundsException when index is invalid
     */
    public void checkIndex(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid Index");
    }

    public final class SLLIterator implements Iterator<E>{

        SLLElement<E> _current = _first;

        //public SLLIterator(){}

        @Override
        public void next() {
            _current = _current.getNext();
        }

        @Override
        public void first() {
            _current = _first.getNext();
        }

        @Override
        public boolean isDone() {
            return _current == null || _current.equals(_first);
        }

        @Override
        public E current() {
            return _current.getValue();
        }
    }

    /**
     * Add value in ascending order
     * @param value value to add
     */
    public void sortedAdd(E value){
        insert(findIndexOfFirstBigger(value),value);
    }

    /**
     * Return index of first element which is bigger than specified
     * or size of list if there is no bigger
     * @param value value to comapareTo
     * @return index of first element which is bigger than specified
     * or size of list if there is no bigger
     */
    public int findIndexOfFirstBigger(E value){
        int index = 0;
        for (   SLLElement<E> element = _first.getNext();
                index < _size && value.compareTo(element.getValue()) > 0;
                index++, element = element.getNext()
                );
        return index;
    }

    public int howMuchVisible(){
        SLLElement<E> element = _first.getNext();
        int ile = 0;
        int odk = 0;
        while (ile < _size){
            if ( ((Card)element.getValue()).getValue() != 14) {
                odk++;
                //System.out.println(((Card)element.getValue()).getValue() == 14);
            }
            ile++;
            element = element.getNext();
        }
        return odk;
    }

    public int howMuchHidden(){
        return _size - howMuchVisible();
    }
    public void howMuchCards(){
        System.out.println("Jest "+howMuchVisible()+" kart widocznych oraz "+howMuchHidden()+" kart niewidocznych");
    }

    public void cutHidden(){
        if (howMuchHidden()>0) {
            _size -= howMuchHidden();
            SLLElement<E> element = _first.getNext();
            while (((Card)element.getNext().getValue()).isMarker())
                element = element.getNext();
            if (!(((Card)element.getValue()).getValue() == 14 )) element.setNext(null);
        }
    }


}
