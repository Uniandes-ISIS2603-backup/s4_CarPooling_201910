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
public class TrayectoEntity extends BaseEntity implements Serializable{
    
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity conductor;
    
    @PodamExclude
    @OneToMany(mappedBy = "trayectoActualPasajero")
    private List<UsuarioEntity> pasajeros;
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity trayectoActualConductor;
    
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuarioActualPasajero;
    
    @PodamExclude
    @OneToOne(orphanRemoval = true)
    private TrayectoInfoEntity infoTrayecto;
    
    @PodamExclude
    @OneToMany
    private List<PagoEntity> pago;
    
    
    public TrayectoEntity(){
        
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
     * @return the trayectoActualConductor
     */
    public UsuarioEntity getTrayectoActualConductor() {
        return trayectoActualConductor;
    }

    /**
     * @param trayectoActualConductor the trayectoActualConductor to set
     */
    public void setTrayectoActualConductor(UsuarioEntity trayectoActualConductor) {
        this.trayectoActualConductor = trayectoActualConductor;
    }

    /**
     * @return the usuarioActualPasajero
     */
    public UsuarioEntity getUsuarioActualPasajero() {
        return usuarioActualPasajero;
    }

    /**
     * @param usuarioActualPasajero the usuarioActualPasajero to set
     */
    public void setUsuarioActualPasajero(UsuarioEntity usuarioActualPasajero) {
        this.usuarioActualPasajero = usuarioActualPasajero;
    }

    /**
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
        
}
