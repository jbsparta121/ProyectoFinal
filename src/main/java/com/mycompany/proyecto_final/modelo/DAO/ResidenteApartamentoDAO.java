package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.ResidenteApartamento;
import java.util.List;
import org.hibernate.HibernateException;

public class ResidenteApartamentoDAO extends ModeloDAO{

    public boolean grabar(ResidenteApartamento residenteApartamento) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(residenteApartamento);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }
    
    public List<ResidenteApartamento> listarResidenteApartamento(Integer idResidente) {
        List<ResidenteApartamento> listadoResidenteApartamento = null;
        
        try {
            iniciaOperacion();
            listadoResidenteApartamento = sesion.createQuery("from ResidenteApartamento " + 
                    "where residente = " + idResidente + " order by id").list();
        } catch (HibernateException e) {
            manejaException(e);
        }
        return listadoResidenteApartamento;
    }
    
    public List<ResidenteApartamento> listaResidenteApartamento() {
        List<ResidenteApartamento> listadoResidenteApartamento = null;
        
        try {
            iniciaOperacion();
            listadoResidenteApartamento = sesion.createQuery("from ResidenteApartamento").list();
        } catch (HibernateException e) {
            manejaException(e);
        }
        return listadoResidenteApartamento;
    }
}
