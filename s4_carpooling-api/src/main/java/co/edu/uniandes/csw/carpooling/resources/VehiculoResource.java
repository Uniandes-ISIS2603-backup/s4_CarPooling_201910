/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

/**
 *
 * @author estudiante
 */
public class VehiculoResource {
        private static final Logger LOGGER = Logger.getLogger(VehiculoResource.class.getName());
        
        @GET 
        public List getVehiculos()
        {
            return null;
        }
        @POST 
        public VehiculoDTO createVehiculo(VehiculoDTO vehiculo)
        {
            return vehiculo;
        }
        @DELETE
        public void deleteVehiculo(VehiculoDTO vehiculo)
        {
            
        }

}
