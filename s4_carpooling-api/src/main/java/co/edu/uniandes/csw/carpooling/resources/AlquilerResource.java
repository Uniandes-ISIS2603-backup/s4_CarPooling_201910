/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.AlquilerDTO;
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
@Path("alquileres")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AlquilerResource {
    private static final Logger LOGGER = Logger.getLogger(AlquilerResource.class.getName());
    @GET
    public List getAlquileres()
    {
        return null;
    }
    @POST
    public AlquilerDTO createAlquiler(AlquilerDTO alquiler)
    {
        return alquiler;
    }
    @PUT
    @Path("{id: \\d+}")
    public AlquilerDTO updateAlquiler(@PathParam("id")Long id, AlquilerDTO alquiler)
    {
        return alquiler;
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAlquiler(@PathParam("id")Long id)
    {
        
    }
}
