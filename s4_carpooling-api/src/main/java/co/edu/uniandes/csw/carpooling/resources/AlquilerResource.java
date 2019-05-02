/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.AlquilerDTO;
import co.edu.uniandes.csw.carpooling.ejb.AlquilerLogic;
import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
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
@Path("alquileres")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AlquilerResource {

    private static final Logger LOGGER = Logger.getLogger(AlquilerResource.class.getName());
    @Inject
    private AlquilerLogic logic;

    /**
     * Busca el alquiler con el id asociado recibido en la URL y lo devuelve.
     *
     * @param id Identificador del alquiler que se esta buscando.
     * @return JSON {@link AlquilerDTO} - El alquiler buscado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el alquiler.
     */
    @GET
    @Path("{id: \\d+}")
    public AlquilerDTO getAlquiler(@PathParam("id") Long id) {
        AlquilerEntity alquiler = logic.getAlquiler(id);
        if (alquiler == null) {
            throw new WebApplicationException("El recurso alquiler id: " + id + " no existe", 404);
        }
        return new AlquilerDTO(alquiler);
    }

    /**
     * Busca y devuelve todos los alquileres que existen en la aplicacion.
     *
     * @return JSONArray {@link AlquilerDTO} - Los alquileres encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AlquilerDTO> getAlquileres() {
        List<AlquilerDTO> alquileres = listEntityToDTO(logic.getAlquiler());
        return alquileres;
    }

    /**
     * Crea un nuevo alquiler.
     *
     * @param alquiler {@link AlquilerDTO} - El alquiler que se desea guardar.
     * @return JSON {@link AlquilerDTO} - El alquiler guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el alquiler.
     */
    @POST
    public AlquilerDTO createAlquiler(AlquilerDTO alquiler) throws BusinessLogicException {
        AlquilerEntity entity = alquiler.toEntity();
        entity = logic.createAlquiler(entity);
        return new AlquilerDTO(entity);
    }

    /**
     * Actualiza el alquiler con el id recibido.
     *
     * @param id Identificador del pago que se desea actualizar.
     * @param alquiler {@link AlquilerDTO} El alquiler que se desea guardar.
     * @return JSON {@link AlquilerDTO} - El alquiler guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el alquiler a
     * actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public AlquilerDTO updateAlquiler(@PathParam("id") Long id, AlquilerDTO alquiler) {
        AlquilerEntity find = logic.getAlquiler(id);
        if (find == null) {
            throw new WebApplicationException("El recurso alquiler id: " + id + " no existe", 404);
        }
        AlquilerEntity entity = alquiler.toEntity();
        entity = logic.update(id, entity);
        return new AlquilerDTO(entity);
    }

    /**
     * Borra el alquiler con el id asociado recibido en la URL.
     *
     * @param id Identificador del alquiler que se desea borrar.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el alquiler.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAlquiler(@PathParam("id") Long id) {
        AlquilerEntity alquiler = logic.getAlquiler(id);
        if (alquiler == null) {
            throw new WebApplicationException("El recurso alquiler id: " + id + " no existe", 404);
        }
        logic.deleteAlquiler(id);

    }

    /**
     * Añade las relaciones correspondientes.
     *
     * @param idAlquiler
     * @param idDueno
     * @param idArrendatario
     * @param idSeguro
     * @return El DTO con todas las relaciones.
     * @throws BusinessLogicException Si no se encuentra alguno de los id
     * pasados por parámetro.
     */
    @GET
    @Path("{idA: \\d+}/{idD: \\d+}/{idAr: \\d+}/{idS: \\d+}/{idV: \\d+}")
    public AlquilerDTO addRelacion(@PathParam("idA") Long idAlquiler, @PathParam("idD") Long idDueno, @PathParam("idAr") Long idArrendatario,
            @PathParam("idS") Long idSeguro, @PathParam("idV") Long idVehiculo) throws BusinessLogicException {
        AlquilerEntity entity = logic.addRelacionAlquiler(idAlquiler, idDueno, idArrendatario, idSeguro,idVehiculo);
        return new AlquilerDTO(entity);

    }

    /**
     * Reemplaza el arrendatario.
     *
     * @param idAlquiler
     * @param idArrendatario
     * @return El DTO con el nuevo arrendatario.
     * @throws BusinessLogicException Si el usuario no existe.
     */
    @PUT
    @Path("{idA: \\d+}/arrendatario/{idAr: \\d+}")
    public AlquilerDTO ReplaceArrendatario(@PathParam("idA") Long idAlquiler, @PathParam("idAr") Long idArrendatario) throws BusinessLogicException {

        AlquilerEntity entity;
        entity = logic.replaceRelacionArrendatario(idAlquiler, idArrendatario);
        return new AlquilerDTO(entity);
    }

    /**
     * Reemplaza el seguro de un alquiler.
     *
     * @param idAlquiler
     * @param idSeguro
     * @return El DTO con un seguro nuevo.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{idA: \\d+}/seguro/{idS: \\d+}")
    public AlquilerDTO ReplaceSeguro(@PathParam("idA") Long idAlquiler, @PathParam("idS") Long idSeguro) throws BusinessLogicException {
        AlquilerEntity entity = logic.replaceRelacionSeguro(idAlquiler, idSeguro);
        return new AlquilerDTO(entity);
    }

    private List<AlquilerDTO> listEntityToDTO(List<AlquilerEntity> alquiler) {
        List<AlquilerDTO> list = new ArrayList<>();
        for (AlquilerEntity entity : alquiler) {
            list.add(new AlquilerDTO(entity));
        }
        return list;
    }
}
