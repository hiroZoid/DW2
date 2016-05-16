/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.dao;

import hiroZoid.database.DBConnection;
import hiroZoid.entity.Ambulatorio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salina
 */
public class AmbulatorioDAO {

    public AmbulatorioDAO() {
    }

    public ArrayList<Ambulatorio> listarAmbulatorios() {
        ArrayList<Ambulatorio> ambulatorios = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT * FROM ambulat");

            while (rs.next()) {
                ambulatorios.add(new Ambulatorio(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ambulatorios;
    }

    public Ambulatorio pesquisarPorCodigo(int codigo) {
        Ambulatorio ambulatorio = null;
        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT * FROM ambulat WHERE NROA = " + codigo);
            if (rs.next()) {
                ambulatorio = new Ambulatorio(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ambulatorio;
    }

    public int incluirAmbulatorio(Ambulatorio ambulatorio) {
        String qry = "INSERT INTO ambulat VALUES("
                + ambulatorio.getNroAmbulatorio()
                + "," + ambulatorio.getAndar()
                + "," + ambulatorio.getCapacidade()
                + ")";
        try {
            return DBConnection.INSTANCE.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void removerAmbulatorio(int nroAmbulatorio) {
        String qry = "DELETE FROM ambulat WHERE NROA=" + nroAmbulatorio;
        try {
            DBConnection.INSTANCE.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removerAmbulatorio(Ambulatorio ambulatorio) {
        this.removerAmbulatorio(ambulatorio.getNroAmbulatorio());
    }

    public int alterarAmbulatorio(Ambulatorio ambulatorio) {
        String qry = "UPDATE ambulat SET ANDAR=" + ambulatorio.getAndar()
                + " , CAPACIDADE=" + ambulatorio.getCapacidade()
                + " WHERE NROA=" + ambulatorio.getNroAmbulatorio();
        try {
            return DBConnection.INSTANCE.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int referencias(int nroAmbulatorio) {
        String qry
                = "SELECT SUM(cnt) FROM ("
                + "SELECT count(1) cnt FROM funcionario WHERE NROA=" + nroAmbulatorio
                + " UNION "
                + "SELECT count(1) cnt FROM medico WHERE NROA=" + nroAmbulatorio
                + ") temp";
        try {
            return DBConnection.INSTANCE.executeScalar(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
