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
    private Integer costo;
    private Integer combustiblePrecio;
    private Integer duracionMins;
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
     * @return the combustiblePrecio
     */
    public Integer getCombustiblePrecio() {
        return combustiblePrecio;
    }

    /**
     * @param combustiblePrecio the combustiblePrecio to set
     */
    public void setCombustiblePrecio(Integer combustiblePrecio) {
        this.combustiblePrecio = combustiblePrecio;
    }

    /**
     * @return the duracionMins
     */
    public Integer getDuracionMins() {
        return duracionMins;
    }

    /**
     * @param duracionMins the duracionMins to set
     */
    public void setDuracionMins(Integer duracionMins) {
        this.duracionMins = duracionMins;
    }


    
}
