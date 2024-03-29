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
    @Path("calificado/{idCa1: [a-zA-Z][a-zA-Z]*}/calificador/{idCa2: [a-zA-Z][a-zA-Z]*}/trayecto/{idT:  \\d+}")
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion, @PathParam("idCa1") String idCalificado, @PathParam("idCa2") String idCalificador, @PathParam("idT") Long idTrayecto) throws BusinessLogicException {
        CalificacionEntity entity = calificacion.toEntity();
        entity = logic.createCalificacion(entity);
        entity = logic.replaceRelacionCalificado(entity.getId(), idCalificado);
        entity = logic.replaceRelacionCalificador(entity.getId(), idCalificador);
        entity = logic.replaceRelacionTrayecto(entity.getId(), idTrayecto);
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
