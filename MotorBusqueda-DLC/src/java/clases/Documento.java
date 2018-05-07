/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import BD.ConexionBD;
import java.io.File;

/**
 *
 * @author nicolastomassi
 */
public class Documento {
    private File documento;
    private double pesoTotal;
    
    public Documento(String documento){
        String direccion = "/home/nicolastomassi/NetBeansProjects/MotorBusqueda-DLC/MotorBusqueda-DLC/src/documentos" + documento;
        this.documento = new File(direccion);
        this.pesoTotal = 0;
    }
    
    public double getCalcularPeso(Termino palabra, int N) throws Exception{
        ConexionBD bd = new ConexionBD();
        bd.MySQLConnection();
        int tf = bd.getFrecuenciaDoc("palabraxdocumento", palabra.getPalabra(), documento.getName());
        bd.closeConnection();
        
        double w = tf * Math.log10(N/palabra.getNr());
        return w;
    }
    
    public void agregarPeso(double w)
    {
        this.pesoTotal += w;
    }
    
    public double pesoTotal(){
        return pesoTotal;
    }
}

