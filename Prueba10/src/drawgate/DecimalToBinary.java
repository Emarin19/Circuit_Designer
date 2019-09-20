/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgate;

import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class DecimalToBinary{
 
    public static ArrayList<Integer> inputs(int num, int entradas){
        ArrayList<Integer> result = new ArrayList<>();
        if(num == 0){
            result.add(0);
            int size = result.size();
            for(int i=0; i<entradas-size; i++){
                result.add(i, 0);
            }
        }
        else{
            int index = 0;
            while(num>0){
                result.add(index, num%2);
                num = num/2;
                index++;
            }
            int size = result.size();
            for(int i=0; i<entradas-size; i++){
                result.add(index, 0);
            }
        }
        return result;
        
    }

}
