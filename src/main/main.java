package main;

import control.AdminC;
import control.ClientC;
import hilos.HiloInsercion;

/**
 *
 * @author Agarimo
 */
public class main {

    public static void main(String[] args) {
        iniciaDatos();
//        iniciaInsercion();
//        iniciaAdmin();
        iniciaApp();
    }

    public static void iniciaDatos() {
        Variables.iniciaVariables();
    }

    public static void iniciaInsercion() {
        HiloInsercion i=new HiloInsercion();
        i.start();
    }

    public static void iniciaAdmin() {
        AdminC c=new AdminC();
    }
    
    public static void iniciaApp(){
        ClientC c=new ClientC();
    }
}
