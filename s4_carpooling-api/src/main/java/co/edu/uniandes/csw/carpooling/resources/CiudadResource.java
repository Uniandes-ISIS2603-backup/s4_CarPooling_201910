/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.CiudadDTO;
import co.edu.uniandes.csw.carpooling.ejb.CiudadLogic;
import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Path("ciudades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CiudadResource {

    @Inject
    CiudadLogic logic;

    /**
     * Busca la ciudad con el id asociado recibido en la URL y lo devuelve.
     *
     * @param id Identificador de la ciudad que se esta buscando.
     * @return JSON {@link CiudadDTO} - La ciudad buscada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ciudad.
     */
    @GET
    @Path("id:\\d+")
    public List getCiudad(@PathParam("id") Long id) {
        CiudadEntity ent = logic.get(id);
        if (ent == null) {
            throw new WebApplicationException("El recurso ciudad con id: " + id + " no existe.", 404);
        }
        return (List) new CiudadDTO(ent);
    }

    /**
     * Busca y devuelve todas las ciudades que existen en la aplicacion.
     *
     * @return JSONArray {@link CiudadDTO} - Las ciudades encontrados en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CiudadDTO> getCiudades() {
        List<CiudadDTO> ciudad = new ArrayList<>();
        for (CiudadEntity ent : logic.get()) {
            ciudad.add(new CiudadDTO(ent));
        }
        return ciudad;
    }

    /**
     * Crea una nueva ciudad.
     *
     * @param ciudad {@link CiudadDTO} - La ciudad que se desea guardar.
     * @return JSON {@link CiudadDTO} - La ciudad guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la ciudad.
     */
    @POST
    public CiudadDTO createCiudad(CiudadDTO ciudad) throws BusinessLogicException {
        CiudadEntity ent = ciudad.toEntity();
        ent = logic.create(ent);
        return new CiudadDTO(ent);

    }

}
