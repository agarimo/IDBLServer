package insercion;

import control.InsercionC;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Variables;
import util.Dates;
import util.Files;

/**
 *
 * @author Agárimo
 */
public class Fichero extends Thread {

    private final File fichero;
    private final List<File> pila;
    private final List<File> split;
    private BB0 archivo;
    private int total;
    private int contador;
    private boolean activo;

    public Fichero(File fichero) {
        activo = true;
        this.fichero = fichero;
        this.pila = new ArrayList<>();
        this.split = new ArrayList<>();
        llenarPila(this.fichero);
        splitPila();
        llenarPila(this.fichero);
        Variables.tm.setSplit(Dates.curdate());
    }

    @Override
    public void run() {
        while (activo) {
            getStatus();
        }
    }

    public void getStatus() {
        InsercionC.win.setProgreso(getPorcentaje());
        InsercionC.win.setTitulo("Completado " + getPorcentaje() + "%");
        InsercionC.win.setLabel("Cargando datos en la BBDD");
        InsercionC.win.setProgresoBB0(BB0.getPorcentajeBB0());
    }

    public int getTotal() {
        return total;
    }

    public int getContador() {
        return contador;
    }

    public int getPorcentaje() {
        return (contador * 100) / total;
    }

    public int getTotalBB0() {
        return BB0.getTotalBB0();
    }

    public int getContadorBB0() {
        return BB0.getContadorBB0();
    }

    public int getPorcentajeBB0() {
        return BB0.getPorcentajeBB0();
    }

    private void llenarPila(File fichero) {
        File[] ficheros = fichero.listFiles();

        for (File fichero1 : ficheros) {
            if (fichero1.isDirectory()) {
                llenarPila(fichero1);
            } else {
                if (fichero1.getName().contains(".bb2") || fichero1.getName().contains(".bb1")) {
                    pila.add(fichero1);
                } else if (fichero1.getName().contains(".big")) {
                    split.add(fichero1);
                } else {
                    Files.moverArchivo(fichero1, new File("dsc", fichero1.getName()));
                }
            }
        }
        total = pila.size();
        contador = 0;
    }

    private void splitPila() {
        InsercionC.win.setTitulo("Preparando archivos");
        InsercionC.win.setLabel("...Spliteando Archivos...");
        InsercionC.win.setIndeterminado(true);

        File destino = new File("data");
        File aux;
        Iterator it = split.iterator();

        while (it.hasNext()) {
            aux = (File) it.next();
            try {
                Files.splitArchivo(aux, destino, 50000, "bb2");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
            }
            aux.delete();
        }
        InsercionC.win.setIndeterminado(false);
        pila.clear();

    }

    private void procesar() {
        archivo.procesaArchivo();
    }

    public void procesaFichero() {
        Iterator it = pila.iterator();
        while (it.hasNext()) {
            archivo = new BB0((File) it.next());
            procesar();
            contador++;
        }
        activo = false;
    }
    
    public void limpiarFichero() {
        File fic=new File("data");
        File destino= new File("trsh");
        destino.mkdirs();
        File[] ficheros = fic.listFiles();

        for (File fichero1 : ficheros) {
            if (fichero1.isDirectory()) {
                llenarPila(fichero1);
            } else {
               Files.moverDirectorio(fichero1,destino);
            }
        }
        Files.borraDirectorio(destino);
    }
    
}
