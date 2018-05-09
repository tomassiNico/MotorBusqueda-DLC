/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BD.ConexionBD;
import clases.Busqueda;
import clases.Documento;
import clases.Serializacion.VocabularioIOException;
import clases.Serializacion.VocabularioReader;
import clases.Serializacion.VocabularioWriter;
import clases.Termino;
import clases.Vocabulario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author aleex
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws VocabularioIOException, Exception {
        // TODO code application logic here
        Vocabulario vocabulario;
        try
        {
            VocabularioReader hr = new VocabularioReader();
            vocabulario = hr.read();
        }
        catch(Exception e)
        {
            vocabulario = new Vocabulario();
            vocabulario.agregarCarpetaDocumentos();
            VocabularioWriter hw = new VocabularioWriter();
            hw.write(vocabulario);
        }
        ArrayList<Termino> palabrasBuscadas = new ArrayList<>();
        Termino t = vocabulario.getVocabulario().get("work");
        palabrasBuscadas.add(t);
        Busqueda busqueda = new Busqueda(palabrasBuscadas, 10, vocabulario.getContador());
            
            ArrayList<Documento> docsResultado = busqueda.ejecutarBusqueda();
            for (Documento d:docsResultado)
            {
                System.out.println("Documento: " + d.getNombre());
                System.out.println("PESO: " + d.getPesoTotal());
            }
        
    }
    
}
