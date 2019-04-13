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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class TrayectoEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    private Integer estado;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity conductor;

    @PodamExclude
    @ManyToMany(mappedBy = "trayectoActualPasajero")
    private List<UsuarioEntity> pasajeros;

    @PodamExclude
    @OneToOne(orphanRemoval = true)
    private TrayectoInfoEntity infoTrayecto;

    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagoEntity> pago = new ArrayList<PagoEntity>();

    /**
     * Constructor vacío.
     */
    public TrayectoEntity() {

    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the conductor
     */
    public UsuarioEntity getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(UsuarioEntity conductor) {
        this.conductor = conductor;
    }

    /**
     * @return the pasajeros
     */
    public List<UsuarioEntity> getPasajeros() {
        return pasajeros;
    }

    /**
     * @param pasajeros the pasajeros to set
     */
    public void setPasajeros(List<UsuarioEntity> pasajeros) {
        this.pasajeros = pasajeros;
    }

    /**
     *
     * /
     *
     **
     * @return the infoTrayecto
     */
    public TrayectoInfoEntity getInfoTrayecto() {
        return infoTrayecto;
    }

    /**
     * @param infoTrayecto the infoTrayecto to set
     */
    public void setInfoTrayecto(TrayectoInfoEntity infoTrayecto) {
        this.infoTrayecto = infoTrayecto;
    }

    /**
     * @return the pago
     */
    public List<PagoEntity> getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(List<PagoEntity> pago) {
        this.pago = pago;
    }

    /**
     * @return the estado
     */
    public Integer getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
