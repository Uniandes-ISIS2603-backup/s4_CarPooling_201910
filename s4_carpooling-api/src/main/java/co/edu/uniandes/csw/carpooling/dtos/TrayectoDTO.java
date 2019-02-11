/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author estudiante
 */
public class TrayectoDTO implements Serializable{
    
    private long idTrayecto;
    private int horaInicial;
    private int horaFinal;
    private Calendar fechaSalida;
    private Calendar fechaLlegada;
    private int cupos;
    private TrayectoInfoDTO info;
    private UsuarioDTO conductor;
    //private Ciudad salida;
    //private Ciudad llegada
    
    public TrayectoDTO(){
        
    }

    /**
     * @return the idTrayecto
     */
    public long getIdTrayecto() {
        return idTrayecto;
    }

    /**
     * @param idTrayecto the idTrayecto to set
     */
    public void setIdTrayecto(long idTrayecto) {
        this.idTrayecto = idTrayecto;
    }

    /**
     * @return the horaInicial
     */
    public int getHoraInicial() {
        return horaInicial;
    }

    /**
     * @param horaInicial the horaInicial to set
     */
    public void setHoraInicial(int horaInicial) {
        this.horaInicial = horaInicial;
    }

    /**
     * @return the horaFinal
     */
    public int getHoraFinal() {
        return horaFinal;
    }

    /**
     * @param horaFinal the horaFinal to set
     */
    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * @return the fechaSalida
     */
    public Calendar getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Calendar fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the fechaLlegada
     */
    public Calendar getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * @param fechaLlegada the fechaLlegada to set
     */
    public void setFechaLlegada(Calendar fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * @return the cupos
     */
    public int getCupos() {
        return cupos;
    }

    /**
     * @param cupos the cupos to set
     */
    public void setCupos(int cupos) {
        this.cupos = cupos;
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
    
    
}
