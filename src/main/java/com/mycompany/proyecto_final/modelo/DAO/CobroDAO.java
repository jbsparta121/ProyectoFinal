
package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.Cobro;
import org.hibernate.HibernateException;

public class CobroDAO extends ModeloDAO{

    public boolean grabar(Cobro cobro) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(cobro);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }
}
