<%-- 
    Document   : pagPrincipal
    Created on : 13-abr-2018, 10:11:29
    Author     : aleexiz1996
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Motor de Busqueda MM</title>
        <link rel="shortcut icon" href="http://www.orientacionandujar.es/wp-content/uploads/2013/10/gato-negro-silueta.png">
        <link type="text/css" href="StyleSheet.css" rel="stylesheet"/> 
    </head>
    <body>
        <nav>
	  <ul>
		<li><a href="#">Agregar Documentos</a></li>
		<li><a href="#">Agregar Carpeta con Documentos</a></li>
		<li><a href="#">Informacion</a></li>
	  </ul>
	</nav>
        <h1 class="title">Motor de Busqueda MM</h1>
        <form action="buscar" method="post">
            <input type="text" id="buscar" name="busqueda" placeholder="Ingrese su busqueda">
            <input type="submit" id="entrar" name="entrar" value="Buscar">
        </form>
        <form method="POST" action="upload" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" /> <br/>
            Destination:
            <input type="text" value="/tmp" name="destination"/>
            </br>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
        <footer>
            <link type="text/css" href="StyleSheet.css" rel="stylesheet"/>
            Copyright &copy; DLC - UTN 2018
        </footer>
    </body>
</html>