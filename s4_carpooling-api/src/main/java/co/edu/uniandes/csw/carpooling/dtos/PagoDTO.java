/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import java.io.Serializable;
/**
 *
 * @author jf.garcia
 */
public class PagoDTO implements Serializable {
    
    private Long id;
    private Double valor;
    private UsuarioDTO pasajero;
    private UsuarioDTO conductor;
    private TrayectoDTO trayecto;
    private InfoTCDTO infoTC;
    
    /**
     * Método constructor.
     */
    public PagoDTO(){}
    
    /**
     * @return El identificador.
     */
    public Long getId() {return id;}

    /**
     * @param id El identificador nuevo.
     */
    public void setId(Long id) {this.id = id;}
    
    /**
     * @return El valor a pagar.
     */
    public Double getValor() {return valor;}
    
    /**
     * @param valor El valor nuevo a pagar.
     */
    public void setValor(Double valor) {this.valor = valor;}
  
    /**
     * @return El pasajero.
     */
    public UsuarioDTO getPasajero() {return pasajero;}
    
    /**
     * @param pasajero El pasajero nuevo asociado.
     */
    public void setPasajero(UsuarioDTO pasajero) {this.pasajero = pasajero;}
    
    /**
     * @return El conductor.
     */
    public UsuarioDTO getConductor() {return conductor;}
    
    /**
     * @param conductor El conductor nuevo asociado.
     */
    public void setConductor(UsuarioDTO conductor) {this.conductor = conductor;}
    
    /**
     * @return El trayecto asociado.
     */
    public TrayectoDTO getTrayecto() {return trayecto;}
    
    /**
     * @param trayecto El nuevo trayecto asociado.
     */
    public void setTrayecto(TrayectoDTO trayecto) {this.trayecto = trayecto;}
    
    /**
     * @return La información de la tarjeta de crédito.
     */
    public InfoTCDTO getInfoTC() {return infoTC;}
    
    /**
     * @param infoTC Nueva información de tarjeta.
     */
    public void setInfoTC(InfoTCDTO infoTC) {this.infoTC = infoTC;}
    
    
    public PagoDTO (PagoEntity pagoEntity)
    {
        
        if (pagoEntity != null) {
            this.id = pagoEntity.getId();
            this.valor = pagoEntity.getValor();
            if (pagoEntity.getInfoTC() != null) {
                this.infoTC = new InfoTCDTO();
            }
            if (pagoEntity.getTrayecto() != null) {
                this.trayecto =  new TrayectoDTO();
            }
            if (pagoEntity.getUsuarioRecibe() != null) {
                this.conductor = new UsuarioDTO();
            }
            if (pagoEntity.getUsuarioHace() != null) {
                this.pasajero = new UsuarioDTO();
            }
        }
    }
    
    /**
     * Método para transfomar el DTO a una entidad.
     * @return La entidad asociada.
     */
    public PagoEntity toEntity(){
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setId(this.id);
        pagoEntity.setValor(this.valor);
       // if (this.conductor != null) {
       //     pagoEntity.setUsuarioRecibe(this.conductor.toEntity();
       // }
       // if (this.pasajero != null) {
       //     pagoEntity.setUsuarioHace(this.pasajero.toEntity();
       // }
        //if (this.trayecto != null) {
        //    pagoEntity.setTrayecto(this.trayecto.toEntity());
        //}
        //El código del método toEntity no se ecnuentra implementado para las tres clases anteriores.
        return pagoEntity;
    }
}
