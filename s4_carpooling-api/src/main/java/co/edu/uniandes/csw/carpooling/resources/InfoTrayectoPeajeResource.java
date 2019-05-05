/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PeajeDTO;
import co.edu.uniandes.csw.carpooling.dtos.TrayectoInfoDTO;
import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import co.edu.uniandes.csw.carpooling.ejb.InfoTrayectoPeajeLogic;
import co.edu.uniandes.csw.carpooling.ejb.PeajeLogic;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Produces("application/json")
@Consumes("application/json")
public class InfoTrayectoPeajeResource {
    
    @Inject
    private InfoTrayectoPeajeLogic logic;
    
    @Inject
    private PeajeLogic peajeLogic;

    /**
     * Agregar un peaje a un trayecto
     */
    @POST
    @Path ("{peajesId: \\d+}")
    public TrayectoInfoDTO addPeaje(@PathParam("infoId") Long idInfoTrayecto, @PathParam("peajesId") Long peajesId) {
        
        if (peajeLogic.get(peajesId) == null) {
            throw new WebApplicationException("El recurso /peaje/" + peajesId + " no existe.", 404);
        }
        TrayectoInfoDTO nuevoTrayectoInfo = new TrayectoInfoDTO(logic.addPeaje(idInfoTrayecto, peajesId));
        return nuevoTrayectoInfo;
    }
    
}
