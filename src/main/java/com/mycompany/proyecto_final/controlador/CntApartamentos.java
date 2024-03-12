package com.mycompany.proyecto_final.controlador;

import com.mycompany.proyecto_final.modelo.*;
import com.mycompany.proyecto_final.modelo.DAO.*;
import com.mycompany.proyecto_final.vista.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class CntApartamentos extends InternalFrameAdapter {

    private FrmApartamentos frmApartamentos;
    private FrmPropietario frmPropietario;
    private Apartamento apartamento;
    private ApartamentoDAO apartamentoDAO;
    private Set<Integer> listaApartamentosModificados;
    int fila;

    public CntApartamentos(FrmApartamentos frmApartamentos, ApartamentoDAO apartamentoDAO) {
        this.frmApartamentos = frmApartamentos;
        this.apartamentoDAO = apartamentoDAO;
        listaApartamentosModificados = new HashSet();
        registrarControladores();
    }

    private void registrarControladores() {
        frmApartamentos.addInternalFrameListener(this);
        registrarControladorTabla();
        registrarControladorBotonActualizar();
    }

    private void registrarControladorTabla() {
        frmApartamentos.getTblApartamentos().addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fila = frmApartamentos.getTblApartamentos().getSelectedRow();
                listaApartamentosModificados.add(fila);
            }
        });
    }

    private void registrarControladorBotonActualizar() {
        frmApartamentos.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
                listaApartamentosModificados.removeAll(listaApartamentosModificados);
            }
        });
    }

    private void actualizar() {
        Integer numeroUnico, numeroApartamento, numeroBloque, matricula, numeroParqueadero;
        String asignado, disponible;
        int numeroApartamentosActualizados = 0;
        Object parqueadero;

        for (Integer i : listaApartamentosModificados) {

            matricula = frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 3) == null ? null : Integer.valueOf(frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 3).toString());
            parqueadero = (frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 4) == null ? null : frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 4));
                
            if (matricula != null || parqueadero != null) {
                numeroUnico = Integer.valueOf(frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 0).toString());
                numeroApartamento = Integer.valueOf(frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 1).toString());
                numeroBloque = Integer.valueOf(frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 2).toString());
                numeroParqueadero = Integer.valueOf(parqueadero.toString());
                asignado = frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 5).toString();
                disponible = frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 6).toString();
                apartamento = new Apartamento(numeroUnico, numeroApartamento, numeroBloque, matricula, numeroParqueadero, asignado, disponible);

                if (apartamentoDAO.actualizar(apartamento)) {
                    numeroApartamentosActualizados = numeroApartamentosActualizados + 1;
                } else {
                    JOptionPane.showMessageDialog(null, "Apartamento no actualizado");
                }
            } else {
                numeroUnico = Integer.valueOf(frmApartamentos.getDtmModelTableApartamentos().getValueAt(i, 0).toString());
                JOptionPane.showMessageDialog(null, "No hay nada que actualizar en la linea " + numeroUnico);
            }
        }
        JOptionPane.showMessageDialog(null, "Apartamentos actualizados " + numeroApartamentosActualizados);
    }

    private void cargarTablaApartamento() {
        List<Apartamento> listadoApartamentos = apartamentoDAO.listarApartamentos();
        Object itemApartamento[] = new Object[7];

        for (Apartamento apartamento : listadoApartamentos) {
            itemApartamento[0] = apartamento.getNumeroUnico();
            itemApartamento[1] = apartamento.getNumeroApartamento();
            itemApartamento[2] = apartamento.getNumeroBloque();
            itemApartamento[3] = apartamento.getMatricula();
            itemApartamento[4] = apartamento.getParqueadero();
            itemApartamento[5] = apartamento.getAsignado();
            itemApartamento[6] = apartamento.getDisponible();
            frmApartamentos.getDtmModelTableApartamentos().addRow(itemApartamento);
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        cargarTablaApartamento();
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        {
            frmApartamentos.dispose();
            FrmApartamentos.setContador(0);
        }
    }
}
