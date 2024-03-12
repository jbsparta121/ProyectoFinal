package com.mycompany.proyecto_final.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "apartamento")
public class Apartamento implements Serializable {

    @Id
    @Column(name = "numero_unico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer numeroUnico;

    @Column(name = "numero_apartamento")
    private Integer numeroApartamento;

    @Column(name = "numero_bloque")
    private Integer numeroBloque;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "parqueadero")
    private Integer parqueadero;

    @Column(name = "asignado")
    private String asignado;
    
    @Column(name = "disponible")
    private String disponible;

    @OneToMany(mappedBy = "apartamento", cascade = CascadeType.ALL)
    private Set<PropietarioApartamento> listadoPropietarioApartamento;

    public Apartamento() {
    }

    public Apartamento(Integer numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public Apartamento(Integer numeroUnico, Integer numeroApartamento, Integer numeroBloque, String asignado, String disponible) {
        this.numeroUnico = numeroUnico;
        this.numeroApartamento = numeroApartamento;
        this.numeroBloque = numeroBloque;
        this.asignado = asignado;
        this.disponible = disponible;
    }

    public Apartamento(Integer numeroUnico, Integer numeroApartamento, Integer numeroBloque, Integer matricula, Integer parqueadero, String asignado, String disponible) {
        this.numeroUnico = numeroUnico;
        this.numeroApartamento = numeroApartamento;
        this.numeroBloque = numeroBloque;
        this.matricula = matricula;
        this.parqueadero = parqueadero;
        this.asignado = asignado;
        this.disponible = disponible;
    }

    public Integer getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(Integer numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public Integer getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(Integer numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public Integer getNumeroBloque() {
        return numeroBloque;
    }

    public void setNumeroBloque(Integer numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Integer parqueadero) {
        this.parqueadero = parqueadero;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public Set<PropietarioApartamento> getListadoPropietarioApartamento() {
        return listadoPropietarioApartamento;
    }

    public void setListadoPropietarioApartamento(Set<PropietarioApartamento> listadoPropietarioApartamento) {
        this.listadoPropietarioApartamento = listadoPropietarioApartamento;
    }
}
