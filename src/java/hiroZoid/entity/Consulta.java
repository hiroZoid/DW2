/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.entity;

import java.util.Date;

/**
 *
 * @author hiroshi
 */
public class Consulta {

    private int codigoMedico;
    private int codigoPaciente;
    private Date data;

    public Consulta(int codigoMedico, int codigoPaciente, Date data) {
        this.codigoMedico = codigoMedico;
        this.codigoPaciente = codigoPaciente;
        this.data = data;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
