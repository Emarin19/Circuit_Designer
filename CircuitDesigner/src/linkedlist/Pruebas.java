/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.Collection;
import nodes.AndTwo;
import nodes.LogicGate;
import nodes.NandTwo;
import nodes.OrTwo;

/**
 *
 * @author Emanuel
 */
public class Pruebas {
    
    Collection<LogicGate> list;
    

    public Pruebas() {
        this.list = (Collection<LogicGate>) new LinkedList<LogicGate>();
        AndTwo and = new AndTwo();
        OrTwo or = new OrTwo();
        NandTwo nand = new NandTwo();
        list.add(and);
        list.add(or);
        list.add(nand);
    }
    
}
