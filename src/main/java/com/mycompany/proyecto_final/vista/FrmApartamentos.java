package com.mycompany.proyecto_final.vista;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.edisoncor.gui.button.ButtonAction;

public class FrmApartamentos extends javax.swing.JInternalFrame {

    private DefaultTableModel dtmModelTableApartamentos;
    private static int contador;

    public FrmApartamentos() {
        initComponents();
        configurarTablaApartamentos();
        contador = 0;
    }

    private void configurarTablaApartamentos() {
        dtmModelTableApartamentos = new DefaultTableModel(
                null,
                new String[]{"Numero único", "No. apartamento", "No. Bloque",
                    "Matricula", "Parqueadero", "Asignado", "Disponible"}
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tblApartamentos.setModel(dtmModelTableApartamentos);
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        FrmApartamentos.contador = contador;
    }
    
    public DefaultTableModel getDtmModelTableApartamentos() {
        return dtmModelTableApartamentos;
    }

    public void setDtmModelTableApartamentos(DefaultTableModel dtmModelTableApartamentos) {
        this.dtmModelTableApartamentos = dtmModelTableApartamentos;
    }

    public ButtonAction getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(ButtonAction btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JTable getTblApartamentos() {
        return tblApartamentos;
    }

    public void setTblApartamentos(JTable tblApartamentos) {
        this.tblApartamentos = tblApartamentos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblApartamentos = new javax.swing.JTable();
        btnActualizar = new org.edisoncor.gui.button.ButtonAction();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Apartamentos");
        setAutoscrolls(true);

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo-azul-clasico-abstracto_23-2148421773.jpg"))); // NOI18N

        tblApartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblApartamentos);

        btnActualizar.setText("Actualizar");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(412, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(440, 440, 440))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAction btnActualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JTable tblApartamentos;
    // End of variables declaration//GEN-END:variables
}
