package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            data = e;
            next = n;
        }

        public E getData() {
            return data;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
	if(isEmpty() || i >= size){
		return null;
	}
	Node<E> node = tail;
        for(int j = -1; j < i; j++){
		node = node.getNext();
	}
	return node.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
        //starting from tail index -1
        if(isEmpty() && i <= 0){ // making a new head
            tail = new Node<>(e, null);
            tail.setNext(tail);
            size++;
            return;
        }
        else if(i <= 0){//set a first head to an existing list
            tail.setNext(new Node<>(e, tail.getNext()));
            size++;
            return;
        }
        if(isEmpty() || i > size) return;

        Node<E> node = tail;
        for(int j = 0; j < i; j++){//starting from tail
            node = node.getNext();
        }
//        if(i == size){ //adding last node
//            node.setNext(new Node<>(e, node.getNext()));
//            tail = node.getNext();
//            size++;
//            return;
//        }
//        Node<E> nodeToAdd = new Node<E>(e, node.getNext());
        node.setNext(new Node<E>(e, node.getNext()));
        if(i == size){ //adding the last item
            tail = node.getNext();
        }
        size++;
    }

    @Override
    public E remove(int i) {
        // TODO
        if(isEmpty() || i >= size){
            return null;
        }
        Node<E> node = tail;
        for(int j = 0; j < i; j++){//starting from tail so will get to element before insertion (starting from -1 instead of 0)
            node = node.getNext();
        }
        Node<E> removed = node.getNext();
        node.setNext(node.getNext().getNext());
        size--;
        return removed.getData();
    }

    public void rotate() {
        // TODO
	    tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        if(isEmpty()){
            return null;
        }
        Node<E> removed = tail.getNext();
        tail.setNext(tail.getNext().getNext());
        size--;
        return removed.getData();
    }

    @Override
    public E removeLast() {
        // TODO
        return remove(size-1);
    }

    @Override
    public void addFirst(E e) {
        // TODO
	    add(0, e);
    }

    @Override
    public void addLast(E e) {
        // TODO
	    add(size, e);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
