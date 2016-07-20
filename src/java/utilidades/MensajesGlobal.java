/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author Cristian
 */
public class MensajesGlobal {

    java.util.ResourceBundle Configuracion = java.util.ResourceBundle.
            getBundle("utilidades.Mensajes");

    private String MENSAJE = Configuracion.getString("MENSAJE");

    public String getMENSAJE() {
        return MENSAJE;
    }

    public void setMENSAJE(String MENSAJE) {
        this.MENSAJE = MENSAJE;
    }


    
}
