package Servicios.gui;

import Entidades.Tarjetas;
import Servicios.ServiceException;
import Servicios.ServicioCuenta;
import Servicios.ServicioTarjetas;
import Servicios.ServicioUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui_tarjeta extends JFrame
{
    private JLabel tx_id;
    private JTextField tf_id;
    private JLabel tx_disponible;
    private JLabel tx_saldo;
    private JTextField tf_disp;
    private JTextField tf_saldo;
    private JButton btn_crear;
    private JPanel jp_tarjeta;
    private JLabel tx_modtitle;
    private JLabel tx_id2;
    private JTextField tf_id2;
    private JTextField tf_new_disp;
    private JTextField tf_new_saldo;
    private JLabel tx_new_disp;
    private JLabel tx_new_saldo;
    private JButton btn_mod;
    private JTextField tf_id_tarj;
    private JLabel tx_id_tarj;
    private JButton btn_cerrar;
    private ServicioTarjetas servicioTarjetas = new ServicioTarjetas();
    private ServicioCuenta servicioCuenta = new ServicioCuenta();


    public gui_tarjeta()
    {
        setContentPane(jp_tarjeta);
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
                    if(servicioCuenta.buscarcbubool(Integer.parseInt(tf_id.getText())))
                    {
                        Tarjetas tarjeta = new Tarjetas();
                        tarjeta.setId(Integer.parseInt(tf_id.getText()));
                        tarjeta.setId_tarjeta(Integer.parseInt(tf_id_tarj.getText()));
                        tarjeta.setDisponibilidad(Integer.parseInt(tf_disp.getText()));
                        tarjeta.setSaldo_pagar(Integer.parseInt(tf_saldo.getText()));

                        try {
                            servicioTarjetas.guardarTarjeta(tarjeta);
                        } catch (ServiceException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch(ServiceException e){
                    throw new RuntimeException(e);
                }

                /*
                Tarjetas tarjeta = new Tarjetas();
                tarjeta.setId(Integer.parseInt(tf_id.getText()));
                tarjeta.setId_tarjeta(Integer.parseInt(tf_id_tarj.getText()));
                tarjeta.setDisponibilidad(Integer.parseInt(tf_disp.getText()));
                tarjeta.setSaldo_pagar(Integer.parseInt(tf_saldo.getText()));


                try {
                    servicioTarjetas.guardarTarjeta(tarjeta);
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                */



            }
        });

        btn_mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ActionEvent)
            {
                Tarjetas tarjeta = new Tarjetas();

                try
                {
                    tarjeta = servicioTarjetas.buscarTarjeta(Integer.parseInt(tf_id2.getText()));
                    tarjeta.setDisponibilidad(Integer.parseInt(tf_new_disp.getText()));
                    tarjeta.setSaldo_pagar(Integer.parseInt(tf_new_saldo.getText()));


                    try {
                        servicioTarjetas.modificarTarjeta(tarjeta);
                    } catch (ServiceException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (ServiceException ex) {
                    throw new RuntimeException(ex);
                }
                    /*
                    tarjeta.setDisponibilidad(Integer.parseInt(tf_new_disp.getText()));
                    tarjeta.setSaldo_pagar(Integer.parseInt(tf_new_saldo.getText()));
                    tarjeta = servicioTarjetas.modificarTarjeta(tarjeta);

                     */

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
