/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;

/**
 *
 * @author nicolastomassi
 */
public class Documento {
    private File documento;
    private double peso;
    
    public Documento(String documento){
        this.documento = new File(documento);
    }
    
}
