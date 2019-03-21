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
 * @author df.penap
 */
@Entity
public class SeguroEntity extends BaseEntity implements Serializable{
     private String tipo;
     public SeguroEntity(){
         
     }

    /**
     * @return the nombre
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the nombre to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
     
}
