/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoInfoDTO;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoInfoLogic;
import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
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
@Path("infoTrayecto")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TrayectoInfoResource {
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoInfoResource.class.getName());
    
    @Inject
    private TrayectoInfoLogic infoLogic;
    
    @POST
    public TrayectoInfoDTO createInfoTrayeecto(TrayectoInfoDTO info)throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "BookResource createBook: input: {0}", info);
        TrayectoInfoDTO nuevoBookDTO = new TrayectoInfoDTO(infoLogic.createEntity(info.toEntity()));
        LOGGER.log(Level.INFO, "BookResource createBook: output: {0}", nuevoBookDTO);
        return nuevoBookDTO;
    }
    
    @GET
    public List<TrayectoInfoDTO> getTrayectosInfo() {
        LOGGER.info("BookResource getBooks: input: void");
        List<TrayectoInfoDTO> listaBooks = listEntity2DetailDTO(infoLogic.getTrayectoInfos());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBooks);
        return listaBooks;
    }
    
    private List<TrayectoInfoDTO> listEntity2DetailDTO(List<TrayectoInfoEntity> entityList) {
        List<TrayectoInfoDTO> list = new ArrayList<>();
        for (TrayectoInfoEntity entity : entityList) {
            list.add(new TrayectoInfoDTO(entity));
        }
        return list;
    }
    
    @GET
    @Path("{infoId: \\d+}")
    public TrayectoInfoDTO getBook(@PathParam("infoId") Long infoId) {
        LOGGER.log(Level.INFO, "BookResource getTrayectoInfo: input: {0}", infoId);
        TrayectoInfoEntity TrayectoInfoEntity = infoLogic.getTrayectoInfo(infoId);
        if (TrayectoInfoEntity == null) {
            throw new WebApplicationException("El recurso /TrayectoInfo/" + infoId + " no existe.", 404);
        }
        TrayectoInfoDTO infoDTO = new TrayectoInfoDTO(TrayectoInfoEntity);
        LOGGER.log(Level.INFO, "BookResource getTrayectoInfo: output: {0}", infoDTO);
        return infoDTO;
    }
    
    
    @PUT
    @Path("{infoId: \\d+}")
    public TrayectoInfoDTO updateBook(@PathParam("infoId") Long infoId, TrayectoInfoDTO info) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource updateBook: input: id: {0} , book: {1}", new Object[]{infoId, info});
        info.setId(infoId);
        if (infoLogic.getTrayectoInfo(infoId) == null) {
            throw new WebApplicationException("El recurso /books/" + infoId + " no existe.", 404);
        }
        TrayectoInfoDTO detailDTO = new TrayectoInfoDTO(infoLogic.updateTrayecoInfo(infoId,  info.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO);
        return detailDTO;
    }
    
     @DELETE
    @Path("{infoId: \\d+}")
    public void deleteBook(@PathParam("infoId") Long infoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource deleteBook: input: {0}", infoId);
        TrayectoInfoEntity entity = infoLogic.getTrayectoInfo(infoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + infoId + " no existe.", 404);
        }
        infoLogic.deleteTrayectoInfo(infoId);
        LOGGER.info("BookResource deleteBook: output: void");
    }
    
    
    
}
