/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools, Templates
 * and open the template in the editor.
 */
package hiroZoid.dao;

import hiroZoid.database.DBConnection;
import hiroZoid.entity.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hiroshi
 */
public class PacienteDAO {

    public PacienteDAO() {
    }

    public ArrayList<Paciente> listarPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT CODP, NOMEP, IDADE, CIDADEP, PLANO FROM paciente");

            while (rs.next()) {
                pacientes.add(new Paciente(
                        rs.getInt("CODP"),
                        rs.getString("NOMEP"),
                        rs.getInt("IDADE"),
                        rs.getString("CIDADEP"),
                        rs.getString("PLANO")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public int inserir(Paciente paciente) {
        String query = "INSERT INTO paciente(CODP, NOMEP, IDADE, CIDADEP, PLANO) VALUES("
                + paciente.getCodigoPaciente() + ", "
                + "" + DaoHelper.INSTANCE.quote(paciente.getNome()) + ", "
                + paciente.getIdade() + ", "
                + "" + DaoHelper.INSTANCE.quote(paciente.getCidade()) + ", "
                + "" + DaoHelper.INSTANCE.quote(paciente.getPlano()) + ""
                + ")";
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int alterar(Paciente paciente) {
        String query = "UPDATE paciente SET"
                + " NOMEP=" + DaoHelper.INSTANCE.quote(paciente.getNome()) + ","
                + " IDADE=" + paciente.getIdade() + ","
                + " CIDADEP=" + DaoHelper.INSTANCE.quote(paciente.getCidade()) + ","
                + " PLANO=" + DaoHelper.INSTANCE.quote(paciente.getPlano()) + ""
                + " WHERE CODP=" + paciente.getCodigoPaciente();
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deletar(int codigoPaciente) {
        String query = "DELETE FROM paciente WHERE CODP=" + codigoPaciente;
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Paciente buscar(int codigoPaciente) {
        Paciente paciente = null;

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT CODP, NOMEP, IDADE, CIDADEP, PLANO "
                            + "FROM paciente WHERE CODP=" + codigoPaciente);

            while (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("CODP"),
                        rs.getString("NOMEP"),
                        rs.getInt("IDADE"),
                        rs.getString("CIDADEP"),
                        rs.getString("PLANO")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    public int referencias(int codigoPaciente) {
        String qry = "SELECT count(1) FROM consulta WHERE CODP=" + codigoPaciente;
        try {
            return DBConnection.INSTANCE.executeScalar(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
