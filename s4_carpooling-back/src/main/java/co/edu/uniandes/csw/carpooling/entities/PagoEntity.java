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
 * @author jf.garcia
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
    private Integer valor;
    
    public PagoEntity(){}
    
    /**
     * Obtiene el valor.
     * @return valor
     */
    public Integer getValor(){return valor;}
    
    /**
     * Modifica el valor.
     * @param valor 
     */
    public void setValor(Integer valor){this.valor = valor;}
    
    
}
