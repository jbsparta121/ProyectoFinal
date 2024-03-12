package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "concepto")
public class Concepto implements Serializable{

    @Id
    @Column(name = "numero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer numero;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "valor")
    private Double valor;
    
    @OneToMany(mappedBy = "concepto")
    private Set<ItemCobro> listadoCobros;

    public Concepto() {
    }

    public Concepto(String descripcion, Double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Concepto(String descripcion, Double valor, Set<ItemCobro> listadoCobros) {
        this.descripcion = descripcion;
        this.valor = valor;
        this.listadoCobros = listadoCobros;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Set<ItemCobro> getListadoCobros() {
        return listadoCobros;
    }

    public void setListadoCobros(Set<ItemCobro> listadoCobros) {
        this.listadoCobros = listadoCobros;
    }
}
