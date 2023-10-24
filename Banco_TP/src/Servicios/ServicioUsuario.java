package Servicios;

import Entidades.Usuario;
import dao.DAOException;
import dao.DAOUsuario;

import java.util.ArrayList;

public class ServicioUsuario
{
    private DAOUsuario daoUsuario;

    public ServicioUsuario() {
        daoUsuario = new DAOUsuario();
    }

    public void guardarUsuario(Usuario usuario) throws ServiceException
    {
        try
        {
            daoUsuario.guardar(usuario);
        }
        catch(DAOException e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarUsuario(Usuario usuario) throws ServiceException{
        try{
            daoUsuario.modificar(usuario);
        }
        catch(DAOException e){
            throw new ServiceException((e.getMessage()));
        }
    }
    public Usuario buscarUsuario(int id) throws ServiceException
    {
        Usuario usuario = new Usuario();
        try{
           usuario = daoUsuario.buscar(id);
           return usuario;
        }
        catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void borrarUsuario(int id) throws ServiceException{
        try{
            daoUsuario.eliminar(id);
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Usuario> bustartodosusuarios() throws ServiceException{
        ArrayList<Usuario> usuarios;
        try{
            usuarios = daoUsuario.buscarTodos();
            return usuarios;
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean buscarUsuariobool(int id) throws ServiceException{
        Usuario usuario;
        usuario = buscarUsuario(id);
        if(usuario != null){
            return true;
        }
        else{
            return false;
        }
    }

}
