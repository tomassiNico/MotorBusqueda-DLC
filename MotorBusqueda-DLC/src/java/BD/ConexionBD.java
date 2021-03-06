/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import clases.Documento;
import clases.Termino;
import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
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
    private static String servidor = "jdbc:mysql://localhost:3306/dlc-motor";
    private static String user = "root";
    private static String pass = "";
    private static ConexionBD instancia;
    
    
    
    public static String driver = "com.mysql.jdbc.Driver";
    public static Connection conexion;
    
    
    
    public static ConexionBD getInstance() throws Exception
    {
        if(instancia != null){
            instancia.MySQLConnection();
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
            //System.out.println("SE ABRIO UNA NUEVA CONEXION A LA BASE DE DATOS");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void closeConnection() {
        try {
            //System.out.println("SE CERRO LA CONEXION A LA BASE DE DATOS");
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
        this.closeConnection();
    } 
    public void insertDataMasivo(String table_name, ArrayList palabra, String doc, ArrayList frec)
    {
         try {
             StringBuilder Query = new StringBuilder();
             Query.append("INSERT INTO " + table_name + " VALUES");
             int conMaxPaquete = 5000;
             Statement st = (Statement) conexion.createStatement();
            for (int i = 0; i< palabra.size(); i++)
             {
                 if (i == conMaxPaquete) {
                        Query.append("('" + doc + "','" + palabra.get(i) + "'," + frec.get(i) + ")"); 
                        st.executeUpdate(Query.toString());
                        Query = new StringBuilder();
                        Query.append("INSERT INTO " + table_name + " VALUES");
                        conMaxPaquete += 5000;
                        continue;
                 }
                 if (i == palabra.size() - 1){
                     Query.append("('" + doc + "','" + palabra.get(i) + "'," + frec.get(i) + ")"); 
                     continue;
                 }
                 Query.append("('" + doc + "','" + palabra.get(i) + "'," + frec.get(i) + "),");
                 //System.out.println("('" + doc + "','" + palabra.get(i) + "'," + frec.get(i) + "),");
                 //
             }
             //System.out.println("TAMAÑO DEL DOC: " + palabra.size());
             st.executeUpdate(Query.toString());
             
         } 
         catch (SQLException ex) {
             System.out.println("Error en la insercion de datos del Posteo. DOCUMENTO: " + doc);
        }
         this.closeConnection();
    }

    public int getFrecuenciaDoc(String table_name, String palabra, String documento) {
        int frecuencia = 0;
        try {
            String Query = "SELECT Frecuencia FROM " + table_name + " WHERE Termino = '"
                    + palabra + "' AND Documento = '" + documento + "'";
            PreparedStatement ps = (PreparedStatement) conexion.prepareStatement(Query);
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next())
                {
                    frecuencia = rs.getInt("Frecuencia");
                }
        }
        catch (SQLException e){
            System.out.println("ERROR AL OBTENER LA FRECUENCIA");
        }
        this.closeConnection();
        return frecuencia;
    }

    public int getCantidadDocumento() {
        int cont = 0;
        try{
            String Query = "SELECT Distinct Documento FROM palabraxdocumento";
            Statement st = (Statement) conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            while (resultSet.next())
            {
                cont += 1;
            }
        }
        catch(SQLException e){
        
        }
        this.closeConnection();
        return cont;
    }

    public ArrayList<Documento> getDocumentosRelevantes(String table_name, int R, Termino termino, int N) throws Exception
    {
        ArrayList<Documento> docs = new ArrayList();
        try {
            String Query = "SELECT Documento, Frecuencia FROM " + table_name + " WHERE Termino='" + termino.getPalabra() + 
                "' ORDER BY Frecuencia DESC LIMIT "+ R; 
            Statement st = (Statement) conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            while(resultSet.next())
            {
                String documento = resultSet.getString("Documento");
                int frec = resultSet.getInt("Frecuencia");
                Documento d = new Documento(documento);
                d.agregarPeso(termino, N, frec);
                docs.add(d);
            }
        }
        catch(SQLException e){
            System.out.println("Ocurrio un problema con la base de datos");
        }
        this.closeConnection();
        return docs;
    }
    
}


