/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.CiudadDTO;
import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import co.edu.uniandes.csw.carpooling.ejb.CiudadLogic;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoCiudadLogic;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrayectoCiudadResource {
    
    
    @Inject
    private TrayectoCiudadLogic trayectoCiudadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CiudadLogic ciudadLogic;
    
  /*  
    @POST
    @Path ("{idCiudadOrigen: \\d+}")
    public CiudadDTO addCiudadOrigen(@PathParam("trayectoId") Long idTrayecto, @PathParam("idCiudadOrigen") Long ciudadId) {
        
        if (ciudadLogic.get(ciudadId) == null) {
            throw new WebApplicationException("El recurso /ciudad/" + ciudadId + " no existe.", 404);
        }
        CiudadDTO ciudadDTO = null;
        try {
            ciudadDTO = new CiudadDTO(trayectoCiudadLogic.addCiudadOrigen(idTrayecto, ciudadId));
        }
        catch (Exception e)
        {
            throw new WebApplicationException(e.getMessage());
        }
        return ciudadDTO;
    }*/
    
    @POST
    @Path ("{idCiudadDestino: \\d+}")
    public CiudadDTO addCiudadDestino(@PathParam("trayectoId") Long idTrayecto, @PathParam("idCiudadDestino") Long ciudadId) {
        
        if (ciudadLogic.get(ciudadId) == null) {
            throw new WebApplicationException("El recurso /ciudad/" + ciudadId + " no existe.", 404);
        }
        CiudadDTO ciudadDTO = null;
        try {
            ciudadDTO = new CiudadDTO(trayectoCiudadLogic.addCiudadDestino(idTrayecto, ciudadId));
        }
        catch (Exception e)
        {
            throw new WebApplicationException(e.getMessage());
        }
        return ciudadDTO;
    }
    
    @POST
    public CiudadDTO createCiudad(@PathParam("trayectoId") Long idTrayecto, CiudadDTO ciudad) throws BusinessLogicException {
        CiudadDTO nuevoReviewDTO = new CiudadDTO(trayectoCiudadLogic.createCiudadDestino(idTrayecto, ciudad.toEntity()));
        return nuevoReviewDTO;
    }
    
}
