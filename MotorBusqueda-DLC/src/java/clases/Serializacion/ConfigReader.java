/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.Serializacion;

import clases.Config;
import clases.Vocabulario;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author aleex
 */
public class ConfigReader {
    private String arch = "Config.dat";
    
      /**
       * Crea un objeto SimpleListReader. Asume que el nombre del archivo desde el 
       * cual se recupera es "lista.dat".
       */
      public ConfigReader()
      {
      }
      
      /**
       * Crea un objeto SimpleListReader. Fija el nombre del archivo desde el cual 
       * se recupera con el nombre tomado como parametro.
       * @param nom el nombre del archivo a abrir para iniciar la recuperacion.
       */
      public ConfigReader(String nom)
      {
          arch = nom;
      }
      
      
      /**
       * Recupera una SimpleList desde un archivo serializado.
       * @throws HashtableIOException si se encuentra un error de IO.
       * @return una referencia al objeto recuperado.
       */
      public Config read() throws VocabularioIOException
      {
           Config c = null;
           
           try
           {
                 FileInputStream istream = new FileInputStream(arch);
                 ObjectInputStream p = new ObjectInputStream(istream);
          
                 c = ( Config ) p.readObject();
                 
                 p.close();
                 istream.close();
           }
           catch (Exception e)
           {
             throw new VocabularioIOException("No se pudo recuperar la configuracion");
           }
           
           return c;
       }
}
