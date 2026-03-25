package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;
import project20280.tree.LinkedBinaryTree;
import project20280.tree.Timer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.IntStream;


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
        int length = keys.length;
        for(int i = 0; i < length; i++){
            heap.add(new PQEntry<K, V>(keys[i], values[i]));
        }
        heapify();
    }

    // protected utilities
    protected int parent(int j) {
        // TODO
        return (int)(j-1)/2;
    }

    protected int left(int j) {
        // TODO
        return j*2+1;
    }

    protected int right(int j) {
        // TODO
        return j*2+2;
    }

    protected boolean hasLeft(int j) {
        // TODO
        return left(j) < size();
    }

    protected boolean hasRight(int j) {
        // TODO
        return right(j) < size();
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
            swap(parent(j), j); //actually change entries
            j = parent(j); //update index
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    //[1, 2, 4, 12, 23, 5, 26, 15, 35, 24, 33, 21]
    protected void downheap(int j) {
        // TODO
        //move j down if either child smaller, ie j is bigger
//            while (hasLeft(j) || hasRight(j)) {
//                if(hasLeft(j) && hasRight(j)){
//                    if(compare(heap.get(j), heap.get(right(j))) > 0 || compare(heap.get(j), heap.get(left(j))) > 0){ //has both children
//                        swap(j, Math.min(right(j), left(j)));
//                        j = compare(heap.get(right(j)), heap.get(left(j))) > 0 ? left(j) : right(j);
//                    }
//                    else break;
//                }
//                else if(hasLeft(j)){
//                    if(compare(heap.get(j), heap.get(left(j))) > 0){//only left child
//                        swap(left(j), j);
//                        j = left(j);
//                    }
//                    else break;
//                }
//                else if(hasRight(j)){
//                   if(compare(heap.get(j), heap.get(right(j))) > 0){
//                        swap(right(j), j);
//                        j = right(j);
//                   }  //only right child
//                    else break;
//                }
//                else{ //no children (no further down to go)
//                    break;
//                }
//            }
        int max = j;
        if(hasLeft(j) && compare(heap.get(left(j)), heap.get(j)) < 0){ //if smaller child left
            max = left(j);
        }
        else if(hasRight(j) && compare(heap.get(right(j)), heap.get(j)) < 0){ //if smaller child right
            max = right(j);
        }
        if(j != max){ //if smaller child found
            swap(j,max);
            downheap(max);
        }

    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        // TODO
        //check heap is correct
        for(int i = size()-1; i >= 0; i--){
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

    public static <E> void PQSort(E[] full){
        HeapPriorityQueue<E, ?> fillMeUp = new HeapPriorityQueue<>();
        for(E item : full){
            fillMeUp.insert(item, null);
        }
        for(int i = 0; i < full.length; i++){
            full[i] = fillMeUp.removeMin().getKey();
        }
    }

    public static <E> void Heapsort(E[] arr) {
        HeapPriorityQueue<E, ?> fillMeUp = new HeapPriorityQueue<>(arr, arr);

        for (int i = arr.length-1; i >= 0; i--) {
            fillMeUp.swap(0, i);
            fillMeUp.downheap(0);
        }

        fillMeUp.swap(0,1);

        for(int i=0; i<arr.length; ++i){
            arr[i] = fillMeUp.removeMin().getKey();
        }
    }

    public static void PQtimer() throws FileNotFoundException, UnsupportedEncodingException {
        project20280.tree.Timer timer = new Timer();
        double total = 0;

        PrintWriter writer = new PrintWriter("PQSorttimer.csv", "UTF-8");
        //do from 1 000 to 1 000 000
        for(int i = 1000; i < 1000000; i+=1000){ //each size
            for(int j = 0; j < 100; j++){ //100 different ones
                int finalI = i;
                Integer[] ok = IntStream.generate(() -> new Random().nextInt(finalI)).limit(i).boxed().toArray( Integer[]::new );
                Runnable worker = () -> {
                    PQSort(ok);
                };
                double result = timer.measure(worker);
                total +=  result;
            }
            System.out.println(i+", "+ total/100);
            writer.printf("%d, %e\n",i , total/100);

            total = 0;
        }
        writer.close();
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PQtimer();
//        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
//        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);
//
//        System.out.println("elements: " + Arrays.toString(rands));
//        System.out.println("after adding elements: " + pq);
//
//        System.out.println("min element: " + pq.min());
//
//        pq.removeMin();
//        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
        //my tree
        //[          2,
        //       26,      5,
        //   23,     4, 12, 15,
        // 35, 24, 33, 21]
    }
}
