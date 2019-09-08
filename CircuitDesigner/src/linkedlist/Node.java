/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author Emanuel
 */
public class Node <T>{
    
    private T value;
    private Node <T> next;
    private Node <T> prev;
    
    public Node(T value, Node next, Node prev){
        this.value = value;
        this.next = next;
        this.prev = prev;       
    }
    
    public Node(T value){
        this.value = value;
        this.next = null;
        this.prev = null;
    }
    
    public T getValue(){
        return value;
    }
    
    public Node <T> getNext(){
        return next;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
    public Node <T> getPrevious(){
        return prev;
    }
    
    public void setPrevious(Node prev){
        this.prev = prev;
    }
}
