/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.NotificacionDTO;
import co.edu.uniandes.csw.carpooling.ejb.NotificacionLogic;
import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ja.morales11
 */
@Path("/notificaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class NotificacionResource {

    private static final Logger LOGGER = Logger.getLogger(NotificacionResource.class.getName());

    @Inject
    NotificacionLogic logic;

    /**
     * Busca la notificación con el id asociado recibido en la URL y lo
     * devuelve.
     *
     * @param id Identificador de la notificacion que se esta buscando.
     * @return JSON {@link NotificacionDTO} - La notificación buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el notificación.
     */
    @GET
    @Path("{id: \\d+}")
    public NotificacionDTO getNotificacion(@PathParam("id") Long id) {
        NotificacionEntity Notificacion = logic.getNotificacion(id);
        if (Notificacion == null) {
            throw new WebApplicationException("El recurso notificación id: " + id + " no existe", 404);
        }
        return new NotificacionDTO(Notificacion);
    }

    /**
     * Busca y devuelve todas las notificaciones que existen en la aplicacion.
     *
     * @return JSONArray {@link NotificacionDTO} - Las notificaciones
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<NotificacionDTO> getNotificaciones() {
        List<NotificacionDTO> Notificacions = listEntityToDTO(logic.getNotificaciones());
        return Notificacions;
    }

    /**
     * Crea una notificación nueva.
     *
     * @param Notificacion {@link NotificacionDTO} - La notificación que se
     * desea guardar.
     * @return JSON {@link NotificacionDTO} - La notificación guardada con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la notificación.
     */
    @POST
    public NotificacionDTO createNotificacion(NotificacionDTO Notificacion) throws BusinessLogicException {
        NotificacionEntity entity = Notificacion.toEntity();
        entity = logic.createNotificacion(entity);
        return new NotificacionDTO(entity);
    }
    
    
    
 


    /**
     * Borra la notificación con el id asociado recibido en la URL.
     *
     * @param id Identificador de la notificación que se desea borrar.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la notificación.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteNotificacion(@PathParam("id") Long id) throws BusinessLogicException {
        NotificacionEntity entity = logic.getNotificacion(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Notificaciones/" + id + "no existe.", 404);
        }
        logic.deleteNotificacion(id);
    }

    
    
     /**
     * Reemplaza el emisor.
     *
     * @param idNotificacion
     * @param idEmisor
     * @return El DTO con el nuevo emisor.
     * @throws BusinessLogicException .
     */
    @PUT
    @Path("{idN: \\d+}/emisor/{idEm: [a-zA-Z][a-zA-Z]*}")
    public NotificacionDTO ReplaceEmisor(@PathParam("idN") Long idNotificacion, @PathParam("idEm") String idEmisor) throws BusinessLogicException {

        NotificacionEntity entity;
        entity = logic.replaceRelacionEmisor(idNotificacion, idEmisor);
        return new NotificacionDTO(entity);
    }

    
     /**
     * Reemplaza el receptor.
     *
     * @param idNotificacion
     * @param idReceptor
     * @return El DTO con el nuevo receptor.
     * @throws BusinessLogicException .
     */
    @PUT
    @Path("{idN2: \\d+}/receptor/{idRe: [a-zA-Z][a-zA-Z]*}")
    public NotificacionDTO ReplaceCalificador(@PathParam("idN2") Long idNotificacion, @PathParam("idRe") String idReceptor) throws BusinessLogicException {

        NotificacionEntity entity;
        entity = logic.replaceRelacionReceptor(idNotificacion, idReceptor);
        return new NotificacionDTO(entity);
    }
    
    
    /**
     * Reemplaza el trayecto.
     *
     * @param idNotificacion
     * @param idTrayecto
     * @return El DTO con el nuevo trayecto.
     * @throws BusinessLogicException .
     */
    @PUT
    @Path("{idN3: \\d+}/trayecto/{idT:  \\d+}")
    public NotificacionDTO ReplaceTrayecto(@PathParam("idN3") Long idNotificacion, @PathParam("idT") Long idTrayecto) throws BusinessLogicException {

        NotificacionEntity entity;
        entity = logic.replaceRelacionTrayecto(idNotificacion, idTrayecto);
        return new NotificacionDTO(entity);
    }
    
    
    
    
    
    
    
    
    
    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     *
     * @param Notificacion
     * @return una lista de DTOs.
     */
    private List<NotificacionDTO> listEntityToDTO(List<NotificacionEntity> Notificacion) {
        List<NotificacionDTO> list = new ArrayList<>();
        for (NotificacionEntity entity : Notificacion) {
            list.add(new NotificacionDTO(entity));
        }
        return list;
    }

}
