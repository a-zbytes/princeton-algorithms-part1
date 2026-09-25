/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (size() == 0) {
            first = newNode;
            last = first;
        }
        else {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node();
        newNode.item = item;

        if (size() == 0) {
            last = newNode;
            first = last;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item oldItem = first.item;

        first = first.next;
        if (size == 1) {
            last = first;
        }
        else {
            first.prev = null;
        }

        size--;

        return oldItem;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item oldItem = last.item;

        last = last.prev;

        if (size == 1) {
            first = last;
        }
        else {
            last.next = null;
        }

        size--;

        return oldItem;
    }

    public int size() {
        return size;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item oldItem = current.item;
            current = current.next;

            return oldItem;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
