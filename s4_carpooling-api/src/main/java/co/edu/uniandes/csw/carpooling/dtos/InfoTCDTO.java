/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import java.io.Serializable;

/**
 *
 * @author jf.garcia
 */
public class InfoTCDTO implements Serializable {

    private Long id;
    private String t1;
    private String t2;
    private String entidad1;
    private String entidad2;

    /**
     * Método constructor vacío.
     */
    public InfoTCDTO() {
    }

    /**
     * @return El identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id El identificador nuevo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Tarjeta 1.
     */
    public String getT1() {
        return t1;
    }

    /**
     * @param t1 La nueva tarjeta.
     */
    public void setT1(String t1) {
        this.t1 = t1;
    }

    /**
     * @return Tarjeta 2.
     */
    public String getT2() {
        return t2;
    }

    /**
     * @param t2 La nueva tarjeta.
     */
    public void setT2(String t2) {
        this.t2 = t2;
    }

    /**
     * @return Entidad bancaria 1.
     */
    public String getEntidad1() {
        return entidad1;
    }

    /**
     * @param entidad1 La nueva entidad.
     */
    public void setEntidad1(String entidad1) {
        this.entidad1 = entidad1;
    }

    /**
     * @return Entidad bancaria 2.
     */
    public String getEntidad2() {
        return entidad2;
    }

    /**
     * @param entidad2 La nueva entidad.
     */
    public void setEntidad2(String entidad2) {
        this.entidad2 = entidad2;
    }

    /**
     * Constructor a partir de entidad.
     *
     * @param pInfoTCEntity
     */
    public InfoTCDTO(InfoTCEntity pInfoTCEntity) {
        this.id = pInfoTCEntity.getId();
        this.entidad1 = pInfoTCEntity.getEntidad1();
        this.entidad2 = pInfoTCEntity.getEntidad2();
        this.t1 = pInfoTCEntity.getT1();
        this.t2 = pInfoTCEntity.getT2();
    }

    /**
     * Método para transformar DTO a una entidad.
     *
     * @return Entidad creada
     */
    public InfoTCEntity toEntity() {
        InfoTCEntity infoTCEntity = new InfoTCEntity();
        infoTCEntity.setId(this.id);
        infoTCEntity.setEntidad1(this.entidad1);
        infoTCEntity.setEntidad2(this.entidad2);
        infoTCEntity.setT1(this.t1);
        infoTCEntity.setT2(this.t2);
        return infoTCEntity;
    }
}
