package datastructures;

import java.util.ArrayList;
import java.util.*;

public class Queue<T> {
    private int n;
    private Node first;
    private Node last;

    private class Node{
        T val;
        Node next;
    }

    public Queue(){
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public T peek(){
        if(isEmpty())
            throw new NoSuchElementException("Queue Underflow");
        return first.val;
    }

    public void enqueue(T value){
        Node old = last;
        last = new Node();
        last.val = value;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            old.next = last;
        n++;
    }

    public T dequeue(){
        if(isEmpty())
            throw new NoSuchElementException("Queue Underflow");
        T item = first.val;
        first = first.next;
        if(isEmpty())
            first = null;
        n--;
        return item;
    }

    public static void main(String args[]){
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(34);
       System.out.println(q.dequeue());
        //q.dequeue();
        q.enqueue(45);
        q.enqueue(678);
        System.out.println(q.size());
        System.out.println(q.peek());

    }

}
