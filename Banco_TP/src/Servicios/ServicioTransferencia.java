package Servicios;

import Entidades.Cuenta;
import dao.DAOCuentas;
import dao.DAOException;
import dao.DAOTransferencia;
import Entidades.Trasferencias;
import dao.*;
import Servicios.*;
import Entidades.*;

import java.util.ArrayList;
import java.util.Collections;

public class ServicioTransferencia {
    private DAOTransferencia daoTransferencia;

    public ServicioTransferencia() {
        daoTransferencia = new DAOTransferencia();
    }

    public void guardarTransferencia(Trasferencias trasferencias) throws ServiceException {
        try {
            daoTransferencia.guardar(trasferencias);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarTransferencia(Trasferencias trasferencias) throws ServiceException {
        try {
            daoTransferencia.modificar(trasferencias);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarTransferencia(int id) throws ServiceException {
        try {
            daoTransferencia.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    public Trasferencias buscarTrasferencia(int id) throws ServiceException {
        Trasferencias trasferencias;
        try {
            trasferencias = daoTransferencia.buscar(id);
            return trasferencias;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Trasferencias> buscarTodasTrasferencia() throws ServiceException {
        ArrayList<Trasferencias> trasferencias;
        try {
            trasferencias = daoTransferencia.buscarTodos();
            return trasferencias;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void realizarTrasferencia(int id_origen, int id_destino, int cant_trasferir,int id_trasferencia) throws ServiceException {
        Trasferencias trasferencia_nueva = new Trasferencias();
        //DAOCuentas daoCuentas = new DAOCuentas();
        //Cuenta cuenta_origen = new Cuenta();
        //Cuenta cuenta_destino = new Cuenta();
        Tarjetas tarjeta_origen = new Tarjetas();
        Tarjetas tarjeta_destino = new Tarjetas();
        DAOTarjeta daotarjeta = new DAOTarjeta();
        DAOTransferencia daoTransferencia = new DAOTransferencia();

        try {
            //busco las cuentas de origen y destino en la base
            tarjeta_origen = daotarjeta.buscar(id_origen);
            tarjeta_destino = daotarjeta.buscar(id_destino);

            //a esas cuentas le modifico sus atributos de "Caja_ahorro"
            if(cant_trasferir <= tarjeta_origen.getDisponibilidad()) {
                tarjeta_origen.setDisponibilidad(tarjeta_origen.getDisponibilidad() - cant_trasferir);
                tarjeta_destino.setDisponibilidad(tarjeta_destino.getDisponibilidad() + cant_trasferir);
                //modifico en la base, los cambios que hice
                daotarjeta.modificar(tarjeta_origen);
                daotarjeta.modificar(tarjeta_destino);
                System.out.println("trasferencia exitosa");
            }
            else{
                daoTransferencia.eliminar(id_trasferencia);
                System.out.println("trasferencia fallida");
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

/*
    public void realizarTrasferencia(String alias_origen, String alias_destino, int cant_trasferir) throws  ServiceException
    {
        Trasferencias trasferencia_nueva = new Trasferencias();
        Trasferencias max_id_trasferencia = new Trasferencias();
        ArrayList<Trasferencias> list_trasferencia = new ArrayList<>();
        DAOCuentas daoCuentas = new DAOCuentas();
        Cuenta cuenta_origen = new Cuenta();
        Cuenta cuenta_destino = new Cuenta();

        try
        {
            //busco las cuentas de origen y destino en la base
            cuenta_origen = daoCuentas.buscarAlias(alias_origen);
            cuenta_destino = daoCuentas.buscarAlias(alias_destino);

            //a esas cuentas le modifico sus atributos de "Caja_ahorro"
            cuenta_origen.setCaja_ahorro(cuenta_origen.getCaja_ahorro() - cant_trasferir);
            cuenta_destino.setCaja_ahorro(cuenta_destino.getCaja_ahorro() + cant_trasferir);

            //modifico en la base, los cambios que hice
            daoCuentas.modificar(cuenta_origen);
            daoCuentas.modificar(cuenta_destino);

            //consigo la lista de trasferencias en la base
            list_trasferencia = daoTransferencia.buscarTodos();

            //consigo la trasferencia con el Id_trasferencia mas alto
            max_id_trasferencia = (Trasferencias) Collections.max(list_trasferencia);


            //si id_trasferencia es 0 o un numero mayor, a la trasferencia nueva le sumo 1
            if(max_id_trasferencia.getId_trasferencia() >= 0)
            {
                trasferencia_nueva.setId_trasferencia(max_id_trasferencia.getId_trasferencia() + 1);
            }
            //si la lista de trasferencia esta vacia y me devuelve un null, seteo que Id trasferencia es 1
            else if(max_id_trasferencia.equals(null)){
                trasferencia_nueva.setId_trasferencia(1);
            }

            //a la nueva trasferencia le agrego los datos para guardar
            trasferencia_nueva.setId_origen(cuenta_origen.getId());
            trasferencia_nueva.setId_destino(cuenta_destino.getId());
            trasferencia_nueva.setCant_trasferir(cant_trasferir);

            //guardo la nueva trasferencia en la base de datos.
            daoTransferencia.guardar(trasferencia_nueva);

        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void realizarTrasferencia(int cant_trasferir, String cbu_origen, String cbu_destino) throws  ServiceException
    {
        Trasferencias trasferencia_nueva = new Trasferencias();
        Trasferencias max_id_trasferencia = new Trasferencias();
        ArrayList<Trasferencias> list_trasferencia = new ArrayList<>();
        DAOCuentas daoCuentas = new DAOCuentas();
        Cuenta cuenta_origen = new Cuenta();
        Cuenta cuenta_destino = new Cuenta();

        try
        {
            //busco las cuentas de origen y destino en la base
            cuenta_origen = daoCuentas.buscarcbu(cbu_origen);
            cuenta_destino = daoCuentas.buscarcbu(cbu_destino);

            //a esas cuentas le modifico sus atributos de "Caja_ahorro"
            cuenta_origen.setCaja_ahorro(cuenta_origen.getCaja_ahorro() - cant_trasferir);
            cuenta_destino.setCaja_ahorro(cuenta_destino.getCaja_ahorro() + cant_trasferir);

            //modifico en la base, los cambios que hice
            daoCuentas.modificar(cuenta_origen);
            daoCuentas.modificar(cuenta_destino);

            //consigo la lista de trasferencias en la base
            list_trasferencia = daoTransferencia.buscarTodos();

            //consigo la trasferencia con el Id_trasferencia mas alto
            max_id_trasferencia = (Trasferencias) Collections.max(list_trasferencia);


            //si id_trasferencia es 0 o un numero mayor, a la trasferencia nueva le sumo 1
            if(max_id_trasferencia.getId_trasferencia() >= 0)
            {
                trasferencia_nueva.setId_trasferencia(max_id_trasferencia.getId_trasferencia() + 1);
            }
            //si la lista de trasferencia esta vacia y me devuelve un null, seteo que Id trasferencia es 1
            else if(max_id_trasferencia.equals(null)){
                trasferencia_nueva.setId_trasferencia(1);
            }

            //a la nueva trasferencia le agrego los datos para guardar
            trasferencia_nueva.setId_origen(cuenta_origen.getId());
            trasferencia_nueva.setId_destino(cuenta_destino.getId());
            trasferencia_nueva.setCant_trasferir(cant_trasferir);

            //guardo la nueva trasferencia en la base de datos.
            daoTransferencia.guardar(trasferencia_nueva);

        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }


    public void realizarTrasferenciaUSD(int id_origen, int id_destino, int cant_trasferir) throws  ServiceException
    {
        Trasferencias trasferencia_nueva = new Trasferencias();
        Trasferencias max_id_trasferencia = new Trasferencias();
        ArrayList<Trasferencias> list_trasferencia = new ArrayList<>();
        DAOCuentas daoCuentas = new DAOCuentas();
        Cuenta cuenta_origen = new Cuenta();
        Cuenta cuenta_destino = new Cuenta();

        try
        {
            //busco las cuentas de origen y destino en la base
            cuenta_origen = daoCuentas.buscar(id_origen);
            cuenta_destino = daoCuentas.buscar(id_destino);

            //a esas cuentas le modifico sus atributos de "Caja_ahorro"
            cuenta_origen.setCaja_ahorro(cuenta_origen.getCaja_ahorro_USD() - cant_trasferir);
            cuenta_destino.setCaja_ahorro(cuenta_destino.getCaja_ahorro_USD() + cant_trasferir);

            //modifico en la base, los cambios que hice
            daoCuentas.modificar(cuenta_origen);
            daoCuentas.modificar(cuenta_destino);

            //consigo la lista de trasferencias en la base
            list_trasferencia = daoTransferencia.buscarTodos();

            //consigo la trasferencia con el Id_trasferencia mas alto
            max_id_trasferencia = (Trasferencias) Collections.max(list_trasferencia);


            //si id_trasferencia es 0 o un numero mayor, a la trasferencia nueva le sumo 1
            if(max_id_trasferencia.getId_trasferencia() >= 0)
            {
                trasferencia_nueva.setId_trasferencia(max_id_trasferencia.getId_trasferencia() + 1);
            }
            //si la lista de trasferencia esta vacia y me devuelve un null, seteo que Id trasferencia es 1
            else if(max_id_trasferencia.equals(null)){
                trasferencia_nueva.setId_trasferencia(1);
            }

            //a la nueva trasferencia le agrego los datos para guardar
            trasferencia_nueva.setId_origen(id_origen);
            trasferencia_nueva.setId_destino(id_destino);
            trasferencia_nueva.setCant_trasferir(cant_trasferir);

            //guardo la nueva trasferencia en la base de datos.
            daoTransferencia.guardar(trasferencia_nueva);

        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }
    public void realizarTrasferenciaUSD(String alias_origen, String alias_destino, int cant_trasferir) throws  ServiceException
    {
        Trasferencias trasferencia_nueva = new Trasferencias();
        Trasferencias max_id_trasferencia = new Trasferencias();
        ArrayList<Trasferencias> list_trasferencia = new ArrayList<>();
        DAOCuentas daoCuentas = new DAOCuentas();
        Cuenta cuenta_origen = new Cuenta();
        Cuenta cuenta_destino = new Cuenta();

        try
        {
            //busco las cuentas de origen y destino en la base
            cuenta_origen = daoCuentas.buscarAlias(alias_origen);
            cuenta_destino = daoCuentas.buscarAlias(alias_destino);

            //a esas cuentas le modifico sus atributos de "Caja_ahorro"
            cuenta_origen.setCaja_ahorro(cuenta_origen.getCaja_ahorro_USD() - cant_trasferir);
            cuenta_destino.setCaja_ahorro(cuenta_destino.getCaja_ahorro_USD() + cant_trasferir);

            //modifico en la base, los cambios que hice
            daoCuentas.modificar(cuenta_origen);
            daoCuentas.modificar(cuenta_destino);

            //consigo la lista de trasferencias en la base
            list_trasferencia = daoTransferencia.buscarTodos();

            //consigo la trasferencia con el Id_trasferencia mas alto
            max_id_trasferencia = (Trasferencias) Collections.max(list_trasferencia);


            //si id_trasferencia es 0 o un numero mayor, a la trasferencia nueva le sumo 1
            if(max_id_trasferencia.getId_trasferencia() >= 0)
            {
                trasferencia_nueva.setId_trasferencia(max_id_trasferencia.getId_trasferencia() + 1);
            }
            //si la lista de trasferencia esta vacia y me devuelve un null, seteo que Id trasferencia es 1
            else if(max_id_trasferencia.equals(null)){
                trasferencia_nueva.setId_trasferencia(1);
            }

            //a la nueva trasferencia le agrego los datos para guardar
            trasferencia_nueva.setId_origen(cuenta_origen.getId());
            trasferencia_nueva.setId_destino(cuenta_destino.getId());
            trasferencia_nueva.setCant_trasferir(cant_trasferir);

            //guardo la nueva trasferencia en la base de datos.
            daoTransferencia.guardar(trasferencia_nueva);

        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public void realizarTrasferenciaUSD(int cant_trasferir, String cbu_origen, String cbu_destino ) throws  ServiceException
    {
        Trasferencias trasferencia_nueva = new Trasferencias();
        Trasferencias max_id_trasferencia = new Trasferencias();
        ArrayList<Trasferencias> list_trasferencia = new ArrayList<>();
        DAOCuentas daoCuentas = new DAOCuentas();
        Cuenta cuenta_origen = new Cuenta();
        Cuenta cuenta_destino = new Cuenta();

        try
        {
            //busco las cuentas de origen y destino en la base
            cuenta_origen = daoCuentas.buscarcbu(cbu_origen);
            cuenta_destino = daoCuentas.buscarcbu(cbu_destino);

            //a esas cuentas le modifico sus atributos de "Caja_ahorro"
            cuenta_origen.setCaja_ahorro(cuenta_origen.getCaja_ahorro_USD() - cant_trasferir);
            cuenta_destino.setCaja_ahorro(cuenta_destino.getCaja_ahorro_USD() + cant_trasferir);

            //modifico en la base, los cambios que hice
            daoCuentas.modificar(cuenta_origen);
            daoCuentas.modificar(cuenta_destino);

            //consigo la lista de trasferencias en la base
            list_trasferencia = daoTransferencia.buscarTodos();

            //consigo la trasferencia con el Id_trasferencia mas alto
            max_id_trasferencia = (Trasferencias) Collections.max(list_trasferencia);


            //si id_trasferencia es 0 o un numero mayor, a la trasferencia nueva le sumo 1
            if(max_id_trasferencia.getId_trasferencia() >= 0)
            {
                trasferencia_nueva.setId_trasferencia(max_id_trasferencia.getId_trasferencia() + 1);
            }
            //si la lista de trasferencia esta vacia y me devuelve un null, seteo que Id trasferencia es 1
            else if(max_id_trasferencia.equals(null)){
                trasferencia_nueva.setId_trasferencia(1);
            }

            //a la nueva trasferencia le agrego los datos para guardar
            trasferencia_nueva.setId_origen(cuenta_origen.getId());
            trasferencia_nueva.setId_destino(cuenta_destino.getId());
            trasferencia_nueva.setCant_trasferir(cant_trasferir);

            //guardo la nueva trasferencia en la base de datos.
            daoTransferencia.guardar(trasferencia_nueva);

        }
        catch(DAOException e){
            throw new ServiceException(e.getMessage());
        }
    }

}
*/
}