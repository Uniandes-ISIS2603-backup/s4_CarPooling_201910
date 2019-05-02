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
 * @author estudiante
 */
@Path("vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class VehiculoResource
{
        private static final Logger LOGGER = Logger.getLogger(VehiculoResource.class.getName());
        
        @Inject
        private VehiculoLogic logic;
        
        @GET 
        public List<VehiculoDTO> getVehiculos()
        {
            List<VehiculoDTO> lista = new ArrayList<>();
            for (VehiculoEntity entity : logic.get())
            {
               lista.add(new VehiculoDTO(entity));
            }
            return lista;
        }
        
        @GET
        @Path("{id: \\d+}")
        @Produces(MediaType.APPLICATION_JSON)
        public VehiculoDTO getVehiculo(@PathParam("id")Long id)
        {
         VehiculoEntity ent = logic.get(id);
         return new VehiculoDTO(ent);
        }
        
        @POST
        public VehiculoDTO createVehiculo(VehiculoDTO vehiculo) throws BusinessLogicException{
            VehiculoEntity entity = vehiculo.toEntity();
            VehiculoEntity entity2 = logic.createVehiculo(entity);
            return new VehiculoDTO(entity2);
        }
        
        @DELETE
        @Path("{id: \\d+}")
        public void deleteVehiculo(@PathParam("id")Long id) throws BusinessLogicException
        {
            logic.delete(id);
        }
        
        @PUT
        @Path ("{id: \\d+}")
        public VehiculoDTO updateVehiculo(@PathParam("id")Long id, VehiculoDTO vehiculo)
        {
            VehiculoEntity entity = vehiculo.toEntity();
            entity = logic.updateVehiculo(id, entity);
            return new VehiculoDTO(entity);
        }
        /**
     * Añade las relaciones correspondientes.
     *
     * @param idVehiculo
     * @param idAlquiler
     * @return El DTO con todas las relaciones.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     */
    @GET
    @Path("{idV: \\d+}/{idA: \\d+}")
    public VehiculoDTO addRelacion(@PathParam("idV") Long idVehiculo,@PathParam("idA") Long idAlquiler) throws BusinessLogicException {
        VehiculoEntity entity = logic.addRelacionAlquiler(idVehiculo,idAlquiler);
        return new VehiculoDTO(entity);

    }
}
