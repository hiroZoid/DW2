/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.entity;

/**
 *
 * @author hiroshi
 */
public class Funcionario {
    
    private int codigoFuncionario;
    private String nome;
    private int idade;
    private int salario;
    private int nroAmbulatorio;

    public Funcionario(int codigoFuncionario, String nome, int idade, int salario, int nroAmbulatorio) {
        this.codigoFuncionario = codigoFuncionario;
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.nroAmbulatorio = nroAmbulatorio;
    }

    public Funcionario() {
    }

    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getNroAmbulatorio() {
        return nroAmbulatorio;
    }

    public void setNroAmbulatorio(int nroAmbulatorio) {
        this.nroAmbulatorio = nroAmbulatorio;
    }
    
}
