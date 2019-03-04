/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Julio Morales
 */
@Entity
public class NotificacionEntity extends BaseEntity implements Serializable {
    private String mensaje;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity emisor;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity receptor;
    
 
    
    public NotificacionEntity () {
        
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
     * @return the emisor
     */
    public UsuarioEntity getEmisor() {
        return emisor;
    }

    /**
     * @param emisor the emisor to set
     */
    public void setEmisor(UsuarioEntity emisor) {
        this.emisor = emisor;
    }

    /**
     * @return the receptor
     */
    public UsuarioEntity getReceptor() {
        return receptor;
    }

    /**
     * @param receptor the receptor to set
     */
    public void setReceptor(UsuarioEntity receptor) {
        this.receptor = receptor;
    }
    
    
}
