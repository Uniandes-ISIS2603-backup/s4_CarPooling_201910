/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import java.io.Serializable;

/**
 *
 * @author df.penap
 */
public class PeajeDTO implements Serializable{
    private String nombre;
    private double costo;
    private double lat;
    private double lon;
    private Long id;
    
    public PeajeDTO() {
        
    }
    
    public PeajeDTO(PeajeEntity peajeEntity) {
        if (peajeEntity != null) {
            this.id = peajeEntity.getId();
            this.nombre = peajeEntity.getNombre();
            this.costo = peajeEntity.getCosto();
            this.lat = peajeEntity.getLatitud();
            this.lon = peajeEntity.getLongitud();
        }
    }
    
    /**
     * Método para transformar del DTO a una entidada.
     *
     * @return La entidad de esta reseña.
     */
    public PeajeEntity toEntity() {
        PeajeEntity peajeEntity = new PeajeEntity();
        peajeEntity.setId(this.id);
        peajeEntity.setNombre(this.nombre);
        peajeEntity.setCosto(this.costo);
        peajeEntity.setLatitud(this.lat);
        peajeEntity.setLongitud(this.lon);
        return peajeEntity;
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
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
