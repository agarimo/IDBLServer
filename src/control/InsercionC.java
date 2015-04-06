package control;

import insercion.Fichero;
import insercion.SqlTask;
import java.io.File;
import main.Timming;
import main.Variables;
import util.*;
import vista.Insercion;

/**
 *
 * @author Agarimo
 */
public class InsercionC extends Thread {

    Sql bd;
    public static Insercion win;
    private final Fichero fichero;
    private SqlTask sql;

    public InsercionC() {
        win = new Insercion();
        win.setLocationRelativeTo(null);
        win.setVisible(true);
        Variables.tm = new Timming(Dates.curdate());
        fichero = new Fichero(new File("data"));
    }

    @Override
    public void run() {
        fichero.start();
        fichero.procesaFichero();
        sql = new SqlTask();
        Files.borraDirectorio(new File("data"));
        new File("data").mkdirs();
        win.dispose();
    }
}
