/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoInfoDTO;
import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoInfoLogic;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoTrayectoInfoLogic;
import java.util.logging.Logger;
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
public class TrayectoTrayectInfoResource {
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoTrayectInfoResource.class.getName());
    
    @Inject
    private TrayectoTrayectoInfoLogic trayectoInfoTrayectoLogic;
    
    @Inject
    private TrayectoInfoLogic infoLogic;
    
    
    
    @POST
    @Path ("idInfoTrayecto: \\d+}")
    public TrayectoInfoDTO addInfoTrayecto(@PathParam("trayectoId") Long idTrayecto, @PathParam("idInfoTrayecto") Long idInfoTrayecto) {
        
        if (infoLogic.getTrayectoInfo(idInfoTrayecto) == null) {
            throw new WebApplicationException("El recurso /InfoTrayecto/" + idInfoTrayecto + " no existe.", 404);
        }
        TrayectoInfoDTO dto = null;
        try {
            dto  = new TrayectoInfoDTO(trayectoInfoTrayectoLogic.addInfoTrayecto(idTrayecto, idInfoTrayecto));
        }
        catch (Exception e)
        {
            throw new WebApplicationException(e.getMessage());
        }
        return dto;
    }
    
}
