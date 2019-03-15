/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

/**
 *
 * @author root
 */
public class MyRunnable implements Runnable{
     private int var;

    public MyRunnable(int var) {
        this.var = var;
    }

    public void run() {
        // code in the other thread, can reference "var" variable
        //Dispatcher dispatcher = new Dispatcher();
        //dispatcher.DispatchHB();
        
    }
    
    

}
