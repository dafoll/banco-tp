package Servicios.gui;

import Entidades.Cuenta;
import Servicios.ServiceException;
import Servicios.ServicioCuenta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCuenta extends JFrame
{
    ServicioCuenta servicioCuenta;
    JPanel formCuenta;

    JLabel jLabelid;
    JLabel jLabelcbu;
    JLabel jLabelalias;
    JLabel jLabelcaja_ahorro;
    JLabel jLabelcaja_ahorroUSD;
    JLabel jLabellist_tarjeta;

    JTextField jTextFieldid;
    JTextField jTextFieldcbu;
    JTextField jTextFieldalias;
    JTextField jTextFieldcaja_ahorro;
    JTextField jTextFieldcaja_ahorraUSD;
    JTextField jTextFieldlist_tarjeta;

    JPanel botones;
    JButton jButtonGuardar;
    JButton jButtonNuevo;

    PanelManager panelManager;


    public void FormCuenta(PanelManager panelManager){
        panelManager = panelManager;
        armarFormulario(null);
    }

    public FormCuenta()
    {
        setContentPane(formCuenta);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400,400);

    }


    public void armarFormulario(Cuenta cuenta)
    {
        servicioCuenta = new ServicioCuenta();
        formCuenta = new JPanel();
        formCuenta.setLayout(new GridLayout(3,2));

        jLabelid = new JLabel("id");
        jLabelcbu = new JLabel("cbu");
        jLabelalias = new JLabel("alias");
        jLabelcaja_ahorro = new JLabel("caja_ahorro");
        jLabelcaja_ahorroUSD = new JLabel("caja_ahorroUSD");
        jLabellist_tarjeta = new JLabel("list_tarjeta");

        jTextFieldid = new JFormattedTextField(10);
        jTextFieldcbu = new JFormattedTextField(10);
        jTextFieldalias = new JFormattedTextField(10);
        jTextFieldcaja_ahorro = new JFormattedTextField(10);
        jTextFieldcaja_ahorraUSD = new JFormattedTextField(10);

        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");

        formCuenta.add(jLabelid);
        formCuenta.add(jLabelcbu);
        formCuenta.add(jLabelalias);
        formCuenta.add(jLabelcaja_ahorro);
        formCuenta.add(jLabelcaja_ahorroUSD);
        formCuenta.add(jLabellist_tarjeta);

        botones.add(jButtonGuardar);
        botones.add(jButtonNuevo);

        add(botones, BorderLayout.SOUTH);

        jButtonGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(Integer.parseInt(jTextFieldid.getText()));
                cuenta.setcbu(Integer.parseInt(jTextFieldcbu.getText()));
                cuenta.setAlias(jTextFieldalias.getText());
                cuenta.setCaja_ahorro(Integer.parseInt(jTextFieldcaja_ahorro.getText()));
                cuenta.setCaja_ahorro_USD(Integer.parseInt(jTextFieldcaja_ahorraUSD.getText()));
                //cuenta.setList_tarjetas(jLabellist_tarjeta.getText());
                try{
                    servicioCuenta.guardarCuenta(cuenta);
                }
                catch (ServiceException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                vaciarComponentes();
            }
        });
    }
    public void vaciarComponentes()
    {
        jTextFieldid.setText("");
        jTextFieldcbu.setText("");
        jTextFieldalias.setText("");
        jTextFieldcaja_ahorro.setText("");
        jTextFieldcaja_ahorraUSD.setText("");
        jTextFieldlist_tarjeta.setText("");
    }
}
