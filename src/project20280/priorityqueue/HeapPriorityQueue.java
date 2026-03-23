package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        // TODO

    }

    // protected utilities
    protected int parent(int j) {
        // TODO
        return (int)j/2;
    }

    protected int left(int j) {
        // TODO
        return j*2;
    }

    protected int right(int j) {
        // TODO
        return j*2+1;
    }

    protected boolean hasLeft(int j) {
        // TODO
        return j*2 < size();
    }

    protected boolean hasRight(int j) {
        // TODO
        return j*2+1 < size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        // TODO
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
        // TODO
        while(compare(heap.get(parent(j)), heap.get(j)) > 0){
            swap(parent(j), j);
            j = parent(j);
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    //[1, 2, 4, 12, 23, 5, 26, 15, 35, 24, 33, 21]
    protected void downheap(int j) {
        // TODO
        //move j down if either child smaller, ie j is bigger
        if(hasLeft(j) || hasRight(j)) {
            while (hasLeft(j) || hasRight(j)) {
                //swap(parent(j), j);
                if(hasLeft(j) && hasRight(j) && (compare(heap.get(j), heap.get(right(j))) > 0 || compare(heap.get(j), heap.get(left(j))) > 0)){ //has both children
                    swap(j, Math.min(right(j), left(j)));
                    j = compare(heap.get(right(j)), heap.get(left(j))) > 0? left(j):right(j);
                }
                else if(hasLeft(j) && compare(heap.get(j), heap.get(left(j))) > 0){ //only left child
                    swap(left(j), j);
                    j = left(j);
                }
                else if(hasRight(j) && compare(heap.get(j), heap.get(right(j))) > 0){ //only right child
                    swap(right(j), j);
                    j = right(j);
                }
                else{ //no children
                    break;
                }
            }
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        // TODO
        //check heap is correct
        for(int i = 0; i < size(); i++){
            downheap(i);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        // TODO
        Entry<K, V> newEnt = new PQEntry<>(key, value);
        heap.addLast(newEnt);
        upheap(heap.size()-1);
        return newEnt;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        // TODO
        if(isEmpty()) return null;
        swap(0, heap.size()-1); //swap with last element
        Entry<K, V> elemToRemove = heap.getLast(); //save the element
        heap.removeLast(); //remove the last element
        downheap(0); //downheap the first element(last in the list originaly)
        return elemToRemove;
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + rands);
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
    }
}
