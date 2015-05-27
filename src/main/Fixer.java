package main;

import entidades.Sancionado;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Sql;

/**
 *
 * @author Agarimo
 */
public class Fixer {
    
    int cantidad;
    Sancionado san;
    List lista;
    Sql bd;
    String query;
    Iterator it;
    
    
    public Fixer(int cantidad){
        this.cantidad=cantidad;
        query="select * from historico.sancionado where nombre is null limit "+cantidad;
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
            san.setNombre(SqlIDBL.getNombre(san.getNif()));
            System.out.println(san.getNombre());
            
            try {
                bd= new Sql(Variables.con);
                bd.ejecutar(san.SQLEditarNombre());
                bd.close();
            } catch (SQLException ex) {
                Logger.getLogger(Fixer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println(main.contador+"------"+aux+"------");
            aux--;
        }
    }
}
