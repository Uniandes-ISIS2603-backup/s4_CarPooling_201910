/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PeajeDTO;
import co.edu.uniandes.csw.carpooling.ejb.PeajeLogic;
import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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
    @Inject
     PeajeLogic logic;
     
    @GET
    @Path("{id: \\d+}")
    public PeajeDTO getPeaje(@PathParam("id")Long id)
    {
        if(logic.get(id) == null)
       { 
        throw new WebApplicationException("El recurso /peaje/" + id + " no existe.", 404);
       } 
        PeajeEntity entity = logic.get(id);
         return new PeajeDTO(entity);
    }
    @GET
    public List<PeajeDTO> getAlquileres()
    {
        List<PeajeDTO> alquileres = listEntityToDTO(logic.get());
        return alquileres;
    }
    
     @POST
    public PeajeDTO createAlquiler(PeajeDTO Peaje) throws BusinessLogicException
    {
        
        PeajeEntity entity = Peaje.toEntity();
        entity = logic.createPeaje(entity);
        return new PeajeDTO(entity);
    }
    @PUT
    @Path("{id: \\d+}")
    public PeajeDTO updatePeaje(@PathParam("id")Long id, PeajeDTO Peaje) throws BusinessLogicException
    {
       if(logic.get(id) == null)
       { 
        throw new WebApplicationException("El recurso /peaje/" + id + " no existe.", 404);
       }
       PeajeEntity entity = Peaje.toEntity();
       entity = logic.update(id, entity);
       return new PeajeDTO(entity);
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deletePeaje(@PathParam("id")Long id)
    {
        if(logic.get(id) == null)
       { 
        throw new WebApplicationException("El recurso /peaje/" + id + " no existe.", 404);
       }
        logic.delete(id);
    }
    
     private List<PeajeDTO> listEntityToDTO(List<PeajeEntity> Peaje) {
        List<PeajeDTO> list = new ArrayList<>();
        for (PeajeEntity entity : Peaje) {
            list.add(new PeajeDTO(entity));
        }
        return list;
     }
}
