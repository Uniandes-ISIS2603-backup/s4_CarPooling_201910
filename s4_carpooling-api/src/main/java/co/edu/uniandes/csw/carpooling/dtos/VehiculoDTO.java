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
public class VehiculoDTO implements Serializable
{
    private String modelo;
    private String color;
    private String placa;
    private Boolean alquilado;

    
    public VehiculoDTO()
    {
        
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
    public void setAlquilado(Boolean alquilado) {
        this.alquilado = alquilado;
    }
    
    
    
}
