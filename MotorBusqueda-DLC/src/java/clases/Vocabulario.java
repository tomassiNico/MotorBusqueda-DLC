
package clases;

import BD.ConexionBD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author mayur
 */
public class Vocabulario implements Serializable {
    
    private Hashtable<String, Termino> vocabulario;
    
   
    public Vocabulario()
    {
        this.vocabulario = new Hashtable();
    }
    
    public Vocabulario(Hashtable v)
    {       
        this.vocabulario = v;
    }
    
     public Hashtable<String, Termino> getVocabulario() {
        return vocabulario;
    }
 
    public void agregarTermino(Termino t)
    {
        //recibe un termino y agrega a la hash table
        if (this.vocabulario.containsKey(t.getPalabra())) 
        {
            Termino aux = this.vocabulario.get(t.getPalabra()); //trae el termino ya existente que es igual al que se intenta ingresar
            aux.setNr(aux.getNr()+1); //numeros de documentos donde aparece la palabra
            if (aux.getMaxFrecuenia() < t.getMaxFrecuenia() ) 
            {
                aux.setMaxFrecuenia(t.getMaxFrecuenia()); //cambia la frecuencia maxima en caso de ser necesario
            }
            
            return;
        }
        this.vocabulario.put(t.getPalabra(), t); //ingresa el termino en caso de que no esté en la hastable (vocabulario actual)
    }
    
    public void agregarDocumento(String archivo) throws FileNotFoundException, Exception 
    {
        //toma un documento ya parseado e ingresa los terminos encontrados en este con sus respectivos datos
        //ingresa en la base de datos el documento tantas veces como terminos tenga (con su frecuencia observada)
        
        //LectorDocumento l = new LectorDocumento("/home/nicolastomassi/DocumentosTP1/" + archivo);
        LectorDocumento l = new LectorDocumento("documentos\\" + archivo);
        Hashtable<String, Integer> aux = l.palabrasObtenidas();
        ArrayList term = new ArrayList();
        ArrayList frec = new ArrayList();
        for(Iterator i = aux.keySet().iterator(); i.hasNext() ;)
        {
            String palabra = (String) i.next();
            int frecuencia = aux.get(palabra);
            Termino t = new Termino(palabra, frecuencia);
            this.agregarTermino(t);
            term.add(palabra);
            frec.add(frecuencia);
            //ConexionBD.getInstance().insertData("palabraxdocumento", palabra, archivo, frecuencia);
        }
        ConexionBD.getInstance().insertDataMasivo("palabraxdocumento", term, archivo, frec);
    }
    
    
    public Termino get(String palabra)
    {
        return this.vocabulario.get(palabra);
    }
    
    public void agregarCarpetaDocumentos() throws FileNotFoundException, Exception
    {
        //Selecciona una carpeta (y solo una carpeta) y carga todos los archivos .txt que contenga
        // esta carpeta en el vocabulario y el posteo(bd)
        
        
        FilenameFilter filter = new FilenameFilter() // crea filtro para sólo archivos .txt
        {
            public boolean accept(File dir, String fileName)
            {
                return fileName.endsWith("txt");
            }
        };
       
        //File f= new File("/home/nicolastomassi/DocumentosTP1");
        File f= new File("documentos");
        String [] fileList=f.list(filter); //implementa filtro para sólo leer archivos .txt
        
        //itera para agregar cada documento de la carpeta
        for (int i=0; i < fileList.length; i++)
        {
            this.agregarDocumento(fileList[i]);
            System.out.println("**********************************************************************\n******************************");
            System.out.println("Documento " + f.getName() + ": NUMERO " + (i+1) + " DE " + fileList.length );
        }   System.out.println("**********************************************************************\n******************************");
        //ConexionBD.getInstance().closeConnection();
    }
    
    
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CONTENIDO: ");
        for(Iterator i = this.vocabulario.keySet().iterator(); i.hasNext() ;)
        {
            String palabra = (String) i.next();
            Termino t = this.vocabulario.get(palabra);
            sb.append("\n Palabra:  " + t.getPalabra() + " Frecuencia: " + t.getMaxFrecuenia());
            
        }
        return sb.toString();
    }
    
    
}
