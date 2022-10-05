package ru.vsu.edu.shlyikov_d_g;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<T> implements Iterable<T> {

    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private static class SimpleLinkedListNode<T> {
        public T value;
        public SimpleLinkedListNode<T> next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode<T> head = null;
    private SimpleLinkedListNode<T> tail = null;
    private int size = 0;

    public void addFirst(int value) {
        head = new SimpleLinkedListNode(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(int value) {
        if (size == 0) {
            head = tail = new SimpleLinkedListNode(value);
        } else {
            tail.next = new SimpleLinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    private static void checkEmptyError(SimpleLinkedList list) throws SimpleLinkedListException {
        if (list.size == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private void checkEmptyError() throws SimpleLinkedListException {
        if (size == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private SimpleLinkedListNode<T> getNode(int index) {
        SimpleLinkedListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeFirst() throws SimpleLinkedListException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws SimpleLinkedListException {
        checkEmptyError();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    public void remove(int index) throws SimpleLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            SimpleLinkedListNode<T> prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            size--;
        }
    }

    public int size() {
        return size;
    }

    public T get(int index) throws SimpleLinkedListException {
        checkEmptyError();
        return getNode(index).value;
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmptyError();
        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmptyError();
        return tail.value;
    }

    // 9, 11, 9, 9, 9, 9, 11, 10, 11, 11, 40, 32, 30, 13, 10, 10, 20, 19, 20

    public static void task(SimpleLinkedList<Integer>  list) throws Exception {
        checkEmptyError(list);
        SimpleLinkedListNode<Integer> curr = list.head;
        SimpleLinkedListNode<Integer>  prev = list.head;
        if (list.size == 1){
            return;
        }
        for (int i = 0; i < list.size; i++) {
//            System.out.println("currSTART= " + curr.value);
//            System.out.println("prevSTART= " + prev.value);
            if (checkPrime(curr.value)) {
                if (i == 1 && !checkPrime(list.head.value)) {
                    list.head = list.head.next;
                    list.size--;
                    i--;
//                    System.out.println("CODE 1");
                }
                if (i - 2 >= 0 && !checkPrime(prev.next.value)) {
                    prev.next = curr;
                    list.size--;
                    i--;
//                    System.out.println("CODE 2");
                }
                if (i + 2 < list.size && !checkPrime(curr.next.value)) {
                    curr.next = curr.next.next;
                    list.size--;
//                    System.out.println("CODE 3");
                } else if (i + 1 < list.size && !checkPrime(curr.next.value)) {
                    curr.next = null;
                    list.size--;
//                    System.out.println("CODE 4");
                }
            }
                if (i != 0 && prev.next != curr) {
                    prev = prev.next;
                }
                curr = curr.next;
//                if (curr != null){
//                System.out.println("currEND= " + curr.value);
//                }
//                System.out.println("prevEND= " + prev.value);
        }
    }

//    public void deleteSidesOfPrimes() throws IntegerLinkedListException {
//        for (int i = 0; i < size; i++) {
//            if (checkPrime(get(i))){
//                if (i != size - 1 && !checkPrime(get(i + 1))) {
//                    remove(i + 1);
//                }
//                if (i != 0 && !checkPrime(get(i - 1))) {
//                    remove(i - 1);
//                    i -= 1;
//                }
//            }
//        }
//    }

    private static boolean checkPrime(int obj){
        int num = obj;
        if (num < 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        class IntegerLinkedListIterator implements Iterator<T> {
            SimpleLinkedListNode<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        // работать с IntegerLinkedListNode
        // не работать с методами обращения по индексу

        return new IntegerLinkedListIterator();
    }
}
