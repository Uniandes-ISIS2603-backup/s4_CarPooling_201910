/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import java.io.Serializable;

/**
 *
 * @author df.penap
 */
public class SeguroDTO implements Serializable {
    private Long id;
    private String tipo;
    public SeguroDTO(SeguroEntity entity){
        if(entity!=null)
        {
            tipo = entity.getTipo();    
        }

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
    public SeguroEntity toEntity(){
        
        SeguroEntity entity = new SeguroEntity();
        entity.setTipo(tipo);
        entity.setId(id);
        return entity;
    }
}
