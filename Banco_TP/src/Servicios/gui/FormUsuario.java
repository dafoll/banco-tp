package Servicios.gui;

import Entidades.*;
import Servicios.ServiceException;
import Servicios.ServicioUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUsuario extends JPanel
{
    ServicioUsuario serviceUsuario;
    JPanel formUsuario = new JPanel();

    JLabel jLabelNombre;
    JLabel jLabelId;
    JLabel jLabelContra;
    JLabel jLabelId_cuenta;

    JTextField jTextFieldNombre;
    JTextField jTextFieldId;
    JTextField jTextFieContra;
    JTextField jTextFieldId_cuenta;


    JPanel botones;
    JButton jButtonGuardar;
    JButton jButtonNuevo;

    PanelManager panelmanager;

    public FormUsuario(PanelManager panelprincipal){
        panelmanager = panelprincipal;
        armarFormulario(null);
    }
    public void armarFormulario(Usuario usuario)
    {

        serviceUsuario = new ServicioUsuario();
        formUsuario = new JPanel();
        formUsuario.setLayout(new GridLayout(3,2)); //seteo un layout para este formulario


        //Hago los label de los elementos;
        jLabelId =new JLabel("id");
        jLabelNombre = new JLabel("Nombre: ");
        jLabelContra = new JLabel("Contra");
        jLabelId_cuenta = new JLabel("Id_cuenta");


        jTextFieldId = new JFormattedTextField(10);
        jTextFieldNombre = new JFormattedTextField(10);
        jTextFieContra = new JTextField(10);
        jTextFieldId_cuenta = new JTextField(10);


        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");

        formUsuario.add(jLabelId);
        formUsuario.add(jLabelNombre);
        formUsuario.add(jLabelContra);
        formUsuario.add(jLabelId_cuenta);

        formUsuario.add(jTextFieldId);
        formUsuario.add(jTextFieldNombre);
        formUsuario.add(jLabelContra);
        formUsuario.add(jLabelId_cuenta);


        formUsuario.setLayout(new BorderLayout());

        botones = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonNuevo = new JButton("Nuevo");

        botones.add(jButtonGuardar);
        botones.add(jButtonNuevo);

        add(botones, BorderLayout.SOUTH); // sito abajo del layout en los bordes.

        jButtonGuardar.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Usuario usuario = new Usuario();
                usuario.setNombre_Usuario(jTextFieldNombre.getText());
                usuario.setId(Integer.parseInt(jTextFieldId.getText()));
                usuario.setContra(jTextFieContra.getText());

                try{
                    serviceUsuario.guardarUsuario(usuario);
                }
                catch(ServiceException e){
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

        jTextFieldId.setText(usuario == null?"":String.valueOf(usuario.getId()));
        jTextFieldNombre.setText(usuario == null?"":String.valueOf(usuario.getNombre_Usuario()));
        jLabelContra.setText(usuario == null?"":String.valueOf(usuario.getContra()));

    }

    public void vaciarComponentes()
    {
        jTextFieldId.setText("");
        jTextFieldNombre.setText("");
        jTextFieContra.setText("");
    }

}
