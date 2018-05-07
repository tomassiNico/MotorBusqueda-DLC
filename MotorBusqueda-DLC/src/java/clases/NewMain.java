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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Vocabulario v = new Vocabulario();
        v.agregarDocumento("00ws110.txt");
        System.out.println("Tamaño del vocabulario: " + v.getVocabulario().size());
    }
    
}
