/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.SeguroDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
     @GET
     public List getSeguros()
     {
         return null;
     }
     @POST
    public SeguroDTO createAlquiler(SeguroDTO seguro)
    {
        return seguro;
    }
    @PUT
    @Path("{id: \\d+}")
    public SeguroDTO updateSeguro(@PathParam("id")Long id, SeguroDTO seguro)
    {
        return seguro;
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSeguro()
    {
        
    }
}
