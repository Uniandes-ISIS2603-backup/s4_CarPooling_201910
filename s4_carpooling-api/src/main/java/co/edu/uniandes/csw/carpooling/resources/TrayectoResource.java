/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("trayecto")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrayectoResource {
    private static final Logger LOGGER = Logger.getLogger(TrayectoResource.class.getName());
    
    @GET
    public TrayectoDTO darTrayecto(){
        
        return null;
    }
    
    
    @POST
    public TrayectoDTO createTrayecto(TrayectoDTO trayecto){
        return trayecto;
    }
}
