package main;

import control.AdminC;
import control.ClientC;
import hilos.HiloInsercion;

/**
 *
 * @author Agarimo
 */
public class main {
    
    public static int contador;

    public static void main(String[] args) {
        iniciaDatos();
//        Fixer a = new Fixer(100000);
//
//        for (int i = 0; i < 10; i++) {
//            contador=10-i;
//            a.run();
//        }

        iniciaInsercion();
//        iniciaAdmin();
//        iniciaApp();
    }

    public static void iniciaDatos() {
        Variables.iniciaVariables();
    }

    public static void iniciaInsercion() {
        HiloInsercion i = new HiloInsercion();
        i.start();
    }

    public static void iniciaAdmin() {
        AdminC c = new AdminC();
    }

    public static void iniciaApp() {
        ClientC c = new ClientC();
    }
}
