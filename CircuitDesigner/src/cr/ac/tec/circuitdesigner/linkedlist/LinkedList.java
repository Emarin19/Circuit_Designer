
package cr.ac.tec.circuitdesigner.linkedlist;

import java.io.Serializable;
import java.util.Observable;

/**
 *
 * @author Emanuel Marín
 * @param <T>
 */
public class LinkedList <T> extends Observable implements Serializable{
    private  Node<T> head;
    private  Node<T> last;
    private int size;
    
    public LinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;
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
   
    public void remove(int index){
        if(index == 0){
            if (head == last){
                head = last =  null;
            }
            else{
                head = head.getNext();
                head.setPrevious(null);
            }
            System.out.println("Removing First");
            
        }
        else if(index>size){
            if(head == last){
                head = last = null;
            }
            else{
                last = last.getPrevious();
                last.setNext(null);
            }
            
        }
        else{
            Node<T> pointer = head;
            int counter = 0;
            while(counter < index && pointer.getNext() != null){
                pointer = pointer.getNext();
                counter++;
            }
            System.out.println("Sali " + counter  + " " + index);
            pointer = pointer.getPrevious();
            pointer.setNext(pointer.getNext().getNext());   
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
    
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

}
