/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularDeque<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;
    private int head;
    private int tail;

    public CircularDeque() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == items.length) {
            resize(items.length * 2);
        }

        if (!isEmpty()) {
            head = (head - 1 + items.length) % items.length;
        }
        items[head] = item;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == items.length) {
            resize(items.length * 2);
        }

        if (!isEmpty()) {
            tail = (tail + 1) % items.length;
        }
        items[tail] = item;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = items[head];
        items[head] = null;

        size--;
        if (size != 0) {
            head = (head + 1) % items.length;
        }

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = items[tail];
        items[tail] = null;

        size--;
        if (size != 0) {
            tail = (tail - 1 + items.length) % items.length;
        }

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];

        for (int i = 0; i < size; i++) {
            copy[i] = items[head];
            head = (head + 1) % items.length;
        }

        head = 0;
        tail = size - 1;

        items = copy;
    }

    public Iterator<Item> iterator() {
        return new CircularDequeIterator();
    }

    private class CircularDequeIterator implements Iterator<Item> {
        private int current;
        private int count;

        public CircularDequeIterator() {
            current = head;
        }

        public boolean hasNext() {
            return count < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = items[current];
            current = (current + 1) % items.length;
            count++;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    public static void main(String[] args) {

    }
}
