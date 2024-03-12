package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class MesesGeneradosId implements Serializable {
    
    @Column(name="mes")
    private String mes;
    
    @Column(name="vigencia")
    private Integer vigencia;
    
    public MesesGeneradosId() {
    }

    public MesesGeneradosId(String mes, Integer vigencia) {
        this.mes = mes;
        this.vigencia = vigencia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }
}
