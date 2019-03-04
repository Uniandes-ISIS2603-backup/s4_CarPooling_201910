/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import java.io.Serializable;

/**
 *
 * @author ja.morales11
 */
public class NotificacionDTO implements Serializable{
    
    private String mensaje;
    private UsuarioDTO receptor;
    private UsuarioDTO emisor;

    /**
     * Constructor NotificacionDTO
     */
    public NotificacionDTO(NotificacionEntity entity) {
        if(entity!=null)
        {
            mensaje = entity.getMensaje();
        }
    }
    
    
    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    /**
     * @return the receptor
     */
    public UsuarioDTO getReceptor() {
        return receptor;
    }

    /**
     * @param receptor the receptor to set
     */
    public void setReceptor(UsuarioDTO receptor) {
        this.receptor = receptor;
    }

    /**
     * @return the emisor
     */
    public UsuarioDTO getEmisor() {
        return emisor;
    }

    /**
     * @param emisor the emisor to set
     */
    public void setEmisor(UsuarioDTO emisor) {
        this.emisor = emisor;
    }
    
    
    
    
    
    
    public NotificacionEntity toEntity(){
        NotificacionEntity entity = new NotificacionEntity();
        entity.setMensaje(mensaje);
        return entity;
    }
}
