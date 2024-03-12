package com.mycompany.proyecto_final.controlador;

import com.mycompany.proyecto_final.modelo.*;
import com.mycompany.proyecto_final.modelo.DAO.*;
import com.mycompany.proyecto_final.vista.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

public class CntResidente extends InternalFrameAdapter implements ActionListener, FocusListener {

    private FrmResidente frmResidente;
    private Apartamento apartamento;
    private ApartamentoDAO apartamentoDAO;
    private Persona persona;
    private PersonaDAO personaDAO;
    private ResidenteApartamentoDAO residenteApartamentoDAO;
    private ResidenteApartamento residenteApartamento;
    private PropietarioApartamento propietarioApartamento;
    private PropietarioApartamentoDAO propietarioApartamentoDAO;
    private List<Apartamento> listaApartamentosDisponibles;
    private List<PropietarioApartamento> listaPropietarioApartamento;
    private List<ResidenteApartamento> listaResidenteApartamentoRegistrado;
    private Set<Apartamento> listadoApartamentosCambiarEstadoDisponible;
    private Set<Persona> listadoPersonaPropietario;
    private Set<ResidenteApartamento> listaResidenteApartamento;
    private int filaComboBox;
    private int fila;
    private int contadorExistente = 0;
    private int contadorPersonaExistente = 0;
    private int contadorPropietario = 0;
    private Date fechaInicio;
    private Date fechaFin;

    public CntResidente(FrmResidente frmResidente, ResidenteApartamentoDAO residenteApartamentoDAO,
            PersonaDAO personaDAO, ApartamentoDAO apartamentoDAO) {
        this.frmResidente = frmResidente;
        this.residenteApartamentoDAO = residenteApartamentoDAO;
        this.personaDAO = personaDAO;
        this.apartamentoDAO = apartamentoDAO;
        this.propietarioApartamentoDAO = new PropietarioApartamentoDAO();
        registrarControladores();
        cargarApartamentoComboBox();
    }

    private void registrarControladores() {
        frmResidente.getBtnAdicionarResidente().addActionListener(this);
        frmResidente.getBtnBorrarResidente().addActionListener(this);
        frmResidente.getBtnGrabarResidente().addActionListener(this);
        frmResidente.getCbxApartamentosResidente().addActionListener(this);
        frmResidente.getTxtIdResidente().addFocusListener(this);
        frmResidente.addInternalFrameListener(this);
    }

    private void cargarApartamentoComboBox() {
        frmResidente.getCbxApartamentosResidente().removeAllItems();
        listaApartamentosDisponibles = apartamentoDAO.listarApartamentosDisponibles();
        Integer numeroUnico, numeroApartamento, numeroBloque, matricula, parqueadero;

        for (Apartamento apartamento : listaApartamentosDisponibles) {
            numeroUnico = apartamento.getNumeroUnico();
            numeroApartamento = apartamento.getNumeroApartamento();
            numeroBloque = apartamento.getNumeroBloque();
            matricula = (apartamento.getMatricula() == null ? null : apartamento.getMatricula());
            parqueadero = (apartamento.getParqueadero() == null ? null : apartamento.getParqueadero());
            String relleno = "Numero unico " + numeroUnico.toString() + " Apartamento "
                    + numeroApartamento.toString() + " Bloque " + numeroBloque.toString()
                    + " Matricula " + matricula + " Parqueadero " + parqueadero;
            frmResidente.getCbxApartamentosResidente().addItem(relleno);
        }
    }

