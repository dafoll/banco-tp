package Servicios.gui;

import Entidades.Trasferencias;
import Servicios.ServiceException;
import Servicios.ServicioTarjetas;
import Servicios.ServicioTransferencia;
import Servicios.*;

import Entidades.Tarjetas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui_trasferencia extends JFrame
{
    private JTextField tf_id;
    private JLabel tx_id;
    private JLabel tx_titulo;
    private JLabel tx_id_dest;
    private JLabel tx_id_origen;
    private JTextField tf_id_dest;
    private JTextField tf_id_org;
    private JButton btn_crear;
    private JPanel jp_trasferencia;
    private JTextField tf_cant;
    private JLabel tx_cant;
    private JButton btn_cerrar;

    private ServicioTarjetas servicioTarjetas = new ServicioTarjetas();
    private ServicioTransferencia servicioTransferencia = new ServicioTransferencia();

    public gui_trasferencia()
    {
        setContentPane(jp_trasferencia);
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

                    if (servicioTarjetas.buscartarjetabool(Integer.parseInt(tf_id_dest.getText())))
                    {

                        if (servicioTarjetas.buscartarjetabool(Integer.parseInt(tf_id_org.getText())))
                        {


                            Trasferencias transferencia = new Trasferencias();

                            transferencia.setId_trasferencia(Integer.parseInt(tf_id.getText()));

                            transferencia.setId_destino(Integer.parseInt(tf_id_dest.getText()));

                            transferencia.setId_origen(Integer.parseInt(tf_id_org.getText()));

                            transferencia.setCant_trasferir(Integer.parseInt(tf_cant.getText()));

                            // servicioTransferencia.realizarTrasferencia(Integer.parseInt(tf_id_org.getText()),Integer.parseInt(tf_id_dest.getText()),Integer.parseInt(tf_cant.getText()));

                            try
                            {
                                servicioTransferencia.guardarTransferencia(transferencia);
                                servicioTransferencia.realizarTrasferencia(Integer.parseInt(tf_id_org.getText()), Integer.parseInt(tf_id_dest.getText()), Integer.parseInt(tf_cant.getText()),Integer.parseInt((tf_id.getText())));

                            } catch (ServiceException e)
                            {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                } catch (ServiceException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
