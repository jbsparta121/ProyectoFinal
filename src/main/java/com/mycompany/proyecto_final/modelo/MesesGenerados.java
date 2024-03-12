package com.mycompany.proyecto_final.modelo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "meses_generados")
public class MesesGenerados implements Serializable {
    
    @EmbeddedId
    private MesesGeneradosId mesesGeneradosId;
    
    @Column(name="generado")
    private boolean generado;
    
    @OneToMany(mappedBy="mesesGenerados", cascade=CascadeType.ALL)
    private Set<Cobro> listadoCobros;

    public MesesGenerados() {
    }

    public MesesGenerados(MesesGeneradosId mesesGeneradosId) {
        this.mesesGeneradosId = mesesGeneradosId;
    }

    public MesesGenerados(MesesGeneradosId mesesGeneradosId, boolean generado) {
        this.mesesGeneradosId = mesesGeneradosId;
        this.generado = generado;
    }

    public MesesGenerados(MesesGeneradosId mesesGeneradosId, Set<Cobro> listadoCobros) {
        this.mesesGeneradosId = mesesGeneradosId;
        this.listadoCobros = listadoCobros;
    }

    public MesesGeneradosId getMesesGeneradosId() {
        return mesesGeneradosId;
    }

    public void setMesesGeneradosId(MesesGeneradosId mesesGeneradosId) {
        this.mesesGeneradosId = mesesGeneradosId;
    }

    public Set<Cobro> getListadoCobros() {
        return listadoCobros;
    }

    public void setListadoCobros(Set<Cobro> listadoCobros) {
        this.listadoCobros = listadoCobros;
    }

    public boolean isGenerado() {
        return generado;
    }

    public void setGenerado(boolean generado) {
        this.generado = generado;
    }
}
