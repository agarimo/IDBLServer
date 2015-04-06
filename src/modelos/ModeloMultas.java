package modelos;

import entidades.VistaMulta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import util.Dates;

/**
 * Modelo Edictos
 *
 * @author Agárimo
 */
public class ModeloMultas extends AbstractTableModel {

    private int numColumnas = 4;
    private String[] nombresDeColumnas = {"Sancion", "Fase", "Publicacion", "Vencimiento"};
    private ArrayList<String[]> ResultSets;

    public ModeloMultas() {
        ResultSets = new ArrayList<>();
    }

    public ModeloMultas(List aux) {
        Iterator rs = aux.iterator();
        VistaMulta vm;
        ResultSets = new ArrayList<>();

        try {
            while (rs.hasNext()) {
                vm = (VistaMulta) rs.next();
                String[] fila = {vm.getSancion(), vm.getFase(), Dates.imprimeFecha(vm.getPublicacion()), Dates.imprimeFecha(vm.getVencimiento())};
                ResultSets.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡ERROR: Excepción en Modelo.!" + e.getMessage(),
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getValueAt(int indiceFila, int indiceColumna) {
        String[] fila = ResultSets.get(indiceFila);
        return fila[indiceColumna];
    }

    @Override
    public int getRowCount() {
        return ResultSets.size();
    }

    @Override
    public int getColumnCount() {
        return numColumnas;
    }

    @Override
    public String getColumnName(int numeroColumna) {

        return nombresDeColumnas[numeroColumna];
    }
}
