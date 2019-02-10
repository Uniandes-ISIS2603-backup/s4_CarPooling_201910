/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PeajeDTO;
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
 * @author im.sarmiento
 */
@Path("peajes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PeajeResource {
    private static final Logger LOGGER = Logger.getLogger(PeajeResource.class.getName());
    @GET
    public List getPeajes()
    {
        return null;
    }
    @POST
    public PeajeDTO createPeaje(PeajeDTO peaje)
    {
        return peaje;
    }
    @PUT
    @Path("{id: \\d+}")
    public PeajeDTO updatePeaje(@PathParam("id")Long id, PeajeDTO peaje)
    {
        return peaje;
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deletePeaje(@PathParam("id")Long id)
    {
        
    }
}
