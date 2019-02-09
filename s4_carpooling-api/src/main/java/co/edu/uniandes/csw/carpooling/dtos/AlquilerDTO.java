/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import java.io.Serializable;

/**
 *
 * @author df.penap
 */
public class AlquilerDTO implements Serializable{
    private Long id;
    // private UsuarioDTO arrendatario
    // private UsuarioDTO dueño
    // private VehiculoDTO vehiculoAlquilado
    private SeguroDTO seguro;
    public AlquilerDTO()
    {
        
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

    /**
     * @return the seguro
     */
    public SeguroDTO getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(SeguroDTO seguro) {
        this.seguro = seguro;
    }
    
}
