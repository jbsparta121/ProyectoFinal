package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class ItemCobroId implements Serializable{
    
    @Column(name="cobro")
    private Cobro cobro;
    
    @Column(name="concepto")
    private Concepto concepto;

    public ItemCobroId() {
    }

    public ItemCobroId(Cobro cobro, Concepto concepto) {
        this.cobro = cobro;
        this.concepto = concepto;
    }

    public Cobro getCobro() {
        return cobro;
    }

    public void setCobro(Cobro cobro) {
        this.cobro = cobro;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }
}
