/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools, Templates
 * and open the template in the editor.
 */
package hiroZoid.dao;

import hiroZoid.database.DBConnection;
import hiroZoid.entity.Medico;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Salina
 */
public class MedicoDAO {

    public ArrayList<Medico> listarMedicos() {
        ArrayList<Medico> medicos = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.INSTANCE.executeQuery("SELECT * FROM medico");
            while (rs.next()) {
                medicos.add(new Medico(
                        rs.getInt("CODM"),
                        rs.getString("NOMEM"),
                        rs.getInt("IDADE"),
                        rs.getString("ESPECIALIDADE"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getInt("NROA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
    }

    public Medico buscar(int codigoMedico) {
        Medico medico = null;

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT * FROM medico WHERE CODM=" + codigoMedico);
            while (rs.next()) {
                medico = new Medico(
                        rs.getInt("CODM"),
                        rs.getString("NOMEM"),
                        rs.getInt("IDADE"),
                        rs.getString("ESPECIALIDADE"),
                        rs.getString("CIDADE"),
                        rs.getString("ESTADO"),
                        rs.getInt("NROA")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medico;
    }

    public int inserir(Medico medico) {
        String query = "INSERT INTO medico(CODM, NOMEM, IDADE, ESPECIALIDADE, CIDADE, ESTADO, NROA) VALUES("
                + medico.getCodigo() + ", "
                + DaoHelper.INSTANCE.quote(medico.getNome()) + ", "
                + medico.getIdade() + ", "
                + DaoHelper.INSTANCE.quote(medico.getEspecialidade()) + ", "
                + DaoHelper.INSTANCE.quote(medico.getCidade()) + ", "
                + DaoHelper.INSTANCE.quote(medico.getEstado()) + ", "
                + medico.getNroAmbulatorio()
                + ")";
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int alterar(Medico medico) {
        String query = "UPDATE medico SET"
                + " NOMEM=" + DaoHelper.INSTANCE.quote(medico.getNome()) + ","
                + " IDADE=" + medico.getIdade() + ","
                + " ESPECIALIDADE=" + DaoHelper.INSTANCE.quote(medico.getEspecialidade()) + ","
                + " CIDADE=" + DaoHelper.INSTANCE.quote(medico.getCidade()) + ","
                + " ESTADO=" + DaoHelper.INSTANCE.quote(medico.getEstado()) + ","
                + " NROA=" + medico.getNroAmbulatorio()
                + " WHERE CODM=" + medico.getCodigo();
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deletar(int codigoMedico) {
        String query = "DELETE FROM medico WHERE CODM=" + codigoMedico;
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int referencias(int codigoMedico) {
        String qry = "SELECT count(1) FROM consulta WHERE CODM=" + codigoMedico;
        try {
            return DBConnection.INSTANCE.executeScalar(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
