/**
 * Interface of Stack
 *
 * @author Grzegorz Chilczuk
 */
public interface Stack<E> {

    /**
     * Pushes an item onto the top of stack.
     * @param value value to push
     */
    E push(E value);


    /**
     * Removes the object at the top of stack and returns that object
     * @return object from top of stack
     * @throws EmptyStackException
     */
    E pop() throws EmptyStackException;

    /**
     * Looks at the object at the top of stack without removing it from the stack.
     * @return object at the top of stack
     * @throws EmptyStackException
     */
    E peek() throws EmptyStackException;

    /**
     * Returns true if this stack is empty
     * @return true if this stack is empty; else: false
     */
    boolean isEmpty();

    



}