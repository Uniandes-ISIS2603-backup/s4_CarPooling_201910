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
public class TrayectoDTO  extends BaseEntity implements Serializable {
    
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicial;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFinal;
    
    private UsuarioDTO conductor;
    
    private TrayectoInfoDTO info;
    
    public TrayectoDTO(){
        
    }
    
    public TrayectoDTO(TrayectoEntity entity){
        if(entity != null){
            this.setId(entity.getId());

            this.fechaInicial = entity.getFechaInicial();

            this.fechaInicial = entity.getFechaFinal();

            if(entity.getConductor() != null){
                this.conductor = new UsuarioDTO(entity.getConductor());
            }else{
                this.conductor = null;
            }
            if(entity.getInfoTrayecto() != null){
                this.info = new TrayectoInfoDTO(entity.getInfoTrayecto());
            }
        }
    }
    
    public TrayectoEntity toEntity(){
        TrayectoEntity retorno = new TrayectoEntity();
        retorno.setFechaFinal(this.getFechaFinal());
        retorno.setFechaInicial(this.getFechaInicial());
        if(getInfo() != null){
            retorno.setInfoTrayecto((this.getInfo()).toEntity());
        }if(getConductor() != null){
            retorno.setConductor(this.getConductor().toEntity());
        }
        
        return retorno;
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
    
    
    
}
