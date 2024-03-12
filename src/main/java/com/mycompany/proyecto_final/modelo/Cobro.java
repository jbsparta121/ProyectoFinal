package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "cobro")
public class Cobro implements Serializable {

    @Id
    @Column(name = "numero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer numero;

    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;
    
    @ManyToOne
    @JoinColumn(name = "propietario_apartamento")
    private PropietarioApartamento propietarioApartamento;
    
    @ManyToOne
    @JoinColumn(name = "residente_apartamento")
    private ResidenteApartamento residenteApartamento;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="mes", referencedColumnName="mes")
        ,
        @JoinColumn(name="vigencia", referencedColumnName="vigencia")})
    private MesesGenerados mesesGenerados;
    
    @OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL)
    private Set<ItemCobro> listadoItemCobro;

    public Cobro() {
    }

    public Cobro(Date fechaGeneracion, PropietarioApartamento propietarioApartamento) {
        this.fechaGeneracion = fechaGeneracion;
        this.propietarioApartamento = propietarioApartamento;
    }

    public Cobro(Integer numero, Date fechaGeneracion, PropietarioApartamento propietarioApartamento, ResidenteApartamento residenteApartamento, MesesGenerados mesesGenerados) {
        this.numero = numero;
        this.fechaGeneracion = fechaGeneracion;
        this.propietarioApartamento = propietarioApartamento;
        this.residenteApartamento = residenteApartamento;
        this.mesesGenerados = mesesGenerados;
    }

    public Cobro(Integer numero, Date fechaGeneracion, PropietarioApartamento propietarioApartamento, ResidenteApartamento residenteApartamento) {
        this.numero = numero;
        this.fechaGeneracion = fechaGeneracion;
        this.propietarioApartamento = propietarioApartamento;
        this.residenteApartamento = residenteApartamento;
    }

    public Cobro(Date fechaGeneracion, PropietarioApartamento propietarioApartamento, ResidenteApartamento residenteApartamento, MesesGenerados mesesGenerados) {
        this.fechaGeneracion = fechaGeneracion;
        this.propietarioApartamento = propietarioApartamento;
        this.residenteApartamento = residenteApartamento;
        this.mesesGenerados = mesesGenerados;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public PropietarioApartamento getPropietarioApartamento() {
        return propietarioApartamento;
    }

    public void setPropietarioApartamento(PropietarioApartamento propietarioApartamento) {
        this.propietarioApartamento = propietarioApartamento;
    }

    public ResidenteApartamento getResidenteApartamento() {
        return residenteApartamento;
    }

    public void setResidenteApartamento(ResidenteApartamento residenteApartamento) {
        this.residenteApartamento = residenteApartamento;
    }

    public MesesGenerados getMesesGenerados() {
        return mesesGenerados;
    }

    public void setMesesGenerados(MesesGenerados mesesGenerados) {
        this.mesesGenerados = mesesGenerados;
    }

    public Set<ItemCobro> getListadoItemCobro() {
        return listadoItemCobro;
    }

    public void setListadoItemCobro(Set<ItemCobro> listadoItemCobro) {
        this.listadoItemCobro = listadoItemCobro;
    }
}
