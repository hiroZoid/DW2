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
public class Ambulatorio {
    private int nroAmbulatorio;
    private int andar;
    private int capacidade;

    public Ambulatorio() {
    }

    public Ambulatorio(int nroAmbulatorio, int andar, int capacidade) {
        this.nroAmbulatorio = nroAmbulatorio;
        this.andar = andar;
        this.capacidade = capacidade;
    }

    public int getNroAmbulatorio() {
        return nroAmbulatorio;
    }

    public void setNroAmbulatorio(int nroAmbulatorio) {
        this.nroAmbulatorio = nroAmbulatorio;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
}
