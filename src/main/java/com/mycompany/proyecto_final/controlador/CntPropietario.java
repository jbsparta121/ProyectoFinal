package com.mycompany.proyecto_final.controlador;

import com.mycompany.proyecto_final.modelo.*;
import com.mycompany.proyecto_final.modelo.DAO.*;
import com.mycompany.proyecto_final.vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

public class CntPropietario extends InternalFrameAdapter implements ActionListener {

    private FrmPropietario frmPropietario;
    private Apartamento apartamento;
    private Persona persona;
    private PersonaDAO personaDAO;
    private ApartamentoDAO apartamentoDAO;
    private PropietarioApartamento propietarioApartamento;
    private PropietarioApartamentoDAO propietarioApartamentoDAO;
    private int fila;
    private int filaComboBox;
    private List<Apartamento> listadoApartamentos;
    private Set<Apartamento> listadoApartamentosCambiarEstadoAsignado;
    private Set<PropietarioApartamento> listaPropietarioApartamento;
    private int contadorConsultar = 0;
    private int numeroApartamentos = 0;
    private Date fechaCompra;

    public CntPropietario(FrmPropietario frmPropietario,
            PersonaDAO personaDAO, PropietarioApartamentoDAO propietarioApartamentoDAO, ApartamentoDAO apartamentoDAO) {
        this.frmPropietario = frmPropietario;
        this.personaDAO = personaDAO;
        this.propietarioApartamentoDAO = propietarioApartamentoDAO;
        this.apartamentoDAO = apartamentoDAO;
        registrarControladores();
        cargarApartamento();
    }

    private void registrarControladores() {
        frmPropietario.getBtnGrabar().addActionListener(this);
        frmPropietario.getBtnAdicionar().addActionListener(this);
        frmPropietario.getBtnBorrar().addActionListener(this);
        frmPropietario.getCbxApartamentos().addActionListener(this);
        frmPropietario.getBtnConsultar().addActionListener(this);
        frmPropietario.addInternalFrameListener(this);
    }

    private void cargarApartamento() {
        frmPropietario.getCbxApartamentos().removeAllItems();
        listadoApartamentos = apartamentoDAO.listarApartamentosNoAsignado();
        Integer numeroUnico, numeroApartamento, numeroBloque, matricula, parqueadero;

        for (Apartamento apartamento : listadoApartamentos) {
            numeroUnico = apartamento.getNumeroUnico();
            numeroApartamento = apartamento.getNumeroApartamento();
            numeroBloque = apartamento.getNumeroBloque();
            matricula = (apartamento.getMatricula() == null ? null : apartamento.getMatricula());
            parqueadero = (apartamento.getParqueadero() == null ? null : apartamento.getParqueadero());
            String relleno = "Numero unico " + numeroUnico.toString() + " Apartamento "
                    + numeroApartamento.toString() + " Bloque " + numeroBloque.toString()
                    + " Matricula " + matricula + " Parqueadero " + parqueadero;
            frmPropietario.getCbxApartamentos().addItem(relleno);
        }
    }

    public void obtenerPropietario() {
        Integer id = Integer.valueOf(frmPropietario.getTxtId().getText());
        String nombre1 = frmPropietario.getTxtNombre1().getText();
        String nombre2 = frmPropietario.getTxtNombre2().getText();
        String apellido1 = frmPropietario.getTxtApellido1().getText();
        String apellido2 = frmPropietario.getTxtApellido2().getText();
        String emailPersona = frmPropietario.getTxtEmail().getText();
        String celular = frmPropietario.getTxtCelular().getText();
        String tipo = "Propietario";
        fechaCompra = frmPropietario.getTxtFechaCompra().getDate();
        persona = new Persona(id, apellido1, apellido2, nombre1, nombre2, celular, emailPersona, tipo);
        crearListadoPropietarioApartamento();
        persona.setListadoPropietarioApartamento(listaPropietarioApartamento);
    }

