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
    public InfoTCDTO getInfo() {return infoTC;}
    
    /**
     * @param infoTC Nueva información de tarjeta.
     */
    public void setInfo(InfoTCDTO infoTC) {this.infoTC = infoTC;}
    
}
