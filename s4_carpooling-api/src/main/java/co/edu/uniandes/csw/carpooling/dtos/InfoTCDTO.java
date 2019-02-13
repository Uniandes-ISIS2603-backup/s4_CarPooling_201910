/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;
import java.io.Serializable;
/**
 *
 * @author jf.garcia
 */
public class InfoTCDTO implements Serializable{
    private Long id;
    private Integer t1;
    private Integer t2;
    private String entidad1;
    private String entidad2;
    
     /**
     * Método constructor.
     */
    public InfoTCDTO(){}
    
    /**
     * @return El identificador.
     */
    public Long getId() {return id;}

    /**
     * @param id El identificador nuevo.
     */
    public void setId(Long id) {this.id = id;}
    
    /**
     * @return Tarjeta 1.
     */
    public Integer getT1() {return t1;}

    /**
     * @param t1 La nueva tarjeta.
     */
    public void setT1(Integer t1) {this.t1 = t1;}
    
    /**
     * @return Tarjeta 2.
     */
    public Integer getT2() {return t2;}

    /**
     * @param t2 La nueva tarjeta.
     */
    public void setT2(Integer t2) {this.t2 = t2;}
    
    /**
     * @return Entidad bancaria 1.
     */
    public String getEntidad1() {return entidad1;}

    /**
     * @param entidad1 La nueva entidad.
     */
    public void setEntidad1(String entidad1) {this.entidad1 = entidad1;}
    
    /**
     * @return Entidad bancaria 2.
     */
    public String getEntidad2() {return entidad2;}

    /**
     * @param entidad2 La nueva entidad.
     */
    public void setEntidad2(String entidad2) {this.entidad2 = entidad2;}
}
