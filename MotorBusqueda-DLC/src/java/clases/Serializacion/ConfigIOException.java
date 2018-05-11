/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.Serializacion;

/**
 *
 * @author aleex
 */
public class ConfigIOException {
    private String message = "Problema al serializar la configuracion";
    
      /**
       * Inicializa el objeto de excepcion con valores por default.
       */
       public ConfigIOException()
       {
       }  
    
      /**
       * Inicializa el mensaje a retornar con getMessage() con la cadena tomada como
       * parametro.
       * @param msg el mensaje que sera retornado con getMessage().
       * @see getMessage().
       */
       public ConfigIOException(String msg)
       {
            message = msg;
       }
    
      /**
       * Retorna una descripcion del error que provoco la excepcion.
       * @return una cadena con la descripcion del error.
       */
       public String getMessage()
       {
         return message;
       } 
}
