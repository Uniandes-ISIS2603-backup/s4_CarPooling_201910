/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class VehiculoDTO implements Serializable
{    
    private Long id;
    private String modelo;
    private String color;
    private String placa;
    private Boolean alquilado;
    private AlquilerDTO alquilerInfo;

    
    public VehiculoDTO()
    {
        
    }

    public VehiculoDTO(VehiculoEntity ent) 
    {
        if(ent != null)
        {
            this.id = ent.getId();
            this.placa = ent.getPlaca();
            this.alquilado = ent.getAlquilado();
            this.modelo = ent.getModelo();
     
        if(ent.getAlquilerInfo() != null)
        {
            this.alquilerInfo = new AlquilerDTO(ent.getAlquilerInfo());
        }
        }
        
    }
    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the alquilado
     */
    public Boolean getAlquilado() {
        return alquilado;
    }

    /**
     * @param alquilado the alquilado to set
     */
    public void setAlquilado(Boolean alquilado)
    {
        this.alquilado = alquilado;
    }
    
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del vehiculo asociado.
     */
    
    public VehiculoEntity toEntity() {
        VehiculoEntity vehiculoEntity = new VehiculoEntity();
        vehiculoEntity.setModelo(this.modelo);
        vehiculoEntity.setPlaca(this.placa);
        vehiculoEntity.setAlquilado(this.alquilado);
        vehiculoEntity.setId(this.id);
        return vehiculoEntity;
    }

    /**
     * @return the alquilerInfo
     */
    public AlquilerDTO getAlquilerInfo() {
        return alquilerInfo;
    }

    /**
     * @param alquilerInfo the alquilerInfo to set
     */
    public void setAlquilerInfo(AlquilerDTO alquilerInfo) {
        this.alquilerInfo = alquilerInfo;
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
