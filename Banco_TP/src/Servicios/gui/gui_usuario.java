package Servicios.gui;

import Entidades.Usuario;
import Servicios.ServiceException;
import Servicios.ServicioUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui_usuario extends  JFrame
{

    ServicioUsuario serviceUsuario;
    private JTextField tf_usuario;
    private JPanel JLusuario;
    private JLabel TX_usuario;
    private JLabel TX_contra;
    private JTextField tf_contra;
    private JButton btn_crear;

    private JLabel tx_id;

    private JTextField tf_id;
    private JPanel jp_us;
    private JButton btn_cerrar;

    public gui_usuario()
    {

        serviceUsuario = new ServicioUsuario();

        setContentPane(JLusuario);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
/*
        btn_crear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Usuario usuario = new Usuario();
                usuario.setNombre_Usuario(tf_usuario.getText());
                usuario.setId(Integer.parseInt(tf_id.getText()));
                usuario.setContra(tf_contra.getText());

                try{
                    serviceUsuario.guardarUsuario(usuario);
                }
                catch(ServiceException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
*/

        btn_crear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Usuario usuario = new Usuario();
                usuario.setNombre_Usuario(tf_usuario.getText());
                usuario.setId(Integer.parseInt(tf_id.getText()));
                usuario.setContra(tf_contra.getText());

                try
                {
                    serviceUsuario.guardarUsuario(usuario);
                }
                catch(ServiceException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

/*
    private void createUIComponents()
    {
        // TODO: place custom component creation code here

        jp_us = new JPanel();
        tx_id = new JLabel("id");
        TX_usuario = new JLabel("usuario");
        TX_contra = new JLabel("contra");

        JLusuario = new JPanel();

        tf_id = new JTextField("id");
        tf_usuario = new JTextField("usuario");
        tf_contra = new JTextField("contra");

        jp_us.setLayout(new GridLayout());
        JLusuario.setLayout(new GridLayout()) ;


        jp_us.add(tf_id);
        jp_us.add(tf_usuario);
        jp_us.add(tf_contra);
        jp_us.add(tx_id);
        jp_us.add(TX_usuario);
        jp_us.add(TX_contra);

        JLusuario.add(jp_us);

        JLusuario.add(tf_id);
        JLusuario.add(tf_usuario);
        JLusuario.add(tf_contra);
        JLusuario.add(tx_id);
        JLusuario.add(TX_usuario);
        JLusuario.add(TX_contra);



        btn_crear.addActionListener(actionEvent -> {
            Usuario usuario = new Usuario();
            usuario.setNombre_Usuario(tf_usuario.getText());
            usuario.setId(Integer.parseInt(tf_id.getText()));
            usuario.setContra(tf_contra.getText());

            try{
                serviceUsuario.guardarUsuario(usuario);
            }
            catch(ServiceException e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }

        });
    }

 */
}

