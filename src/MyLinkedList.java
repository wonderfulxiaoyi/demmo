import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }
    private final Node<E> head ,tail;
    private int size;
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;

        this.size = 0;
    }
    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}
    private boolean isElementIndex(int index) {return index >= 0 && index < size;}
    private boolean isPositionIndex(int index) {return index >= 0 && index <= size;}
    //检查索引位置是否可以有元素
    private void checkElementIndex(int index) {
        if(!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: "+ index + "Size: "+ size);
    }
    //检查索引位置能否插入元素
    private void checkPositionIndex(int index) {
        if(!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: "+ index + "Size: "+ size);
    }

    private Node<E> getNode(int index) {
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }//返回index的Node

    public void addFirst(E e) {
        Node<E> x = new Node<>(e);
        Node<E> tmp = head.next;
        x.next = tmp;
        x.prev = head;
        tmp.prev = x;
        head.next = x;
        size++;

    }
    public void addLast(E e) {
        Node<E> x = new Node<>(e);
        Node<E> tmp = tail.prev;
        x.next = tail;
        x.prev = tmp;
        tmp.next = x;
        tail.prev = x;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        Node<E> p = getNode(index);
        Node<E> temp = p.prev;
        Node<E> x = new Node(element);
        x.prev = temp;
        x.next = p;

        temp.next = x;
        p.prev = x;
        size++;

    }

    public E removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> x = tail.prev;
        Node<E> tmp = x.prev;
        tmp.next = tail;
        tail.prev = tmp;
        x.next = x.prev = null;
        size--;
        return x.val;
    }
    public E removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> x = head.next;
        Node<E> tmp = x.next;
        head.next = tmp;
        tmp.prev = head;
        x.prev = x.next = null;
        size--;
        return x.val;
    }
    public E remove(int index) {

        checkElementIndex(index);
        Node<E> x = getNode(index);
        Node<E> prev = x.prev;
        Node<E> next = x.next;
        prev.next = next;
        next.prev = prev;
        x.prev = x.next = null;
        size--;
        return x.val;
    }

    public E getFirst(){
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }
    public E getLast() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }
    public E get(int index) {
        checkElementIndex(index);
        Node<E> p =getNode(index);
        return p.val;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> p = getNode(index);
        E oldVal = p.val;
        p.val = element;
        return oldVal;
    }
}
