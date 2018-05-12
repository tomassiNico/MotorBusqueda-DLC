/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BD.ConexionBD;
import clases.Busqueda;
import clases.Documento;
import clases.Serializacion.VocabularioReader;
import clases.Serializacion.VocabularioWriter;
import clases.Termino;
import clases.Vocabulario;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aleexiz1996
 */
public class CtrlPagPrincipal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        //C:\Users\aleex\AppData\Roaming\NetBeans\8.2\config\GF_4.1.1\domain1\config
        ErrorMsg errorMsg = null;
        String errorTitle = "No se encontraron resultados para la busqueda";
        String dest = "/error.jsp";
        
        HttpSession session = request.getSession();
        Vocabulario vocabulario = (Vocabulario) session.getAttribute("vocabulario");
        int cantidadDocumentos = (int) session.getAttribute("cantidadDocumentos");
        
        //toma la busqueda ingresada
        String palabras = request.getParameter("busqueda");
        
        //ESTO DEBERIA PEDIR R String R = request.getParameter("R");
        //parsea la busqueda para sólo quedarse con las palabras, ignorando lo que no sean letras
        StringTokenizer st = new StringTokenizer(palabras, "\"’,.-_+&<>``={}~^@/()[]%'*$|°[0-1-2-3-4-5-6-7-8-9]#:*»«?¡!¿; \n");

        ArrayList<Termino> palabrasBuscadas = new ArrayList<>();
        while(st.hasMoreTokens())
        {
            Termino t = vocabulario.getVocabulario().get(st.nextToken());
            if (t != null) {
                palabrasBuscadas.add(t);
            }
        }
      
        
        
      ArrayList<Documento> docsResultado = null; ;
        try {
            //String s = (String) request.getParameter("cantDoc");
            //int cantDoc = Integer.valueOf(s);
            Long tiempoInicial = System.currentTimeMillis();
            Busqueda busqueda = new Busqueda(palabrasBuscadas, 10, cantidadDocumentos);            
            docsResultado = busqueda.ejecutarBusqueda();
             System.out.println("Tiempo que tarda: " + (System.currentTimeMillis() - tiempoInicial));
            request.setAttribute("documentos", docsResultado);
            request.setAttribute("busqueda", palabras);
            dest = "/ResultadoBusqueda.jsp";

        } catch (Exception e) {
            
            errorMsg = new ErrorMsg(errorTitle, e.getMessage());
            request.setAttribute("errorMsg", errorMsg);
        }
        ServletContext app = this.getServletContext();
        RequestDispatcher disp = app.getRequestDispatcher(dest);
        disp.forward(request, response);
        
        
        
        }
  
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CtrlPagPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CtrlPagPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
