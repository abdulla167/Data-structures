import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int numOfLines = Integer.valueOf(args[0]);
        while (!StdIn.isEmpty())
        {
            String line = StdIn.readString();
            queue.enqueue(line);
        }
        while (numOfLines > 0) {
            StdOut.println(queue.dequeue());
            numOfLines--;
        }
    }

}
