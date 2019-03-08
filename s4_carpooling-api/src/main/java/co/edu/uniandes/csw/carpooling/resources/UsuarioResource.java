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
 * @author 
 */

@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    
    //Isa, falta documentar cada método, poner tu nombre de usuario en el autor, y revisar que la implementación funcione.
    //Las excepciones las manejé como en los ejemplos, la clase resource también lanza las excepciones de lógica. Corrige y organiza a tu gusto.

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic logic;
   
    @GET
    public List getUsuarios()
    {
        List<UsuarioDTO> usuarios = listEntityToDTO(logic.getUsuarios());
        return usuarios;
    }
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
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException{
        UsuarioEntity entity = usuario.toEntity();
        entity = logic.create(entity);
        return new UsuarioDTO(entity);
    }
    @PUT
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public UsuarioDTO updateUsuario(@PathParam("username")String username, UsuarioDTO usuario) throws BusinessLogicException
    {
        UsuarioEntity entity = usuario.toEntity();
        entity = logic.updateUsuario(username, entity);
        return new UsuarioDTO(entity);
    }
    @DELETE
    @Path("{username: [a-zA-Z][a-zA-Z]*}}")
    public void deleteUsuario(@PathParam("username")String username) throws BusinessLogicException
    {
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
