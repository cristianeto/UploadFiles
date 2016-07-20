/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
import org.apache.commons.io.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import utilidades.MensajesGlobal;
import utilidades.uploadFileUtility;

/**
 *
 * @author Cristian
 */
public class uploadFileServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession ssnResultado = request.getSession();
        Boolean blnResultado = false;
        /**
         * Autor: Cristian Guamán Fecha: 17 Julio 2015 Descripcion: Este Método
         * me permite subir Archivos
         */

        String strUbicacionArchivo = "/Users/Cristian/Desktop/";
        String strTipoArchivo = ".png";

        DiskFileItemFactory factory = new DiskFileItemFactory();//Para importar el archivo
        //factory.setSizeThreshold(1024); //Identifica la Tasa de transferencia del archivo.
        factory.setRepository(new File(strUbicacionArchivo));//Donde se almacenará el archivo.
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> partes = upload.parseRequest(request);
        String strAccion = uploadFileUtility.obtenerAccionFormulario(partes);
        out.print("La accion del formulario es: " + strAccion + "<br>");

        if (strAccion != null && strAccion.equals("Guardar")) {
            blnResultado = entidadInsertar(request, response, partes, strUbicacionArchivo, strTipoArchivo);

        }

        if (blnResultado) {
            MensajesGlobal oMensaje = new MensajesGlobal();
            out.print("ESTADO: " + oMensaje.getMENSAJE());
            ssnResultado.setAttribute("resultado", blnResultado);
        }

    }

    /**
     * Descripción: Este método permite el ingreso de una entidad
     *
     * @param request
     * @param response
     * @param partes
     * @param strUbicacionArchivo
     * @param strTipoArchivo
     * @return
     * @throws Exception
     */
    public Boolean entidadInsertar(HttpServletRequest request, HttpServletResponse response, List<FileItem> partes, String strUbicacionArchivo, String strTipoArchivo) throws Exception {
        PrintWriter out = response.getWriter();
        Boolean blnResultado = false;
        String strNombreCampo = null;
        String strValorCampo = null;
        try {
            for (FileItem item : partes) {
                if (!item.isFormField()) {
                    if (item.getSize() < 5242880) {//menor que 5MB
                        File file = new File(strUbicacionArchivo, item.getFieldName() + strTipoArchivo);
                        item.write(file);
                        out.println("InputFile: " + item.getFieldName() + " se ha subido correctamente.<br>");
                    } else {
                        out.println("InputFile: " + item.getFieldName() + " pesa más de 500 KB.<br>");
                    }
                } else {
                    strNombreCampo = item.getFieldName();
                    strValorCampo = item.getString();

                    out.println("NombreInput: " + strNombreCampo + ". Valor: " + strValorCampo + "<br>");
                }
            }
            blnResultado=true;

        } catch (FileUploadException ex) {
            blnResultado=false;
            out.write("Error al subir archivo " + ex.getMessage());
        }
        return blnResultado;
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
            Logger.getLogger(uploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(uploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
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
