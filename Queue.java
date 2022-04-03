
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Queue: custom implementation
 * @author     John Mortensen
 *
 * 1. Uses custom LinkedList of Generic type T
 * 2. Implements Iterable
 * 3. "has a" LinkedList for head and tail
 */
public class Queue<T> implements Iterable<T> {
    LinkedList<T> head, tail;


    public void add(T data) {
        // add new object to end of Queue
        LinkedList<T> tail = new LinkedList<>(data, null);

        if (head == null)  // initial condition
            this.head = this.tail = tail;
        else {  // nodes in queue
            this.tail.setNextNode(tail); // current tail points to new tail
            this.tail = tail;  // update tail
        }

    }
  //remove tail
    public void remove(T data) {
        LinkedList<T> tail = new LinkedList<>(data, null);
        if(head == null){
            throw new RuntimeException("Deque is empty");
        }

        if(head.getNext() == null){
            tail = null;
        }else{
            // previous of next node (new first) becomes null
            head.getNext().setPrevNode(tail);
        }
        head = head.getNext();
    }


    /**
     *  Returns the head object.
     *
     * @return  this.head, the head object in Queue.
     */
    public LinkedList<T> getHead() {
        return this.head;
    }

    /**
     *  Returns the tail object.
     *
     * @return  this.tail, the last object in Queue
     */
    public LinkedList<T> getTail() {
        return this.tail;
    }

    /**
     *  Returns the iterator object.
     *
     * @return  this, instance of object
     */
    public Iterator<T> iterator() {
        return new QueueIterator<>(this);
    }


}

/**
 * Queue Iterator
 *
 * 1. "has a" current reference in Queue
 * 2. supports iterable required methods for next that returns a data object
 */
class QueueIterator<T> implements Iterator<T> {
    LinkedList<T> current;  // current element in iteration

    // QueueIterator is intended to the head of the list for iteration
    public QueueIterator(Queue<T> q) {
        current = q.getHead();
    }

    // hasNext informs if next element exists
    public boolean hasNext() {
        return current != null;
    }

    // next returns data object and advances to next position in queue
    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
}

/**
 * Queue Manager
 * 1. "has a" Queue
 * 2. support management of Queue tasks (aka: titling, adding a list, printing)
 */
class QueueManager<T> {
    // queue data
    private String name; // name of queue
    private int count = 0; // number of objects in queue
    public final Queue<T> queue = new Queue<>(); // queue object
    private int size;
    private static int qCount = 0;

    /**
     *  Queue constructor
     *  Title with empty queue
     */
    public QueueManager(String name) {
        this.name = name;
    }

    /**
     *  Queue constructor
     *  Title with series of Arrays of Objects
     */
    public QueueManager(String name, T[]... seriesOfObjects) {
        this.name = name;
        this.addList(seriesOfObjects);

    }


    public QueueManager(T[]... seriesOfObjects) {
        this.addListC(seriesOfObjects);
        qCount++;

        // make custom add lisst without printing
    }

  //getting numbers for the sort
    public int getNumbers(){
        String numbers = "";

        for (T data : queue) {
            numbers += data + " ";


        }
        System.out.println("Before: " + numbers);
        return Integer.valueOf(numbers.replaceAll(" ", ""));
    }
  //get the size of the queue
    public int size(){
        int iter = 0;
        for (T data : queue) {
            iter++;
        }
        return iter;
    }

  //get which queue# the queue is at rn
    public void getQueue(){
        if (qCount == 1){
            System.out.print("(1st Queue) ");
        }
        else if (qCount == 2){
            System.out.print("(2nd Queue) ");
        }

        for (T data : queue) {
            System.out.print(data + " -> ");
        }
        if (qCount <= 2) {
            System.out.print("nil");
        }
        System.out.println("");
    }

    public void sort(QueueManager seriesOfObjects){
//        Object[] empty = new String[] {};
//        QueueManager emptyQ = new QueueManager(empty);
        T previous = null;
        T current = null;
        ArrayList<Integer> emptyAL = new ArrayList<Integer>();
        for (T data : queue) {
            emptyAL.add(Integer.valueOf((String) data));
        }
        for (Object data : seriesOfObjects.queue) {
            emptyAL.add(Integer.valueOf((String) data));
        }
        int n = emptyAL.size();
        for (int i = 1; i < n; ++i) {
            int key = emptyAL.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && emptyAL.get(j) > key) {
                emptyAL.set(j + 1, emptyAL.get(j));
                j = j - 1;
            }
            emptyAL.set(j + 1, key);
        }
        System.out.println();
        for (int data : emptyAL){
            System.out.print(data + " -> ");
        }
        System.out.print("nil");


    }
    /**
     * Add a list of objects to queue
     */
    public void addListC(T[]... seriesOfObjects) {
        for (T[] objects: seriesOfObjects)
            for (T data : objects) {
                this.queue.add(data);
                this.count++;
            }
    }
    public void addList(T[]... seriesOfObjects) {
        for (T[] objects: seriesOfObjects)
            for (T data : objects) {
                System.out.println("Enqeued data: " + data);
                this.queue.add(data);
                this.count++;
                printQueue();
            }
    }
  //dequeue
    public void removeList(T[]... seriesOfObjects) {
        for (T[] objects: seriesOfObjects)
            for (T data : objects) {
                System.out.println("Deqeued data: " + data);
                this.queue.remove(data);
                this.count--;
                printQueue();
            }
    }

    /**
     * Print any array objects from queue
     */
    public void printQueue() {
        System.out.print(this.name + " count: " + count + ", data: ");
        for (T data : queue) {
            if (count != 0) {
                System.out.print(data + " ");
            }
            else{
                System.out.print("null");
            }
        }
        System.out.println("");
    }
}

/**
 * Driver Class
 * Tests queue with string, integers, and mixes of Classes and types
 */
class QueueTester {
    public static void main(String[] args)
    {
        // Create iterable Queue of Words
        Object[] words = new String[] { "seven", "slimy", "snakes", "sallying", "slowly", "slithered", "southward"};
        QueueManager qWords = new QueueManager("Words", words );
        qWords.removeList(words);

    }
}