    private void obtenerResidente() {
        String nombre1, nombre2, apellido1, apellido2, email, celular, tipo;
        Integer id = Integer.valueOf(frmResidente.getTxtIdResidente().getText());
        nombre1 = frmResidente.getTxtNombre1Residente().getText();
        nombre2 = frmResidente.getTxtNombre2Residente().getText();
        apellido1 = frmResidente.getTxtApellido1Residente().getText();
        apellido2 = frmResidente.getTxtApellido2Residente().getText();
        email = frmResidente.getTxtEmailResidente().getText();
        celular = frmResidente.getTxtCelularResidente().getText();
        tipo = "Residente";
        fechaInicio = frmResidente.getTxtFechaInicio().getDate();
        fechaFin = frmResidente.getTxtFechaFin().getDate();
        persona = new Persona(id, apellido1, apellido2, nombre1, nombre2, celular, email, tipo);
        crearListadoResidenteApartamento();
        persona.setListadoResidenteApartamento(listaResidenteApartamento);
    }

    private void crearListadoResidenteApartamento() {
        List<Apartamento> listadoApartamentos = apartamentoDAO.listarApartamentos();
        Apartamento apartamentDisponible = null;
        DefaultTableModel dtmResidente = frmResidente.getDtmTablaResidenteApartamento();
        listaResidenteApartamento = new HashSet();
        listadoApartamentosCambiarEstadoDisponible = new HashSet();
        int filas = dtmResidente.getRowCount();
        int columnas = dtmResidente.getColumnCount();
        Integer numeroUnico, numeroApartamento, numeroBloque, matricula = null, parqueadero = null;
        String asignado = null;
        Date fechaInicio = null, fechaFin = null;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                numeroUnico = Integer.valueOf(dtmResidente.getValueAt(i, 0).toString());
                numeroApartamento = Integer.valueOf(dtmResidente.getValueAt(i, 1).toString());
                numeroBloque = Integer.valueOf(dtmResidente.getValueAt(i, 2).toString());
                String fechaInicioString = dtmResidente.getValueAt(i, 3).toString();
                String fechaFinString = dtmResidente.getValueAt(i, 4) == null ? null : dtmResidente.getValueAt(i, 4).toString();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    fechaInicio = formatoFecha.parse(fechaInicioString);
                    fechaFin = (fechaFinString == null) ? null : formatoFecha.parse(fechaFinString);
                } catch (ParseException ex) {
                    Logger.getLogger(CntPropietario.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Apartamento apartamento : listadoApartamentos) {
                    if (apartamento.getNumeroUnico() == numeroUnico) {
                        matricula = apartamento.getMatricula();
                        parqueadero = apartamento.getParqueadero();
                        asignado = apartamento.getAsignado();
                    }
                }
                apartamento = new Apartamento(numeroUnico);
                apartamentDisponible = new Apartamento(numeroUnico, numeroApartamento, numeroBloque, matricula, parqueadero, asignado, "");
                residenteApartamento = new ResidenteApartamento(persona, apartamento, fechaInicio, fechaFin);
            }
            listaResidenteApartamento.add(residenteApartamento);
            listadoApartamentosCambiarEstadoDisponible.add(apartamentDisponible);
        }
    }

    private void actualizaEstadoApartamento() {
        for (Apartamento apartamento : listadoApartamentosCambiarEstadoDisponible) {
            apartamento.setNumeroApartamento(apartamento.getNumeroApartamento());
            apartamento.setNumeroBloque(apartamento.getNumeroBloque());
            apartamento.setDisponible("N");
            apartamentoDAO.actualizar(apartamento);
        }
    }

    private void grabarResidente() {
        if (contadorExistente == 0) {
            obtenerResidente();
            if (personaDAO.grabar(persona)) {
                JOptionPane.showMessageDialog(null, "Persona almacenada");
                actualizaEstadoApartamento();
            } else {
                JOptionPane.showMessageDialog(null, "Persona no almacenada");
            }
        } else {
            if (apartamentoActivo()) {
                JOptionPane.showMessageDialog(null, "El residente ya tiene un apartamento asignado.");
            } else {
                crearListadoResidenteApartamento();
                if (residenteApartamentoDAO.grabar(residenteApartamento)) {
                    JOptionPane.showMessageDialog(null, "Apartamento almacenado");
                    actualizaEstadoApartamento();
                    if (contadorPropietario != 0) {
                        actualizaTipoPropietario();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Apartamento no almacenado");
                }
            }
        }
    }

    private boolean apartamentoActivo() {
        Integer idResidente = Integer.valueOf(frmResidente.getTxtIdResidente().getText());
        listaResidenteApartamentoRegistrado = residenteApartamentoDAO.listaResidenteApartamento();

        for (ResidenteApartamento residenteApartamento : listaResidenteApartamentoRegistrado) {
            if (idResidente.equals(residenteApartamento.getResidente().getIdPersona())
                    && residenteApartamento.getFechaFin() == null) {
                return true;
            }
        }
        return false;
    }

    private void cargarTablaApartamento() {
        Object itemPropietario[] = new Object[6];
        apartamento = listaApartamentosDisponibles.get(filaComboBox);
        itemPropietario[0] = apartamento.getNumeroUnico();
        itemPropietario[1] = apartamento.getNumeroApartamento();
        itemPropietario[2] = apartamento.getNumeroBloque();
        Date fechaInicio = frmResidente.getTxtFechaInicio().getDate();
        Date fechaFin = frmResidente.getTxtFechaFin().getDate();;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioFormato = formatoFecha.format(fechaInicio);
        String fechaFinFormato = (fechaFin != null) ? formatoFecha.format(fechaFin) : null;
        itemPropietario[3] = fechaInicioFormato;
        itemPropietario[4] = fechaFinFormato;
        frmResidente.getDtmTablaResidenteApartamento().addRow(itemPropietario);
    }

    private void cargarApartamentosAsignados() {
        Object itemTablaResidenteApartamento[] = new Object[5];
        listaResidenteApartamento = new HashSet<ResidenteApartamento>(residenteApartamentoDAO.listarResidenteApartamento(Integer.valueOf(frmResidente.getTxtIdResidente().getText())));
        for (ResidenteApartamento residenteApartamento : listaResidenteApartamento) {
            itemTablaResidenteApartamento[0] = residenteApartamento.getApartamento().getNumeroUnico().toString();
            itemTablaResidenteApartamento[1] = residenteApartamento.getApartamento().getNumeroApartamento().toString();
            itemTablaResidenteApartamento[2] = residenteApartamento.getApartamento().getNumeroBloque().toString();
            itemTablaResidenteApartamento[3] = residenteApartamento.getFechaInicio();
            itemTablaResidenteApartamento[4] = residenteApartamento.getFechaFin();
            frmResidente.getDtmTablaResidenteApartamento().addRow(itemTablaResidenteApartamento);
        }
    }

    private void borrarFila() {
        fila = frmResidente.getTblResidenteApartamentos().getSelectedRow();
        frmResidente.getDtmTablaResidenteApartamento().removeRow(fila);
    }

    private void limpiarTabla() {
        int a = frmResidente.getDtmTablaResidenteApartamento().getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            frmResidente.getDtmTablaResidenteApartamento().removeRow(i);
        }
    }

    private void limpiar() {
        frmResidente.getTxtIdResidente().setText("");
        frmResidente.getTxtNombre1Residente().setText("");
        frmResidente.getTxtNombre2Residente().setText("");
        frmResidente.getTxtApellido1Residente().setText("");
        frmResidente.getTxtApellido2Residente().setText("");
        frmResidente.getTxtEmailResidente().setText("");
        frmResidente.getTxtCelularResidente().setText("");
        frmResidente.getTxtFechaInicio().setDate(null);
        frmResidente.getTxtFechaFin().setDate(null);
        limpiarTabla();
        cargarApartamentoComboBox();
    }

    private void existePropietario() {
        Integer id = Integer.valueOf(frmResidente.getTxtIdResidente().getText());
        listaPropietarioApartamento = propietarioApartamentoDAO.listaPropietarioApartamento();
        listaResidenteApartamentoRegistrado = residenteApartamentoDAO.listaResidenteApartamento();
        persona = personaDAO.consultar(id);

        for (PropietarioApartamento propietarioApartamento : listaPropietarioApartamento) {
            if (contadorPersonaExistente == 0) {
                if (id.equals(propietarioApartamento.getPropietario().getIdPersona())) {
                    System.out.println("Existe Propietario");
                    frmResidente.getTxtNombre1Residente().setText(persona.getNombre1());
                    frmResidente.getTxtNombre2Residente().setText(persona.getNombre2());
                    frmResidente.getTxtApellido1Residente().setText(persona.getApellido1());
                    frmResidente.getTxtApellido2Residente().setText(persona.getApellido2());
                    frmResidente.getTxtEmailResidente().setText(persona.getEmailPersona());
                    frmResidente.getTxtCelularResidente().setText(persona.getCelularPersona());
                    cargarApartamentosAsignados();
                    contadorExistente++;
                    contadorPersonaExistente++;
                    contadorPropietario++;
                } else {
                }
            }
        }
    }

    private void obtenerPropietarioResidente() {
        Persona personaPropietario = null;
        listadoPersonaPropietario = new HashSet();
        Integer id = Integer.valueOf(frmResidente.getTxtIdResidente().getText());
        String nombre1, nombre2, apellido1, apellido2, email, celular;
        nombre1 = frmResidente.getTxtNombre1Residente().getText();
        nombre2 = frmResidente.getTxtNombre2Residente().getText();
        apellido1 = frmResidente.getTxtApellido1Residente().getText();
        apellido2 = frmResidente.getTxtApellido2Residente().getText();
        email = frmResidente.getTxtEmailResidente().getText();
        celular = frmResidente.getTxtCelularResidente().getText();
        personaPropietario = new Persona(id, apellido1, apellido2, nombre1, nombre2, celular, email, "");
        listadoPersonaPropietario.add(personaPropietario);
    }

    private void actualizaTipoPropietario() {
        obtenerPropietarioResidente();
        for (Persona persona : listadoPersonaPropietario) {
            persona.setIdPersona(persona.getIdPersona());
            persona.setTipo("Propietario/Residente");
            personaDAO.actualizar(persona);
        }
    }

    private void existeResidente() {
        Integer id = Integer.valueOf(frmResidente.getTxtIdResidente().getText());
        listaResidenteApartamentoRegistrado = residenteApartamentoDAO.listaResidenteApartamento();
        persona = personaDAO.consultar(id);

        for (ResidenteApartamento residenteApartamento : listaResidenteApartamentoRegistrado) {
            if (contadorPersonaExistente == 0) {
                if (id.equals(residenteApartamento.getResidente().getIdPersona())) {
                    frmResidente.getTxtNombre1Residente().setText(persona.getNombre1());
                    frmResidente.getTxtNombre2Residente().setText(persona.getNombre2());
                    frmResidente.getTxtApellido1Residente().setText(persona.getApellido1());
                    frmResidente.getTxtApellido2Residente().setText(persona.getApellido2());
                    frmResidente.getTxtEmailResidente().setText(persona.getEmailPersona());
                    frmResidente.getTxtCelularResidente().setText(persona.getCelularPersona());
                    cargarApartamentosAsignados();
                    contadorExistente++;
                    contadorPersonaExistente++;
                } else {

                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmResidente.getBtnGrabarResidente()) {
            grabarResidente();
            limpiar();
            contadorExistente = 0;
            contadorPersonaExistente = 0;
            contadorPropietario = 0;
        } else if (e.getSource() == frmResidente.getBtnAdicionarResidente()) {
            cargarTablaApartamento();
        } else if (e.getSource() == frmResidente.getBtnBorrarResidente()) {
            borrarFila();
        } else {
            filaComboBox = frmResidente.getCbxApartamentosResidente().getSelectedIndex();
        }
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        frmResidente.dispose();
        FrmResidente.setContador(0);
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        existePropietario();
        existeResidente();
    }
}
