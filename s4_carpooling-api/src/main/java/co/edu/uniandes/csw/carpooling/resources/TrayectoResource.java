/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoDTO;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoLogic;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Path("trayectos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrayectoResource {
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoResource.class.getName());
    
    @Inject
    private TrayectoLogic trayectoLogic;
    
    
    @GET
    public List<TrayectoDTO> darTrayecto(){
        
        LOGGER.info("BookResource getBooks: input: void");
        List<TrayectoDTO> listaBooks = listEntity2DetailDTO(trayectoLogic.getTrayectos());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBooks);
        return listaBooks;
    }
    
     private List<TrayectoDTO> listEntity2DetailDTO(List<TrayectoEntity> entityList) {
        List<TrayectoDTO> list = new ArrayList<>();
        for (TrayectoEntity entity : entityList) {
            list.add(new TrayectoDTO(entity));
        }
        return list;
    }
    
    
    @POST
    public TrayectoDTO createTrayecto(TrayectoDTO trayecto){
        return trayecto;
    }
    
    @DELETE
    @Path("{trayectosId: \\d+}")
    public void deleteBook(@PathParam("trayectosId") Long trayectosId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "BookResource deleteBook: input: {0}", trayectosId);
       trayectoLogic.deletetTrayecto(trayectosId);
       LOGGER.info("TrayectoResource deleteTrayecto: output: void");
    }
}
