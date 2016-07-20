/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.File;
import java.util.List;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Cristian
 */
public class uploadFileUtility {

    public static String obtenerAccionFormulario(List<FileItem> listItems) {
        String strAccion = null;
        String strNombreCampo = null;
        String strValorCampo = null;
        try {
            for (FileItem item : listItems) {
                if (item.isFormField()) {
                    strNombreCampo = item.getFieldName();
                    strValorCampo = item.getString();
                    if (strNombreCampo.equals("accion")) {
                        strAccion = strValorCampo;
                        break;
                    } else {
                        strAccion = "No existe un campo o input que contenga el nombre acción.";
                    }
                } else {
                    strAccion = "Solo existen campos o inputs tipo File.";
                }
            }
        } catch (Exception ex) {
            strAccion = "Error al obtener la acción del formulario. Detalle: " + ex.getMessage();
        }
        return strAccion;

    }
}
