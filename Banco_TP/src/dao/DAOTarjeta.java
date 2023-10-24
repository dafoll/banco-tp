package dao;
import Entidades.Cuenta;
import Entidades.Tarjetas;

import java.sql.*;
import java.util.ArrayList;

import Entidades.Cuenta;
import Entidades.Usuario;
import java.sql.*;
import java.util.ArrayList;
import com.mysql.jdbc.Driver;
import Entidades.Cuenta;
import Entidades.Tarjetas;

import java.sql.*;
import java.util.ArrayList;


public class DAOTarjeta implements IDAO<Tarjetas>
{
    //Primero cargo los parametros para realizar la conexion
    private String DB_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/banco" + "?autoReconnect=true&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASS = "1234";
    //Conectar la base de datos.

    @Override
    public void guardar(Tarjetas elemento) throws DAOException
    {

        Connection connection;
        PreparedStatement preparedStatement = null;

        try
        { //lo atrapo en un catch
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            preparedStatement = connection.prepareStatement("INSERT INTO TARJETA (CBU_CUENTA,ID_TARJETA, DISPONIBLE, SALDO_PAGAR) VALUES (?,?,?,?)");

            preparedStatement.setInt(1,elemento.getId()); //seria la clave foranea
            preparedStatement.setInt(2,elemento.getId_tarjeta());
            preparedStatement.setInt(3,elemento.getDisponibilidad());
            preparedStatement.setInt(4,elemento.getSaldo_pagar());

            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros insertados: " + resultado);
        }
        //no conecta
        //error con sql
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
    public void modificar(Tarjetas elemento) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("UPDATE TARJETA SET DISPONIBLE = ? , SALDO_PAGAR = ? WHERE ID_TARJETA=?");
            preparedStatement.setInt(1,elemento.getDisponibilidad());
            preparedStatement.setInt(2,elemento.getSaldo_pagar());
            preparedStatement.setInt(3,elemento.getId_tarjeta());
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros modificados: " + resultado);
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
    public void eliminar(int id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("DELETE FROM TARJETAS WHERE ID_TARJETA = ?");
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
    public Tarjetas buscar(int id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Tarjetas tarjeta = null;
        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM TARJETA WHERE ID_TARJETA = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                tarjeta = new Tarjetas();
                //
                tarjeta.setId(rs.getInt("CBU_CUENTA"));
                tarjeta.setId_tarjeta(rs.getInt("ID_TARJETA"));
                tarjeta.setDisponibilidad(rs.getInt("DISPONIBLE"));
                tarjeta.setSaldo_pagar(rs.getInt("SALDO_PAGAR"));
            }

        } catch (ClassNotFoundException | SQLException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        } finally
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
        return tarjeta;
    }

    @Override
    public Cuenta buscarAlias(String alias) throws DAOException {
        return null;
    }

    @Override
    public ArrayList<Tarjetas> buscarTodos() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Tarjetas tarjeta = null;
        ArrayList<Tarjetas> tarjetas =new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT FROM TARJETA");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next())
            {
                tarjeta = new Tarjetas();
                tarjeta.setId(rs.getInt("ID"));
                tarjeta.setId_tarjeta(rs.getInt("ID_TARJETA"));
                tarjeta.setDisponibilidad(rs.getInt("DISPONIBILIDAD"));
                tarjeta.setSaldo_pagar(rs.getInt("SALDO_PAGAR"));
                tarjetas.add(tarjeta);
            }
        } catch (ClassNotFoundException | SQLException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        } finally
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
        return tarjetas;
    }
}
