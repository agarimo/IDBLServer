package main;

import DBFix.*;
import insrt.Insercion;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Dates;
import util.Files;

/**
 *
 * @author Agarimo
 */
public class main {

    public static int contador;

    public static void main(String[] args) {
        iniciaDatos();
//        iniciaInsercion();
    }

    public static void iniciaDatos() {
        Variables.iniciaVariables();
    }

    public static void iniciaInsercion() {
        File block = new File("block.blk");

        if (block.exists()) {
            JOptionPane.showMessageDialog(null, "Ya se está ejecutando el programa");
        } else {
            try {
                block.createNewFile();
                Files.escribeArchivo(block, System.getProperty("user.name") + "-" + Dates.imprimeFechaCompleta(Dates.curdate()));
                Insercion i = new Insercion();
                i.start();
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void FixNames() {
        FixerNames a = new FixerNames(100000);

        for (int i = 0; i < 10; i++) {
            contador = 10 - i;
            a.run();
        }
    }

    public static void FixNie() {
        FixerNie a = new FixerNie(100000);

        for (int i = 0; i < 10; i++) {
            contador = 10 - i;
            a.run();
        }
    }
}
