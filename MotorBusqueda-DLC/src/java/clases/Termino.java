/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import BD.ConexionBD;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mayur
 */
public class Termino implements Comparable , Serializable{
    
    private String palabra;
    private int maxFrecuenia;
    private int nr;
    
    public Termino(String palabra, int frecuencia)
    {
        this.palabra = palabra;
        this.nr = 1;
        this.maxFrecuenia = frecuencia;
        
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getMaxFrecuenia() {
        return maxFrecuenia;
    }

    public void setMaxFrecuenia(int maxFrecuenia) {
        this.maxFrecuenia = maxFrecuenia;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.palabra);
        return hash;
    }
    
    public String toString()
    {
        return "Palabra: " + this.palabra + " Frecuencia: " + this.maxFrecuenia;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Termino other = (Termino) obj;
        if (!Objects.equals(this.palabra, other.palabra)) {
            return false;
        }
        return true;
    }

    public double getIdf() throws Exception{
        int N = ConexionBD.getInstance().getCantidadDocumento();
        return (Math.log10(N/nr));
    }
    
    /*@Override
    public int compareTo(Object o) {
        Termino obj = (Termino) o;
    }*/

    @Override
    public int compareTo(Object t) {
        if (this.getNr() < ((Termino)t).getNr())
        {
            return -1;
        }
        else if (this.getNr() > ((Termino)t).getNr()){
            return 1;
        }
        return 0;
    }

    public ArrayList<Documento> buscarDocumentos(int R) throws Exception {
            ArrayList<Documento> d = ConexionBD.getInstance().getDocumentosRelevantes("palabraxdocumento", R,this);
            ConexionBD.getInstance().closeConnection();
            return d;
    }
}


