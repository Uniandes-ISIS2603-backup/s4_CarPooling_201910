/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class CalificacionDTO implements Serializable{
    
    private int puntaje;
    private String comentario;
    private int idTrayectoCalificacion;

    /**
     * Constructor CalificacionDTO
     */
    public CalificacionDTO() {
    
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
    
}
