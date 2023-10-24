package Servicios.gui;

import Entidades.*;
import Servicios.ServiceException;
import Servicios.ServicioTransferencia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormTrasferencia extends JPanel
{
    ServicioTransferencia servicioTransferencia;
    JPanel fromTrasferencia;

    JLabel jLabelId_trasferencia;
    JLabel jLabelCant_trasferir;
    JLabel jLabelId_origen;
    JLabel jLabelId_destino;

    JTextField jTextFieldId_trasferencia;
    JTextField jTextFieldCant_trasferir;
    JTextField jTextFieldId_origen;
    JTextField jTextFieldId_destino;

    JPanel botones;
    JButton jButtonGurardar;
    JButton jButtonNuevo;
    PanelManager panelManager;

    public FormTrasferencia(PanelManager panelprincipal){
        panelManager = panelprincipal;
        armarFormulario(null);
    }

    public void armarFormulario(Trasferencias trasferencias)
    {
        servicioTransferencia = new ServicioTransferencia();
        fromTrasferencia = new JPanel();
        fromTrasferencia.setLayout(new GridLayout(3,2));

        jLabelId_trasferencia = new JLabel("ID_Trasferencia");
        jLabelCant_trasferir = new JLabel("Cant_trasferir");
        jLabelId_destino = new JLabel("ID_Destino");
        jLabelId_origen = new JLabel("ID_Origen");

        jTextFieldId_trasferencia = new JFormattedTextField(10);
        jTextFieldCant_trasferir = new JFormattedTextField(10);
        jTextFieldId_origen = new JFormattedTextField(10);
        jTextFieldId_destino = new JFormattedTextField(10);

        jButtonNuevo = new JButton("Nuevo");
        jButtonGurardar = new JButton("Guardar");

        fromTrasferencia.add(jLabelId_trasferencia);
        fromTrasferencia.add(jLabelCant_trasferir);
        fromTrasferencia.add(jLabelId_origen);
        fromTrasferencia.add(jLabelId_destino);

        setLayout(new BorderLayout());
        add(fromTrasferencia, BorderLayout.CENTER);

        botones = new JPanel();
        jButtonGurardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");


        jButtonGurardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Trasferencias trasferencias = new Trasferencias();
                trasferencias.setId_trasferencia(Integer.parseInt(jTextFieldId_trasferencia.getText()));
                trasferencias.setCant_trasferir(Integer.parseInt(jTextFieldCant_trasferir.getText()));
                trasferencias.setId_origen(Integer.parseInt(jTextFieldId_origen.getText()));
                trasferencias.setId_destino(Integer.parseInt(jLabelId_destino.getText()));
                try
                {
                servicioTransferencia.guardarTransferencia(trasferencias);
                }
                catch(ServiceException e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
            }
        });

    }

    public void vaciarComponentes()
    {
        jTextFieldId_trasferencia.setText("");
        jTextFieldCant_trasferir.setText("");
        jTextFieldId_origen.setText("");
        jTextFieldId_destino.setText("");
    }
}
