/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.morales11
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable {

    private int puntaje;
    private String comentario;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity calificado;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity calificador;

    @PodamExclude
    @OneToOne
    private TrayectoEntity trayecto;

    public CalificacionEntity() {

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
     * @return the calificado
     */
    public UsuarioEntity getCalificado() {
        return calificado;
    }

    /**
     * @param calificado the calificado to set
     */
    public void setCalificado(UsuarioEntity calificado) {
        this.calificado = calificado;
    }

    /**
     * @return the calificador
     */
    public UsuarioEntity getCalificador() {
        return calificador;
    }

    /**
     * @param calificador the calificador to set
     */
    public void setCalificador(UsuarioEntity calificador) {
        this.calificador = calificador;
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

}
