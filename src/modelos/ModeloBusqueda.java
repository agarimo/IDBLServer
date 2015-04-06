package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo Edictos
 *
 * @author Agárimo
 */
public class ModeloBusqueda extends AbstractTableModel {

    private int numColumnas = 1;
    private String[] nombresDeColumnas = {"Búsqueda"};
    private ArrayList<String> ResultSets;

    public ModeloBusqueda() {
        ResultSets = new ArrayList<>();
    }

    public ModeloBusqueda(List aux) {
        Iterator rs = aux.iterator();
        String vm;
        ResultSets = new ArrayList<>();

        try {
            while (rs.hasNext()) {
                vm =  (String) rs.next();
                ResultSets.add(vm);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡ERROR: Excepción en Modelo.!" + e.getMessage(),
                    "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Object getValueAt(int indiceFila, int row) {
        String fila = ResultSets.get(indiceFila);
        return fila;
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