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
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author estudiante
 */
public class VehiculoResource
{
        private static final Logger LOGGER = Logger.getLogger(VehiculoResource.class.getName());
        
        @Inject
        private VehiculoLogic logic;
        
        @GET 
        public List getVehiculos()
        {
            List<VehiculoDTO> lista = new ArrayList<>();
            for (VehiculoEntity entity : logic.get())
            {
               lista.add(new VehiculoDTO(entity));
            }
            return lista;
        }
        
        @GET
        @PathParam ("id: \\d+")
        public VehiculoDTO get(@PathParam("id")Long id)
        {
         VehiculoEntity ent = logic.get(id);
         return new VehiculoDTO(ent);
        }
        @POST 
        public VehiculoDTO createVehiculo(VehiculoDTO vehiculo) throws BusinessLogicException 
        {
            VehiculoEntity entity = vehiculo.toEntity();
            entity = logic.createVehiculo(entity);
            return new VehiculoDTO(entity);
        }
        @DELETE
        @PathParam("{id: \\d+}")
        public void deleteVehiculo(@PathParam("id")Long id) throws BusinessLogicException
        {
            logic.delete(id);
        }
        
        @PUT
        @PathParam ("{id: \\d+}")
        public VehiculoDTO updateVehiculo(@PathParam("id")Long id, VehiculoDTO vehiculo)
        {
            VehiculoEntity entity = vehiculo.toEntity();
            entity = logic.updateVehiculo(id, entity);
            return new VehiculoDTO(entity);
        }

}
