/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.CiudadDTO;
import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import co.edu.uniandes.csw.carpooling.ejb.CiudadLogic;
import co.edu.uniandes.csw.carpooling.ejb.VehiculoLogic;
import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

@Path("ciudades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CiudadResource 
{
        private static final Logger LOGGER = Logger.getLogger(CiudadResource.class.getName());
       @Inject
       CiudadLogic logic;
               
        @GET
        @Path("id:\\d+")
        public List getCiudad(@PathParam("id")Long id)
        {
            CiudadEntity ent = logic.get(id);
            return (List) new CiudadDTO(ent);
        }
        
        @GET
        public List<CiudadDTO> getCiudades()
        {
            List<CiudadDTO> ciudad = new ArrayList<>();
            for(CiudadEntity ent: logic.get())
            {
                ciudad.add(new CiudadDTO(ent));
            }
            return ciudad;
        }
        @POST
        public CiudadDTO createCiudad (CiudadDTO ciudad) throws BusinessLogicException
        {
           CiudadEntity ent = ciudad.toEntity();
           ent = logic.create(ent);
           return new CiudadDTO(ent);
        
        }
        
}
