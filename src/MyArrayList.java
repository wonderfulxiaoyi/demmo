import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyArrayList<E> {
        private E[] data;

        private int size;

        private static final int INIT_CAP = 1;

        public MyArrayList(){this(INIT_CAP);}

        public MyArrayList(int initCapacity){}

        public void addLast(E e){
            if(data.length == size) {
                resize(data.length*2);
            }
            data[size] = e;
            size++;
        }
        public void add(int index, E e){
            checkPositionIndex(index);
            System.arraycopy(data,index,data,index+1,size-index);
            data[index] = e;
            size++;
        }

        public E removeLast() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            if(size < data.length / 4) {
                resize(data.length / 2);
            }
            E deleteVal = data[size - 1];
            data[size - 1] = null;
            size--;
            return deleteVal;

        }
        public E remove(int index) {
            checkElementIndex(index);
            if(size < data.length / 4) {
                resize(data.length / 2);
            }
            E deleteVal = data[index];
            System.arraycopy(data,index + 1,data,index,size - index - 1);
            data[size-1] = null;
            size--;
            return deleteVal;
        }
        public boolean isEmpty() {
            return size==0;
        }

        public E get(int index){
            checkElementIndex(index);
            return data[index];
        }

        public E set(int index, E element) {
            checkElementIndex(index);
            E oldVal = data[index];
            data[index] = element;
            return oldVal;
        }
        private boolean isElementIndex(int index) {return index >= 0 && index < size;}
        private boolean isPositionIndex(int index) {return index >= 0 && index <= size;}
        //检查索引位置是否可以有元素
        private void checkElementIndex(int index) {
            if(!isElementIndex(index))
                throw new IndexOutOfBoundsException("Index: "+ index + "Size: "+ size);
        }
        private void checkPositionIndex(int index) {
            if(!isPositionIndex(index))
                throw new IndexOutOfBoundsException("Index: "+ index + "Size: "+ size);
        }
        public void resize(int newCap) {
            E[] temp = (E[]) new Object[newCap];
            System.arraycopy(data,0,temp,0,size);
            data = temp;
        }
}
