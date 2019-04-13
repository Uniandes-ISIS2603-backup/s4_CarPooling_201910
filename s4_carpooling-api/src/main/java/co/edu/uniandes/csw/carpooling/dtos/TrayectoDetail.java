/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class TrayectoDetail extends TrayectoDTO implements Serializable {

    private List<UsuarioDTO> pasajeros;
    private List<PagoDTO> pagos;

    /**
     * Constructor.
     */
    public TrayectoDetail() {
        super();
    }

    /**
     * Constructor desde entidad.
     *
     * @param trayectoEntity
     */
    public TrayectoDetail(TrayectoEntity trayectoEntity) {
        super(trayectoEntity);
        pagos = new ArrayList<PagoDTO>();
        /*if (trayectoEntity.getPagos()!=null){
            pagos = new ArrayList<PagoDTO>();
            for (PagoEntity pagoEntity: trayectoEntity.getPagos()){
                pagos.add(new PagoDTO(pagoEntity));
            }
        }*/
        if (trayectoEntity.getPasajeros() != null) {
            pasajeros = new ArrayList<>();
            for (UsuarioEntity usuarioEntity : trayectoEntity.getPasajeros()) {
                pasajeros.add(new UsuarioDTO(usuarioEntity));
            }
        }

    }

    /**
     * @return the usuarios
     */
    public List<UsuarioDTO> getPasajeros() {
        return pasajeros;
    }

    /**
     * @param pasajeros the usuarios to set
     */
    public void setPasajeros(List<UsuarioDTO> pasajeros) {
        this.pasajeros = pasajeros;
    }
}
