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
public class TrayectoDetail extends TrayectoDTO implements Serializable{
    
    private List<UsuarioDTO> usuarios;
    
    public TrayectoDetail(){
        
    }

    /**
     * @return the usuarios
     */
    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }
}
