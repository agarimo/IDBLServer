package control;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Variables;
import util.Dates;
import util.Sql;
import util.Varios;
import vista.Admin;

/**
 *
 * @author Agarimo
 */
public class AdminC {
    
    public static Admin win;
    
    public AdminC(){
        win = new Admin();
    }
    
    public static void iniciaInsercion(){
        main.main.iniciaInsercion();
    }
    
    public static void cargaStats(Date ini,Date fin){
        int bol=0;
        int mul=0;
        try {
            Sql bd=new Sql(Variables.con);
            bol=bd.getInt("SELECT count(*) FROM historico.boletin WHERE fechaPublicacion BETWEEN "+Varios.entrecomillar(Dates.imprimeFecha(ini))+"  AND "+Varios.entrecomillar(Dates.imprimeFecha(fin))+";");
            mul=bd.getInt("SELECT count(*) FROM historico.multa WHERE idBoletin IN (SELECT idBoletin FROM historico.boletin WHERE fechaPublicacion BETWEEN "+Varios.entrecomillar(Dates.imprimeFecha(ini))+"  AND "+Varios.entrecomillar(Dates.imprimeFecha(fin))+")");
            bd.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminC.class.getName()).log(Level.SEVERE, null, ex);
        }
        win.setDatos(Integer.toString(bol), Integer.toString(mul));
    }
}
