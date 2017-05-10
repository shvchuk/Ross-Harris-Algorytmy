package listy;

import iteratory.Iterator;
import iteratory.IteratorOutOfBoundsException;

public class LinkedList implements List {

    private final Element _headAndTail = new Element(null);
    private int _size;

    public LinkedList(){
        clear();
    }

    private static final class Element{
        private Object _value;
        private Element _previous;
        private Element _next;

        public Element(Object value){
            setValue(value);
        }

        public void setValue(Object value){
            _value = value;
        }

        public Object getValue(){
            return _value;
        }

        public Element getPrevious(){
            return _previous;
        }

        public void setPrevious(Element previous){
            assert previous != null : "wskaźnik na element poprzedni nie może być pusty";
            _previous = previous;
        }

        public Element getNext(){
            return _next;
        }

        public void setNext(Element next){
            assert next != null : "wskaźnik na element następny nie może być pusty";

            _next = next;
        }

        public void attachBefore(Element next){
            assert next != null : "wskaźnik na element następny nie może być pusty";

            Element previous = next.getPrevious();
            setNext(next);
            setPrevious(previous);

            next.setPrevious(this);
            previous.setNext(this);
        }

        public void detach(){
            _previous.setNext(_next);
            _next.setPrevious(_previous);
        }
    }

    private final class ValueIterator implements Iterator{
        private Element _current = _headAndTail;

        public Iterator iterator(){
            return new ValueIterator();
        }

        @Override
        public void first() {
            _current = _headAndTail.getNext();

        }

        @Override
        public void last() {
            _current = _headAndTail.getPrevious();

        }

        @Override
        public boolean isDone() {
            return _current == _headAndTail;
        }

        @Override
        public void next() {
            _current = _current.getNext();

        }

        @Override
        public void previous() {
            _current = _current.getPrevious();

        }

        @Override
        public Object current() throws IteratorOutOfBoundsException {
            if(isDone()){
                throw new IteratorOutOfBoundsException();
            }

            return _current.getValue();
        }
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void insert(int index, Object value) throws IndexOutOfBoundsException {
        assert value != null : "nie można wstawiać pustych wartości";

        if(index < 0 || index > _size){
            throw new IndexOutOfBoundsException();
        }

        Element element = new Element(value);
        element.attachBefore(getElement(index));
        ++_size;
    }

    private Element getElement(int index){
        Element element = _headAndTail.getNext();
        for(int i = index; i > 0; --i){
            element = element.getNext();
        }

        return element;
    }

    @Override
    public void add(Object value) {
        insert(size(), value);
    }

    @Override
    public Object delete(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        Element element = getElement(index);
        element.detach();
        --_size;
        return element.getValue();
    }

    @Override
    public boolean delete(Object value) {
        assert value != null : "w liście nie ma wartości pustych";

        for(Element e = _headAndTail.getNext();
                e != _headAndTail;
                e = e.getNext()){
            if(value.equals(e.getValue())){
                e.detach();
                --_size;
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        _headAndTail.setPrevious((_headAndTail));
        _headAndTail.setNext(_headAndTail);
        _size = 0;

    }

    @Override
    public Object set(int index, Object value) throws IndexOutOfBoundsException {
        assert value != null : "Nie można umieszczać w liście wartości pustych";
        checkOutOfBounds(index);
        Element element = getElement(index);
        Object oldValue = element.getValue();
        element.setValue(value);
        return oldValue;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        return getElement(index).getValue();
    }

    private void checkOutOfBounds(int index) {
        if(isOutOfBounds(index)){
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size();
    }

    @Override
    public int indexOf(Object value) {
        assert value != null : "nie ma pustych wartości";

        int index = 0;

        for(Element e = _headAndTail.getNext();
                e != _headAndTail;
                e = e.getNext()){
            if(value.equals(e.getValue())){
                return index;
            }

            ++index;
        }

        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
