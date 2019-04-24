/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.VehiculoDTO;
import co.edu.uniandes.csw.carpooling.ejb.UsuarioVehiculoLogic;
import co.edu.uniandes.csw.carpooling.ejb.VehiculoLogic;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "usuario/{username}/vehiculos".
 *
 * @author Isabela Sarmiento
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioVehiculoResource {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioVehiculoResource.class.getName());

    @Inject
    private UsuarioVehiculoLogic usuarioVehiculoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private VehiculoLogic vehiculoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda un libro dentro de una editorial con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la editorial.
     *
     * @param editorialsId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param booksId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - El libro guardado en la editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path ("{id: \\d+}")
    public VehiculoDTO addVehiculo(@PathParam("username") String usuariosId, @PathParam("vehiculosId") Long vehiculosId) {
        if (vehiculoLogic.get(vehiculosId) == null) {
            throw new WebApplicationException("El recurso /vehiculo/" + vehiculosId + " no existe.", 404);
        }
        VehiculoDTO vehiculoDTO = new VehiculoDTO(usuarioVehiculoLogic.addVehiculo(usuariosId, vehiculosId));
        return vehiculoDTO;
    }
}
