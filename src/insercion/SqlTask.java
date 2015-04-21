package insercion;

import control.InsercionC;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Stats;
import main.Timming;
import main.Variables;
import util.Dates;
import util.Log;
import util.Sql;

/**
 *
 * @author Agarimo
 */
public class SqlTask {

    Sql bd;

    public SqlTask() {
        iniciaStats();
        procesaTemporal();
    }

    private void iniciaStats() {
        int bol, san;
        try {
            bd = new Sql(Variables.con);
            bol = bd.getInt("select count(*) from historico.boletin");
            san = bd.getInt("select count(*) from historico.sancion");
            bd.close();

            Variables.st = new Stats(Dates.curdate(), bol, san);
        } catch (SQLException ex) {
            Logger.getLogger(SqlTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void procesaTemporal() {
        try {
            Variables.tm.setCarga(Dates.curdate());
            InsercionC.win.setIndeterminado(true);
            InsercionC.win.setLabel("Procesando Organismos");
            procesaOrganismos();
            Variables.tm.setOrganismos(Dates.curdate());
            InsercionC.win.setTitulo("Completado 16%");
            InsercionC.win.setLabel("Procesando Boletines");
            procesaBoletines();
            Variables.tm.setBoletines(Dates.curdate());
            InsercionC.win.setTitulo("Completado 32%");
            InsercionC.win.setLabel("Procesando Sancionados");
            procesaSancionados();
            Variables.tm.setSancionados(Dates.curdate());
            InsercionC.win.setTitulo("Completado 48%");
            InsercionC.win.setLabel("Procesando Vehiculos");
            procesaVehiculos();
            Variables.tm.setVehiculos(Dates.curdate());
            InsercionC.win.setTitulo("Completado 64%");
            InsercionC.win.setLabel("Procesando Sanciones");
            procesaSanciones();
            Variables.tm.setSanciones(Dates.curdate());
            InsercionC.win.setTitulo("Completado 80%");
            InsercionC.win.setLabel("Procesando Multas");
            procesaMultas();
            Variables.tm.setMultas(Dates.curdate());
            InsercionC.win.setTitulo("Completado 100%");
            InsercionC.win.setFin();
            limpiar();
            Variables.tm.setTotal(Dates.curdate());
            procesaTimmingStats(Variables.tm, "OK");
            Variables.tm = null;

        } catch (SQLException ex) {
            try {
                procesaTimmingStats(Variables.tm, "ERROR");
                limpiar();
            } catch (SQLException ex1) {
                Logger.getLogger(SqlTask.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Log lg = new Log();
            lg.escribeError("SQL", ex.getMessage());
            Logger.getLogger(SqlTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void procesaOrganismos() throws SQLException {
        String query = "insert into historico.origen (nombreOrigen) select organismo from historico.temp_historico where not exists "
                + "(select nombreOrigen from historico.origen where historico.origen.nombreOrigen = historico.temp_historico.organismo) group by organismo";
        bd = new Sql(Variables.con);
        bd.ejecutar(query);
        bd.close();
    }

    private void procesaBoletines() throws SQLException {
//        String queryAppD = "insert into app.boletines_duplicados (codigo,fecha) select a.boe,a.fecha_publicacion from historico.temp_historico as a "
//                + "where exists "
//                + "(select codigo from app.boletines_insertados where app.boletines_insertados.codigo = a.boe and app.boletines_insertados.fecha = a.fecha_publicacion) group by boe";

        String queryAppI = "insert into app.boletines_insertados (codigo,fecha) select a.boe,fecha_publicacion from historico.temp_historico as a "
                + "where not exists "
                + "(select codigo from app.boletines_insertados where app.boletines_insertados.codigo = a.boe and app.boletines_insertados.fecha = a.fecha_publicacion) group by boe";

//        String queryAppB = "delete from historico.temp_historico where exists "
//                + "(select codigo from app.boletines_duplicados where app.boletines_duplicados.codigo = boe and app.boletines_duplicados.fecha = fecha_publicacion)";

        String query = "insert into historico.boletin (nBoe,origen,fechaPublicacion) select a.boe, b.idOrigen, a.fecha_publicacion from historico.temp_historico as a "
                + "left join historico.origen as b on a.organismo=b.nombreOrigen where not exists "
                + "(select nBoe from historico.boletin where historico.boletin.nBoe = a.boe) group by boe";

        bd = new Sql(Variables.con);
//        bd.ejecutar(queryAppD);
        Variables.st.setBol_err(bd.getInt("select count(*) from app.boletines_duplicados"));
        Variables.st.setSan_err(bd.getInt("select count(*) from historico.temp_historico"));
//        bd.ejecutar(queryAppB);
        bd.ejecutar(queryAppI);
        bd.ejecutar(query);
        Variables.st.setBol(bd.getInt("select count(*) from historico.boletin"));
        bd.close();
    }

    private void procesaSancionados() throws SQLException {
        String query = "insert into historico.sancionado (nif,tipoJuridico) select cif,tipoJuridico from historico.temp_historico where not exists "
                + "(select cif from historico.sancionado where historico.sancionado.nif = historico.temp_historico.cif) group by cif";
        bd = new Sql(Variables.con);
        bd.ejecutar(query);
        bd.close();
    }

    private void procesaVehiculos() throws SQLException {
        String query = "insert into historico.vehiculo (matricula) select matricula from historico.temp_historico where not exists "
                + "(select matricula from historico.vehiculo where historico.vehiculo.matricula = historico.temp_historico.matricula) group by matricula";
        bd = new Sql(Variables.con);
        bd.ejecutar(query);
        bd.close();
    }

    private void procesaSanciones() throws SQLException {
        String query = "insert into historico.sancion (idSancion,expediente,fechaMulta,articulo,cuantia,puntos,nombre,localidad,linea,link) "
                + "select a.codigoSancion, a.expediente, a.fecha_multa, a.articulo, a.euros, a.puntos, a.nombre, a.poblacion, a.linea, a.link from historico.temp_historico as a "
                + "where not exists (select idSancion from historico.sancion where historico.sancion.idSancion = a.codigoSancion)";
        bd = new Sql(Variables.con);
        bd.ejecutar(query);
        Variables.st.setSan(bd.getInt("select count(*) from historico.sancion"));
        bd.close();
    }

    private void procesaMultas() throws SQLException {
        String query = "insert into historico.multa (idBoletin,idMatricula,idSancionado,idSancion,fase,plazo,fechaEntrada,fechaVencimiento) "
                + "select b.idBoletin,c.idVehiculo,d.idSancionado,a.codigoSancion,a.fase,a.plazo,CURDATE(),DATE_ADD(a.fecha_publicacion, interval a.plazo day) from historico.temp_historico as a "
                + "Left join historico.boletin as b on a.boe=b.nBoe "
                + "Left join historico.vehiculo as c on a.matricula=c.matricula "
                + "Left join historico.sancionado as d on a.cif=d.nif "
                + "where not exists (select codigoSancion from historico.sancion where historico.sancion.idSancion = a.codigoSancion)";
        bd = new Sql(Variables.con);
        bd.ejecutar(query);
        bd.close();
    }

    private void limpiar() throws SQLException {
        bd = new Sql(Variables.con);
        bd.ejecutar("delete from historico.temp_historico");
        bd.ejecutar("delete from app.boletines_duplicados");
        bd.close();
    }

    private void procesaTimmingStats(Timming aux, String ex) throws SQLException {
        Variables.tm.setStatus(ex);
        bd = new Sql(Variables.con);
        bd.ejecutar(aux.SQLCrear());
        bd.ejecutar(Variables.st.SQLCrear());
        bd.close();
    }
}
