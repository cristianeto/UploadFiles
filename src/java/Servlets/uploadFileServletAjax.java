/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.JsonObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author Cristian
 */
@MultipartConfig
public class uploadFileServletAjax extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "/Users/Cristian/Desktop/";

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
            throws ServletException, IOException, Exception {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        Boolean blnResultado = false;
        JsonObject objJson = new JsonObject();
        String strMensaje = "No se pudo guardar los archivos!";
        // process only if its multipart content
        if (isMultipart) {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();
            String strNombreCampo;
            String strValorCampo;
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                // Parse the request
                List<FileItem> multiparts = upload.parseRequest(request);
                String accion = request.getParameter("accion");//Si queremos trabajar con request.getParameter("algunaVariable") debemos hacerlo después de haber creado el multiparts de la linea de arriba, sino no podremos leer los tipo file
                for (FileItem item : multiparts) {//
                    strNombreCampo = item.getFieldName();//Traigo el nombre o name del input del formulario
                    strValorCampo = item.getString();// Traigo el valor del input del formulario

                    if (!item.isFormField()) { //determina si es o no un input tipo file
                        String name = new File(item.getName()).getName();
                        switch (strNombreCampo) {
                            case "enombre":
                                item.write(new File(UPLOAD_DIRECTORY + name));
                                String rutaEname = new String(Base64.encode(Files.readAllBytes(Paths.get(UPLOAD_DIRECTORY + name))));
                                System.out.println("rutaEresponsable: " + rutaEname);
                                objJson.addProperty("rutaEname", rutaEname);
                                break;
                            case "eresponsable":
                                item.write(new File(UPLOAD_DIRECTORY + name));
                                String rutaEresponsable = new String(Base64.encode(Files.readAllBytes(Paths.get(UPLOAD_DIRECTORY + name))));
                                System.out.println("rutaEresponsable: " + rutaEresponsable);
                                objJson.addProperty("rutaEresponsable", rutaEresponsable);
                        }
                        //item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    } else {//entra al else si no es un input tipo file. ejemplo: entran los tipo text, select...
                        System.out.println("Nombre: " + strNombreCampo + "; " + "Value: " + strValorCampo);
                    }
                }
                blnResultado = true;
                strMensaje = "Se guardó correctamente!";
            } catch (Exception e) {
                blnResultado = false;
                strMensaje = "No se pudo guardar los archivos!";
            }
        }
        objJson.addProperty("resultado", blnResultado);
        objJson.addProperty("mensaje", strMensaje);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());//respondo la petición al ajax pasádole la variable Json transormada en string.
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
            Logger.getLogger(uploadFileServletAjax.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(uploadFileServletAjax.class.getName()).log(Level.SEVERE, null, ex);
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
