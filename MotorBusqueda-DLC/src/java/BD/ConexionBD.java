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
    public static String servidor = "jdbc:mysql://localhost:3306/DLC_Posteo";
    public static String user = "root";
    public static String pass = "";
    
    
    /*Configuración de BD de Mayra
    public static String servidor = "jdbc:mysql://localhost:3306/DLC-TP-Motor";
    public static String user = "root";
    public static String pass = "";
    
    /*Configuración de BD de Alexis
    public static String servidor = "jdbc:mysql://localhost:3306/DLC_Posteo";
    public static String user = "root";
    public static String pass = "";
    */
    
    
    public static String driver = "com.mysql.jdbc.Driver";
    public static Connection conexion;
    
    
    
    
    
    public void MySQLConnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(this.servidor + this.servidor, this.user, this.pass);
            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void closeConnection() {
        try {
            conexion.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void insertData(String table_name, String palabra, String documento, int frecuencia) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + palabra + "\", "
                    + "\"" + documento + "\", "
                    + "\"" + frecuencia + "\")";
            Statement st = (Statement) conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
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
}
