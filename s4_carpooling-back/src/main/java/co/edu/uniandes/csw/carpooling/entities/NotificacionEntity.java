/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.morales11
 */
@Entity
public class NotificacionEntity extends BaseEntity implements Serializable {

    public static final int SOLICITUD = 0;
    public static final int INFORMACION = 1;
    
    
    private String mensaje;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private boolean leido;
    private Integer tipo;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity emisor;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity receptor;
    
    @PodamExclude
    @ManyToOne
    private TrayectoEntity trayecto;

    public NotificacionEntity() {

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

    /**
     * @return the trayecto
     */
    public TrayectoEntity getTrayecto() {
        return trayecto;
    }

    /**
     * @param trayecto the trayecto to set
     */
    public void setTrayecto(TrayectoEntity trayecto) {
        this.trayecto = trayecto;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the leido
     */
    public boolean isLeido() {
        return leido;
    }

    /**
     * @param leido the leído to set
     */
    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    /**
     * @return the tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    

}
