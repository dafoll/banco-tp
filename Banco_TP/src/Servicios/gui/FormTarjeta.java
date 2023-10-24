package Servicios.gui;

import Entidades.Tarjetas;
import Servicios.ServiceException;
import Servicios.ServicioTarjetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormTarjeta extends JPanel
{
    ServicioTarjetas servicioTarjetas;
    JPanel formTarjeta;

    JLabel jLabelid;
    JLabel jLabeldisponibilidad;
    JLabel jLabelSaldo_pagar;

    JTextField jTextFieldid;
    JTextField jTextFieldDisponibilidad;
    JTextField jTextFieldSaldo_pagar;

    JPanel botones;
    JButton jButtonGuardar;
    JButton jButtonNuevo;

    PanelManager panelManager;

    public FormTarjeta(PanelManager panelprincipal){
        panelManager = panelprincipal;
        armarFormulario(null);
    }
    public void armarFormulario(Tarjetas tarjetas)
    {
        servicioTarjetas = new ServicioTarjetas();
        formTarjeta = new JPanel();
        formTarjeta.setLayout(new GridLayout(3,2));

        jLabelid = new JLabel("id");
        jLabeldisponibilidad = new JLabel("disponibilidad");
        jLabelSaldo_pagar = new JLabel("Saldo_pagar");

        jTextFieldid = new JFormattedTextField(10);
        jTextFieldDisponibilidad = new JFormattedTextField(10);
        jTextFieldSaldo_pagar = new JFormattedTextField(10);

        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");

        formTarjeta.add(jLabelid);
        formTarjeta.add(jLabeldisponibilidad);
        formTarjeta.add(jLabelSaldo_pagar);

        setLayout(new BorderLayout());
        add(formTarjeta, BorderLayout.SOUTH);

        jButtonGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Tarjetas tarjetas = new Tarjetas();
                tarjetas.setId(Integer.parseInt(jTextFieldid.getText()));
                tarjetas.setId_tarjeta(Integer.parseInt(jTextFieldid.getText()));
                tarjetas.setDisponibilidad(Integer.parseInt(jTextFieldDisponibilidad.getText()));
                tarjetas.setSaldo_pagar(Integer.parseInt(jTextFieldSaldo_pagar.getText()));
                try{
                    servicioTarjetas.guardarTarjeta(tarjetas);
                }
                catch(ServiceException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
            }
        });

        jTextFieldid.setText(tarjetas == null?"":String.valueOf(tarjetas.getId()));
        jTextFieldDisponibilidad.setText(tarjetas == null?"":String.valueOf(tarjetas.getDisponibilidad()));
        jTextFieldSaldo_pagar.setText(tarjetas == null?"":String.valueOf(tarjetas.getSaldo_pagar()));
    }

    public void vaciarComponentes(){
        jTextFieldid.setText("");
        jTextFieldDisponibilidad.setText("");
        jTextFieldSaldo_pagar.setText("");
    }
}
