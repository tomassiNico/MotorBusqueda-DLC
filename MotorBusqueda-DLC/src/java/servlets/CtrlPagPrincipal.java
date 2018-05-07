/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.Serializacion.HashtableIOException;
import clases.Serializacion.HashtableReader;
import clases.Vocabulario;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            throws ServletException, IOException, HashtableIOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        
        //toma la busqueda ingresada
        String busqueda = request.getParameter("busqueda");
        //parsea la busqueda para sólo quedarse con las palabras, ignorando lo que no sean letras
        StringTokenizer st = new StringTokenizer(busqueda, "\"’,.-_+&<>``={}~^@/()[]%'*$|°[0-1-2-3-4-5-6-7-8-9]#:*»«?¡!¿; \n");

        ArrayList palabrasBuscadas = new ArrayList();
        while(st.hasMoreTokens())
        {
            palabrasBuscadas.add(st.nextToken()); //obtiene la busqueda ya parseada
        }
        
        
        ErrorMsg errorMsg = null;
        String errorTitle = "No se encontraron resultados para la busqueda";
        String dest = "/error.jsp";
        
        Vocabulario vocabulario;
        
        try
        {
            HashtableReader hr = new HashtableReader();
            vocabulario = new Vocabulario(hr.read());
        }
        catch(Exception e)
        {
            vocabulario = new Vocabulario();
            vocabulario.agregarCarpetaDocumentos();
        }
       
        
        try {
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
