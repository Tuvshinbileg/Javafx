package epm.controllers;

import epm.database.DAO;
import epm.database.SQLITEConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PrintReport extends JFrame {
    private static final long serialVersionUID = 1L;
    private final String dbUrl = "jdbc:sqlite:";
    private final String dbName = "EPM.db";

    public void showReport() throws JRException, ClassNotFoundException, SQLException {
        //String reportSrcFile = "payslip5.jrxml";
        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport("/home/tuvshinbileg/IdeaProjects/EPM_UI/src/epm/controllers/payslip5.jrxml");
        // Fields for report

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, SQLITEConnection.getConnection(dbUrl,dbName));
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");

    }
}
