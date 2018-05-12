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
        //String direccion = "/home/nicolastomassi/DocumentosTP1/" + documento;
        //String direccion = "C:\\Users\\aleex\\Documents\\NetBeansProjects\\MotorBusqueda-DLC\\MotorBusqueda-DLC\\src\\documentos\\" + documento;
        String direccion = "documentos\\" + documento;
        this.documento = new File(direccion);
        this.pesoTotal = 0.0;
        this.nombre = this.documento.getName();
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    public double getCalcularPeso(Termino palabra, int N, int tf) throws Exception{
        double n = (double)N;
        double nr = (double)palabra.getNr();
        double log = Math.log10(n/nr);
        double w = ((double)tf) * log;
        w = Math.round(w*1000.0)/1000.0;
        return w;
    }
    
    public void agregarPeso(Termino t, int N , int frecuencia) throws Exception
    {
        double w = this.getCalcularPeso(t , N, frecuencia);
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

