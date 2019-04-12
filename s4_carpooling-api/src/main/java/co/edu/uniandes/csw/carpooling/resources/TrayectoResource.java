/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoDTO;
import co.edu.uniandes.csw.carpooling.dtos.TrayectoDetail;
import co.edu.uniandes.csw.carpooling.dtos.UsuarioDTO;
import co.edu.uniandes.csw.carpooling.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoLogic;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
 * @author estudiante
 */
@Path("/trayectos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrayectoResource {

    private static final Logger LOGGER = Logger.getLogger(TrayectoResource.class.getName());

    @Inject
    private TrayectoLogic trayectoLogic;

    /**
     * Obtiene el trayecto.
     *
     * @param trayectoId el id de trayecto deseado
     * @return el trayecto especificado
     */
    @GET
    @Path("{trayectoId: \\d+}")
    public TrayectoDetail getTrayecto(@PathParam("trayectoId") Long trayectoId) {
        TrayectoEntity trayecto = trayectoLogic.getTrayeto(trayectoId);
        if (trayecto == null) {
            throw new WebApplicationException("El usuario con nombre: " + trayectoId + "no existe", 404);
        }
        return new TrayectoDetail(trayecto);
    }

    /**
     * Obtiene todos los trayectos.
     *
     * @return Una lista con los trayectos.
     */
    @GET
    public List<TrayectoDetail> darTrayectos() {


        List<TrayectoDetail> listaTrayecto = listEntity2DetailDTO(trayectoLogic.getTrayectos());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaTrayecto);
        return listaTrayecto;
    }

    /**
     * Convierte una lista de entidades a DTOs.
     *
     * @param entityList
     * @return Lista con DTOs.
     */
    private List<TrayectoDetail> listEntity2DetailDTO(List<TrayectoEntity> entityList) {
        List<TrayectoDetail> list = new ArrayList<>();
        for (TrayectoEntity entity : entityList) {
            list.add(new TrayectoDetail(entity));
        }
        return list;
    }

    /**
     * Crea un trayecto.
     * @param trayecto
     * @return Trayecto creado en DTO.
     * @throws BusinessLogicException
     */
    @POST
    public TrayectoDTO createUsuario(TrayectoDTO usuario) throws BusinessLogicException{
        TrayectoEntity entity = usuario.toEntity();
        TrayectoEntity entity2 = trayectoLogic.createEntity(entity);
        return new TrayectoDetail(entity2);
    }

    /**
     * Borra el trayecto con el id pasado por parámetro.
     *
     * @param trayectosId
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{trayectosId: \\d+}")
    public void deleteBook(@PathParam("trayectosId") Long trayectosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource deleteBook: input: {0}", trayectosId);
        trayectoLogic.deletetTrayecto(trayectosId);
        LOGGER.info("TrayectoResource deleteTrayecto: output: void");
    }
    
    /**
     * 
     * @param username usuario anterior que se queria aplicar 
     * @param usuario nuevo con la nueva informacion
     * @return el usuario modificado 
     * @throws BusinessLogicException si no se cumplean las reglas de la lògica
     */
    @PUT
    @Path("{trayectoId: \\d+}")
    public TrayectoDetail updateUsuario(@PathParam("trayectoId") Long trayectoId, TrayectoDetail usuario) throws BusinessLogicException
    {
        if (trayectoLogic.getTrayeto(trayectoId) == null) {
            throw new WebApplicationException("El recurso /trayecto/" + trayectoId + " no existe.", 404);
        }
        TrayectoEntity entity = usuario.toEntity();
        entity = trayectoLogic.updateBook(trayectoId, entity);
        return new TrayectoDetail(entity);
    }
}
