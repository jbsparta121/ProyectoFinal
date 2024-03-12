package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.Apartamento;
import java.util.List;
import org.hibernate.HibernateException;

public class ApartamentoDAO extends ModeloDAO{

    public boolean grabar(Apartamento apartamento) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(apartamento);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }

    public boolean actualizar(Apartamento apartamento) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.update(apartamento);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
            ok = false;
        }
        return ok;
    }

    public List<Apartamento> listarApartamentos() {
        List<Apartamento> listadoApartamentos = null;

        try {
            iniciaOperacion();
            listadoApartamentos = sesion.createQuery("from Apartamento order by numeroUnico").list();
        } catch (HibernateException e) {
            manejaException(e);
        } finally {
            return listadoApartamentos;
        }
    }
    
    public List<Apartamento> buscaAparatamentos() {
        List<Apartamento> apartamentos = null;
        iniciaOperacion();
        try {
            apartamentos = sesion.createQuery("from Apartamento").list();
        } catch (HibernateException e) {
            manejaException(e);
        }
        return apartamentos;
    }

    public List<Apartamento> listarApartamentosNoAsignado() {
        List<Apartamento> listarApartamentosNoAsignado = null;

        try {
            iniciaOperacion();
            listarApartamentosNoAsignado = sesion.createQuery("from Apartamento where asignado ='N' order by numeroUnico").list();
        } catch (HibernateException e) {
            manejaException(e);
        } finally {
            return listarApartamentosNoAsignado;
        }
    }
    
    public List<Apartamento> listarApartamentosDisponibles() {
        List<Apartamento> listarApartamentosDisponibles = null;
        
        try {
            iniciaOperacion();
            listarApartamentosDisponibles = sesion.createQuery("from Apartamento where disponible ='S' order by numeroUnico").list();
        } catch(HibernateException e) {
            manejaException(e);
        } finally {
            return listarApartamentosDisponibles;
        }
    }
}
