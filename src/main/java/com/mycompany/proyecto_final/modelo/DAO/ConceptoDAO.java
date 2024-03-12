
package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.Concepto;
import java.util.List;
import org.hibernate.HibernateException;

public class ConceptoDAO extends ModeloDAO{
    
    public boolean grabar(Concepto concepto) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(concepto);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }
    
    public Concepto consultar(int id) {
        Concepto concepto = null;
        try {
            iniciaOperacion();
            concepto = (Concepto) sesion.get(Concepto.class, id);
        } catch (HibernateException e) {
            manejaException(e);
        } finally {
            sesion.close();
        }
        return concepto;
    }
    
    public List<Concepto> listaConceptos() {
        List<Concepto> listadoConceptos = null;
        try {
            iniciaOperacion();
            listadoConceptos = sesion.createQuery("from Concepto").list();
        } catch (HibernateException e) {
            manejaException(e);
        } finally {
            return listadoConceptos;
        }
    }
}
