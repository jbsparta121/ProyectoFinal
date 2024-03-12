package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "item_cobro")
public class ItemCobro implements Serializable{
    
    @Id
    @ManyToOne
    @JoinColumn(name = "cobro")
    private Cobro cobro;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "concepto")
    private Concepto concepto;
    
    @Column(name="valor_cobro")
    private Double valor_cobro;

    public ItemCobro() {
    }

    public ItemCobro(Cobro cobro, Concepto concepto, Double valor_cobro) {
        this.cobro = cobro;
        this.concepto = concepto;
        this.valor_cobro = valor_cobro;
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

    public Double getValor_cobro() {
        return valor_cobro;
    }

    public void setValor_cobro(Double valor_cobro) {
        this.valor_cobro = valor_cobro;
    }
}
