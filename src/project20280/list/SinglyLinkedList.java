package project20280.list;

import project20280.interfaces.List;

import java.util.Comparator;
import java.util.Iterator;


public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

    private static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
	    this.element = e;
	    this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
	    this.next = n;
        }

        @Override
        public int compareTo(Node<E> o){ //make the nodes comparable
            return element.compareTo(((Node<E>)o).element);
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {}              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int position) { //position starts from 0
        // TODO
	Node<E> node = head; 
	if(isEmpty() || position >= size){
		return null;
	}
	for(int i = 0; i < position; i++){ node = node.getNext();}

        return node.getElement();
    }

    @Override
    public void add(int position, E e) {
        // TODO
        if(position <= 0){
    //        Node<E> headNext = head;
            head = new Node<>(e, head);//set the new heads next node as the current head
            size++;
            return;
        }
        if(position > size || isEmpty()) return;
        Node<E> node = head;
        for(int i = 0; i < position-1; i++){//get to position right before position to add to
            node = node.getNext();
        }

        if(position == size){ //adding last node
            node.setNext( new Node<>(e, null));
            size++;
            return;
        }
        Node<E> newNode = new Node<E>(e, node.getNext());
        node.setNext(newNode);
        size++;
    }


    @Override
    public void addFirst(E e) {
        // TODO
//        if(isEmpty()){
//            Node<E> newNode = new Node<E>(e, null);
//            this.head = newNode;
//            size++;
//            return;
//        }
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        // TODO
//	if(isEmpty()){
//                Node<E> newNode = new Node<E>(e, null);
//                this.head = newNode;
//        }
	add(size, e);
    }

    @Override
    public E remove(int position) {
        // TODO
	if(isEmpty() || position >= size){
		return null;
	}

	size--;
	if(position == 0){//first node to be removed
		Node<E> node = head; //saving the head node
		head = head.getNext(); //moving the head one to the next node
		return node.getElement(); //returning the original head
	}
	Node<E> nodeBefore = head;
        for(int i = 0; i < position-1; i++){//get to node right before the one to remove;
		nodeBefore = nodeBefore.getNext();
	}
	Node<E> nodeToBeRemoved = nodeBefore.getNext();

	if(nodeToBeRemoved.getNext() == null){//last element is removed
		nodeBefore.setNext(null);
		return nodeToBeRemoved.getElement();
	}
	nodeBefore.setNext(nodeBefore.getNext().getNext());
	return nodeToBeRemoved.getElement();
    }

    @Override
    public E removeFirst() {
        // TODO
        return remove(0);
    }

    @Override
    public E removeLast() {
        // TODO
        return remove(size-1);
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> twin = new SinglyLinkedList<E>();
        Node<E> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.getElement());
            tmp = tmp.next;
        }
        return twin;
    }

    public void reverse() {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next;
        while(curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> list2){
        if(!(list2 instanceof Comparable)){
            throw new IllegalArgumentException("List contains elements which are not comparable");
        }
        Node<E> startOfNewList;
        int biggestSize = list2.size() > size()? list2.size():size();
        for(int i = 0; i < biggestSize; i++){

        }
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E extends Comparable<E>> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
//        int a = 1;
//        System.out.println("test: " + a.compareTo((Object)2)? "wow": "not wow");
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
