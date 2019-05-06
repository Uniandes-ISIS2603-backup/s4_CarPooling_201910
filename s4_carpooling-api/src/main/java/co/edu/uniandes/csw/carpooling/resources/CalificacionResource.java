/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.CalificacionDTO;
import co.edu.uniandes.csw.carpooling.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("/calificaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CalificacionResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());

  
    @Inject
    private CalificacionLogic logic;

    /**
     * Busca la calificación con el id asociado recibido en la URL y lo
     * devuelve.
     *
     * @param id Identificador de la calificacion que se esta buscando.
     * @return JSON {@link CalificacionDTO} - La calificación buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("id") Long id) {
        CalificacionEntity calificacion = logic.getCalificacion(id);
        if (calificacion == null) {
            throw new WebApplicationException("El recurso calificacion id: " + id + " no existe", 404);
        }
        return new CalificacionDTO(calificacion);
    }

    /**
     * Busca y devuelve todos las calificaciones que existen en la aplicacion.
     *
     * @return JSONArray {@link CalificacionDTO} - Las calificaciones
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CalificacionDTO> getCalificaciones() {
        List<CalificacionDTO> calificaciones = listEntityToDTO(logic.getCalificaciones());
        return calificaciones;
    }

    /**
     * Crea una calificación nueva.
     *
     * @param calificacion {@link CalificacionDTO} - La calificación que se
     * desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificación guardado con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe La calificación o no tiene
     * tarjetas asociadas.
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws BusinessLogicException {
        CalificacionEntity entity = calificacion.toEntity();
        entity = logic.createCalificacion(entity);
        return new CalificacionDTO(entity);
    }

    

    /**
     * Borra la calificación con el id asociado recibido en la URL.
     *
     * @param id Identificador de la calificación que se desea borrar.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id") Long id) throws BusinessLogicException {
        CalificacionEntity entity = logic.getCalificacion(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + id + "no existe.", 404);
        }
        logic.deleteCalificacion(id);
    }
    
    
   
     /**
     * Reemplaza el calificado.
     *
     * @param idCalificacion
     * @param idCalificado
     * @return El DTO con el nuevo calificado.
     * @throws BusinessLogicException .
     */
    @PUT
    @Path("{idC: \\d+}/calificado/{idCa: [a-zA-Z][a-zA-Z]*}")
    public CalificacionDTO ReplaceCalificado(@PathParam("idC") Long idCalificacion, @PathParam("idCa") String idCalificado) throws BusinessLogicException {

        CalificacionEntity entity;
        entity = logic.replaceRelacionCalificado(idCalificacion, idCalificado);
        return new CalificacionDTO(entity);
    }

    
     /**
     * Reemplaza el calificador.
     *
     * @param idCalificacion
     * @param idCalificador
     * @return El DTO con el nuevo calificador.
     * @throws BusinessLogicException .
     */
    @PUT
    @Path("{idC2: \\d+}/calificador/{idCal: [a-zA-Z][a-zA-Z]*}")
    public CalificacionDTO ReplaceCalificador(@PathParam("idC2") Long idCalificacion, @PathParam("idCal") String idCalificador) throws BusinessLogicException {

        CalificacionEntity entity;
        entity = logic.replaceRelacionCalificador(idCalificacion, idCalificador);
        return new CalificacionDTO(entity);
    }
    
    
    /**
     * Reemplaza el trayecto.
     *
     * @param idCalificacion
     * @param idTrayecto
     * @return El DTO con el nuevo trayecto.
     * @throws BusinessLogicException .
     */
    @PUT
    @Path("{idC3: \\d+}/trayecto/{idT:  \\d+}")
    public CalificacionDTO ReplaceTrayecto(@PathParam("idC3") Long idCalificacion, @PathParam("idT") Long idTrayecto) throws BusinessLogicException {

        CalificacionEntity entity;
        entity = logic.replaceRelacionTrayecto(idCalificacion, idTrayecto);
        return new CalificacionDTO(entity);
    }
    
    
    

    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     *
     * @param calificacion
     * @return una lista de DTOs.
     */
    private List<CalificacionDTO> listEntityToDTO(List<CalificacionEntity> Calificacion) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : Calificacion) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
}
