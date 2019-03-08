/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jf.garcia
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable {

    private Double valor;

    @PodamExclude
    @OneToOne(mappedBy = "pagoARecibir")
    private UsuarioEntity conductor;

    @PodamExclude
    @OneToOne(mappedBy = "pagoAHacer")
    private UsuarioEntity pasajero;

    @PodamExclude
    @ManyToOne
    private TrayectoEntity trayecto;

    @PodamExclude
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private InfoTCEntity infoTC;

    public PagoEntity() {
    }

    /**
     * Obtiene el valor.
     *
     * @return valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Modifica el valor.
     *
     * @param valor
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el usuario que recibe el pago.
     *
     * @return UsuarioEntity
     */
    public UsuarioEntity getUsuarioRecibe() {
        return conductor;
    }

    /**
     * Agrega un usuario.
     *
     * @param pUsuarioEntity
     */
    public void setUsuarioRecibe(UsuarioEntity pUsuarioEntity) {
        this.conductor = pUsuarioEntity;
    }

    /**
     * Obtiene el usuario que hace el pago.
     *
     * @return UsuarioEntity
     */
    public UsuarioEntity getUsuarioHace() {
        return pasajero;
    }

    /**
     * Agrega un usuario.
     *
     * @param pUsuarioEntity
     */
    public void setUsuarioHace(UsuarioEntity pUsuarioEntity) {
        this.pasajero = pUsuarioEntity;
    }

    /**
     * Obtiene el trayecto.
     *
     * @return trayecto
     */
    public TrayectoEntity getTrayecto() {
        return trayecto;
    }

    /**
     * Agrega un trayecto nuevo.
     *
     * @param pTrayecto
     */
    public void setTrayecto(TrayectoEntity pTrayecto) {
        this.trayecto = pTrayecto;
    }

    /**
     * Obtiene la info del pago.
     *
     * @return infoTC
     */
    public InfoTCEntity getInfoTC() {
        return infoTC;
    }

    /**
     * Agrega una info nueva.
     *
     * @param pInfoTC
     */
    public void setInfoTC(InfoTCEntity pInfoTC) {
        this.infoTC = pInfoTC;
    }
}
