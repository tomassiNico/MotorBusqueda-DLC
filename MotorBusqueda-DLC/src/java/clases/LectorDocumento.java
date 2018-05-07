/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author nicolastomassi
 */
public class LectorDocumento {
    
    private File archivo;
    private Hashtable<String, Integer> palabras;
    
    public LectorDocumento()
    {
        
    }
    
    public LectorDocumento(String arch)
    {
        archivo = new File(arch);
        palabras = new Hashtable();
    }
    
    public void setArchivo(String arch)
    {
        archivo = new File(arch);
    }
    
    private void contarPalabras() throws FileNotFoundException
    // método para poder obtener las palabras contenidas en el archivo sin puntos, comas, guiones, etc
    // y contando el número de apariciones de esta.
    {
        
        
        Scanner sc = new Scanner(archivo, "ISO-8859-1");
        StringTokenizer st;
        while(sc.hasNext())
        {
            st = new StringTokenizer(sc.nextLine(), "\"’,.-_+&<>``={}~^@/()[]%'*$|°[0-1-2-3-4-5-6-7-8-9]#:*»«?¡!¿; \n");
            while(st.hasMoreTokens())
            {
                String p = st.nextToken().toLowerCase();
                Integer valorAnterior = this.palabras.get(p);
                if (valorAnterior == null)
                {
                    this.palabras.put(p, 1);
                }
                else  
                {
                    valorAnterior++;
                    this.palabras.put(p, valorAnterior);
                }
                
            }
        
    }
    }
    public Hashtable palabrasObtenidas() throws FileNotFoundException
    {
        this.contarPalabras();
        return palabras;
    }
    
    
}
