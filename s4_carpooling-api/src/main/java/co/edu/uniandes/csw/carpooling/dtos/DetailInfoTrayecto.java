/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class DetailInfoTrayecto extends TrayectoInfoDTO implements Serializable{
    
    private List<PeajeDTO> peajes;
    //private List<CiudadesDTO> ciudades;
    
    public DetailInfoTrayecto(){
        
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
    
    
}
