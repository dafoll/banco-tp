package dao;

import Entidades.Cuenta;
import Entidades.Usuario;
import java.sql.*;
import java.util.ArrayList;
import com.mysql.jdbc.Driver;

public class DAOUsuario implements IDAO<Usuario>
{
//Primero cargo los parametros para realizar la conexion
    private String DB_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/banco" + "?autoReconnect=true&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASS = "1234";

    //Conectar la base de datos.

    @Override
    public void guardar(Usuario elemento) throws DAOException
    {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try { //lo atrapo en un catch
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("INSERT INTO USUARIO(ID,NOMBRE_USUARIO,CONTRA) VALUES(?,?,?)");
            preparedStatement.setInt(1,elemento.getId());
            preparedStatement.setString(2,elemento.getNombre_Usuario());
            preparedStatement.setString(3,elemento.getContra());
            //preparedStatement.setArray(4, (Array) elemento.getList_cuenta());
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros insertados: " + resultado);
        }
        //no conecta
        //error con sql
        catch(ClassNotFoundException | SQLException e) // por posibles errores
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
    public void modificar(Usuario elemento) throws DAOException
    {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("UPDATE USUARIO SET NOMBRE_USUARIO = ? , CONTRA = ? WHERE ID=?");
            preparedStatement.setString(1,elemento.getNombre_Usuario());
            preparedStatement.setString(2,elemento.getContra());
            preparedStatement.setInt(3,elemento.getId());
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros modificados: " + resultado);
        }
        catch(ClassNotFoundException |  SQLException e) // por posibles errores
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
            preparedStatement = connection.prepareStatement("DELETE FROM USUARIO WHERE ID = ?");
            preparedStatement.setInt(1, id);
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros BORRADOS: " + resultado);
        }
        catch(ClassNotFoundException |  SQLException e) // por posibles errores
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
    public Usuario buscar(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario usuario = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT NOMBRE_USUARIO, CONTRA FROM USUARIO WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next())
            {
                usuario = new Usuario();
                usuario.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setContra(rs.getString("CONTRA"));
            }
        } catch (ClassNotFoundException |  SQLException e) // por posibles errores
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

        return usuario;
    }

    @Override
    public Cuenta buscarAlias(String alias) throws DAOException {
        return null;
    }

    @Override
    public ArrayList<Usuario> buscarTodos() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario usuario = null;
        ArrayList<Usuario> usuarios =new ArrayList<>();
        try {
           Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM USUARIO");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next())
            {
                usuario = new Usuario();
                usuario.setNombre_Usuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setContra(rs.getString("CONTRA"));
                usuarios.add(usuario);
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
        return usuarios;
    }

}


