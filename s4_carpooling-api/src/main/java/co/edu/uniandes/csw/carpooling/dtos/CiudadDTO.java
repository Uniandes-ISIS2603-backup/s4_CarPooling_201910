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
    private String[] coordenadas;

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
     * @return the coordenadas
     */
    public String[] getCoordenadas() {
        return coordenadas;
    }

    /**
     * @param coordenadas the coordenadas to set
     */
    public void setCoordenadas(String[] coordenadas) {
        this.coordenadas = coordenadas;
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
            this.coordenadas = entity.getCoordenadas();
        }
    }

    /**
     * Método para convertir el DTO a una entidad.
     *
     * @return CiudadEntity con los atributos del DTO.
     */
    public CiudadEntity toEntity() {
        CiudadEntity ce = new CiudadEntity();
        ce.setCoordenadas(coordenadas);
        ce.setNombre(nombre);
        return ce;
    }
}
