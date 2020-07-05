import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array = (Item[]) new Object[1];
    private int tail = -1;
    private int head = 0;
    private int numOfElements = 0;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return tail == -1;
    }


    // return the number of items on the randomized queue
    public int size()
    {
        return numOfElements;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        if (tail == array.length-1)
        {
            resize(array.length * 2);
        }
        tail++;
        array[tail] = item;
        numOfElements++;
    }

    private void resize(int capacity)
    {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i <= tail; i++)
        {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        if (tail == array.length/4 && array.length > 1)
        {
            resize(array.length/2);
        }
        int rand = StdRandom.uniform(tail+1);
        Item temp = array[rand];
        if (rand == tail)
        {
            array[rand] = null;
        }
        else {
            array[rand] = array[tail];
            array[tail] = null;
        }
        tail--;
        numOfElements--;
        return temp;

    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(tail+1);
        Item temp = array[rand];
        return temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item>
    {
        private final Item[] newArray = (Item[]) new Object[tail+1];


        public QueueIterator()
        {
            for (int i = 0; i < tail+1; i++)
            {
                newArray[i] = array[i];
            }
            StdRandom.shuffle(newArray);

        }

        @Override
        public boolean hasNext() {
            return head != tail+1;
        }

        @Override
        public Item next() {
            if (head == tail+1)
            {
                throw new NoSuchElementException();
            }
            Item temp = newArray[head];
            head++;
            return temp;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(5);
        queue.dequeue();

        queue.enqueue(8);

        queue.enqueue(2);
        queue.enqueue(0);
        queue.dequeue();


        System.out.println(queue.isEmpty());
        System.out.println(queue.size());




    }

}
