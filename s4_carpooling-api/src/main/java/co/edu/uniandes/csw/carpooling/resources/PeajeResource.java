/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PeajeDTO;
import co.edu.uniandes.csw.carpooling.ejb.PeajeLogic;
import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author im.sarmiento
 */
@Path("peajes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PeajeResource {

    private static final Logger LOGGER = Logger.getLogger(PeajeResource.class.getName());
    
    @Inject
    PeajeLogic logic;

    /**
     * Busca el peaje con el id asociado recibido en la URL y lo devuelve.
     *
     * @param id Identificador del peaje que se esta buscando.
     * @return JSON {@link PeajeDTO} - El peaje buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el peaje.
     */
    @GET
    @Path("{id: \\d+}")
    public PeajeDTO getPeaje(@PathParam("id") Long id) {
        if (logic.get(id) == null) {
            throw new WebApplicationException("El recurso /peaje/" + id + " no existe.", 404);
        }
        PeajeEntity entity = logic.get(id);
        return new PeajeDTO(entity);
    }

    /**
     * Busca y devuelve todos los peajes que existen en la aplicacion.
     *
     * @return JSONArray {@link ¨PeajeDTO} - Los peajes encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PeajeDTO> getPeajes() {
        List<PeajeDTO> alquileres = listEntityToDTO(logic.get());
        return alquileres;
    }

    /**
     * Crea un nuevo peaje.
     *
     * @param peaje {@link PeajeDTO} - El peaje que se desea guardar.
     * @return JSON {@link PagoDTO} - El peaje guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el peaje.
     */
    @POST
    public PeajeDTO createPeaje(PeajeDTO peaje) throws BusinessLogicException {

        PeajeEntity entity = peaje.toEntity();
        entity = logic.createPeaje(entity);
        return new PeajeDTO(entity);
    }

    /**
     * Actualiza el peaje con el id recibido.
     *
     * @param id Identificador del peaje que se desea actualizar.
     * @param peaje {@link PeajeDTO} El peaje que se desea guardar.
     * @return JSON {@link PeajeDTO} - El peaje guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el peaje a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el peaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public PeajeDTO updatePeaje(@PathParam("id") Long id, PeajeDTO peaje) throws BusinessLogicException {
        if (logic.get(id) == null) {
            throw new WebApplicationException("El recurso /peaje/" + id + " no existe.", 404);
        }
        PeajeEntity entity = peaje.toEntity();
        entity = logic.update(id, entity);
        return new PeajeDTO(entity);
    }

    /**
     * Borra el peaje con el id asociado recibido en la URL.
     *
     * @param id Identificador del peaje que se desea borrar.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el peaje.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePeaje(@PathParam("id") Long id) {
        if (logic.get(id) == null) {
            throw new WebApplicationException("El recurso /peaje/" + id + " no existe.", 404);
        }
        logic.delete(id);
    }

    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     *
     * @param pago
     * @return una lista de DTOs.
     */
    private List<PeajeDTO> listEntityToDTO(List<PeajeEntity> Peaje) {
        List<PeajeDTO> list = new ArrayList<>();
        for (PeajeEntity entity : Peaje) {
            list.add(new PeajeDTO(entity));
        }
        return list;
    }
}
