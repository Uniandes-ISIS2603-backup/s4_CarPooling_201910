/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class CiudadDTO implements Serializable {

    private String nombre;
    private Long lat;
    private Long lon;

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
     * Constructor vacío.
     */
    public CiudadDTO() {

    }

    /**
     * Constructor desde una entidad.
     *
     * @param entity
     */
    public CiudadDTO(CiudadEntity entity) {
        if (entity != null) {
            this.nombre = entity.getNombre();
            this.lat = entity.getLat();
            this.lon = entity.getLon();
        }
    }

    /**
     * Método para convertir el DTO a una entidad.
     *
     * @return CiudadEntity con los atributos del DTO.
     */
    public CiudadEntity toEntity() {
        CiudadEntity ce = new CiudadEntity();
        ce.setLat(this.lat);
        ce.setLon(this.lon);
        ce.setNombre(nombre);
        return ce;
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
}
