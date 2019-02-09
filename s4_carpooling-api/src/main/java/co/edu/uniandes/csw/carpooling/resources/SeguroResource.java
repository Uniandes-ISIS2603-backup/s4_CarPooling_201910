/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.SeguroDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author df.penap
 */
@Path("seguros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SeguroResource {
     private static final Logger LOGGER = Logger.getLogger(AlquilerResource.class.getName());
      @POST
    public SeguroDTO createAlquiler(SeguroDTO seguro)
    {
        return seguro;
    }
}
