/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import BD.ConexionBD;
import clases.Serializacion.VocabularioReader;
import clases.Serializacion.VocabularioWriter;

/**
 *
 * @author aleex
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
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
        }    }
    
}
