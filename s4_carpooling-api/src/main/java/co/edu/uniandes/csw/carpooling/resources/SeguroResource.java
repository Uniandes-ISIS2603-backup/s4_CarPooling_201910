/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.SeguroDTO;
import co.edu.uniandes.csw.carpooling.ejb.SeguroLogic;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author df.penap
 */
@Path("/seguros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SeguroResource {
     private static final Logger LOGGER = Logger.getLogger(AlquilerResource.class.getName());
     @Inject
     SeguroLogic logic;
     
    @GET
    @Path("{id: \\d+}")
    public SeguroDTO getSeguro(@PathParam("id")Long id)
    {
         SeguroEntity entity = logic.get(id);
         return new SeguroDTO(entity);
    }
    @GET
    public List<SeguroDTO> getAlquileres()
    {
        List<SeguroDTO> alquileres = listEntityToDTO(logic.get());
        return alquileres;
    }
    
     @POST
    public SeguroDTO createAlquiler(SeguroDTO seguro) throws BusinessLogicException
    {
        SeguroEntity entity = seguro.toEntity();
        entity = logic.create(entity);
        return new SeguroDTO(entity);
    }
    @PUT
    @Path("{id: \\d+}")
    public SeguroDTO updateSeguro(@PathParam("id")Long id, SeguroDTO seguro) throws BusinessLogicException
    {
       SeguroEntity entity = seguro.toEntity();
       entity = logic.update(id, entity);
       return new SeguroDTO(entity);
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSeguro(Long id)
    {
        
        logic.delete(id);
    }
    
     private List<SeguroDTO> listEntityToDTO(List<SeguroEntity> seguro) {
        List<SeguroDTO> list = new ArrayList<>();
        for (SeguroEntity entity : seguro) {
            list.add(new SeguroDTO(entity));
        }
        return list;
    }
}
