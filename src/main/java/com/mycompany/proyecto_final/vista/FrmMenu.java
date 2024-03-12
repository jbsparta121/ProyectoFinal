package com.mycompany.proyecto_final.vista;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class FrmMenu extends javax.swing.JFrame {

    public FrmMenu() {
        initComponents();
        setExtendedState(FrmMenu.MAXIMIZED_BOTH);
        ImageIcon backgroundImage = new ImageIcon("src\\main\\resources\\imagenes\\1.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()));
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        setContentPane(layeredPane);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = e.getComponent().getSize();
                backgroundLabel.setSize(newSize);
                layeredPane.repaint();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        propietario = new javax.swing.JMenu();
        apartamento = new javax.swing.JMenu();
        residente = new javax.swing.JMenu();
        cobro = new javax.swing.JMenu();
        concepto = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        propietario.setText("Propietario");
        jMenuBar1.add(propietario);

        apartamento.setText("Apartamento");
        jMenuBar1.add(apartamento);

        residente.setText("Residentes");
        jMenuBar1.add(residente);

        cobro.setText("Cobro");
        jMenuBar1.add(cobro);

        concepto.setText("Concepto");
        jMenuBar1.add(concepto);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMenu getApartamento() {
        return apartamento;
    }

    public void setApartamento(JMenu apartamento) {
        this.apartamento = apartamento;
    }

    public JMenu getCobro() {
        return cobro;
    }

    public void setCobro(JMenu cobro) {
        this.cobro = cobro;
    }

    public JMenu getPropietario() {
        return propietario;
    }

    public void setPropietario(JMenu propietario) {
        this.propietario = propietario;
    }

    public JMenu getResidente() {
        return residente;
    }

    public void setResidente(JMenu residente) {
        this.residente = residente;
    }

    public JMenu getConcepto() {
        return concepto;
    }

    public void setConcepto(JMenu concepto) {
        this.concepto = concepto;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu apartamento;
    private javax.swing.JMenu cobro;
    private javax.swing.JMenu concepto;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu propietario;
    private javax.swing.JMenu residente;
    // End of variables declaration//GEN-END:variables
}
