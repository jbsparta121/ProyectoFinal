package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.PropietarioApartamento;
import java.util.List;
import org.hibernate.HibernateException;

public class PropietarioApartamentoDAO extends ModeloDAO{

    public boolean grabar(PropietarioApartamento propietarioApartamento) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(propietarioApartamento);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }
    
    public boolean actualizar(PropietarioApartamento propietarioApartamento) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.update(propietarioApartamento);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
            ok = false;
        }
        return ok;
    }
    
    public List<PropietarioApartamento> listarPropietarioApartamento(Integer idPropietario) {
        List<PropietarioApartamento> listadoPropietarioApartamento = null;
        
        try {
            iniciaOperacion();
            listadoPropietarioApartamento = sesion.createQuery("from PropietarioApartamento " + 
                    "where propietario = " + idPropietario + " order by id").list();
        } catch (HibernateException e) {
            manejaException(e);
        }
        return listadoPropietarioApartamento;
    }
    
    public List<PropietarioApartamento> listaPropietarioApartamento() {
        List<PropietarioApartamento> listadoPropietarioApartamento = null;
        
        try {
            iniciaOperacion();
            listadoPropietarioApartamento = sesion.createQuery("from PropietarioApartamento").list();
        } catch (HibernateException e) {
            manejaException(e);
        }
        return listadoPropietarioApartamento;
    }
}
