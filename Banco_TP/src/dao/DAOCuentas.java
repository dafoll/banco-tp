package dao;

import Entidades.Cuenta;
import Entidades.Tarjetas;

import java.sql.*;
import java.util.ArrayList;

public class DAOCuentas implements IDAO<Cuenta>
{
    private String DB_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/banco" + "?autoReconnect=true&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASS = "1234";

    @Override
    public void guardar(Cuenta elemento) throws DAOException
    {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try { //lo atrapo en un catch
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            preparedStatement = connection.prepareStatement("INSERT INTO CUENTAS(id_usuario, cbu, alias, caja_ahorro, caja_ahorrousd) VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1,elemento.getId());
            preparedStatement.setInt(2,elemento.getcbu());
            preparedStatement.setString(3, elemento.getAlias());
            preparedStatement.setInt(4,elemento.getCaja_ahorro());
            preparedStatement.setInt(5,elemento.getCaja_ahorro_USD());

            //preparedStatement = connection.prepareStatement("INSERT INTO TARJETA (CBU_CUENTA,ID_TARJETA, DISPONIBLE, SALDO_PAGAR) VALUES (11,101,0,0)");
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros insertados: " + resultado);
        }
        //no conecta
        //error con sql
        catch(SQLException | ClassNotFoundException e) // por posibles errores
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
    public void modificar(Cuenta elemento) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try { //lo atrapo en un catch
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("UPDATE CUENTAS SET CBU = ?, ALIAS = ?, CAJA_AHORRO = ?, CAJA_AHORROUSD = ? WHERE ID_USUARIO = ? ");
            preparedStatement.setInt(1,elemento.getcbu());
            preparedStatement.setString(2, elemento.getAlias());
            preparedStatement.setInt(3,elemento.getCaja_ahorro());
            preparedStatement.setInt(4,elemento.getCaja_ahorro_USD());
            preparedStatement.setInt(5,elemento.getId());
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros Modificadas: " + resultado);
        }
        //no conecta
        //error con sql
        catch(SQLException | ClassNotFoundException e) // por posibles errores
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
            preparedStatement = connection.prepareStatement("DELETE FROM CUENTAS WHERE ID_USUARIO = ?");
            preparedStatement.setInt(1,id);
            int resultado = preparedStatement.executeUpdate();
            System.out.println("Registros BORRADOS: " + resultado);
        }
        catch(SQLException | ClassNotFoundException e) // por posibles errores
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
    public Cuenta buscar(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cuenta cuenta = new Cuenta();
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT ID_USUARIO, CBU,ALIAS,CAJA_AHORRO,CAJA_AHORROUSD FROM CUENTAS WHERE ID_USUARIO = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID_USUARIO"));
                cuenta.setcbu(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
                cuenta.setCaja_ahorro(rs.getInt("CAJA_AHORRO"));
                cuenta.setCaja_ahorro_USD(rs.getInt("CAJA_AHORROUSD"));
            }
        } catch (SQLException | ClassNotFoundException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        }finally
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
        return cuenta;
    }

    @Override
    public Cuenta buscarAlias(String alias) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cuenta cuenta = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE ALIAS = ?");
            preparedStatement.setString(1,alias);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setcbu(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
                cuenta.setCaja_ahorro(rs.getInt("CAJA_AHORRO"));
                cuenta.setCaja_ahorro_USD(rs.getInt("CAJA_AHORROUSD"));
            }
        } catch (SQLException | ClassNotFoundException e) // por posibles errores
        {
            throw new DAOException(e.getMessage()); // y mando un mensaje pero atrapado en mi Clase Exp
        }finally
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
        return cuenta;
    }

    public Cuenta buscarcbu(String cbu) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cuenta cuenta = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE CBU = ?");
            preparedStatement.setString(1, cbu);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID_USUARIO"));
                cuenta.setcbu(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
                cuenta.setCaja_ahorro(rs.getInt("CAJA_AHORRO"));
                cuenta.setCaja_ahorro_USD(rs.getInt("CAJA_AHORROUSD"));
            }
        } catch (SQLException | ClassNotFoundException e) // por posibles errores
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
        return cuenta;
    }

    @Override
    public ArrayList<Cuenta> buscarTodos() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cuenta cuenta = null;
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            preparedStatement = connection.prepareStatement("SELECT * FROM CUENTAS WHERE ID_USUARIO = ?");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setcbu(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
                cuenta.setCaja_ahorro(rs.getInt("CAJA_AHORRO"));
                cuenta.setCaja_ahorro_USD(rs.getInt("CAJA_AHORROUSD"));
                cuentas.add(cuenta);

            }
        } catch (SQLException | ClassNotFoundException e) // por posibles errores
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
        return cuentas;
    }
}
