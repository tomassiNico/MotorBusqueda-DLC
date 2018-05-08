/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.Serializacion;

import clases.Vocabulario;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

/**
 *
 * @author nicolastomassi
 */
public class VocabularioReader {
    private String arch = "Vocabulario.dat";
    
      /**
       * Crea un objeto SimpleListReader. Asume que el nombre del archivo desde el 
       * cual se recupera es "lista.dat".
       */
      public VocabularioReader()
      {
      }
      
      /**
       * Crea un objeto SimpleListReader. Fija el nombre del archivo desde el cual 
       * se recupera con el nombre tomado como parametro.
       * @param nom el nombre del archivo a abrir para iniciar la recuperacion.
       */
      public VocabularioReader(String nom)
      {
          arch = nom;
      }
      
      
      /**
       * Recupera una SimpleList desde un archivo serializado.
       * @throws HashtableIOException si se encuentra un error de IO.
       * @return una referencia al objeto recuperado.
       */
      public Vocabulario read() throws VocabularioIOException
      {
           Vocabulario v = null;
           
           try
           {
                 FileInputStream istream = new FileInputStream(arch);
                 ObjectInputStream p = new ObjectInputStream(istream);
          
                 v = ( Vocabulario ) p.readObject();
                 
                 p.close();
                 istream.close();
           }
           catch (Exception e)
           {
             throw new VocabularioIOException("No se pudo recuperar la lista");
           }
           
           return v;
       }
}

