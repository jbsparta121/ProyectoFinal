package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.*;
import java.util.List;
import org.hibernate.HibernateException;

public class PersonaDAO extends ModeloDAO{

    public boolean grabar(Persona persona) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(persona);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }
    
    public boolean actualizar(Persona persona) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.update(persona);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
            ok = false;
        }
        return ok;
    }
    
    public Persona consultar(int id) {
        Persona persona = null;
        try {
            iniciaOperacion();
            persona = (Persona) sesion.get(Persona.class, id);
        } catch (HibernateException e) {
            manejaException(e);
        } finally {
            sesion.close();
        }
        return persona;
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
    
    public List<Persona> listarPersonaPropietario() {
        List<Persona> listarPersonaPropietario = null;
        
        try {
            iniciaOperacion();
            listarPersonaPropietario = sesion.createQuery("from Persona where tipo ='Propietario' order by numeroUnico").list();
        } catch(HibernateException e) {
            manejaException(e);
        } finally {
            return listarPersonaPropietario;
        }
    }
}
