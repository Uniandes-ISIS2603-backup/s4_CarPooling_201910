/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.adapters.DateAdapter;
import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author ja.morales11
 */
public class NotificacionDTO implements Serializable {

    private Long id;
    private String mensaje;
    private UsuarioDTO receptor;
    private UsuarioDTO emisor;
    private TrayectoDTO trayecto;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    private boolean leido;
    private Integer tipo;
    
    /**
     * Constructor vacío.
     */
    public NotificacionDTO() {

    }

    /**
     * Constructor desde una entidad.
     *
     * @param entity La entidad de donde sale la información para el DTO.
     */
    public NotificacionDTO(NotificacionEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.mensaje = entity.getMensaje();
            this.fecha = entity.getFecha();
            this.leido = entity.isLeido();
            this.tipo = entity.getTipo();
            
            if(entity.getEmisor()!= null){
                this.emisor = new UsuarioDTO(entity.getEmisor());
            } else {
                this.emisor = null;
            }
            if(entity.getReceptor()!= null){
                this.receptor = new UsuarioDTO(entity.getReceptor());
            } else {
                this.receptor = null;
            }
            if(entity.getTrayecto()!= null){
                this.trayecto = new TrayectoDTO(entity.getTrayecto());
            } else {
                this.trayecto = null;
            }
        }
    }

    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
    
    

    /**
     * Crear una entidad a partir del DTO.
     *
     * @return La entidad.
     */
    public NotificacionEntity toEntity() {
        NotificacionEntity entity = new NotificacionEntity();
        entity.setMensaje(mensaje);
        entity.setFecha(fecha);
        entity.setLeido(leido);
        entity.setTipo(tipo);
        
        
        if(this.emisor != null){
            entity.setEmisor(this.emisor.toEntity());
        }
        if(this.receptor != null){
            entity.setReceptor(this.receptor.toEntity());
        }
        if(this.trayecto != null){
            entity.setTrayecto(this.trayecto.toEntity());
        }
        return entity;
    }

    /**
     * @return the trayecto
     */
    public TrayectoDTO getTrayecto() {
        return trayecto;
    }

    /**
     * @param trayecto the trayecto to set
     */
    public void setTrayecto(TrayectoDTO trayecto) {
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
     * @param leido the leido to set
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
