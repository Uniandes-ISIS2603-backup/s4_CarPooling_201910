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
public class InfoTCEntity extends BaseEntity implements Serializable{
    
    private String t1;
    private String t2;
    private String entidad1;
    private String entidad2;
    
    /**
     * Contructor vacío
     */
    public InfoTCEntity(){}
    
    /**
     * Devuelve el número de la primera tarjeta.
     * @return t1
     */
    public String getT1(){return t1;}
    
    /**
     * Nuevo número de primera tarjeta
     * @param t1 
     */
    public void setT1(String t1){this.t1 = t1;}
    
    /**
     * Devuelve el número de la segunda tarjeta.
     * @return 
     */
    public String getT2(){return t2;}
    
    /**
     * Nuevo número de la segunda tarjeta
     * @param t2 
     */
    public void setT2(String t2){this.t2 = t2;}
    
    /**
     * Devuelve la primera entidad bancaria.
     * @return entidad1
     */
    public String getEntidad1(){return entidad1;}
    
    /**
     * Nueva entidad bancaria.
     * @param entidad1 
     */
    public void setEntidad1(String entidad1){this.entidad1 = entidad1;}
    
    /**
     * Devuelve la segunda entidad bancaria.
     * @return entidad2
     */
    public String getEntidad2(){return entidad2;}
    
    /**
     * Nueva entidad bancaria.
     * @param entidad2 
     */
    public void setEntidad2(String entidad2){this.entidad2 = entidad2;}
}
