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
public class Documento implements Comparable {
    private File documento;
    private double pesoTotal;
    private String nombre;
    
    public Documento(String documento){
        //String direccion = "/home/nicolastomassi/NetBeansProjects/MotorBusqueda-DLC/MotorBusqueda-DLC/src/documentos" + documento;
        String direccion = "C:\\Users\\aleex\\Documents\\NetBeansProjects\\MotorBusqueda-DLC\\MotorBusqueda-DLC\\src\\documentos\\" + documento;
        this.documento = new File(direccion);
        this.pesoTotal = 0;
        this.nombre = this.documento.getName();
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    public double getCalcularPeso(Termino palabra, int N) throws Exception{
        int tf = ConexionBD.getInstance().getFrecuenciaDoc("palabraxdocumento", palabra.getPalabra(), documento.getName());
        System.out.println("FRECUENCIA DEL DOC: " + this.getNombre() + " f: " + tf);
        ConexionBD.getInstance().closeConnection();
        double w = tf * Math.log10(N/palabra.getNr());
        return w;
    }
    
    public void agregarPeso(Termino t, int N) throws Exception
    {
        double w = this.getCalcularPeso(t , N);
        this.pesoTotal += w;
    }
    
    public void incrementarPeso(double w)
    {
        this.pesoTotal += w;
    }
    public File getDocumento() {
        return documento;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }
    
    public double pesoTotal(){
        return pesoTotal;
    }

    @Override
    public int compareTo(Object t) {
        if (this.pesoTotal() < ((Documento)t).pesoTotal())
        {
            return 1;
        }
        if (this.pesoTotal() > ((Documento)t).pesoTotal())
        {
            return -1;
        }
        return 0;
    }
}

