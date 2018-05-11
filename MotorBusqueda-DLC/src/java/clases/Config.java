/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author aleex
 */
public class Config {
    private String pathDocumentos;
    private int numeroDocumentos;
    private String nombreUsuario;

    public Config(String pathDocumentos) {
        this.pathDocumentos = pathDocumentos;
    }

    public Config(int numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    public Config(String pathDocumentos, int numeroDocumentos, String nombreUsuario) {
        this.pathDocumentos = pathDocumentos;
        this.numeroDocumentos = numeroDocumentos;
        this.nombreUsuario = nombreUsuario;
    }

    public String getPathDocumentos() {
        return pathDocumentos;
    }

    public int getNumeroDocumentos() {
        return numeroDocumentos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setPathDocumentos(String pathDocumentos) {
        this.pathDocumentos = pathDocumentos;
    }

    public void setNumeroDocumentos(int numeroDocumentos) {
        this.numeroDocumentos = numeroDocumentos;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
}
