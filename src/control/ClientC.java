package control;

import entidades.VistaMulta;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import main.SqlIDBL;
import modelos.ModeloBusqueda;
import modelos.ModeloMultas;
import vista.Client;

/**
 *
 * @author Agarimo
 */
public class ClientC {

    public static Client win;
    private static List multas;

    public ClientC() {
        win = new Client();
    }

    /**
     * Tipo 0 para nif Tipo 1 para matricula Tipo 2 para expediente
     *
     * @param aux
     * @param tipo
     */
    public static void buscar(String aux, int tipo) {
        switch (tipo) {
            case 0:
                multas = SqlIDBL.listaProducto(VistaMulta.SQLBuscarNif(aux));
                break;
            case 1:
                multas = SqlIDBL.listaProducto(VistaMulta.SQLBuscarMatricula(aux));
                break;
            case 2:
                multas = SqlIDBL.listaProducto(VistaMulta.SQLBuscarExpediente(aux));
                break;
        }

        if (multas.size() < 1) {
            win.limpiarMultas();
            JOptionPane.showMessageDialog(null, "No se han encontrado coincidencias",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mostrarMultas();
            cargarMultas();
            win.setStats(multas.size(), countInPlazo());
        }
    }

    /**
     * Tipo 0 para nif Tipo 1 para matricula Tipo 2 para expediente
     *
     * @param aux
     * @param tipo
     */
    public static void buscarA(String aux, int tipo) {
        switch (tipo) {
            case 0:
                multas = SqlIDBL.listaProductoA(VistaMulta.SQLBuscarNifA(aux), "nif");
                break;
            case 1:
                multas = SqlIDBL.listaProductoA(VistaMulta.SQLBuscarMatriculaA(aux), "matricula");
                break;
            case 2:
                multas = SqlIDBL.listaProductoA(VistaMulta.SQLBuscarExpedienteA(aux), "expediente");
                break;
        }

        if (multas.size() < 1) {
            win.limpiarBuscar();
            JOptionPane.showMessageDialog(null, "No se han encontrado coincidencias",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            cargarBuscar();
        }
    }

    public static void cargarMultas() {
        if (win.isPlazo()) {
            win.setModel(new ModeloMultas(getInPlazo()));
        } else {
            win.setModel(new ModeloMultas(multas));
        }
    }

    public static void cargarBuscar() {
        win.setModelA(new ModeloBusqueda(multas));
    }

    public static void cargarBusqueda(String aux, int tipo) {
        win.limpiarBuscar();
        buscar(aux, tipo);
    }

    private static List getInPlazo() {
        List list = new ArrayList();
        Iterator it = multas.iterator();
        VistaMulta aux;

        while (it.hasNext()) {
            aux = (VistaMulta) it.next();

            if (aux.isPlazo()) {
                list.add(aux);
            }
        }
        return list;
    }

    private static int countInPlazo() {
        Iterator it = multas.iterator();
        VistaMulta aux;
        int n = 0;

        while (it.hasNext()) {
            aux = (VistaMulta) it.next();

            if (aux.isPlazo()) {
                n++;
            }
        }
        return n;
    }

    public static void mostrarMultas() {
        win.mostrarPanel("multas");
    }

    public static void mostrarVer() {
        if (win.isSelectedRow()) {
            mostrarSancion(win.getSelectedMulta());
            win.mostrarPanel("ver");
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una sanción",
                    "¡ERROR!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void mostrarBuscar() {
        win.mostrarPanel("buscar");
    }

    public static void mostrarMultasA() {
        if (win.isSelectedRowA()) {
            mostrarBusqueda();
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un elemento",
                    "¡ERROR!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void mostrarSancion(String id) {
        win.cargarVer(SqlIDBL.cargaMultaS(id));
    }

    private static void mostrarBusqueda() {
        win.setBuscar(win.getSelectedBusqueda());
        win.setTipoBuscar(win.getTipoA());
        buscar(win.getSelectedBusqueda(), win.getTipoA());
    }

    public static void abrirWeb(String url) {

        System.out.println("Se va a mostrar el link: " + url);

        if (url != null || !"".equals(url)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (URISyntaxException | IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sanción sin enlace WEB",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
