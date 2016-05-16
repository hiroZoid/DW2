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
public class Paciente {

    private int codigoPaciente;
    private String nome;
    private int idade;
    private String cidade;
    private String plano;

    public Paciente() {
    }
    
    public Paciente(int codigoPaciente, String nome, int idade, String cidade, String plano) {
        this.codigoPaciente = codigoPaciente;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.plano = plano;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }
    
}
