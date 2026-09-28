/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;


    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniformInt(size);

        Item item = items[i];
        items[i] = items[--size];
        items[size] = null;

        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniformInt(size);
        return items[i];
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];

        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator();
    }

    private class RandomizeQueueIterator implements Iterator<Item> {
        private int current;
        private Item[] randomizedItems;

        public RandomizeQueueIterator() {
            randomizedItems = (Item[]) new Object[size];

            for (int i = 0; i < size; i++) {
                randomizedItems[i] = items[i];
            }
            StdRandom.shuffle(randomizedItems);
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = randomizedItems[current];
            current++;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}
