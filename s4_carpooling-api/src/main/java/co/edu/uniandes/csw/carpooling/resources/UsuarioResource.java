/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoDetail;
import co.edu.uniandes.csw.carpooling.dtos.UsuarioDTO;
import co.edu.uniandes.csw.carpooling.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoLogic;
import co.edu.uniandes.csw.carpooling.ejb.UsuarioLogic;
import co.edu.uniandes.csw.carpooling.ejb.UsuarioTrayectoLogic;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @im.sarmiento 
 */

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic logic;
    
    @Inject
    private TrayectoLogic trayectoLogic;
    
    @Inject
    private UsuarioTrayectoLogic usuarioTrayectoLogic;
  
   
    /**
     * 
     * @return la lista de todos los usuarios 
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios()
    {
        List<UsuarioDetailDTO> usuarios = listEntitydeToDetailDTO(logic.getUsuarios());
        return usuarios;
    }
    /**
     * 
     * @param username el nombre de usuario deseado
     * @return el usuario especificado
     */
    @GET
    @Path("{username: [a-zA-Z][a-zA-Z]*}")
    public UsuarioDetailDTO getUsuario(@PathParam("username")String username)
    {
        UsuarioEntity usuario = logic.getUsuario(username);
        if (usuario == null) {
            throw new WebApplicationException("El usuario con nombre: " + username + "no existe", 404);
        }
        return new UsuarioDetailDTO(usuario);
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
        UsuarioEntity entity2 = logic.create(entity);
        return new UsuarioDetailDTO(entity2);
    }
    
    /**
     * 
     * @param username usuario anterior que se queria aplicar 
     * @param usuario nuevo con la nueva informacion
     * @return el usuario modificado 
     * @throws BusinessLogicException si no se cumplean las reglas de la lògica
     */
    @PUT
    @Path("{username: [a-zA-Z][a-zA-Z]*}")
    public UsuarioDetailDTO updateUsuario(@PathParam("username")String username, UsuarioDetailDTO usuario) throws BusinessLogicException
    {
        //usuario.setUsername(username);
        if (logic.getUsuario(username) == null) {
            throw new WebApplicationException("El recurso /usuario/" + username + " no existe.", 404);
        }
        UsuarioEntity entity = usuario.toEntity();
        entity = logic.updateUsuario(username, entity);
        return new UsuarioDetailDTO(entity);
    }
    
    /**
     * 
     * @param username del usuario que se quiere borrar
     * @throws BusinessLogicException si no se cumplen las reglas de la logica
     */
    @DELETE
    @Path("{username: [a-zA-Z][a-zA-Z]*}")
    public void deleteUsuario(@PathParam("username")String username) throws BusinessLogicException
    {
        UsuarioEntity usuario = logic.getUsuario(username);
        if (usuario == null) {
            throw new WebApplicationException("El usuario con nombre: " + username + "no existe", 404);
        }
        logic.deleteUsuario(username);
    }
    
     /**
     * Conexión con el servicio de libros para una editorial.
     * {@link EditorialBooksResource}
     *
     * Este método conecta la ruta de /editorials con las rutas de /books que
     * dependen de la editorial, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los libros de una editorial.
     *
     * @param editorialsId El ID de la editorial con respecto a la cual se
     * accede al servicio.
     * @return El servicio de libros para esta editorial en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @Path("{username: [a-zA-Z][a-zA-Z]*}/vehiculos")
    public Class<UsuarioVehiculoResource> getUsuarioVehiculoResource(@PathParam("username") String username) {
        if (logic.getUsuario(username) == null) {
            throw new WebApplicationException("El recurso /usuario/" + username + " no existe.", 404);
        }
        return UsuarioVehiculoResource.class;
    }
    
    
    /**
     * Trayectos conductor
     */
    @POST
    @Path("{username: [a-zA-Z][a-zA-Z]*}/trayectosConductor")
    public TrayectoDetail getTraycetosConductor(@PathParam("username") String username, TrayectoDetail trayecto) {
        if (logic.getUsuario(username) == null) {
            throw new WebApplicationException("El recurso /usuario/" + username + "/trayectosConductor no existe.", 404);
        }
        TrayectoDetail nuevoReviewDTO = new TrayectoDetail(usuarioTrayectoLogic.addTrayectoConductor(username, trayecto.toEntity()));
        return nuevoReviewDTO;
       
    }
    
    /**
     * Trayectos pasajero. El trayecto ya existe.
     */
    @POST
    @Path("{username: [a-zA-Z][a-zA-Z]*}/trayectosPasajero/{trayectoId: \\d+}")
    public TrayectoDetail getTraycetosPasajero(@PathParam("username") String username, @PathParam("trayectoId") Long trayectoId) {
        if (logic.getUsuario(username) == null) {
            throw new WebApplicationException("El recurso /usuario/" + username + "/trayectosConductor no existe.", 404);
        }
        if (trayectoLogic.getTrayeto(trayectoId) == null) {
            throw new WebApplicationException("El trayecto de id "+ trayectoId +" no existe.", 404);
        }
        TrayectoDetail trayecto = new TrayectoDetail(usuarioTrayectoLogic.addTrayectoPasajero(username, trayectoId));
        return trayecto;
    }
    
    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     * @param Usuario
     * @return una lista de DTOs.
     */
    private List<UsuarioDetailDTO> listEntitydeToDetailDTO(List<UsuarioEntity> Usuario) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : Usuario) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
    

}
