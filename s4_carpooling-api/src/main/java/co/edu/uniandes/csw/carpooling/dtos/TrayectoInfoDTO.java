/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.BaseEntity;
import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class TrayectoInfoDTO extends BaseEntity implements Serializable {

    private Integer costo;
    private Integer combustiblePrecio;
    private Integer duracionMins;
    private Date horaSalida;
    private Date horaLlegada;
    private VehiculoDTO carro;
    private List<PeajeDTO> peajes;
    private List<CiudadDTO> paradas;


    /**
     * Constructor vacío.
     */
    public TrayectoInfoDTO() {

    }

    /**
     * Constructor desde entidad.
     *
     * @param entity
     */
    public TrayectoInfoDTO(TrayectoInfoEntity entity) {

        if (entity != null) {
            this.setId(entity.getId());
            this.horaSalida = entity.getHoraInicial();
            if (entity.getCombustible() != null) {
                this.combustiblePrecio = entity.getCombustible();
            }
            if (entity.getCosto() != null) {
                this.costo = entity.getCosto();
            }
            if (entity.getHoraFinal() != null) {
                this.horaSalida = entity.getHoraFinal();
            }
            if (entity.getDuracion() != null) {
                this.duracionMins = entity.getDuracion();
            }
            if (entity.getVehiculo()!= null) {
                this.carro = new VehiculoDTO(entity.getVehiculo());
            }
            if (entity.getPeajes()!= null) {
            peajes = new ArrayList<PeajeDTO>();
                for (PeajeEntity peaje : entity.getPeajes()) {
                    peajes.add(new PeajeDTO(peaje));
                }
            }
            if (entity.getParadas()!= null) {
            paradas = new ArrayList<CiudadDTO>();
                for (CiudadEntity peaje : entity.getParadas()) {
                    paradas.add(new CiudadDTO(peaje));
                }
            }
        }
    }

    /**
     * Método para convertir de DTO a entidad.
     *
     * @return entidad
     */
    public TrayectoInfoEntity toEntity() {
        TrayectoInfoEntity retorno = new TrayectoInfoEntity();
        retorno.setCombustible(combustiblePrecio);
        retorno.setCosto(costo);
        retorno.setDuracion(duracionMins);
        retorno.setHoraInicial(getHoraSalida());
        if(carro!=null){
        retorno.setVehiculo(carro.toEntity());
        }
        if (getPeajes() != null) {
            List<PeajeEntity> peajes = new ArrayList<PeajeEntity>();
            for (PeajeDTO dto : getPeajes()) {
                peajes.add(dto.toEntity());
            }
            retorno.setPeajes(peajes);
        }
        if (getParadas() != null) {
            List<CiudadEntity> paradas = new ArrayList<CiudadEntity>();
            for (CiudadDTO dto : getParadas()) {
                paradas.add(dto.toEntity());
            }
            retorno.setParadas(paradas);
        }
        return retorno;
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
     * @return the peajes
     */
    public List<PeajeDTO> getPeajes() {
        return peajes;
    }

    /**
     * @param peajes the peajes to set
     */
    public void setPeajes(List<PeajeDTO> peajes) {
        this.peajes = peajes;
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

    /**
     * @return the horaSalida
     */
    public Date getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return the horaLlegada
     */
    public Date getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * @param horaLlegada the horaLlegada to set
     */
    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    /**
     * @return the carro
     */
    public VehiculoDTO getCarro() {
        return carro;
    }

    /**
     * @param carro the carro to set
     */
    public void setCarro(VehiculoDTO carro) {
        this.carro = carro;
    }

    /**
     * @return the ciudades
     */
    public List<CiudadDTO> getParadas() {
        return paradas;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(List<CiudadDTO> ciudades) {
        this.paradas = ciudades;
    }

}
