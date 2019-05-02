/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import java.io.Serializable;

/**
 *
 * @author ja.morales11
 */
public class CalificacionDTO implements Serializable {

    private Long id;
    private Integer puntaje;
    private String comentario;
    private UsuarioDTO calificado;
    private UsuarioDTO calificador;
    private TrayectoDTO trayecto;

    public CalificacionDTO()
    {
        
    }
    /**
     * Constructor CalificacionDTO que recibe una entidad por parámetro.
     *
     * @param entity
     */
    public CalificacionDTO(CalificacionEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.puntaje = entity.getPuntaje();
            this.comentario = entity.getComentario();
            if(entity.getCalificado() != null){
                this.calificado = new UsuarioDTO(entity.getCalificado());
            } else {
                this.calificado = null;
            }
            
            if(entity.getCalificador() != null){
                this.calificador = new UsuarioDTO(entity.getCalificador());
            } else {
                this.calificador = null;
            }
            
            if(entity.getTrayecto()!= null){
                this.trayecto = new TrayectoDTO(entity.getTrayecto());
            } else {
                this.trayecto = null;
            }
        }
    }

    /**
     * @return the puntaje
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    /**
     * @return the calificado
     */
    public UsuarioDTO getCalificado() {
        return calificado;
    }

    /**
     * @param calificado the calificado to set
     */
    public void setCalificado(UsuarioDTO calificado) {
        this.calificado = calificado;
    }

    /**
     * @return the calificador
     */
    public UsuarioDTO getCalificador() {
        return calificador;
    }

    /**
     * @param calificador the calificador to set
     */
    public void setCalificador(UsuarioDTO calificador) {
        this.calificador = calificador;
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
     * Convertir DTO a entity.
     * @return 
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity entity = new CalificacionEntity();
        entity.setPuntaje(puntaje);
        entity.setComentario(comentario);
        
        if(calificado != null)
        {
            entity.setCalificado(calificado.toEntity());
        }
        if(calificador != null)
        {
            entity.setCalificador(calificador.toEntity());
        }
        if(trayecto != null)
        {
            entity.setTrayecto(trayecto.toEntity());
        }
        
        return entity;
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
}
