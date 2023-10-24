package Servicios.gui;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private FormUsuario formularioUsuario;

    JFrame ventana;

    public PanelManager(int tipo)
    {
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        formularioUsuario = new FormUsuario(this);
        mostrar(formularioUsuario);
        ventana.setSize(300,300);
        ventana.setVisible(true);
        ventana.pack();
    }
    void mostrar(JPanel panel)
    {
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.SOUTH,panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();

    }

    public void mostrarFormularioUsuario(){
        formularioUsuario.vaciarComponentes();
        mostrar(formularioUsuario);
    }

    public void mostrarFormularioEstudiante(Usuario usuario){
        formularioUsuario.armarFormulario(usuario);
        mostrar(formularioUsuario);
    }


}