    public void crearListadoPropietarioApartamento() {
        Apartamento apartamentoAsignado = null;
        DefaultTableModel dtmPropietario = frmPropietario.getDtmTablaPropietarioApartamento();
        List<Apartamento> listadoApartamentos = apartamentoDAO.listarApartamentos();
        listaPropietarioApartamento = new HashSet();
        listadoApartamentosCambiarEstadoAsignado = new HashSet();
        int filas = dtmPropietario.getRowCount();
        int columnas = dtmPropietario.getColumnCount();
        Integer numeroUnico, numeroApartamento, numeroBloque, matricula, parqueadero;
        String disponible = null;
        for (int i = numeroApartamentos; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                numeroUnico = Integer.valueOf(dtmPropietario.getValueAt(i, 0).toString());
                numeroApartamento = Integer.valueOf(dtmPropietario.getValueAt(i, 1).toString());
                numeroBloque = Integer.valueOf(dtmPropietario.getValueAt(i, 2).toString());
                String fechaCompraString = dtmPropietario.getValueAt(i, 3).toString();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                fechaCompra = null;
                try {
                    fechaCompra = formatoFecha.parse(fechaCompraString);
                } catch (ParseException ex) {
                    Logger.getLogger(CntPropietario.class.getName()).log(Level.SEVERE, null, ex);
                }
                matricula = (dtmPropietario.getValueAt(i, 4) == null ? null : Integer.valueOf(dtmPropietario.getValueAt(i, 4).toString()));
                parqueadero = (dtmPropietario.getValueAt(i, 5) == null ? null : Integer.valueOf(dtmPropietario.getValueAt(i, 5).toString()));
                for (Apartamento apartamento : listadoApartamentos) {
                    if (apartamento.getNumeroUnico() == numeroUnico) {
                        disponible = apartamento.getDisponible();
                    }
                }
                apartamento = new Apartamento(numeroUnico);
                apartamentoAsignado = new Apartamento(numeroUnico, numeroApartamento, numeroBloque, matricula, parqueadero, "", disponible);
                propietarioApartamento = new PropietarioApartamento(persona, apartamento, fechaCompra);
            }
            listaPropietarioApartamento.add(propietarioApartamento);
            listadoApartamentosCambiarEstadoAsignado.add(apartamentoAsignado);
        }
    }

    private void actualizaEstadoApartamento() {
        for (Apartamento apartamento : listadoApartamentosCambiarEstadoAsignado) {
            apartamento.setNumeroApartamento(apartamento.getNumeroApartamento());
            apartamento.setNumeroBloque(apartamento.getNumeroBloque());
            apartamento.setAsignado("S");
            apartamentoDAO.actualizar(apartamento);
        }
    }

    private void grabarPersona() {
        if (contadorConsultar == 0) {
            obtenerPropietario();
            if (personaDAO.grabar(persona)) {
                JOptionPane.showMessageDialog(null, "Persona almacenado");
                actualizaEstadoApartamento();
            } else {
                JOptionPane.showMessageDialog(null, "Persona no almacenado");
            }
        } else {
            crearListadoPropietarioApartamento();
            for (PropietarioApartamento propietarioApartamento : listaPropietarioApartamento) {
                if (propietarioApartamentoDAO.grabar(propietarioApartamento)) {
                    actualizaEstadoApartamento();
                } else {
                    JOptionPane.showMessageDialog(null, "Apartamento no almacenado");
                }
            }
            JOptionPane.showMessageDialog(null, "Apartamento almacenado");
        }
    }

    private void consultarPropietario() {
        Integer idPropietario = Integer.valueOf(frmPropietario.getTxtId().getText());
        persona = personaDAO.consultar(idPropietario);
        if (persona != null) {
            if (persona.getTipo().equals("Propietario") || persona.getTipo().equals("Propietario/Residente")) {
                frmPropietario.getTxtNombre1().setText(persona.getNombre1());
                frmPropietario.getTxtNombre2().setText(persona.getNombre2());
                frmPropietario.getTxtApellido1().setText(persona.getApellido1());
                frmPropietario.getTxtApellido2().setText(persona.getApellido2());
                frmPropietario.getTxtEmail().setText(persona.getEmailPersona());
                frmPropietario.getTxtCelular().setText(persona.getCelularPersona());
                cargarApartamentosAsignados();
            } else {
                JOptionPane.showMessageDialog(null, "La persona esta registrada como residente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Propietario no existe");
        }
    }

    private void cargarTablaApartamento() {
        Object itemPropietario[] = new Object[6];
        apartamento = listadoApartamentos.get(filaComboBox);
        itemPropietario[0] = apartamento.getNumeroUnico();
        itemPropietario[1] = apartamento.getNumeroApartamento();
        itemPropietario[2] = apartamento.getNumeroBloque();
        Date fechaCompra = frmPropietario.getTxtFechaCompra().getDate();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormato = formatoFecha.format(fechaCompra);
        itemPropietario[3] = fechaFormato;
        itemPropietario[4] = apartamento.getMatricula();
        itemPropietario[5] = apartamento.getParqueadero();
        frmPropietario.getDtmTablaPropietarioApartamento().addRow(itemPropietario);
    }

    private void cargarApartamentosAsignados() {
        Object itemTablaPropietarioApartamento[] = new Object[6];
        numeroApartamentos = 0;
        listaPropietarioApartamento = new HashSet<PropietarioApartamento>(propietarioApartamentoDAO.listarPropietarioApartamento(Integer.valueOf(frmPropietario.getTxtId().getText())));
        for (PropietarioApartamento propietarioApartamento : listaPropietarioApartamento) {
            itemTablaPropietarioApartamento[0] = propietarioApartamento.getApartamento().getNumeroUnico().toString();
            itemTablaPropietarioApartamento[1] = propietarioApartamento.getApartamento().getNumeroApartamento().toString();
            itemTablaPropietarioApartamento[2] = propietarioApartamento.getApartamento().getNumeroBloque().toString();
            itemTablaPropietarioApartamento[3] = propietarioApartamento.getFechaCompra();
            itemTablaPropietarioApartamento[4] = (propietarioApartamento.getApartamento().getMatricula() == null ? null : propietarioApartamento.getApartamento().getMatricula().toString());
            itemTablaPropietarioApartamento[5] = (propietarioApartamento.getApartamento().getParqueadero() == null ? null : propietarioApartamento.getApartamento().getParqueadero().toString());
            frmPropietario.getDtmTablaPropietarioApartamento().addRow(itemTablaPropietarioApartamento);
            numeroApartamentos++;
        }
    }

    private void borrarFila() {
        fila = frmPropietario.getTblApartamentosPropietario().getSelectedRow();
        frmPropietario.getDtmTablaPropietarioApartamento().removeRow(fila);
    }

    public void limpiarTabla() {
        int a = frmPropietario.getDtmTablaPropietarioApartamento().getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            frmPropietario.getDtmTablaPropietarioApartamento().removeRow(i);
        }
    }

    public void limpiar() {
        frmPropietario.getTxtId().setText("");
        frmPropietario.getTxtNombre1().setText("");
        frmPropietario.getTxtNombre2().setText("");
        frmPropietario.getTxtApellido1().setText("");
        frmPropietario.getTxtApellido2().setText("");
        frmPropietario.getTxtEmail().setText("");
        frmPropietario.getTxtCelular().setText("");
        frmPropietario.getTxtFechaCompra().setDate(null);
        limpiarTabla();
        cargarApartamento();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmPropietario.getBtnGrabar()) {
            grabarPersona();
            limpiar();
            contadorConsultar = 0;
        } else if (e.getSource() == frmPropietario.getBtnAdicionar()) {
            cargarTablaApartamento();
        } else if (e.getSource() == frmPropietario.getBtnBorrar()) {
            borrarFila();
        } else if (e.getSource() == frmPropietario.getCbxApartamentos()) {
            filaComboBox = frmPropietario.getCbxApartamentos().getSelectedIndex();
        } else if (e.getSource() == frmPropietario.getBtnConsultar()) {
            consultarPropietario();
            contadorConsultar++;
        }
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        {
            frmPropietario.dispose();
            FrmPropietario.setContador(0);
        }
    }
}
