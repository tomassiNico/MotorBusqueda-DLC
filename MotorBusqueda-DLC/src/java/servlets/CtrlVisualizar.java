/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aleex
 */
public class CtrlVisualizar extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nomDoc = request.getParameter("documento");
        ErrorMsg errorMsg = null;
        String errorTitle = "No se encontraron resultados para la busqueda";
        String dest = "/error.jsp";
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
           archivo = new File ("documentos\\" + nomDoc);
            System.out.println("ARchivo: " +archivo.getAbsolutePath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                sb.append("\n" + linea);
            }
            dest = "/verArchivo.jsp";
           /*Scanner sc =new Scanner(new File ("documentos\\" + nomDoc));
           while (sc.hasNext()){
               System.out.println(sc.nextLine());
           }
            dest = "/verArchivo.jsp";
            sc.close();*/
        }
        catch (Exception e){
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
        finally {
            try{                    
                if( null != fr ){   
                fr.close();     
                }                  
            }
            catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        request.setAttribute("nomDoc", nomDoc);
        request.setAttribute("parrafo", sb.toString());

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
        processRequest(request, response);
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
        processRequest(request, response);
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
