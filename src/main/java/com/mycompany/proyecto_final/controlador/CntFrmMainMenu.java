package com.mycompany.proyecto_final.controlador;

import com.mycompany.proyecto_final.modelo.DAO.*;
import com.mycompany.proyecto_final.vista.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class CntFrmMainMenu extends MouseAdapter {

    FrmMenu frmMainMenu;

    public CntFrmMainMenu(FrmMenu frmMainMenu) {
        this.frmMainMenu = frmMainMenu;
        registrarControladores();
    }

    private void registrarControladores() {
        frmMainMenu.getPropietario().addMouseListener(this);
        frmMainMenu.getApartamento().addMouseListener(this);
        frmMainMenu.getResidente().addMouseListener(this);
        frmMainMenu.getCobro().addMouseListener(this);
        frmMainMenu.getConcepto().addMouseListener(this);
    }

    private void openFrmApartamento() {
        if (FrmApartamentos.getContador() == 0) {
            FrmApartamentos frmApartamento = new FrmApartamentos();
            ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
            CntApartamentos cntFrmApartamento = new CntApartamentos(frmApartamento, apartamentoDAO);
            frmMainMenu.add(frmApartamento);
            frmApartamento.setVisible(true);
            FrmApartamentos.setContador(1);
        }
    }

    private void openFrmPropietario() {
        if (FrmPropietario.getContador() == 0) {
            FrmPropietario frmPropietario = new FrmPropietario();
            PersonaDAO personaDAO = new PersonaDAO();
            ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
            PropietarioApartamentoDAO propietarioApartamentoDAO = new PropietarioApartamentoDAO();
            CntPropietario cntApartamentos = new CntPropietario(frmPropietario, personaDAO, propietarioApartamentoDAO, apartamentoDAO);
            frmMainMenu.add(frmPropietario);
            frmPropietario.setVisible(true);
            FrmPropietario.setContador(1);
        }
    }

    private void openFrmResidente() {
        if (FrmResidente.getContador() == 0) {
            FrmResidente frmResidente = new FrmResidente();
            PersonaDAO personaDAO = new PersonaDAO();
            ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
            ResidenteApartamentoDAO residenteApartamentoDAO = new ResidenteApartamentoDAO();
            CntResidente cntResidente = new CntResidente(frmResidente, residenteApartamentoDAO, personaDAO, apartamentoDAO);
            frmMainMenu.add(frmResidente);
            frmResidente.setVisible(true);
            FrmResidente.setContador(1);
        }
    }

    private void openFrmCobro() {
        if (FrmCobro.getContador() == 0) {
            FrmCobro frmCobros = new FrmCobro();
            CobroDAO cobroDAO = new CobroDAO();
            MesesGeneradosDAO mesesGeneradosDAO = new MesesGeneradosDAO();
            CntCobro cntFrmCobro = new CntCobro(frmCobros, cobroDAO, mesesGeneradosDAO);
            if (cntFrmCobro.buscarMesGenerado()) {
                JOptionPane.showMessageDialog(null, "Cuenta ya fue generada");
                FrmCobro.setContador(1);
            } else {
                frmMainMenu.add(frmCobros);
                frmCobros.setVisible(true);
                FrmCobro.setContador(1);
            }
        }
    }
    
    private void openFrmConcepto() {
        if (FrmConcepto.getContador() == 0) {
            FrmConcepto frmConcepto = new FrmConcepto();
            ConceptoDAO conceptoDAO = new ConceptoDAO();
            CntConcepto cntConcepto = new CntConcepto(frmConcepto,conceptoDAO);
            frmMainMenu.add(frmConcepto);
            frmConcepto.setVisible(true);
            FrmConcepto.setContador(1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmMainMenu.getPropietario()) {
            openFrmPropietario();
        } else if (e.getSource() == frmMainMenu.getApartamento()) {
            openFrmApartamento();
        } else if (e.getSource() == frmMainMenu.getResidente()) {
            openFrmResidente();
        } else if (e.getSource() == frmMainMenu.getCobro()) {
            openFrmCobro();
        } else if (e.getSource() == frmMainMenu.getConcepto()) {
            openFrmConcepto();
        } 
    }
}
