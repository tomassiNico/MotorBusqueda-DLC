/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.Serializacion;

import clases.Config;
import clases.Vocabulario;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author aleex
 */
public class ConfigWriter extends Exception {
    private String arch = "Config.dat";
    
      /**
       * Crea un objeto SimpleListWriter. Supone que el nombre del archivo a grabar 
       * sera "lista.dat".
       */
      public ConfigWriter()
      {
      }
      
      /**
       * Crea un objeto SimpleListWriter. Fija el nombre del archivo que se graba con 
       * el nombre tomado como parametro.
       * @param nom el nombre del archivo a grabar.
       */
      public ConfigWriter(String nom)
      {
            arch = nom;
      }
      
      /**
       * Graba la lista tomada como parametro.
       * @param sl la lista a serializar.
       * @throws HashtableIOException si se encuentra un error de IO.
       */
      public void write (Config c) throws VocabularioIOException
      {
           try
           {
             FileOutputStream ostream = new FileOutputStream(arch);
             ObjectOutputStream p = new ObjectOutputStream(ostream);
      
             p.writeObject(c);
      
             p.flush(); 
             ostream.close();
           }
           catch ( Exception e )
           {
             throw new VocabularioIOException("No se pudo grabar la configuracion...");
           }
      }
}
