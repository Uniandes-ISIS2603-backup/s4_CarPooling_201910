/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.UsuarioDTO;
import co.edu.uniandes.csw.carpooling.ejb.UsuarioLogic;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @im.sarmiento 
 */

@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic logic;
   
    /**
     * 
     * @return la lista de todos los usuarios 
     */
    @GET
    public List getUsuarios()
    {
        List<UsuarioDTO> usuarios = listEntityToDTO(logic.getUsuarios());
        return usuarios;
    }
    /**
     * 
     * @param username el nombre de usuario deseado
     * @return el usuario especificado
     */
    @GET
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public UsuarioDTO getUsuario(@PathParam("username")String username)
    {
        UsuarioEntity usuario = logic.getUsuario(username);
        if (usuario == null) {
            throw new WebApplicationException("El usuario con nombre: " + username + "no existe", 404);
        }
        return new UsuarioDTO(usuario);
    }
    /**
     * 
     * @param usuario el usuario a crear en forma de DTO
     * @return el usuario creado
     * @throws BusinessLogicException segun las reglas de la lògica
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException{
        UsuarioEntity entity = usuario.toEntity();
        entity = logic.create(entity);
        return new UsuarioDTO(entity);
    }
    
    /**
     * 
     * @param username usuario anterior que se queria aplicar 
     * @param usuario nuevo con la nueva informacion
     * @return el usuario modificado 
     * @throws BusinessLogicException si no se cumplean las reglas de la lògica
     */
    @PUT
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public UsuarioDTO updateUsuario(@PathParam("username")String username, UsuarioDTO usuario) throws BusinessLogicException
    {
        UsuarioEntity entity = usuario.toEntity();
        entity = logic.updateUsuario(username, entity);
        return new UsuarioDTO(entity);
    }
    
    /**
     * 
     * @param username del usuario que se quiere borrar
     * @throws BusinessLogicException si no se cumplen las reglas de la logica
     */
    @DELETE
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public void deleteUsuario(@PathParam("username")String username) throws BusinessLogicException
    {
        UsuarioEntity usuario = logic.getUsuario(username);
        if (usuario == null) {
            throw new WebApplicationException("El usuario con nombre: " + username + "no existe", 404);
        }
        logic.deleteUsuario(username);
    }
    
    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     * @param Usuario
     * @return una lista de DTOs.
     */
    private List<UsuarioDTO> listEntityToDTO(List<UsuarioEntity> Usuario) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : Usuario) {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }

}
