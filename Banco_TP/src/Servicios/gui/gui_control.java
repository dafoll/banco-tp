package Servicios.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui_control extends JFrame {
    private JButton btn_usario;
    private JButton btn_cuenta;
    private JButton btn_tarjeta;
    private JButton btn_transferencia;
    private JLabel tx_usuario;
    private JLabel tx_cuenta;
    private JLabel tx_tarjeta;
    private JLabel tx_trans;
    private JPanel jp_pc;

    public gui_control(){
        setContentPane(jp_pc);
        setVisible(true);
        setSize(400,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        btn_usario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui_usuario gui_usuario = new gui_usuario();
                dispose();
            }
        });

        btn_cuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui_cuenta gui_cuenta = new gui_cuenta();
                dispose();
            }
        });

        btn_tarjeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui_tarjeta gui_tarjeta = new gui_tarjeta();
                dispose();
            }
        });

        btn_transferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui_trasferencia gui_trasferencia = new gui_trasferencia();
                dispose();
            }
        });
    }
}
