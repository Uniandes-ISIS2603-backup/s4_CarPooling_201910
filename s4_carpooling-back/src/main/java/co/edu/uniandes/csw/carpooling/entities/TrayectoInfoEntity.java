/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class TrayectoInfoEntity extends BaseEntity implements Serializable {

    private Integer costo;
    private Integer duracion;
    private Integer combustible;
    @Temporal(TemporalType.TIME)
    private Date horaFinal;
    @Temporal(TemporalType.TIME)
    private Date horaInicial;

    private Integer cupos;

    @PodamExclude
    @OneToOne
    private TrayectoEntity infoTrayecto;

    @PodamExclude
    @OneToMany
    private List<CiudadEntity> paradas = new ArrayList<CiudadEntity>();

    @PodamExclude
    @OneToMany
    private List<PeajeEntity> peajes = new ArrayList<PeajeEntity>();

    @PodamExclude
    @OneToOne
    private VehiculoEntity vehiculo;

    /**
     * Constructor vacío.
     */
    public TrayectoInfoEntity() {

    }

    /**
     * @return the costo
     */
    public Integer getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    /**
     * @return the duracion
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the combustible
     */
    public Integer getCombustible() {
        return combustible;
    }

    /**
     * @param combustible the combustible to set
     */
    public void setCombustible(Integer combustible) {
        this.combustible = combustible;
    }

    /**
     * @return the horaFinal
     */
    public Date getHoraFinal() {
        return horaFinal;
    }

    /**
     * @param horaFinal the horaFinal to set
     */
    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * @return the horaInicial
     */
    public Date getHoraInicial() {
        return horaInicial;
    }

    /**
     * @param horaInicial the horaInicial to set
     */
    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    /**
     * @return the cupos
     */
    public Integer getCupos() {
        return cupos;
    }

    /**
     * @param cupos the cupos to set
     */
    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }
}
