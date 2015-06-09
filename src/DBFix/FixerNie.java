package DBFix;

import entidades.Sancionado;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.SqlIDBL;
import main.Variables;
import main.main;
import util.CalculaNif;
import util.Sql;

/**
 *
 * @author Agarimo
 */
public class FixerNie {
    
    int cantidad;
    Sancionado san;
    List lista;
    Sql bd;
    String query;
    Iterator it;
    CalculaNif cal;
    
    
    public FixerNie(int cantidad){
        cal= new CalculaNif();
        this.cantidad=cantidad;
        query="select * from historico.sancionado where nif like 'Y%' or nif like 'Z%' limit "+cantidad;
    }
    
    public void run(){
        System.out.println("Iniciando");
        int aux=cantidad;
        lista=SqlIDBL.listaSancionado(query);
        System.out.println("Se procesarán "+lista.size());
        it=lista.iterator();
        
        while(it.hasNext()){
            san=(Sancionado) it.next();
            System.out.println(san.getNif());
            san.setNif(cal.calcular(san.getNif()));
            System.out.println(san.getNif());
            
            try {
                bd= new Sql(Variables.con);
                bd.ejecutar(san.SQLEditarNif());
                bd.close();
            } catch (SQLException ex) {
                Logger.getLogger(FixerNames.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println(main.contador+"------"+aux+"------");
            aux--;
        }
    }
}
