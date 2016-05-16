/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools, Templates
 * and open the template in the editor.
 */
package hiroZoid.dao;

import hiroZoid.database.DBConnection;
import hiroZoid.entity.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hiroshi
 */
public class FuncionarioDAO {

    public FuncionarioDAO() {
    }

    public ArrayList<Funcionario> listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT CODF, NOMEF, IDADE, SALARIO, NROA FROM funcionario");

            while (rs.next()) {
                funcionarios.add(new Funcionario(
                        rs.getInt("CODF"),
                        rs.getString("NOMEF"),
                        rs.getInt("IDADE"),
                        rs.getInt("SALARIO"),
                        rs.getInt("NROA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public int inserir(Funcionario funcionario) {
        String query = "INSERT INTO funcionario(CODF, NOMEF, IDADE, SALARIO, NROA) VALUES("
                + funcionario.getCodigoFuncionario() + ", "
                + DaoHelper.INSTANCE.quote(funcionario.getNome()) + ", "
                + funcionario.getIdade() + ", "
                + funcionario.getSalario() + ", "
                + funcionario.getNroAmbulatorio()
                + ")";
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int alterar(Funcionario funcionario) {
        String query = "UPDATE funcionario SET"
                + " NOMEF=" + DaoHelper.INSTANCE.quote(funcionario.getNome()) + ","
                + " IDADE=" + funcionario.getIdade() + ","
                + " SALARIO=" + funcionario.getSalario() + ","
                + " NROA=" + funcionario.getNroAmbulatorio()
                + " WHERE CODF=" + funcionario.getCodigoFuncionario();
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deletar(int codigoFuncionario) {
        String query = "DELETE FROM funcionario WHERE CODF=" + codigoFuncionario;
        try {
            return DBConnection.INSTANCE.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Funcionario buscar(int codigoFuncionario) {
        Funcionario funcionario = null;

        try {
            ResultSet rs = DBConnection.INSTANCE
                    .executeQuery("SELECT CODF, NOMEF, IDADE, SALARIO, NROA "
                            + "FROM paciente WHERE CODP=" + codigoFuncionario);

            while (rs.next()) {
                funcionario = new Funcionario(
                        rs.getInt("CODF"),
                        rs.getString("NOMEF"),
                        rs.getInt("IDADE"),
                        rs.getInt("SALARIO"),
                        rs.getInt("NROA")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    public int referencias(int codigoFuncionario) {
        return 0;
    }

}
