package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    @Id
    @Column(name = "id")
    private Integer idPersona;

    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "nombre1")
    private String nombre1;

    @Column(name = "nombre2")
    private String nombre2;

    @Column(name = "celular")
    private String celularPersona;

    @Column(name = "email")
    private String emailPersona;

    @Column(name = "tipo")
    private String tipo;
    
    @OneToMany(mappedBy = "propietario",cascade = CascadeType.ALL)
    private Set<PropietarioApartamento> listadoPropietarioApartamento;
    
    @OneToMany(mappedBy = "residente",cascade = CascadeType.ALL)
    private Set<ResidenteApartamento> listadoResidenteApartamento;

    public Persona() {
    }

    public Persona(Integer idPersona, String apellido1, String apellido2, String nombre1, String nombre2, String celularPersona, String emailPersona, String tipo) {
        this.idPersona = idPersona;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.celularPersona = celularPersona;
        this.emailPersona = emailPersona;
        this.tipo = tipo;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getCelularPersona() {
        return celularPersona;
    }

    public void setCelularPersona(String celularPersona) {
        this.celularPersona = celularPersona;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<PropietarioApartamento> getListadoPropietarioApartamento() {
        return listadoPropietarioApartamento;
    }

    public void setListadoPropietarioApartamento(Set<PropietarioApartamento> listadoPropietarioApartamento) {
        this.listadoPropietarioApartamento = listadoPropietarioApartamento;
    }

    public Set<ResidenteApartamento> getListadoResidenteApartamento() {
        return listadoResidenteApartamento;
    }

    public void setListadoResidenteApartamento(Set<ResidenteApartamento> listadoResidenteApartamento) {
        this.listadoResidenteApartamento = listadoResidenteApartamento;
    }
}
