/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.entity;

/**
 *
 * @author Salina
 */
public class Medico {

    private int codigo;
    private String nome;
    private int idade;
    private String especialidade;
    private String cidade;
    private String estado;
    private int nroAmbulatorio;

    public Medico() {
    }

    public Medico(int codigo, String nome, int idade, String especialidade, String cidade, String estado, int nroAmbulatorio) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.especialidade = especialidade;
        this.cidade = cidade;
        this.estado = estado;
        this.nroAmbulatorio = nroAmbulatorio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNroAmbulatorio() {
        return nroAmbulatorio;
    }

    public void setNroAmbulatorio(int nroAmbulatorio) {
        this.nroAmbulatorio = nroAmbulatorio;
    }

}
