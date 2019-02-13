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
public class TrayectoInfoDTO implements Serializable {
    
    private Long idDetalle;
    private int costo;
    private int combustiblePrecio;
    private int ducracionMins;
    //private VehiculoDTO carro;

    public TrayectoInfoDTO(){
    
    }
    
    /**
     * @return the idDetalle
     */
    public Long getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * @return the costo
     */
    public int getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }

    /**
     * @return the combustiblePrecio
     */
    public int getCombustiblePrecio() {
        return combustiblePrecio;
    }

    /**
     * @param combustiblePrecio the combustiblePrecio to set
     */
    public void setCombustiblePrecio(int combustiblePrecio) {
        this.combustiblePrecio = combustiblePrecio;
    }

    /**
     * @return the ducracionMins
     */
    public int getDucracionMins() {
        return ducracionMins;
    }

    /**
     * @param ducracionMins the ducracionMins to set
     */
    public void setDucracionMins(int ducracionMins) {
        this.ducracionMins = ducracionMins;
    }
    
}
