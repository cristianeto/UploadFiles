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
        <title></title> 
    </head> 
    <body> 
        <h1>Upload files and field type text without Ajax</h1>
        <h3>Subir Archivos y campos tipo text sin Ajax</h3>
        <!--<form action="SubirArchivoServlet" method="POST" enctype="multipart/form-data"> -->
        <form action="uploadFileServlet" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="nombre"/><br>
            <input type="file" name="enombre"/> <br>
            <input type="file" name="eresponsable"/> 
            <br />
            <select name="periodo">
                <option value="">Seleccione un período</option>
                <option value="periodo1">periodo 1</option>
                <option value="periodo2">periodo 2</option>
                <option value="periodo3">periodo 3</option>
            </select>
            <br />
            
            <button type="submit" name="accion" value="Guardar">Guardar</button>
        </form> 
        
        
        
        <footer>
            <h4><strong>Autor:</strong> Cristianeto</h4>
            <a href="./fileUploadAjax.jsp">Subir archivos con AJAX</a>
        </footer>
    </body> 
</html>