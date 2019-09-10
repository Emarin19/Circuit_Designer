/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.Observable;

/**
 *
 * @author Emanuel
 */
public class LinkedList <T> extends Observable{
    
    public Node<T> head;
    public Node<T> last;
    public Node<T> node;
    private int size;
    
    public LinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;
    }
    
    public void addFirst(T value){
        if(head != null){
            head = new Node(value,head,null);
            head.getNext().setPrevious(head);
        }
        else{
            head = new Node(value);
            last = head;
        }
        size++;
        setChanged(); 
        notifyObservers(head.getValue()); 
    }
    
    public void addLast(T value){
        if(head != null){
            last = new Node(value,null,last);
            last.getPrevious().setNext(last);
        }
        else{
            head = new Node(value);
            last = head;
        }
        size++;
        setChanged(); 
        notifyObservers(last.getValue());
    }
    
    public void add(T value){
        if(head != null){
            last = new Node(value,null,last);
            last.getPrevious().setNext(last);
        }
        else{
            head = new Node(value);
            last = head;
        }
        size++;
        setChanged(); 
        notifyObservers(last.getValue());
    }
    
    public void add(int index, T value){
        Node node = new Node(value);
        if(head == null){
            node = head;
        }
        else if(index == 0){
            addFirst(value);
        }
        else if(index>size){
            addLast(value);
        }
        else{
            Node pointer = new Node(value);
            int counter = 1;
            while(counter < index && pointer.getNext() != null){
                pointer = pointer.getNext();
                counter++;
            }
            node.setNext(pointer.getNext());
            pointer.setNext(node);
        }
        size++;
        setChanged(); 
        notifyObservers(last.getValue());
    }
    
    public T searchItem(int index){
        if (head == null){
            return null;
        }
        
        else{
            Node<T> pointer = head;
            int counter = 0;
            while(counter < index && pointer.getNext() !=  null){
                pointer.getNext();
                counter++;
            }
            return pointer.getValue();
        }
    }
    
    public void removeFirst(){
        if (head == last){
            head = last =  null;
        }
        else{
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
    }
    
    public void removeLast(){
        if(head == last){
            head = last = null;
        }
        else{
            last = last.getPrevious();
            last.setNext(null);
        }
        size--;
    }
    
    public void remove(int index){
        if(index == 0){
            removeFirst();
        }
        else if(index>size){
            removeLast();
        }
        else{
            Node<T> pointer = head;
            int counter = 1;
            while(counter < index && pointer.getNext() != null){
                pointer = pointer.getNext();
                counter++;
            }
            pointer = pointer.getPrevious();
            pointer.setNext(pointer.getNext().getNext());
            pointer.setNext(pointer.getNext());
        }
        size--;
    }
    
    public T getValue(int index){
        Node<T> pointer = head;
        int counter = 0;
        while(counter<index){
            pointer = pointer.getNext();
            counter++;
        }
        return pointer.getValue();
    }

    public int getSize(){
        return size;
    }

}
