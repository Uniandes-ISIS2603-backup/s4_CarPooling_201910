/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import co.edu.uniandes.csw.carpooling.ejb.VehiculoLogic;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class VehiculoResource {

    private static final Logger LOGGER = Logger.getLogger(VehiculoResource.class.getName());

    @Inject
    private VehiculoLogic vehiculoLogic;

    @GET
    public List<VehiculoDTO> gets() {
        List<VehiculoDTO> lista = new ArrayList<>();
        for (VehiculoEntity entity : vehiculoLogic.get()) {
            lista.add(new VehiculoDTO(entity));
        }
        return lista;
    }

    @GET
    @Path("{vehiculoId: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public VehiculoDTO get(@PathParam("vehiculoId") Long vehiculoId) {
        LOGGER.log(Level.INFO, "VehiculoResource get: input: {0}", vehiculoId);
        VehiculoEntity vehiculoEntity = vehiculoLogic.get(vehiculoId);
        if (vehiculoEntity == null) {
            throw new WebApplicationException("El recurso /vehiculos/" + vehiculoId + " no existe.", 404);
        }
        VehiculoDTO vehiculoDetailDTO = new VehiculoDTO(vehiculoEntity);
        LOGGER.log(Level.INFO, "VehiculoResource get: output: {0}", vehiculoDetailDTO);
        return vehiculoDetailDTO;
    }

    @POST
    public VehiculoDTO createVehiculo(VehiculoDTO vehiculo) throws BusinessLogicException {
        VehiculoEntity entity = vehiculo.toEntity();
        entity = vehiculoLogic.createVehiculo(entity);
        return new VehiculoDTO(entity);
    }

    @DELETE
    @PathParam("{vehiculoId: \\d+}")
    public void deleteVehiculo(@PathParam("vehiculoId") Long vehiculoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VehiculoResource get: input: {0}", vehiculoId);
        VehiculoEntity vehiculoEntity = vehiculoLogic.get(vehiculoId);
        if (vehiculoEntity == null) {
            throw new WebApplicationException("El recurso /vehiculos/" + vehiculoId + " no existe.", 404);
        }
        vehiculoLogic.delete(vehiculoId);
    }

    @PUT
    @PathParam("{vehiculoId: \\d+}")
    public VehiculoDTO updateVehiculo(@PathParam("vehiculoId") Long vehiculoId, VehiculoDTO vehiculo) {
        LOGGER.log(Level.INFO, "VehiculoResource get: input: {0}", vehiculoId);
        VehiculoEntity vehiculoEntity = vehiculoLogic.get(vehiculoId);
        if (vehiculoEntity == null) {
            throw new WebApplicationException("El recurso /vehiculos/" + vehiculoId + " no existe.", 404);
        }
        vehiculoEntity = vehiculoLogic.updateVehiculo(vehiculoId, vehiculoEntity);
        return new VehiculoDTO(vehiculoEntity);
    }

}
