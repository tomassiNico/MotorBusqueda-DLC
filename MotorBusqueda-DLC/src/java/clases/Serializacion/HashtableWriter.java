package clases.Serializacion;

/**
 * Una clase usada para grabar objetos de la clase TSBSimpleList 
 * mediante Serializacion.
 * 
 * @author Ing. Valerio Frittelli.
 * @version Septiembre de 2017.
 */
import java.io.*;
import java.util.Hashtable;
public class HashtableWriter
{
      // nombre del archivo serializado...
      private String arch = "htVocabulario.dat";
    
      /**
       * Crea un objeto SimpleListWriter. Supone que el nombre del archivo a grabar 
       * sera "lista.dat".
       */
      public HashtableWriter()
      {
      }
      
      /**
       * Crea un objeto SimpleListWriter. Fija el nombre del archivo que se graba con 
       * el nombre tomado como parametro.
       * @param nom el nombre del archivo a grabar.
       */
      public HashtableWriter(String nom)
      {
            arch = nom;
      }
      
      /**
       * Graba la lista tomada como parametro.
       * @param sl la lista a serializar.
       * @throws HashtableIOException si se encuentra un error de IO.
       */
      public void write (Hashtable sl) throws HashtableIOException
      {
           try
           {
             FileOutputStream ostream = new FileOutputStream(arch);
             ObjectOutputStream p = new ObjectOutputStream(ostream);
      
             p.writeObject(sl);
      
             p.flush(); 
             ostream.close();
           }
           catch ( Exception e )
           {
             throw new HashtableIOException("No se pudo grabar la lista...");
           }
      }
}
