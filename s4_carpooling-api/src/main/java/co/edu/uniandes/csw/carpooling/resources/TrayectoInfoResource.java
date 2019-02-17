/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoInfoDTO;
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
@Path("infoTrayecto")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrayectoInfoResource {
    private static final Logger LOGGER = Logger.getLogger(TrayectoInfoResource.class.getName());
    
    @POST
    public TrayectoInfoDTO createInfoTrayeecto(){
        
        TrayectoInfoDTO info = new TrayectoInfoDTO();
        info.setCombustiblePrecio(20);
        info.setIdDetalle(new Long(10));
        info.setCombustiblePrecio(40);
        info.setDuracionMins(30);
        
        return info;
    }
}
