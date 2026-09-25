/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        int count = 0;

        while (!StdIn.isEmpty()) {
            String text = StdIn.readString();
            count++;

            if (count <= k) {
                randomizedQueue.enqueue(text);
            }
            else {
                if (StdRandom.bernoulli((double) k / count)) {
                    randomizedQueue.dequeue();
                    randomizedQueue.enqueue(text);
                }
            }
        }


        for (String s : randomizedQueue) {
            System.out.println(s);
        }

    }
}
