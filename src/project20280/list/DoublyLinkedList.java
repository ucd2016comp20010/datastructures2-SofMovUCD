package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

	public void setNext(Node<E> n){
		this.next = n;
	}

        public Node<E> getPrev() {
            return prev;
        }
	public void setPrev(Node<E> n){
		this.prev = n;
	}

    }

    private final Node<E> head;
    private final Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
	if(isEmpty()){ //if the two nodes dont exist in succession then dont add anything
		return;
	}
	Node<E> node = head;
	while(node != pred){
		node = node.getNext();
	}
	if(node.getNext() == succ){
		Node<E> nodeToAdd = new Node<E>(e, node.getPrev(), node.getNext());
		node.setNext(nodeToAdd);
		node.getNext().getNext().setPrev(nodeToAdd);
		size++;
	}
    }

    @Override
    public int size() {
        // TODO
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
	if(isEmpty() || i >= size){
		return null;
	}
	Node<E> node = head; 
        for(int j = 0; j < i; j++){
		node = node.getNext();
	}
	return node.getData();
    }

    @Override
    public void add(int i, E e) {
        // TODO
	if(isEmpty() || i >= size){
		return;
	}
	Node<E> node = head;
	for(int j = 1; j < i; j++){ //get to the node before the position at which the node must be added
		node = node.getNext();
	}
	Node<E> nodeToBeAdded = new Node(e, node, node.getNext());
	node.setNext(nodeToBeAdded);
	nodeToBeAdded.getNext().setPrev(nodeToBeAdded);
	size++;
    }

    @Override
    public E remove(int i) {
        // TODO
	if(isEmpty() || i >= size){
		return null;
	}
	Node<E> node = head;//node to be removed
	for(int j = 0; j < size; j++){
		node = node.getNext();
	}
	node.getPrev().setNext(node.getNext());
	node.getNext().setPrev(node.getPrev());
	size--;
	return node.getData();

    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        // TODO
	if(isEmpty()){
		return null;
	}
	Node<E> node = head;
	while(node != tail){
		node = node.getNext();
		if(node == n){
			node.getNext().setPrev(node.getPrev());
			node.getPrev().setNext(node.getNext());
			size--;
			return n.getData();
		}
	}
        return null;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        // TODO
	if (isEmpty()) {
            return null;
        }
        return tail.getPrev().getData();
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

    @Override
    public void addLast(E e) {
        // TODO
	add(size-1, e);
    }

    @Override
    public void addFirst(E e) {
        // TODO
	add(0, e);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}
