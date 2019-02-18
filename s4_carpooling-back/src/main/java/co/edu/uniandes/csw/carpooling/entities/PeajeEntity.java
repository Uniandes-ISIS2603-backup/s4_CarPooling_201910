/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author im.sarmiento
 */
@Entity
public class PeajeEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    private Double costo;
    private Double latitud;
    private Double longitud;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
    
}
