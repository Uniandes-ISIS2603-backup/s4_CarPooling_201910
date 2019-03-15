/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PagoDTO;
import co.edu.uniandes.csw.carpooling.ejb.PagoLogic;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
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
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {

    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());

    @Inject
    private PagoLogic logic;

    /**
     * Busca el pago con el id asociado recibido en la URL y lo devuelve.
     *
     * @param id Identificador del pago que se esta buscando.
     * @return JSON {@link PagoDTO} - El pago buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @GET
    @Path("{id: \\d+}")
    public PagoDTO getPago(@PathParam("id") Long id) {
        PagoEntity pago = logic.getPago(id);
        if (pago == null) {
            throw new WebApplicationException("El recurso pago id: " + id + " no existe", 404);
        }
        return new PagoDTO(pago);
    }

    /**
     * Busca y devuelve todos los pagos que existen en la aplicacion.
     *
     * @return JSONArray {@link PagoDTO} - Los pagos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List getPagos() {
        List<PagoDTO> pagos = listEntityToDTO(logic.getPagos());
        return pagos;
    }

    /**
     * Crea un nuevo pago.
     *
     * @param pago {@link PagoDTO} - El pago que se desea guardar.
     * @return JSON {@link PagoDTO} - El pago guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el pago o no tiene
     * tarjetas asociadas.
     */
    @POST
    public PagoDTO createPago(PagoDTO pago) throws BusinessLogicException {
        PagoEntity entity = pago.toEntity();
        entity = logic.createPago(entity);
        return new PagoDTO(entity);
    }

    /**
     * Actualiza el pago con el id recibido.
     *
     * @param id Identificador del pago que se desea actualizar.
     * @param pago {@link PagoDTO} El pago que se desea guardar.
     * @return JSON {@link PagoDTO} - El pago guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el pago.
     */
    @PUT
    @Path("{id: \\d+}")
    public PagoDTO updatePago(@PathParam("id") Long id, PagoDTO pago) throws BusinessLogicException {
        PagoEntity entity = pago.toEntity();
        entity = logic.updatePago(id, entity);
        return new PagoDTO(entity);
    }

    /**
     * Borra el pago con el id asociado recibido en la URL.
     *
     * @param id Identificador del pago que se desea borrar.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id") Long id) throws BusinessLogicException {
        PagoEntity entity = logic.getPago(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /pagos/" + id + "no existe.", 404);
        }
        logic.deletePago(id);
    }

    /**
     * Se utiliza un método para convertir una lista de Entidades a DTOs.
     *
     * @param pago
     * @return una lista de DTOs.
     */
    private List<PagoDTO> listEntityToDTO(List<PagoEntity> pago) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : pago) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
}
