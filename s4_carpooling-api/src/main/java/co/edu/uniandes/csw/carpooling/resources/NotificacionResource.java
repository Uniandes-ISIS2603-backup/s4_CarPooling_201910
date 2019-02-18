/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.NotificacionDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("notificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class NotificacionResource {
    private static final Logger LOGGER = Logger.getLogger(NotificacionResource.class.getName());
    
     @POST
    public NotificacionDTO createNotificacion (NotificacionDTO notificacion)
    {
        return notificacion;
    }
    
    
    
    
    
    
    
    
    
    
}
