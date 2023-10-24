package Servicios;

import dao.DAOException;
import dao.DAOTarjeta;
import Entidades.Tarjetas;
import java.sql.Connection;

import javax.security.sasl.SaslServer;
import java.util.ArrayList;

public class ServicioTarjetas
{
    private DAOTarjeta daoTarjeta;


    //contructor
    public ServicioTarjetas(){
        daoTarjeta = new DAOTarjeta();
    }

    public void guardarTarjeta(Tarjetas tarjetas) throws ServiceException
    {
        try{
            System.out.println("1");
            daoTarjeta.guardar(tarjetas);
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarTarjeta(Tarjetas tarjeta) throws ServiceException
    {
        try{
            daoTarjeta.modificar(tarjeta);
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarTarjeta(int id) throws ServiceException
    {
        try{
            daoTarjeta.eliminar(id);
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Tarjetas buscarTarjeta(int id) throws ServiceException
    {
        Tarjetas tarjeta = new Tarjetas();
        try{
            tarjeta = daoTarjeta.buscar(id);
            return tarjeta;
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Tarjetas> buscartodasTarjetas() throws ServiceException
    {
        ArrayList<Tarjetas> tarjetas;
        try{
            tarjetas = daoTarjeta.buscarTodos();
            return tarjetas;
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean buscartarjetabool(int id) throws ServiceException
    {
        Tarjetas tarjetas;
        tarjetas = buscarTarjeta(id);

        if( tarjetas != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
