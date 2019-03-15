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

    private int puntaje;
    private String comentario;
    private int idTrayectoCalificacion;
    private UsuarioDTO calificado;
    private UsuarioDTO calificador;
    private TrayectoDTO trayecto;

    /**
     * Constructor CalificacionDTO que recibe una entidad por parámetro.
     *
     * @param entity
     */
    public CalificacionDTO(CalificacionEntity entity) {
        if (entity != null) {
            puntaje = entity.getPuntaje();
            comentario = entity.getComentario();
            idTrayectoCalificacion = entity.getIdTrayectoCalificacion();
        }
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
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
     * @return the idTrayectoCalificacion
     */
    public int getIdTrayectoCalificacion() {
        return idTrayectoCalificacion;
    }

    /**
     * @param idTrayectoCalificacion the idTrayectoCalificacion to set
     */
    public void setIdTrayectoCalificacion(int idTrayectoCalificacion) {
        this.idTrayectoCalificacion = idTrayectoCalificacion;
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
        entity.setIdTrayectoCalificacion(idTrayectoCalificacion);
        return entity;
    }
}
