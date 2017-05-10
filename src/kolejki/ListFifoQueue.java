package kolejki;

import listy.LinkedList;
import listy.List;

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
        _list.add(value);

    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException();
        }

        return _list.delete(0);
    }

    @Override
    public void clear() {
        _list.clear();
    }

    @Override
    public int size() {
        return _list.size();
    }

    @Override
    public boolean isEmpty() {
        return _list.isEmpty();
    }
}
