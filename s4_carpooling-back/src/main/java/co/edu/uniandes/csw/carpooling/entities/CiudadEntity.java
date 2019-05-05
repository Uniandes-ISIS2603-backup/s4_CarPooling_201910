/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CiudadEntity extends BaseEntity implements Serializable {

    private String nombre;
    private Long lat;
    private Long lon;

    
    @PodamExclude
    @OneToOne
    private TrayectoEntity origen;
    
    @PodamExclude
    @OneToOne
    private TrayectoEntity destino;
    
    public CiudadEntity() {

    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the lat
     */
    public Long getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(Long lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public Long getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(Long lon) {
        this.lon = lon;
    }

    /**
     * @return the origen
     */
    public TrayectoEntity getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(TrayectoEntity origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public TrayectoEntity getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(TrayectoEntity destino) {
        this.destino = destino;
    }

    
    

}
