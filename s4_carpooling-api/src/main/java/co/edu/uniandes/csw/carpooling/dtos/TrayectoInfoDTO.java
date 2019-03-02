/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class TrayectoInfoDTO implements Serializable {


    
    private Long idDetalle;
    private Integer costo;
    private Integer combustiblePrecio;
    private Integer duracionMins;
    private Date horaSalida;
    private Date horaLlegada;
    //private VehiculoDTO carro;

    public TrayectoInfoDTO(){
        
    }
    
    public TrayectoInfoDTO(TrayectoInfoEntity entity){
        
        if(entity != null){
            this.idDetalle = entity.getId();
            this.horaSalida = entity.getHoraInicial();
            if(entity.getCombustible() != null){
                this.combustiblePrecio = entity.getCombustible();
            }if(entity.getCosto() != null){
                this.costo = entity.getCosto();
            }if(entity.getHoraFinal() != null){
                this.horaSalida = entity.getHoraFinal();
            }if(entity.getDuracion() != null){
                this.duracionMins = entity.getDuracion();
            }
        }
    }
    
    public TrayectoInfoEntity toEntity(){
        TrayectoInfoEntity retorno = new TrayectoInfoEntity();
        retorno.setCombustible(combustiblePrecio);
        retorno.setCosto(costo);
        retorno.setDuracion(duracionMins);
        retorno.setHoraInicial(horaSalida);
        retorno.setId(idDetalle);
        return retorno;
    }
    
    /**
     * @return the idDetalle
     */
    public Long getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * @return the costo
     */
    public Integer getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    /**
     * @return the combustiblePrecio
     */
    public Integer getCombustiblePrecio() {
        return combustiblePrecio;
    }

    /**
     * @param combustiblePrecio the combustiblePrecio to set
     */
    public void setCombustiblePrecio(Integer combustiblePrecio) {
        this.combustiblePrecio = combustiblePrecio;
    }

    /**
     * @return the duracionMins
     */
    public Integer getDuracionMins() {
        return duracionMins;
    }

    /**
     * @param duracionMins the duracionMins to set
     */
    public void setDuracionMins(Integer duracionMins) {
        this.duracionMins = duracionMins;
    }


    
}
