public class Main {

    static void whatis(MyQueue queue)throws EmptyException{
        System.out.println("isFull: "+queue.isFull());
        System.out.println("isEmpty: "+queue.isEmpty());
        System.out.println("first: "+ queue.first());
        System.out.println("———————————————————————");
    }

    public static void main(String[] args) throws FullException, EmptyException{
        MyQueue<Integer> queue = new QueueAL<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.enqueue(4);
        queue.dequeue();
        queue.enqueue(5);
        queue.dequeue();
        queue.dequeue();
        whatis(queue);
        queue.dequeue();
        queue.dequeue();
    }
}
