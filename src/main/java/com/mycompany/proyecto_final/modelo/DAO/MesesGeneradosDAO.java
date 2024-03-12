package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.*;
import org.hibernate.HibernateException;

public class MesesGeneradosDAO extends ModeloDAO{

    public boolean grabar(MesesGenerados mesesGenerados) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(mesesGenerados);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }

    public Object buscaMesesGenerados(MesesGeneradosId mesesGeneradosId) {
        Object mesesGenerados = null;
        iniciaOperacion();
        try {
            mesesGenerados = sesion.createQuery("select mgen.mesesGeneradosId from MesesGenerados mgen").uniqueResult();
        } catch (HibernateException e) {
            manejaException(e);
        }
        return mesesGenerados;
    }
}
