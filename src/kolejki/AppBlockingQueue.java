package kolejki;

import listy.LinkedList;
import listy.List;

public class AppBlockingQueue {

    public static void main(String[] args) {
        ListFifoQueue queue = new ListFifoQueue();
        BlockingQueue kolejka = new BlockingQueue(queue, 5);

        kolejka.enqueue(10);
        kolejka.enqueue(20);
        kolejka.enqueue(30);
        kolejka.dequeue();
        kolejka.enqueue(11);
        kolejka.enqueue(12);
        kolejka.dequeue();
        kolejka.clear();

    }

}
