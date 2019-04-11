/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.SeguroDTO;
import co.edu.uniandes.csw.carpooling.ejb.SeguroLogic;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author df.penap
 */
@Path("/seguros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SeguroResource {

    private static final Logger LOGGER = Logger.getLogger(SeguroResource.class.getName());
    
    @Inject
    SeguroLogic logic;

    /**
     * Busca el seguro con el id asociado recibido en la URL y lo devuelve.
     *
     * @param id Identificador del seguro que se esta buscando.
     * @return JSON {@link SeguroDTO} - El seguro buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el seguro.
     */
    @GET
    @Path("{id: \\d+}")
    public SeguroDTO getSeguro(@PathParam("id") Long id) {
        if (logic.get(id) == null) {
            throw new WebApplicationException("El recurso /seguro/" + id + " no existe.", 404);
        }
        SeguroEntity entity = logic.get(id);
        return new SeguroDTO(entity);
    }

    /**
     * Busca y devuelve todos los seguros que existen en la aplicacion.
     *
     * @return JSONArray {@link SeguroDTO} - Los seguros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SeguroDTO> getSeguros() {
        List<SeguroDTO> seguros = listEntityToDTO(logic.get());
        return seguros;
    }

    /**
     * Crea un nuevo seguro.
     *
     * @param seguro {@link SeguroDTO} - El seguro que se desea guardar.
     * @return JSON {@link SeguroDTO} - El seguro guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el seguro.
     */
    @POST
    public SeguroDTO createSeguro(SeguroDTO seguro) throws BusinessLogicException {
        SeguroEntity entity = seguro.toEntity();
        entity = logic.create(entity);
        return new SeguroDTO(entity);
    }

    /**
     * Actualiza el seguro con el id recibido.
     *
     * @param id Identificador del seguro que se desea actualizar.
     * @param seguro {@link SeguroDTO} El seguro que se desea guardar.
     * @return JSON {@link SeguroDTO} - El seguro guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el seguro a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el seguro.
     */
    @PUT
    @Path("{id: \\d+}")
    public SeguroDTO updateSeguro(@PathParam("id") Long id, SeguroDTO seguro) throws BusinessLogicException {
        if (logic.get(id) == null) {
            throw new WebApplicationException("El recurso /seguro/" + id + " no existe.", 404);
        }
        SeguroEntity entity = seguro.toEntity();

        entity = logic.update(id, entity);
        return new SeguroDTO(entity);
    }

    /**
     * Borra el seguro con el id asociado recibido en la URL.
     *
     * @param id Identificador del seguro que se desea borrar.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el seguro.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSeguro(@PathParam("id") Long id) {
        if (logic.get(id) == null) {
            throw new WebApplicationException("El recurso /seguro/" + id + " no existe.", 404);
        }
        logic.delete(id);
    }

    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     *
     * @param seguro
     * @return una lista de DTOs.
     */
    private List<SeguroDTO> listEntityToDTO(List<SeguroEntity> seguro) {
        List<SeguroDTO> list = new ArrayList<>();
        for (SeguroEntity entity : seguro) {
            list.add(new SeguroDTO(entity));
        }
        return list;
    }
}
