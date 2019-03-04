/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.UsuarioDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
   
    @GET
    public List getUsuarios()
    {
        return null;
    }
    @GET
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public UsuarioDTO getUsuario(@PathParam("username")String username)
    {
        return null;
    }
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario){
        return usuario;
    }
    @PUT
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public UsuarioDTO updateUsuario(@PathParam("username")String username, UsuarioDTO usuario)
    {
        return usuario;
    }
    @DELETE
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public void deleteUsuario(@PathParam("username")String username)
    {
        
    }

}
