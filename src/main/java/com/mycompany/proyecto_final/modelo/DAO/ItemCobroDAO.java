
package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.ItemCobro;
import org.hibernate.HibernateException;

public class ItemCobroDAO extends ModeloDAO{
    
    public boolean grabar(ItemCobro itemCobro) {
        boolean ok = false;

        try {
            iniciaOperacion();
            sesion.save(itemCobro);
            trns.commit();
            ok = true;
        } catch (HibernateException e) {
            manejaException(e);
        }
        return ok;
    }
}
