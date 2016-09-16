/**
 * Stack implementation
 *
 * @author Grzegorz Chilczuk
 */
public class Stos<E> implements Stack<E> {

    private final StackElement<E> _top = new StackElement<>(null);

    public Stos() {
    }

    @Override
    public E push(E value) {
        StackElement<E> newEl = new StackElement<>(value);
        newEl.setNext(_top.getNext());
        _top.setNext(newEl);
        return value;
    }

    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException("Stack is empty!");
        StackElement<E> oldEl = _top.getNext();
        _top.setNext(oldEl.getNext());
        return oldEl.getValue();
    }

    @Override
    public E peek() throws EmptyStackException {
        return _top.getNext().getValue();
    }

    @Override
    public boolean isEmpty() {
        return _top.getNext() == null;
    }
}
