package hilos;

import control.InsercionC;

/**
 *
 * @author Agarimo
 */
public class HiloInsercion extends Thread {
    InsercionC i;
    
    
    public HiloInsercion(){
        i=new InsercionC();
    }
    
    @Override
    public void run(){
        i.start();
    }
}
