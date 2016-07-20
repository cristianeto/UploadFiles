<%-- 
    Document   : uploadFile
    Created on : 16-jul-2015, 18:57:19
    Author     : Cristian
--%>

<%@ page import="org.apache.commons.fileupload.FileItem" %> 
<%@ page import="java.util.*" %> 
<%@ page import="org.apache.commons.fileupload.*" %> 
<%@ page import="org.apache.commons.fileupload.disk.*" %> 
<%@ page import="org.apache.commons.fileupload.servlet.*" %> 
<%@ page import="org.apache.commons.io.*" %> 
<%@ page import="java.io.*" %> 

<%
    /**
     * Autor: Cristian Guamán Fecha: 17 Julio 2015 Descripcion: Este Método me
     * permite subir Archivos
     */
    String ubicacionArchivo = "/Users/Cristian/Desktop/";
    String tipoArchivo = ".png";

    DiskFileItemFactory factory = new DiskFileItemFactory();//Para importar el archivo
    //factory.setSizeThreshold(1024); //Identifica la Tasa de transferencia del archivo.
    factory.setRepository(new File(ubicacionArchivo));//Donde se almacenará el archivo.
    ServletFileUpload upload = new ServletFileUpload(factory);

    try {
        List<FileItem> partes = upload.parseRequest(request);

        for (FileItem item : partes) {
            if (!item.isFormField()) {
                if (item.getSize() < 512000) {//
                    File file = new File(ubicacionArchivo, item.getFieldName() + tipoArchivo);
                    item.write(file);
                    out.println("El archivo: " + item.getFieldName() + " se ha subido correctamente");
                }else{
                    out.println("El archivo: " + item.getFieldName() + " pesa más de 500 KB.");
                }
            } else {
                String nombre = item.getString();
                //request.getParameter("nombre");
                out.println("texto " + nombre);
            }
        }

    } catch (FileUploadException ex) {
        out.write("Error al subir archivo " + ex.getMessage());
    }

    /*
     FileItemFactory factory = new DiskFileItemFactory();
     ServletFileUpload upload = new ServletFileUpload(factory);
     List items = upload.parseRequest(request);

     for (Object item : items) {
     FileItem uploaded = (FileItem) item;
     if (!uploaded.isFormField()) {
     if (!(uploaded.getName() == "")) {
     String strNombre = "codigo" + uploaded.getFieldName() + tipoArchivo;
     File fichero = new File(ubicacionArchivo, strNombre);
     uploaded.write(fichero);
     if (uploaded.getFieldName().equals("nombre")) {
     out.println("TExto: "+uploaded.getFieldName());
     }
     out.println("Archivo subido correctamento: "+uploaded.getFieldName());
     }
     }else{
     if (uploaded.getFieldName().equals("nombre")) {
     out.println("<br>TExto: "+uploaded.getString());
     }
     }
     }*/
%>