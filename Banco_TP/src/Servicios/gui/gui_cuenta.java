package Servicios.gui;

import Entidades.Cuenta;
import Servicios.ServiceException;
import Servicios.ServicioCuenta;
import Servicios.ServicioUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui_cuenta extends JFrame {

    ServicioCuenta servicioCuenta = new ServicioCuenta();
    ServicioUsuario servicioUsuario = new ServicioUsuario();
    private JPanel jp_cuenta;
    private JLabel tx_id;
    private JLabel tx_cbu;
    private JLabel tx_alias;
    private JLabel tx_cj;
    private JLabel tx_cj_usd;
    private JTextField tf_id;
    private JTextField tf_cbu;
    private JTextField tf_alias;
    private JTextField tf_cj;
    private JTextField tf_cj_usd;
    private JButton btn_crear;
    private JButton btn_cerrar;

    public gui_cuenta() {
        setContentPane(jp_cuenta);
        setVisible(true);
        setSize(400,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btn_crear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    if(servicioUsuario.buscarUsuariobool(Integer.parseInt(tf_id.getText())))
                    {
                        Cuenta cuenta = new Cuenta();

                        cuenta.setId(Integer.parseInt(tf_id.getText()));
                        cuenta.setcbu(Integer.parseInt(tf_cbu.getText()));
                        cuenta.setAlias(tf_alias.getText());
                        cuenta.setCaja_ahorro(Integer.parseInt(tf_cj.getText()));
                        cuenta.setCaja_ahorro_USD(Integer.parseInt(tf_cj_usd.getText()));

                        try {
                            servicioCuenta.guardarCuenta(cuenta);
                        } catch (ServiceException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                catch (ServiceException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        btn_cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui_control gui_control = new gui_control();
                dispose();
            }
        });
    }

}
