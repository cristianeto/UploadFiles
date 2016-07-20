<%-- 
    Document   : index
    Created on : 16-jul-2015, 18:56:43
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="jquery-1.11.3.min.js"></script>
        <title>Subir archivos con ajax</title> 
    </head> 
    <body> 
        <h1>Upload files and field type text with Ajax and JAVA</h1>
        <h3>Subir Archivos y campos tipo text con Ajax y Java</h3>
        <!--<form action="SubirArchivoServlet" method="POST" enctype="multipart/form-data"> -->
        <form id="insertar"  action="./uploadFileServletAjax" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="nombre"/><br>
            <input type="file" name="enombre" id="enombre"/> <br>
            <input type="file" name="eresponsable"/> 
            <br />
            <select name="periodo">
                <option value="">Seleccione un período</option>
                <option value="periodo1">periodo 1</option>
                <option value="periodo2">periodo 2</option>
                <option value="periodo3">periodo 3</option>
            </select>
            <br />

            <input type="submit" name="accion" value="Guardar" />
        </form> 

        <div id="resultado">

        </div>       
        <input type="file" name="nuevoFile" id="nuevoFile" />
        <hr>
        <footer>
            <h4>Cristianeto</h4>
            <a href="./index.jsp">Subir archivos sin AJAX</a><br>
        </footer>
        <script type="text/javascript" src="fileUploadAjax.js"></script>
    </body> 
</html>