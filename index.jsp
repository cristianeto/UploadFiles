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
        <title>JSP Page</title> 
    </head> 
    <body> 
        <h1>Upload files and field type text</h1>
        <h3>Subir Archivos y campos tipo Text</h3>
        <form action="uploadFile.jsp" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="nombre"/><br>
            <input type="file" name="enombre"/> <br>
            <input type="file" name="eresponsable"/> 
            <br /> 
            <input type="submit" value="Subir archivo" /> 
        </form> 
    </body> 
</html>