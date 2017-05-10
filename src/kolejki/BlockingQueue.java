package kolejki;

public class BlockingQueue implements Queue {

    private final Object _mutex = new Object();

    private final Queue _queue;

    private final int _maxSize;

    public BlockingQueue(Queue queue, int maxSize){
        assert queue != null : "nie określono kolejki";
        assert maxSize > 0 : "maksymalny rozmiar musi być dodatni";

        _queue = queue;
        _maxSize = maxSize;
    }

    public BlockingQueue(Queue queue){
        this(queue, Integer.MAX_VALUE);
    }

    @Override
    public void enqueue(Object value) {
        synchronized (_mutex){
            while(size() == _maxSize){
                System.out.println("Bufor jest pełny.");
                waitForNotification();
            }
            _queue.enqueue(value);
            System.out.println("Dodano do kolejki element: " + value.toString());
            _mutex.notifyAll();
        }
    }

    private void waitForNotification() {
        try{
            _mutex.wait();
        } catch (InterruptedException e){
            // ignoruj wątek
        }
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        synchronized (_mutex){
            while (isEmpty()){
                System.out.println("Bufor jest pusty.");
                waitForNotification();
            }
            Object value = _queue.dequeue();
            _mutex.notifyAll();
            System.out.println("Pobrano z kolejki element: " + value.toString());
            return value;
        }
    }

    @Override
    public void clear() {
        synchronized (_mutex){
            _queue.clear();
            _mutex.notifyAll();
        }
    }

    @Override
    public int size() {
        synchronized (_mutex){
            return _queue.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (_mutex){
            return _queue.isEmpty();
        }
    }
}
