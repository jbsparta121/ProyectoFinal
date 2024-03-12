package com.mycompany.proyecto_final.controlador;

import com.mycompany.proyecto_final.modelo.*;
import com.mycompany.proyecto_final.modelo.DAO.*;
import com.mycompany.proyecto_final.vista.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.*;
import org.hibernate.engine.spi.SessionImplementor;

public class CntCobro extends InternalFrameAdapter implements ActionListener {

    private FrmCobro frmCobro;
    private CobroDAO cobroDAO;
    private Cobro cobro;
    private MesesGenerados mesesGenerados;
    private MesesGeneradosDAO mesesGeneradosDAO;
    private MesesGeneradosId mesesGeneradosId;
    private PropietarioApartamentoDAO propietarioApartamentoDAO;
    private Set<PropietarioApartamento> listadoPropietarioApatamento;
    private String mes;
    private Integer año;
    private Set<ItemCobro> listadoCuentasItemCobro;
    private Set<Cobro> listadoCuentasCobro;

    public CntCobro(FrmCobro frmCobro, CobroDAO cobroDAO, MesesGeneradosDAO mesesGeneradosDAO) {
        this.frmCobro = frmCobro;
        this.cobroDAO = cobroDAO;
        this.mesesGeneradosDAO = mesesGeneradosDAO;
        obtenerMes();
        registrarControladores();
    }

    private void registrarControladores() {
        frmCobro.addInternalFrameListener(this);
        frmCobro.getBtnCrear().addActionListener(this);
    }

    private void obtenerMes() {
        Month mesActual = LocalDate.now().getMonth();
        año = LocalDate.now().getYear();
        mes = mesActual.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String primeraLetraNombreMes = mes.substring(0, 1).toUpperCase();
        String demasLetrasNombreMes = mes.substring(1, mes.length());
        mes = primeraLetraNombreMes + demasLetrasNombreMes;
        frmCobro.getTxtMes().setText(mes);
        frmCobro.getTxtVigencia().setText(año.toString());
    }

    public boolean buscarMesGenerado() {
        mesesGeneradosId = new MesesGeneradosId(mes, año);
        mesesGeneradosDAO = new MesesGeneradosDAO();
        Object mesesGenerados = mesesGeneradosDAO.buscaMesesGenerados(mesesGeneradosId);
        return mesesGenerados != null ? true : false;
    }
    
    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        frmCobro.dispose();
        FrmCobro.setContador(0);
    }
    
    private void generarCuentasCobro() {
        propietarioApartamentoDAO = new PropietarioApartamentoDAO();
        LocalDate fechaLocalSistema = LocalDate.now();
        Date fechaSistema = java.sql.Date.valueOf(fechaLocalSistema);
        listadoCuentasItemCobro = new HashSet<>();
        listadoCuentasCobro = new HashSet<>();

        listadoPropietarioApatamento = new HashSet<PropietarioApartamento>(propietarioApartamentoDAO.listaPropietarioApartamento());
        for (PropietarioApartamento propietarioApartamento : listadoPropietarioApatamento) {
            cobro = new Cobro(fechaSistema, propietarioApartamento, null, mesesGenerados);
            generarItemCobros();
            cobro.setListadoItemCobro(listadoCuentasItemCobro);
            listadoCuentasCobro.add(cobro);
        }
    }
    
    private void generarItemCobros() {
        ConceptoDAO conceptoDAO = new ConceptoDAO();
        ItemCobro itemCobro = new ItemCobro();
        ItemCobroDAO itemCobroDAO = new ItemCobroDAO();
        listadoCuentasItemCobro = new HashSet();
        
        Set<Concepto> listadoConceptos = new HashSet<Concepto>(conceptoDAO.listaConceptos());
        for (Concepto concepto : listadoConceptos) {
            itemCobro = new ItemCobro(cobro,concepto,concepto.getValor());
            listadoCuentasItemCobro.add(itemCobro);
        }
    }

    private void crearBarra() {
        BarraProgreso barraProgreso = new BarraProgreso();
        barraProgreso.setVisible(true);
        barraProgreso.iniciarProceso();
    }

    private void generarReporte() {

        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory("edwar", "edwar");
            Session session = sessionFactory.openSession();

            JasperReport reporte = null;
            String path = "src\\main\\java\\com\\mycompany\\proyecto_final\\reporte\\report1.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            SessionImplementor sessionImplementor = (SessionImplementor) session;
            Connection connection = sessionImplementor.getJdbcConnectionAccess().obtainConnection();

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, connection);

            JasperViewer view = new JasperViewer(jprint, false);

            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            view.setVisible(true);

        } catch (JRException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmCobro.getBtnCrear()) {
            mesesGenerados = new MesesGenerados(mesesGeneradosId,true);
            generarCuentasCobro();
            mesesGenerados.setListadoCobros(listadoCuentasCobro);
            if (mesesGeneradosDAO.grabar(mesesGenerados)) {
                crearBarra();
                generarReporte();
            } else {
                JOptionPane.showMessageDialog(frmCobro, "Meses no almacenados");
            }
        }
    }
}
