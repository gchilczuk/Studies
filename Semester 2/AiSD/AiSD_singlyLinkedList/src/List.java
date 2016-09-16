/**
 * Interface of List
 *
 * @author Grzegorz Chilczuk
 */
public interface List<E> {

    /**
     * Add value on the end of the List
     * @param value value to add
     */
    void add(E value);

    /**
     * Insert value on specified index in List
     * @param index index where to insert
     * @param value value to insert
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    void insert(int index, E value) throws IndexOutOfBoundsException;

    /**
     * Removes the first occurrence of the specified element from this list, if it is present
     * @param value value to remove
     * @return true if this list contained the specified element
     */
    boolean remove(E value);

    /**
     * Removes the element at the specified position in this list
     * @param index index of the element to be removed
     * @return Removed element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E remove(int index) throws IndexOutOfBoundsException;

    /**
     * Removes all of the elements from this list
     */
    void clear();

    /**
     * Replaces the element at the specified position in this list with the specified element
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E set(int index, E element) throws IndexOutOfBoundsException;

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E get(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param value element to search for
     * @return the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element
     */
    int indexOf(E value);

    /**
     * Returns true if this list contains the specified element
     * @param value element which we're looking for
     * @return true if this list contains the specified element
     */
    boolean contains(E value);

    /**
     * Returns number of elements in List
     * @return number of elements in List
     */
    int size();

    /**
     * Returns true if this list contains no elements
     * @return true if this list contains no elements
     */
    boolean isEmpty();

    /**
     * List Iterator
     * @return List Iterator
     */
    Iterator<E> iterator();



}
