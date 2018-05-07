<%-- 
    Document   : ResultadoBusqueda
    Created on : 13/04/2018, 11:18:07
    Author     : nicolastomassi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SuBusqueda</title> <!-- SuBusqueda es lo que buscaron-->
    </head>
    <body>
        <form action="buscar" method="post">
            <input type="text" id="buscar" name="busqueda" placeholder="Ingrese su busqueda" value="${busqueda}">
            <input type="submit" id="entrar" name="entrar" value="Buscar">
        </form>
        <div>
            <h3> Documento 1  <h4> Puntaje: n1 </h4> </h3>
            <h3> Documento 2  <h4> Puntaje: n2 </h4> </h3>
            <h3> Documento 3  <h4> Puntaje: n3 </h4> </h3>
            <h3> Documento 4  <h4> Puntaje: n4 </h4> </h3>
            <h3> Documento 5  <h4> Puntaje: n5 </h4> </h3>
            <h3> Documento 6  <h4> Puntaje: n6 </h4> </h3>
                      
        </div>
    </body>
</html>