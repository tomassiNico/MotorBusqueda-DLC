/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author nicolastomassi
 */
public class ConexionBD {
    
    
    //Configuración de BD de Nico
    //public static String servidor = "jdbc:mysql://localhost:3306/DLC_Posteo";
    //public static String user = "root";
    //public static String pass = "";
    
    
    /*Configuración de BD de Mayra
    public static String servidor = "jdbc:mysql://localhost:3306/DLC-TP-Motor";
    public static String user = "root";
    public static String pass = "";*/
    
    //Configuración de BD de Alexis
    public static String servidor = "jdbc:mysql://localhost:3306/dlc-motor";
    public static String user = "root";
    public static String pass = "";
    public static ConexionBD instancia;
    
    
    
    public static String driver = "com.mysql.jdbc.Driver";
    public static Connection conexion;
    
    
    
    public static ConexionBD getInstance() throws Exception
    {
        if(instancia != null){
            return instancia;
        }
        else {
            instancia = new ConexionBD();
            instancia.MySQLConnection();
            return instancia;
        }
        
    }
    
    public void MySQLConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //conexion = (Connection) DriverManager.getConnection(this.servidor + this.servidor, this.user, this.pass);
            conexion = (Connection) DriverManager.getConnection(this.servidor, this.user, this.pass);
            System.out.println("SE ABRIO UNA NUEVA CONEXION A LA BASE DE DATOS");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void closeConnection() {
        try {
            System.out.println("SE CERRO LA CONEXION A LA BASE DE DATOS");
            conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void insertData(String table_name, String palabra, String documento, int frecuencia) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "'" + documento + "',"
                    + "'" + palabra + "',"
                    + frecuencia + ")";
            Statement st = (Statement) conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
        }
    } 
     
     public void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name + "ORDER BY frecuencia DESC";
            Statement st = (Statement) conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

//            while (resultSet.next()) {
//                System.out.println("ID: " + resultSet.getString("ID") + " "
//                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
//                        + "Edad: " + resultSet.getString("Edad") + " "
//                        + "Sexo: " + resultSet.getString("Sexo"));
//            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    } 

    public int getFrecuenciaDoc(String table_name, String palabra, String documento) {
        try{
            String Query = "SELECT Frecuencia FROM " + table_name + " WHERE Termino = '"
                    + palabra + "' AND Documento = '" + documento + "'";
            Statement st = (Statement) conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            return Integer.valueOf(resultSet.getString("Frecuencia"));
        }
        catch (SQLException e) {
            
        }
        return 0;
    }
}
