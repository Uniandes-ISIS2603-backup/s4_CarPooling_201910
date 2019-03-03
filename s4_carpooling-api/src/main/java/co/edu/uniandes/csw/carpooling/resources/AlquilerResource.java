/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.AlquilerDTO;
import co.edu.uniandes.csw.carpooling.ejb.AlquilerLogic;
import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
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
@Path("alquileres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AlquilerResource {
    private static final Logger LOGGER = Logger.getLogger(AlquilerResource.class.getName());
    @Inject
    private AlquilerLogic logic;
    @GET
    public List<AlquilerDTO> getAlquileres()
    {
        List<AlquilerDTO> alquileres = listEntityToDTO(logic.getAlquiler());
        return alquileres;
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

    private List<AlquilerDTO> listEntityToDTO(List<AlquilerEntity> alquiler) {
        List<AlquilerDTO> list = new ArrayList<>();
        for (AlquilerEntity entity : alquiler) {
            list.add(new AlquilerDTO(entity));
        }
        return list;
    }
}
