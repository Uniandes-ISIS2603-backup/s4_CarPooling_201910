/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.InfoTCDTO;
import co.edu.uniandes.csw.carpooling.ejb.InfoTCLogic;
import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
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
 * @author jf.garcia
 */
@Path("info")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class InfoTCResource {

    private static final Logger LOGGER = Logger.getLogger(InfoTCResource.class.getName());

    @Inject
    private InfoTCLogic logic;

    /**
     * Busca la información con el id asociado recibido en la URL y lo devuelve.
     *
     * @param id Identificador de la info que se esta buscando.
     * @return JSON {@link InfoTCDTO} - La información buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la información.
     */
    @GET
    @Path("{id: \\d+}")
    public InfoTCDTO getInfoTC(@PathParam("id") Long id) {
        InfoTCEntity info = logic.getInfoTC(id);
        if (info == null) {
            throw new WebApplicationException("El recurso InfoTC id: " + id + " no existe", 404);
        }
        return new InfoTCDTO(info);
    }

    /**
     * Busca y devuelve toda la información que existe en la aplicacion.
     *
     * @return JSONArray {@link InfoTCDTO} - La información encontrada en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List getInfoTCs() {
        List<InfoTCDTO> InfoTCs = listEntityToDTO(logic.getInfoTCs());
        return InfoTCs;
    }

    /**
     * Crea una nueva información.
     *
     * @param info {@link InfoTCDTO} - La información que se desea guardar.
     * @return JSON {@link InfoTCDTO} - La información guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la información.
     */
    @POST
    public InfoTCDTO createInfoTC(InfoTCDTO info) throws BusinessLogicException {
        InfoTCEntity entity = info.toEntity();
        entity = logic.createInfoTC(entity);
        return new InfoTCDTO(entity);
    }

    /**
     * Actualiza la información con el id recibido.
     *
     * @param id Identificador de la información que se desea actualizar.
     * @param info {@link InfoTCDTO} La información que se desea guardar.
     * @return JSON {@link InfoTCDTO} - La información guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la información a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la
     * información.
     */
    @PUT
    @Path("{id: \\d+}")
    public InfoTCDTO updateInfoTC(@PathParam("id") Long id, InfoTCDTO info) throws BusinessLogicException, WebApplicationException {
        InfoTCEntity entity = info.toEntity();
        if (entity == null) {
            throw new WebApplicationException("No existe el recurso que se quiere actualizar.");
        }
        entity = logic.updateInfoTC(id, entity);
        return new InfoTCDTO(entity);
    }

    /**
     * Borra la información con el id asociado recibido en la URL.
     *
     * @param id Identificador de la info que se desea borrar.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la información.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInfoTC(@PathParam("id") Long id) throws BusinessLogicException {
        InfoTCEntity entity = logic.getInfoTC(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /InfoTCs/" + id + "no existe.", 404);
        }
        logic.deleteInfoTC(id);
    }

    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     *
     * @param info
     * @return una lista de DTOs.
     */
    public static List<InfoTCDTO> listEntityToDTO(List<InfoTCEntity> info) {
        List<InfoTCDTO> list = new ArrayList<>();
        for (InfoTCEntity entity : info) {
            list.add(new InfoTCDTO(entity));
        }
        return list;
    }
}
