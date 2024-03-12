package com.mycompany.proyecto_final.controlador;

import com.mycompany.proyecto_final.modelo.*;
import com.mycompany.proyecto_final.modelo.DAO.*;
import com.mycompany.proyecto_final.vista.FrmConcepto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;

public class CntConcepto extends InternalFrameAdapter implements ActionListener {

    private FrmConcepto frmConcepto;
    private Concepto concepto;
    private ConceptoDAO conceptoDAO;

    public CntConcepto(FrmConcepto frmConcepto, ConceptoDAO conceptoDAO) {
        this.frmConcepto = frmConcepto;;
        this.conceptoDAO = conceptoDAO;
        registrarControlador();
    }
    
    private void registrarControlador() {
        frmConcepto.addInternalFrameListener(this);
        frmConcepto.getBtnGrabar().addActionListener(this);
        frmConcepto.getBtnReporte().addActionListener(this);
    }
    
    public void obtenerConcepto() {
        String descripcion = frmConcepto.getTxtDescripcion().getText();
        Double valor = Double.valueOf(frmConcepto.getTxtValor().getText());
        concepto = new Concepto(descripcion,valor);
    }
    
    public void grabarConcepto() {
        obtenerConcepto();
            if (conceptoDAO.grabar(concepto)) {
                JOptionPane.showMessageDialog(null, "Concepto almacenado");
            }
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
    
    private void limpiar() {
        frmConcepto.getTxtDescripcion().setText("");
        frmConcepto.getTxtValor().setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmConcepto.getBtnGrabar()) {
            grabarConcepto();
            limpiar();
        } else {
            generarReporte();
        }
    }
    
    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        frmConcepto.dispose();
        FrmConcepto.setContador(0);
    }
}
