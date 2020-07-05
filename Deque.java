import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int numOfNodes = 0;

    private class Node {
        Item item;
        Node next = null;
        Node previous = null;
    }

    // construct an empty deque
    public Deque()
    {

    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return first == null;
    }

    // return the number of items on the deque
    public int size()
    {
        return numOfNodes;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("null value is unaccepted");
        }
        else {
            if (isEmpty()) {
                first = new Node();
                first.item = item;
                last = first;
            }
            else {
                Node tempNode = new Node();
                tempNode.item = item;
                tempNode.next = first;
                first.previous = tempNode;
                first = tempNode;
            }
            numOfNodes++;
        }
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException("null value is unaccepted");
        }
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node tempNode = new Node();
            tempNode.item = item;
            tempNode.next = null;
            tempNode.previous = last;
            last.next = tempNode;
            last = tempNode;
        }
        numOfNodes++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item temp;
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        else {
            temp = first.item;
            if (size() == 1) {
                first = null;
                last = null;
            }
            else {
                first = first.next;
                first.previous = null;
            }
            numOfNodes--;
            return temp;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("deque is empty");
        }
        else {
            Item temp = last.item;
            if (size() == 1)
            {
                last = null;
                first = null;
            }
            else
            {
                last = last.previous;
                last.next = null;
            }
            numOfNodes--;
            return temp;
        }
    }


    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item>
    {
        Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (node == null) {
                throw new NoSuchElementException();
            }
            Item temp =  node.item;
            node = node.next;
            return temp;

        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("remove");
        }
    }


    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> newDeque = new Deque<>();
        newDeque.addLast(5);
        newDeque.addLast(8);
        newDeque.addLast(6);
        newDeque.addLast(3);




        Iterator<Integer> iterator = newDeque.iterator();

        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

}

