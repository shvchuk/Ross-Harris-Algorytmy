package kolejki;

public class ListFifoQueue implements Queue {

    // lista na bazie której implementowana jest kolejka
    private final List _list;

    // konstruktor tworzący kolejkę na bazie wskazanej listy

    public ListFifoQueue(List list){
        assert list != null: "nie określono listy";
        _list = list;
    }

    // domyślny konstruktor tworzący kolejkę na bazie utworzonej ad hoc listy wiązanej

    public ListFifoQueue(){
        this(new LinkedList());
    }

    @Override
    public void enqueue(Object value) {

    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
