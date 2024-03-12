
package com.mycompany.proyecto_final.modelo.DAO;

import com.mycompany.proyecto_final.modelo.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModeloDAO {
    protected Session sesion;
    protected Transaction trns;

    public void iniciaOperacion() {
        sesion = HibernateUtil.getSessionFactory("edwar", "edwar").openSession();
        trns = sesion.beginTransaction();
    }

    protected void manejaException(HibernateException e) {
        trns.rollback();
        throw new HibernateException("Error en la capa de datos." + e);
    }
}
