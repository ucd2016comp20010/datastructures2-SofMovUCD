package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll;

    public static void main(String[] args) {
	System.out.println(checkParentheses("()")? "pass":"fail");
   	System.out.println(checkParentheses("(")? "fail":"pass");
    }
    //top of stack is the head of the linked list
    public LinkedStack() {
        // TODO
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        // TODO
        ll.addFirst(e);
    }

    @Override
    public E top() {
        // TODO
        return ll.get(0);
    }

    @Override
    public E pop() {
        // TODO
        return ll.removeFirst();
    }

    public static boolean checkParentheses(String in){
	// TODO
	LinkedStack<String> ls = new LinkedStack<String>();
	for(int i = 0; i < in.length(); i++){
		char lett = in.charAt(i);
		if(lett == '(' || lett == '{' || lett == '['){
			ls.push(String.valueOf(lett));
		}
		else if(lett == ')' || lett == '}' || lett == ']'){
			if(lett ==')' && ls.top().equals("(")){
				ls.pop();
			}
			else if(lett == '}' && ls.top().equals("{")){
				ls.pop();
			}
			else if(lett == ']' && ls.top().equals("[")){
				ls.pop();
			}
			else{
				return false;
			}
		}
	}
    	return true;
    }

    public String toString() {
	return ll.toString();
    }
}
