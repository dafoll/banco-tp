package Servicios;

import Entidades.Usuario;
import dao.DAOException;
import dao.DAOCuentas;
import Entidades.Cuenta;

import java.util.ArrayList;

public class ServicioCuenta
{
    private DAOCuentas daoCuentas;

    public ServicioCuenta(){
        daoCuentas = new DAOCuentas();
    }

    public void guardarCuenta(Cuenta cuenta) throws ServiceException
    {
        try{
            daoCuentas.guardar(cuenta);
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }

    }

    public void modificarCuenta(Cuenta cuenta) throws ServiceException
    {
        try{
            daoCuentas.modificar(cuenta);
        }
        catch(DAOException e){
            throw new ServiceException((e.getMessage()));
        }
    }

    public void eliminarCuenta(int id) throws ServiceException
    {
        try{
            daoCuentas.eliminar(id);
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Cuenta buscarCuenta(int id) throws ServiceException
    {
        Cuenta cuenta = new Cuenta();
        try{
            cuenta = daoCuentas.buscar(id);
            return cuenta;
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public Cuenta buscarcbu(int cbu) throws ServiceException
    {
        Cuenta cuenta = new Cuenta();
        try{
            cuenta = daoCuentas.buscarcbu(Integer.toString(cbu));
            return cuenta;
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Cuenta> buscarTodasCuenta() throws ServiceException
    {
        ArrayList<Cuenta> cuentas;
        try{
            cuentas = daoCuentas.buscarTodos();
            return cuentas;
        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean buscarCuentabool(int id) throws ServiceException{
        Cuenta cuenta;
        cuenta = buscarCuenta(id);
        if(cuenta != null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean buscarcbubool(int id) throws ServiceException
    {
        Cuenta cuenta;
        cuenta = buscarcbu(id);

        if(cuenta != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
