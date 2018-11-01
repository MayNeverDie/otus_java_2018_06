import java.util.*;

public class MyArrayList<T> implements List {
    private int capacity;
    private Object[] data;

    public MyArrayList(){
        this.capacity = 0;
        this.data = new Object[10];

    }

    public MyArrayList(int capacity){
        this.capacity = 0;
        this.data = new Object[capacity];
    }

    public int size() {
        return capacity;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public boolean contains(Object o) {
        throw new RuntimeException();
    }

    public Iterator iterator() {
        throw new RuntimeException();
    }

    public Object[] toArray() {
        return data;
    }

    public boolean add(Object o) {
        data[capacity] = o;
        capacity++;
        return(true);
    }

    public boolean remove(Object o) {
        for(Object element:data){
            if (element.equals(o)){
                element = null;
                capacity--;
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Collection c) {
        throw new RuntimeException();
    }

    public boolean addAll(int index, Collection c) {
        throw new RuntimeException();
    }

    public void clear() {
        for(Object element:data){
            element = null;
        }
    }

    public Object get(int index) {
        return data[index];
        // throw new RuntimeException();
    }

    public Object set(int index, Object element) {
        Object oldElement = data[index];
        data[index] = element;
        return oldElement;
        //throw new RuntimeException();
    }

    public void add(int index, Object element) {
        throw new RuntimeException();
    }

    public Object remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        final Object[] massive = data;
        Object tempObject = massive[index];
        final int newcapacity;
        if ((newcapacity = capacity - 1) > index){
            System.arraycopy(massive,index+1,massive,index,newcapacity-1);
        }
        massive[capacity = newcapacity] = null;
        return tempObject;
    }

    public int indexOf(Object o) {
        throw new RuntimeException();
    }

    public int lastIndexOf(Object o) {
        throw new RuntimeException();
    }

    public ListIterator listIterator() {
        return new MyListIterator();
    }

    public ListIterator listIterator(int index) {
        /*ListIterator listIterator = this.listIterator();
        return listIterator;*/
        throw new RuntimeException();
    }

    public List subList(int fromIndex, int toIndex) {
        throw new RuntimeException();
    }

    public boolean retainAll(Collection c) {
        throw new RuntimeException();
    }

    public boolean removeAll(Collection c) {
        throw new RuntimeException();
    }

    public boolean containsAll(Collection c) {
        throw new RuntimeException();
    }

    public Object[] toArray(Object[] a) {
        throw new RuntimeException();
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "capacity=" + capacity +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MyArrayList<?> that = (MyArrayList<?>) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(capacity, that.capacity)
                .append(data, that.data)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(capacity)
                .append(data)
                .toHashCode();
    }


    private class MyListIterator implements ListIterator<T>{
        int cursor;
        int lastReturned = -1;

        MyListIterator(){
            cursor = 0;
        }

        MyListIterator(int index){
            cursor = index;
        }

        public boolean hasNext() {
            return cursor != capacity;
        }

        public T next() {
            if (cursor>=capacity){
                throw new NoSuchElementException();
            }else {
                int i = cursor;
                cursor = i + 1;
                Object[] currentData = MyArrayList.this.data;
                return (T) currentData[lastReturned = i];
            }
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public T previous() {
            if (cursor<=0){
                throw new NoSuchElementException();
            }else {
                int i = cursor-1;
                cursor = i - 1;
                Object[] currentData = MyArrayList.this.data;
                return (T) currentData[lastReturned = i];
            }
        }

        public int nextIndex() {
            if (cursor==capacity){
                return MyArrayList.this.data.length;
            }else {
                return cursor;
            }
        }

        public int previousIndex() {
            if (cursor==0){
                return -1;
            }else {
                return cursor--;
            }
        }

        public void remove() {
            if (lastReturned <0){
                throw new IllegalStateException();
            }else {
                MyArrayList.this.remove(lastReturned);
                cursor = lastReturned;
                lastReturned = -1;
            }
        }

        public void set(T t) {
            if (lastReturned <0){
                throw new IllegalStateException();
            }else {
                MyArrayList.this.set(lastReturned,t);
            }
        }

        public void add(T t) {
            if (lastReturned <0){
                throw new IllegalStateException();
            }else {
                MyArrayList.this.remove(lastReturned);
                cursor = lastReturned;
                lastReturned = -1;
            }
        }
    }
}