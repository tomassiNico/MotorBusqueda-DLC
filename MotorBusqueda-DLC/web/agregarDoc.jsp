<%-- 
    Document   : agregarDoc
    Created on : 08/05/2018, 14:27:25
    Author     : aleex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Motor de Busqueda</title>
    <link rel="shortcut icon" href="http://www.orientacionandujar.es/wp-content/uploads/2013/10/gato-negro-silueta.png">
    <link type="text/css" href="StyleSheet.css" rel="stylesheet"/> 

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
      <div class="container">
          <div id="menu">
              <jsp:include page="menu.jsp"/>
          </div>
          <h3 class="text-info">Ingresar un documento a la base de datos</h3>
          <form method="POST" action="upload" enctype="multipart/form-data">
                <div class="form-group">
                        <label for="ingresarDoc">Seleccionar el Documento a ingresar:</label>
                        <input type="file" name="file" id="file" placeholder="Email">
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-info" value="Subir" name="upload" id="upload" />
                </div>
          </form>
       <div>
            <jsp:include page="footer.jsp" />
       </div>
      </div>
        

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>