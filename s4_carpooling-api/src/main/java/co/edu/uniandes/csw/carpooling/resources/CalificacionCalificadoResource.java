/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.CalificacionDTO;
import co.edu.uniandes.csw.carpooling.dtos.UsuarioDTO;
import co.edu.uniandes.csw.carpooling.ejb.CalificacionCalificadoLogic;
import co.edu.uniandes.csw.carpooling.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carpooling.ejb.UsuarioLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "usuario/{username}/calificaciones
 * @author ja.morales11
 */
@Path("calificaciones/{calificacionesId: \\d+}/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionCalificadoResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionCalificadoResource.class.getName());
    
    @Inject
    private CalificacionCalificadoLogic calificacionCalificadoLogic;
    
    @Inject 
    private CalificacionLogic calificacionLogic;
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    
    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO setCalificacio(UsuarioDTO calificado, @PathParam("calificacionId") Long calificacionId){
        
        if(calificacionLogic.getCalificacion(calificacionId) == null){
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId+ " no existe.", 404);
        }
        if(usuarioLogic.getUsuario(calificado.getUsername()) == null){
            throw new WebApplicationException("El recurso /usuarios/" + calificado.getUsername() + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionCalificadoLogic.setCalificado(calificado.getUsername(), calificacionId));
           
            
        return calificacionDTO;
        
        
    }
    
}
