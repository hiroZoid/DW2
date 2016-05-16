/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.dao;

import hiroZoid.database.DBConnection;
import hiroZoid.entity.Consulta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;

/**
 *
 * @author hiroshi
 */
public class ConsultaDAO {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public List<Consulta> listarConsultas() {
        ArrayList<Consulta> consultas = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT CODM, CODP, DATA FROM consulta");

            while (rs.next()) {
                consultas.add(new Consulta(
                        rs.getInt("CODM"),
                        rs.getInt("CODP"),
                        rs.getDate("DATA")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return consultas;
    }

    public int deletar(Consulta consulta) {
        String query = "DELETE FROM consulta WHERE "
                + " CODP=" + consulta.getCodigoPaciente()
                + " AND CODM=" + consulta.getCodigoMedico()
                + " AND DATA=" + DaoHelper.INSTANCE.quote(sdf.format(consulta.getData()));
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int inserir(Consulta consulta) {
        String query = "INSERT INTO consulta(CODM, CODP, DATA) VALUES("
                + consulta.getCodigoMedico() + ", "
                + consulta.getCodigoPaciente() + ", "
                + DaoHelper.INSTANCE.quote(sdf.format(consulta.getData()))
                + ")";
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int alterar(Consulta pk, Consulta consulta) {
        String query = "UPDATE consulta SET"
                + " CODP=" + consulta.getCodigoPaciente()
                + ", CODM=" + consulta.getCodigoMedico()
                + ", DATA=" + DaoHelper.INSTANCE.quote(sdf.format(consulta.getData()))
                + " WHERE"
                + " CODP=" + pk.getCodigoPaciente()
                + " AND CODM=" + pk.getCodigoMedico()
                + " AND DATA=" + DaoHelper.INSTANCE.quote(sdf.format(pk.getData()));
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public boolean existe(Consulta consulta) {
        String query = "SELECT COUNT(1) FROM consulta WHERE "
                + " CODP=" + consulta.getCodigoPaciente()
                + " AND CODM=" + consulta.getCodigoMedico()
                + " AND DATA=" + DaoHelper.INSTANCE.quote(sdf.format(consulta.getData()));
        try {
            return DBConnection.INSTANCE.executeScalar(query) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
