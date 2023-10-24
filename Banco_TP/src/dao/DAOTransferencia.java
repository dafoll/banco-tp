package dao;

import Entidades.Cuenta;
import Entidades.Trasferencias;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

public class DAOTransferencia implements IDAO<Trasferencias> {

    private String DB_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/banco" + "?autoReconnect=true&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASS = "1234";


    @Override
    public void guardar(Trasferencias elemento) throws DAOException
    {
        System.out.println(1);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);


            preparedStatement = connection.prepareStatement("INSERT INTO TRASFERENCIA(ID_TRASFERENCIA,ID_DESTINO,ID_ORIGEN,CANT_TRASFERIR) VALUES (?,?,?,?) ");

            preparedStatement.setInt(1,elemento.getId_trasferencia());

            preparedStatement.setInt(2,elemento.getId_destino());

            preparedStatement.setInt(3,elemento.getId_origen());

            preparedStatement.setInt(4,elemento.getCant_trasferir());


            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros insertados: " + resultado);

        }catch(ClassNotFoundException | SQLException e){
            throw new DAOException(e.getMessage());
        }
        finally
        {
            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }

    }

    @Override
    public void modificar(Trasferencias elemento) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            preparedStatement = connection.prepareStatement("UPDATE TRASFERENCIA SET CANT_TRASFERIR = ?, ID_ORIGEN = ?, ID_DESTINO = ? WHERE ID_TRASFERENCIA = ?");

            preparedStatement.setInt(1,elemento.getCant_trasferir());
            preparedStatement.setInt(2,elemento.getId_origen());
            preparedStatement.setInt(3,elemento.getId_destino());
            preparedStatement.setInt(4,elemento.getId_trasferencia());

        }catch(ClassNotFoundException | SQLException e){
            throw new DAOException(e.getMessage());
        }
        finally
        {
            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }
    }

    @Override
    public void eliminar(int id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("DELETE FROM TRASFERENCIA WHERE ID_TRASFERENCIA = ?");
            preparedStatement.setInt(1,id);
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros BORRADOS: " + resultado);
        }
        catch(ClassNotFoundException| SQLException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        }
        finally
        {
            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }
    }


    @Override
    public Trasferencias buscar(int id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Trasferencias trasferencias = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM TRASFERENCIA WHERE ID_TRASFERENCIA = ?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next())
            {
                trasferencias = new Trasferencias();
                trasferencias.setId_trasferencia(rs.getInt("ID_TRASFERENCIA"));
                trasferencias.setCant_trasferir(rs.getInt("CANTIDAD_TRASFERIR"));
                trasferencias.setId_destino(rs.getInt("ID_ORIGEN"));
                trasferencias.setId_origen(rs.getInt("ID_DESTINO"));
            }
        } catch (ClassNotFoundException | SQLException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        }
        finally
        {
            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }
        return trasferencias;
    }

    @Override
    public Cuenta buscarAlias(String alias) throws DAOException {
        return null;
    }

    @Override
    public ArrayList<Trasferencias> buscarTodos() throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Trasferencias trasferencias = null;
        ArrayList<Trasferencias> trasferenciasList =new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM TRASFERENCIA");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next())
            {
                trasferencias = new Trasferencias();
                trasferencias.setId_trasferencia(rs.getInt("ID_TRASFERENCIA"));
                trasferencias.setCant_trasferir(rs.getInt("CANTIDAD_TRASFERIR"));
                trasferencias.setId_destino(rs.getInt("ID_ORIGEN"));
                trasferencias.setId_origen(rs.getInt("ID_DESTINO"));
                trasferenciasList.add(trasferencias);
            }
        } catch (ClassNotFoundException | SQLException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        }

        finally
        {
            if(preparedStatement != null)
            {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }
        return trasferenciasList;
    }
}
