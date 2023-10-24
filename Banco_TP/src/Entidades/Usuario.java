package Entidades;
import java.util.ArrayList;

public class Usuario
{
    private int id;
    private String Nombre_Usuario;
    private String Contra;

    public Usuario ( String nombre, String contra){
        Nombre_Usuario = nombre;
        Contra = contra;
    }

    public Usuario(){

    }

// GET Y SET

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContra()
    {
        return Contra;
    }

    public String getNombre_Usuario()
    {
        return Nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        Nombre_Usuario = nombre_Usuario;
    }

    public void setContra(String contra) {
        Contra = contra;
    }

}

