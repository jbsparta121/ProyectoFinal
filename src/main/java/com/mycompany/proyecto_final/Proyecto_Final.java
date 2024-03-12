package com.mycompany.proyecto_final;

import com.mycompany.proyecto_final.controlador.CntFrmMainMenu;
import com.mycompany.proyecto_final.controlador.CntNumerosApartamentos;
import com.mycompany.proyecto_final.modelo.Apartamento;
import com.mycompany.proyecto_final.modelo.DAO.ApartamentoDAO;
import com.mycompany.proyecto_final.vista.FrmMenu;
import com.mycompany.proyecto_final.vista.FrmNumeroApartamentos;
import java.util.List;

public class Proyecto_Final {

    public static void main(String[] args) {
        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        List<Apartamento> apartamentos = apartamentoDAO.buscaAparatamentos();
        if (apartamentos == null || apartamentos.isEmpty()) {
            FrmNumeroApartamentos frmNumeroApartamentos = new FrmNumeroApartamentos();
            CntNumerosApartamentos cntNumerosApartamentos = new CntNumerosApartamentos(frmNumeroApartamentos);
            frmNumeroApartamentos.setVisible(true);
        } else {
            FrmMenu frmMenu = new FrmMenu();
            CntFrmMainMenu cntFrmMainMenu = new CntFrmMainMenu(frmMenu);
            frmMenu.setVisible(true);
        }
    }
}
