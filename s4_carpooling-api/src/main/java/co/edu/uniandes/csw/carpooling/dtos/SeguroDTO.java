/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.BaseEntity;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import java.io.Serializable;

/**
 *
 * @author df.penap
 */
public class SeguroDTO extends BaseEntity implements Serializable {
    
    private String tipo;
    
    public SeguroDTO(){
        
    }
    public SeguroDTO(SeguroEntity entity){
        if(entity!=null)
        {
            this.setId(entity.getId());
            tipo = entity.getTipo();    
        }

    }



    public SeguroEntity toEntity(){
        
        SeguroEntity entity = new SeguroEntity();
        entity.setTipo(getTipo());
       
        return entity;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
