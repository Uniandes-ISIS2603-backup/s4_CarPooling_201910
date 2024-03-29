/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.adapters.DateAdapter;
import co.edu.uniandes.csw.carpooling.entities.BaseEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author estudiante
 */
public class TrayectoDTO implements Serializable {

    private Integer estado;
    
    private Long id;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicial;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFinal;

    private UsuarioDTO conductor;

    private TrayectoInfoDTO info;
    
    private CiudadDTO ciudadOrigen;
    
    private CiudadDTO ciudadDestino;

    /**
     * Constructor vacío.
     */
    public TrayectoDTO() {

    }

    /**
     * Constructor desde una entidad.
     *
     * @param entity
     */
    public TrayectoDTO(TrayectoEntity entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.estado = entity.getEstado();
            this.fechaInicial = entity.getFechaInicial();
            this.fechaFinal = entity.getFechaFinal();
            if(entity.getDestino() != null){
                this.ciudadDestino = new CiudadDTO( entity.getDestino());
            }if(entity.getOrigen() != null){
                this.ciudadOrigen = new CiudadDTO( entity.getOrigen());
            }
            

            if (entity.getConductor() != null) {
                this.conductor = new UsuarioDTO(entity.getConductor());
            } else {
                this.conductor = null;
            }
            if (entity.getInfoTrayecto() != null) {
                this.info = new TrayectoInfoDTO(entity.getInfoTrayecto());
            } else {
                this.info = null;
            }
        }
    }

    /**
     * Convierte el DTO a una entidad.
     *
     * @return
     */
    public TrayectoEntity toEntity() {
        TrayectoEntity retorno = new TrayectoEntity();
        retorno.setEstado(this.estado);
        retorno.setId(this.getId());
        retorno.setFechaFinal(this.getFechaFinal());
        retorno.setFechaInicial(this.getFechaInicial());
        if (getInfo() != null) {
            retorno.setInfoTrayecto((this.getInfo()).toEntity());
        }
        if (getConductor() != null) {
            retorno.setConductor(this.getConductor().toEntity());
        }if(getCiudadDestino() != null){
            retorno.setDestino(this.getCiudadDestino().toEntity());
        }if(getCiudadOrigen() != null){
            retorno.setOrigen(this.getCiudadOrigen().toEntity());
        }
        return retorno;
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
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the conductor
     */
    public UsuarioDTO getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(UsuarioDTO conductor) {
        this.conductor = conductor;
    }

    /**
     * @return the info
     */
    public TrayectoInfoDTO getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(TrayectoInfoDTO info) {
        this.info = info;
    }
    
    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the ciudadOrigen
     */
    public CiudadDTO getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * @param ciudadOrigen the ciudadOrigen to set
     */
    public void setCiudadOrigen(CiudadDTO ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    /**
     * @return the ciudadDestino
     */
    public CiudadDTO getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * @param ciudadDestino the ciudadDestino to set
     */
    public void setCiudadDestino(CiudadDTO ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
    
    
    
}
