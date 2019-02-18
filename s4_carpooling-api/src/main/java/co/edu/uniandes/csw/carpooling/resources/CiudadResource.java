/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.CiudadDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("ciudades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CiudadResource 
{
        private static final Logger LOGGER = Logger.getLogger(CiudadResource.class.getName());
        @GET
        public List getCiudades()
        {
            return null;
        }
        @POST
        public CiudadDTO createCiudad (CiudadDTO ciudad)
        {
            return ciudad;
        }
         @DELETE
        
         public void deleteCiudad(CiudadDTO ciudad)
         {
         }
        
}
