import java.util.ArrayList;

public class QueueAL<E> implements MyQueue<E>{
    private ArrayList<E> queue;
    private int f, r, size;

    public QueueAL(int size){
        this.size = size+1;
        f = r = 0;
        queue = new ArrayList<>();
        for (int i = 0; i < this.size; i++)
            queue.add(null);
    }

    @Override
    public void enqueue(E x) throws FullException {
        if (isFull()) throw new FullException();
        queue.set(r, x);
        r = (r + 1) % size;
    }

    @Override
    public void dequeue() {
        if (!isEmpty()) f = (f + 1) % size;
    }

    @Override
    public E first() throws EmptyException {
        if (isEmpty()) throw new EmptyException("Queue is empty!");
        return queue.get(f);
    }

    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public boolean isFull() {
        return (r + 1) % size == f;
    }

}
