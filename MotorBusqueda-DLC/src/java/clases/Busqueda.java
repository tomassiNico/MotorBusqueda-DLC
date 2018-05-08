/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import BD.ConexionBD;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author aleex
 */
public class Busqueda {
    private ArrayList<Termino> palabrasBuscadas;
    private Hashtable documentos;
    private int R;
    private int N;

    public Busqueda(ArrayList palabrasBuscadas, int R, int N) {
        this.palabrasBuscadas = palabrasBuscadas;
        this.R = R;
        this.ordenarPalabrasBuscadas();
        this.N = N;
    }
    
    public void ordenarPalabrasBuscadas()
    {
        Collections.sort(this.palabrasBuscadas);
    }
    
    public void buscarDocumentosRelevantes() throws Exception
    {
        int N = ConexionBD.getInstance().getCantidadDocumento();
        for (Termino t: this.palabrasBuscadas)
        {
            ArrayList<Documento> docs = t.buscarDocumentos(this.R);
            for (Documento d: docs)
            {
                d.agregarPeso(t, N);
                this.agregarDocumentoRelevante(d, t);
            }
        }
    }
    
    public void agregarDocumentoRelevante(Documento d, Termino t){
        if (!this.documentos.containsKey(d.getNombre()))
        {
            this.documentos.put(d.getNombre(), d);
            return;
        }
        Documento docAnterior= (Documento) this.documentos.get(d.getNombre());
        docAnterior.incrementarPeso(d.getPesoTotal());
    }
    
    public ArrayList<Documento> ejecutarBusqueda() throws Exception
    {
        this.buscarDocumentosRelevantes();
        return this.rankingDeDocumentos();
    }
    
    public ArrayList<Documento> rankingDeDocumentos()
    {
        Iterator i = this.documentos.values().iterator();
        ArrayList<Documento> docs = new ArrayList<>();
        while (i.hasNext())
        {
            Documento d = (Documento)i.next();
            docs.add(d);
        }
        Collections.sort(docs);
        return docs;
    }
            
    
}
